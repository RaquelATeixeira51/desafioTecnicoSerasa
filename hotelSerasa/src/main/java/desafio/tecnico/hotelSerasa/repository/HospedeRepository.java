package desafio.tecnico.hotelSerasa.repository;

import desafio.tecnico.hotelSerasa.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    @Query("SELECT h FROM Hospede h WHERE h.nome = :nome OR h.documento = :documento OR h.telefone = :telefone")
    Hospede findByNomeDocumentoTelefone(
            @Param("nome") String nome,
            @Param("documento") String documento,
            @Param("telefone") String telefone
    );

    @Query("SELECT h FROM Hospede h WHERE h.nome = :nome AND h.documento = :documento AND h.telefone = :telefone")
    Hospede findByNomeDocumentoTelefoneCheckIn(
            @Param("nome") String nome,
            @Param("documento") String documento,
            @Param("telefone") String telefone
    );
}
