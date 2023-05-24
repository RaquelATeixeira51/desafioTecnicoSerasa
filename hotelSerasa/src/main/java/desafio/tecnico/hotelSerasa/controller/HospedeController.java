package desafio.tecnico.hotelSerasa.controller;

import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("hospede/")
public class HospedeController {
    @Autowired
    HospedeRepository hospedeRepository;

    @PostMapping("criarHospede")
    @ResponseStatus(HttpStatus.CREATED)
    public Hospede criarHospede(@RequestBody Hospede hospede){
        Hospede existingHospede = hospedeRepository.findByDocumento(hospede.getDocumento());
        if(existingHospede != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hospede já cadastrado");
        }
        return hospedeRepository.save(hospede);
    }
}
