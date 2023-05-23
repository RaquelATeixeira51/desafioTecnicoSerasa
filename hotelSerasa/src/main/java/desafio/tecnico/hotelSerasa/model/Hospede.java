package desafio.tecnico.hotelSerasa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hospede")
public class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String documento;
    private String telefone;
    private String dataNascimento;

    @OneToMany(mappedBy = "hospede")
    private List<CheckIn> checkIns;
}
