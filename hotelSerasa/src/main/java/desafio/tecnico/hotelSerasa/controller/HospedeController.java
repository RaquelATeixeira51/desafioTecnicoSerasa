package desafio.tecnico.hotelSerasa.controller;

import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.repository.HospedeRepository;
import desafio.tecnico.hotelSerasa.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("hospede/")
public class HospedeController {
    @Autowired
    private HospedeService hospedeService;

    @PostMapping("criarHospede")
    @ResponseStatus(HttpStatus.CREATED)
    public Hospede criarHospede(@RequestBody Hospede hospede){
        return hospedeService.criarHospede(hospede);
    }

    @GetMapping("hospedesCheckOut")
    @ResponseStatus(HttpStatus.OK)
    public List<Hospede> buscarHospedeCheckOut() {
        return hospedeService.listarHospedesCheckOut();
    }

    @GetMapping("hospedesNoHotel")
    @ResponseStatus(HttpStatus.OK)
    public List<Hospede> buscarHospedeNohotel() {
        return hospedeService.listarHospedesNoHotel();
    }
}
