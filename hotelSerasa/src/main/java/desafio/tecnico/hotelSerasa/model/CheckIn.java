package desafio.tecnico.hotelSerasa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_entrada")
    private Date dataEntrada = new Date();

    @Column(name = "data_saida")
    private Date dataSaida;

    @Column(name = "adicional_veiculo")
    private boolean adicionalVeiculo;

    @Column
    private double valor;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    @JsonIgnore
    private Hospede hospede;
}
