# desafioTecnicoSerasa

## Configurando banco de dados

Eu estou usando o banco default criado pelo postgres chamado postgres, e dentro dele crio duas tabelas utilizando esse schema

```
CREATE TABLE hospede (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  documento VARCHAR(255)NOT NULL,
  telefone VARCHAR(255) NULL,
  data_nascimento VARCHAR(255) NOT NULL
);

CREATE TABLE checkin (
  id SERIAL PRIMARY KEY,
  data_entrada VARCHAR(255) NOT NULL,
  data_saida VARCHAR(255) NOT NULL,
  adicional_veiculo BOOLEAN NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  hospede_id INTEGER REFERENCES hospede (id)
);
```