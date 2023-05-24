package desafio.tecnico.hotelSerasa.controller;

import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("hospede/")
public class HospedeController {
    @Autowired
    HospedeRepository hospedeRepository;

    @PostMapping("criarHospede")
    @ResponseStatus(HttpStatus.CREATED)
    public Hospede criarHospede(@RequestBody Hospede hospede){
        Hospede existingHospede = hospedeRepository.findByNomeDocumentoTelefone(hospede.getNome(), hospede.getDocumento(), hospede.getTelefone());
        if(existingHospede != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hospede já cadastrado com estes dados");
        }

        if (!hospede.getTelefone().matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone deve conter apenas números");
        }

        if (!hospede.getDocumento().matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento deve conter apenas números");
        }

        String dataNascimentoPattern = "\\d{2}/\\d{2}/\\d{4}";
        if (!hospede.getDataNascimento().matches(dataNascimentoPattern)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato inválido para data de nascimento. Utilize o formato dd/mm/yyyy");
        }

        return hospedeRepository.save(hospede);
    }
}
