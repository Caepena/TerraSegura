
# Terra Segura

**Terra Segura** é um projeto voltado para a prevenção e resposta a deslizamentos de terra em regiões de encosta, utilizando IoT.

## 👥 Integrantes

* Caetano Matos Penafiel

* Victor Edigio Lira

* Kauã Firmino Zipf

## 🚀 Como executar o projeto

### Pré-requisitos

- Java 17+ (ou versão compatível configurada no `pom.xml`)
- Maven 3.8.x+
- Git (opcional)

### Clone do repositório

```bash
git clone https://github.com/seu-usuario/terra-segura.git
cd terra-segura
```

### Build do projeto

```bash
./mvnw clean install
```

ou, se estiver no Windows:

```bash
mvnw.cmd clean install
```

### Execução da aplicação

Caso a aplicação possua um método `main`, ou um framework como Spring Boot:

```bash
./mvnw spring-boot:run
```

## 🛰️ API - Endpoints disponíveis

A aplicação expõe os seguintes endpoints:

### 📍 Região Monitorada `/regiao`

| Método | Endpoint         | Descrição                            |
|--------|------------------|--------------------------------------|
| GET    | /regiao          | Listar todas as regiões monitoradas (com filtros opcionais) |
| GET    | /regiao/{id}     | Obter detalhes de uma região monitorada |
| POST   | /regiao          | Criar uma nova região monitorada     |
| PUT    | /regiao/{id}     | Atualizar uma região monitorada existente |
| DELETE | /regiao/{id}     | Deletar uma região monitorada        |

### 🖲️ Sensor Leitura `/sensor`

| Método | Endpoint         | Descrição                            |
|--------|------------------|--------------------------------------|
| GET    | /sensor          | Listar todas as leituras de sensores (com filtros opcionais) |
| GET    | /sensor/{id}     | Obter detalhes de uma leitura de sensor |
| POST   | /sensor          | Criar uma nova leitura de sensor     |
| PUT    | /sensor/{id}     | Atualizar uma leitura de sensor existente |
| DELETE | /sensor/{id}     | Deletar uma leitura de sensor        |

---

## 🔍 Exemplos de testes via Postman ou curl

### Criar uma nova Região Monitorada

```
{
    "nome": "Morro Azul",
    "descricao": "Região com histórico de deslizamentos",
    "latitude": -23.550520,
    "longitude": -46.633308,
    "nivelRisco": "ALTO"
}
```

### Criar uma nova Leitura de Sensor

```
{
    "regiaoMonitoradaId": XXX,
    "tipoSensor": "Umidade",
    "valor": 85.5,
    "dataHoraLeitura": "2025-06-08T15:30:00"
}
```

## 🛠️ Observações

### A documentação interativa da API (Swagger UI) deve estar disponível em:
```
http://localhost:8080/swagger-ui/index.html
```
