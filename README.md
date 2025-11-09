# 🏭 MCM Java API

### Desenvolvido por:
- **Pedro Henrique Bergara**  
- **Henrique Izzi**

---

test de release

## 🧩 Descrição do Projeto

O sistema **MCM (Mottu Courtyard Manager)** foi desenvolvido para resolver um problema identificado no discurso da **Mottu**: a **falta de organização e gerenciamento dos galpões**.  
Para isso, elaboramos uma solução dividida em **duas APIs** que trabalham de forma integrada:

- **API Java (este repositório):** responsável pelas ações relacionadas a **Filiais** e **Pátios**.  
- **API C# (.NET):** responsável pelas ações relacionadas a **Motos**, **Motoristas** e **Funcionários**.

Essa separação permite **maior modularidade**, **escalabilidade** e **organização** do sistema, facilitando o gerenciamento de diferentes entidades e suas relações.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **Hibernate**  
- **Maven**  
- **Swagger UI**  
- **Banco de Dados Relacional (Oracle ou SQL Server)**  

---
|   Método   | Endpoint        | Descrição                                 | Exemplo de Requisição                                                                                     |
| :--------: | :-------------- | :---------------------------------------- | :-------------------------------------------------------------------------------------------------------- |
|   **GET**  | `/api/filiais`      | Lista todas as filiais cadastradas        | `GET http://localhost:8080/api/filiais`                                                                       |
|   **GET**  | `/api/filiais/{id}` | Busca uma filial pelo ID                  | `GET http://localhost:8080/api/filiais/1`                                                                     |
|  **POST**  | `/api/filiais`      | Cria uma nova filial                      | `POST http://localhost:8080/api/filiais`<br>Body:<br>`json { "nome": "Filial SP", "endereco": "Rua X, 100" }` |
|   **PUT**  | `/api/filiais/{id}` | Atualiza uma filial existente             | `PUT http://localhost:8080/api/filiais/1`<br>Body:<br>`json { "nome": "Filial SP Atualizada" }`               |
| **DELETE** | `/api/filiais/{id}` | Remove uma filial pelo ID                 | `DELETE http://localhost:8080/api/filiais/1`                                                                  |
|   **GET**  | `/api/patios`       | Lista todos os pátios cadastrados         | `GET http://localhost:8080/api/patios`                                                                        |
|   **GET**  | `/api/patios/{id}`  | Busca um pátio pelo ID                    | `GET http://localhost:8080/api/patios/1`                                                                      |
|  **POST**  | `/api/patios`       | Cria um novo pátio vinculado a uma filial | `POST http://localhost:8080/api/patios`<br>Body:<br>`json { "nome": "Pátio Central", "filialId": 1 }`         |
|   **PUT**  | `/api/patios/{id}`  | Atualiza informações de um pátio          | `PUT http://localhost:8080/api/patios/1`<br>Body:<br>`json { "nome": "Pátio Reformado" }`                     |
| **DELETE** | `/api/patios/{id}`  | Remove um pátio pelo ID                   | `DELETE http://localhost:8080/api/patios/1`                                                                   |

---
mcm-java/
│
├── src/main/java/com/mcm/
│   ├── controller/        # Controladores REST
│   ├── model/             # Entidades JPA
│   ├── repository/        # Interfaces de acesso ao banco
│   ├── service/           # Regras de negócio
│   └── McmApplication.java # Classe principal
│
├── src/main/resources/
│   ├── application.properties # Configurações do projeto
│
└── pom.xml                # Dependências do Maven

---
test para bacno de dados
http://localhost:8080/swagger-ui/index.html
