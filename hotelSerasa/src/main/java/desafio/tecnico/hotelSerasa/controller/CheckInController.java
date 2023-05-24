package desafio.tecnico.hotelSerasa.controller;

import desafio.tecnico.hotelSerasa.model.CheckIn;
import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.CheckInRepository;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("checkIn/")
public class CheckInController {
    @Autowired
    CheckInRepository checkInRepository;
    @Autowired
    HospedeRepository hospedeRepository;

    @PostMapping("realizarCheckIn")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckIn realizarCheckIn(@RequestParam String nome, @RequestParam String documento, @RequestParam String telefone,@RequestBody CheckIn checkIn){
        Hospede checkHospede = hospedeRepository.findByNomeDocumentoTelefoneCheckIn(nome, documento, telefone);
        if (checkHospede == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospede nao encontrado");
        }
        checkIn.setHospede(checkHospede);

        try {
            return checkInRepository.save(checkIn);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao realizar o check-in", e);
        }
    }
}
