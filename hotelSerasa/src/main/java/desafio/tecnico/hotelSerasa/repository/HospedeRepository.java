package desafio.tecnico.hotelSerasa.repository;

import desafio.tecnico.hotelSerasa.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    Hospede findByDocumento(String documento);
    @Query("SELECT h FROM Hospede h WHERE h.nome = :string OR h.documento = :string OR h.telefone = :string")
    Hospede findByNomeDocumentoTelefone(@Param("string") String string);
}
