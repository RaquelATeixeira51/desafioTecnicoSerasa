package desafio.tecnico.hotelSerasa.service;

import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.model.HospedeDto;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {
    @Autowired
    private HospedeRepository hospedeRepository;

    public Hospede criarHospede(Hospede hospede) {
        validarDadosHospede(hospede);
        validarExistenciaHospede(hospede);

        return hospedeRepository.save(hospede);
    }

    public List<HospedeDto> listarHospedesCheckOut() {
        return hospedeRepository.findAllHospedesCheckOut();
    }

    public ResponseEntity<Hospede> atuelizaHospede(Long id, Hospede updatedHospede) {
        Optional<Hospede> optionalHospede = hospedeRepository.findById(id);

        if (optionalHospede.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Hospede hospede = optionalHospede.get();
        hospede.setDocumento(updatedHospede.getDocumento());
        hospede.setTelefone(updatedHospede.getTelefone());
        hospede.setDataNascimento(updatedHospede.getDataNascimento());
        hospede.setNome(updatedHospede.getNome());

        hospedeRepository.save(hospede);

        return ResponseEntity.ok(hospede);
    }

    public List<HospedeDto> listarHospedesNoHotel() {
        return hospedeRepository.findAllHospedesNoHotel();
    }

    private void validarDadosHospede(Hospede hospede) {
        if (!hospede.getTelefone().matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone deve conter apenas números");
        }

        if (!hospede.getDocumento().matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento deve conter apenas números");
        }

        if (!hospede.getDataNascimento().matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato inválido para data de nascimento.");
        }
    }

    private void validarExistenciaHospede(Hospede hospede) {
        Hospede existingHospede = hospedeRepository.findByNomeDocumentoTelefone(
                hospede.getNome(),
                hospede.getDocumento(),
                hospede.getTelefone()
        );
        if (existingHospede != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hóspede já cadastrado com estes dados");
        }
    }
}
