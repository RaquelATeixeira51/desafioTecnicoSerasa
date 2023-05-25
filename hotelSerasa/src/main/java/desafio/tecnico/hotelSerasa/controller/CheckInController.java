package desafio.tecnico.hotelSerasa.controller;

import desafio.tecnico.hotelSerasa.model.CheckIn;
import desafio.tecnico.hotelSerasa.repository.CheckInRepository;
import desafio.tecnico.hotelSerasa.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("checkIn/")
public class CheckInController {
    @Autowired
    CheckInService checkInService;

    @PostMapping("realizarCheckIn")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckIn realizarCheckIn(@RequestParam String nome, @RequestParam String documento, @RequestParam String telefone,@RequestBody CheckIn checkIn){
       return checkInService.realizarCheckIn(nome, documento, telefone, checkIn);
    }

    @DeleteMapping("/deleteCheckIn/{id}")
    public ResponseEntity<Optional<CheckIn>> delete(@PathVariable Long id) {
       return checkInService.deleteCheckIn(id);
    }
}
