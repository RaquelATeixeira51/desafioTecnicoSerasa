package desafio.tecnico.hotelSerasa.service;

import desafio.tecnico.hotelSerasa.model.CheckIn;
import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.CheckInRepository;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CheckInService {
    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    public CheckIn realizarCheckIn(String nome, String documento, String telefone, CheckIn checkIn) {
        Hospede hospede = buscarHospede(nome, documento, telefone);
        checkIn.setHospede(hospede);

        try {
            return checkInRepository.save(checkIn);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao realizar o check-in", e);
        }
    }

    private Hospede buscarHospede(String nome, String documento, String telefone) {
        Hospede hospede = hospedeRepository.findByNomeDocumentoTelefoneCheckIn(nome, documento, telefone);
        if (hospede == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hóspede não encontrado");
        }
        return hospede;
    }

}
