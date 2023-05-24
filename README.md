# DesafioTecnicoSerasa

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
  data_entrada DATE NOT NULL,
  data_saida DATE NOT NULL,
  adicional_veiculo BOOLEAN NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  hospede_id INTEGER REFERENCES hospede (id)
);
```

## Rodando o projeto

Procedimento padrao, `cd hotelSerasa` e `mvn spring-boot:run`
O java utilizado pelo projeto é o 17 como nao foi específicado uma versao nos requisitos

## Endpoints
estou deixando no diretório a coleçao do postman que utilizei pronta, só importar o arquivo
HotelSerasa.postman_collection.json que esta na pasta raiz do projeto

### /hospede/criarHospede

rota para criar um hospede, método POST, recebe como argumento body do objeto hospede
```
{
"nome": "Fulano da Silva 3",
"documento": "1234346638",
"telefone": "119385452012",
"dataNascimento":"17/02/2004"
}
```

### checkIn/realizarCheckIn

rota para realizar checkin, método POST, recebe como argumentos, 3 parametros sendo nome, documento e telefone do hospede (nesta ordem)
e um objeto de checkin no body.
`checkIn/realizarCheckIn?nome=Fulano da Silva 3&documento=1234346638&telefone=119385452012`
```
{
"dataSaida": "2023-05-25T17:00:00",
"adicionalVeiculo": false
}
```

a data de entrada está sendo preenchida automaticamente com a data atual, por lógica nao tem como fazer um checkin para o futuro ou passado, e o valor 
está sendo calculado automáticamente a partir das datas de entrada e saída, é permitido passar uma dataSaida menor que a data atual por motivos de teste,
para saber se a rota `/hospede/hospedesCheckOut` funciona.

### /hospede/hospedesCheckOut

rota para trazer os hospedes que nao estao mais no hotel, nao recebe parametro.

### /hospede/hospedesNoHotel

rota para trazer os hospedes que ainda estao no hotel, nao recebe parametro.