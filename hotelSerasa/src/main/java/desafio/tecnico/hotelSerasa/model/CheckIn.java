package desafio.tecnico.hotelSerasa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "checkin")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dataEntrada;
    private Date dataSaida;
    private boolean adicionalVeiculo;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;
}
