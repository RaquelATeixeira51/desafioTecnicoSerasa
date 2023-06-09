package desafio.tecnico.hotelSerasa.repository;

import desafio.tecnico.hotelSerasa.model.Hospede;
import desafio.tecnico.hotelSerasa.model.HospedeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    /**
     * seleciona todos os hospedes do objeto tipo HospedeDto para selecionar os novos campos sem fazer inserçoes no banco,seleciona os atributos do objeto,
     * faz uma subquery para somar todos os valores do hospede em questao e separar o últmo valor
     * faz um join entre a entidade hospede e a coleçao checkins para pegar os checkins associados ao hospede
     * aplica a primeira condiçao para selecionar apenas o último checkin feito, e depois uma subbconsulta pra verificar se a dataSaida do
     * último checkin é maior ou menor a data atual
     */
    @Query("SELECT new desafio.tecnico.hotelSerasa.model.HospedeDto(h.id, h.nome, h.documento, h.telefone, h.dataNascimento, c.valor, (SELECT SUM(c2.valor) FROM CheckIn c2 WHERE c2.hospede = h)) " +
            "FROM Hospede h JOIN h.checkIns c WHERE c.id = (SELECT MAX(ci.id) FROM CheckIn ci WHERE ci.hospede = h) AND c.dataSaida < CURRENT_TIMESTAMP")
    List<HospedeDto> findAllHospedesCheckOut();

    @Query("SELECT new desafio.tecnico.hotelSerasa.model.HospedeDto(h.id, h.nome, h.documento, h.telefone, h.dataNascimento, c.valor, (SELECT SUM(c2.valor) FROM CheckIn c2 WHERE c2.hospede = h)) " +
            "FROM Hospede h JOIN h.checkIns c WHERE c.id = (SELECT MAX(ci.id) FROM CheckIn ci WHERE ci.hospede = h) AND c.dataSaida > CURRENT_TIMESTAMP")
    List<HospedeDto> findAllHospedesNoHotel();
}
