package desafio.tecnico.hotelSerasa.repository;

import desafio.tecnico.hotelSerasa.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
}
