# desafioTecnicoSerasa

## Configurando banco de dados

Eu estou usando o banco default criado pelo postgres chamado postgres, e dentro dele crio duas tabelas utilizando esse schema

```
CREATE TABLE hospede (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
documento INTEGER NOT NULL,
telefone INTEGER NOT NULL,
data_nascimento DATE NOT NULL
);

CREATE TABLE checkin (
id SERIAL PRIMARY KEY,
data_entrada DATE NOT NULL,
data_saida DATE NOT NULL,
adicional_veiculo BOOLEAN NOT NULL,
valor DOUBLE PRECISION NOT NULL,
hospede_id INTEGER REFERENCES hospede (id)
);
```