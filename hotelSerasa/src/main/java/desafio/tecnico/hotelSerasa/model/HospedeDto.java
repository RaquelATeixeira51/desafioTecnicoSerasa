package desafio.tecnico.hotelSerasa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospedeDto {
    private long id;
    private String nome;
    private String documento;
    private String telefone;
    private String dataNascimento;
    private double ultimoValor;
    private double valorTotal;
}
