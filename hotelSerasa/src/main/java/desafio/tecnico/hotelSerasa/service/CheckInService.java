package desafio.tecnico.hotelSerasa.service;

import desafio.tecnico.hotelSerasa.model.CheckIn;
import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.CheckInRepository;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class CheckInService {
    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    public CheckIn realizarCheckIn(String nome, String documento, String telefone, CheckIn checkIn) {
        setValorCheckIn(checkIn);
        Hospede hospede = buscarHospede(nome, documento, telefone);
        checkIn.setHospede(hospede);

        try {
            return checkInRepository.save(checkIn);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao realizar o check-in", e);
        }
    }

    public ResponseEntity<Optional<CheckIn>> deleteCheckIn(Long id) {
        Optional<CheckIn> existingCheckIn = checkInRepository.findById(id);

        if(existingCheckIn == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check in nao existe");
        }

        checkInRepository.deleteById(id);

        return ResponseEntity.ok(existingCheckIn);
    }

    public void setValorCheckIn(CheckIn checkIn) {
        Date dataEntrada = checkIn.getDataEntrada();
        Date dataSaida = checkIn.getDataSaida();

        double valorDiaria = calcularValorDiarias(dataEntrada, dataSaida);
        double valorAcrescimoGaragem = calcularValorAcrescimoGaragem(dataEntrada, dataSaida, checkIn.isAdicionalVeiculo());
        double valorDiariaExtra = calcularValorDiariaExtra(dataSaida);

        double valorTotal = valorDiaria + valorAcrescimoGaragem + valorDiariaExtra;
        checkIn.setValor(valorTotal);
    }

    private double calcularValorDiarias(Date dataEntrada, Date dataSaida) {
        double valorDiaria = 0;
        int quantidadeDias = getQuantidadeDias(dataEntrada, dataSaida);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEntrada);

        for (int i = 0; i < quantidadeDias; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                valorDiaria += 150.00;
            } else {
                valorDiaria += 120.00;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return valorDiaria;
    }

    private double calcularValorAcrescimoGaragem(Date dataEntrada, Date dataSaida, boolean adicionalVeiculo) {
        double valorAcrescimoGaragem = 0;
        if (adicionalVeiculo) {
            int quantidadeDias = getQuantidadeDias(dataEntrada, dataSaida);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataEntrada);

            for (int i = 0; i <= quantidadeDias; i++) {
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    valorAcrescimoGaragem += 20.00;
                } else {
                    valorAcrescimoGaragem += 15.00;
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        return valorAcrescimoGaragem;
    }

    private double calcularValorDiariaExtra(Date dataSaida) {
        double valorDiariaExtra = 0;
        Calendar horaSaida = Calendar.getInstance();
        horaSaida.setTime(dataSaida);
        Calendar horaLimite = Calendar.getInstance();
        horaLimite.set(Calendar.HOUR_OF_DAY, 16);
        horaLimite.set(Calendar.MINUTE, 30);

        if (horaSaida.after(horaLimite)) {
            int dayOfWeek = horaSaida.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                valorDiariaExtra = 150.00;
            } else {
                valorDiariaExtra = 120.00;
            }
        }

        return valorDiariaExtra;
    }


    private int getQuantidadeDias(Date dataInicio, Date dataFim) {
        long diff = dataFim.getTime() - dataInicio.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    private Hospede buscarHospede(String nome, String documento, String telefone) {
        Hospede hospede = hospedeRepository.findByNomeDocumentoTelefoneCheckIn(nome, documento, telefone);
        if (hospede == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hóspede não encontrado");
        }
        return hospede;
    }

}
