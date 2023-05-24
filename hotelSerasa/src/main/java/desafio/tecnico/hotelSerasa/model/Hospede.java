package desafio.tecnico.hotelSerasa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hospede")
public class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nome;
    @Column
    private String documento;
    @Column
    private String telefone;
    @Column
    private String dataNascimento;

    @OneToMany(mappedBy = "hospede")
    private List<CheckIn> checkIns;
}
