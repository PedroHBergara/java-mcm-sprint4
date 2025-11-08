# 🏭 MCM Java API

### Desenvolvido por:
- **Pedro Henrique Bergara**  
- **Henrique Izzi**

---

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
|   **GET**  | `/filiais`      | Lista todas as filiais cadastradas        | `GET http://localhost:8080/filiais`                                                                       |
|   **GET**  | `/filiais/{id}` | Busca uma filial pelo ID                  | `GET http://localhost:8080/filiais/1`                                                                     |
|  **POST**  | `/filiais`      | Cria uma nova filial                      | `POST http://localhost:8080/filiais`<br>Body:<br>`json { "nome": "Filial SP", "endereco": "Rua X, 100" }` |
|   **PUT**  | `/filiais/{id}` | Atualiza uma filial existente             | `PUT http://localhost:8080/filiais/1`<br>Body:<br>`json { "nome": "Filial SP Atualizada" }`               |
| **DELETE** | `/filiais/{id}` | Remove uma filial pelo ID                 | `DELETE http://localhost:8080/filiais/1`                                                                  |
|   **GET**  | `/patios`       | Lista todos os pátios cadastrados         | `GET http://localhost:8080/patios`                                                                        |
|   **GET**  | `/patios/{id}`  | Busca um pátio pelo ID                    | `GET http://localhost:8080/patios/1`                                                                      |
|  **POST**  | `/patios`       | Cria um novo pátio vinculado a uma filial | `POST http://localhost:8080/patios`<br>Body:<br>`json { "nome": "Pátio Central", "filialId": 1 }`         |
|   **PUT**  | `/patios/{id}`  | Atualiza informações de um pátio          | `PUT http://localhost:8080/patios/1`<br>Body:<br>`json { "nome": "Pátio Reformado" }`                     |
| **DELETE** | `/patios/{id}`  | Remove um pátio pelo ID                   | `DELETE http://localhost:8080/patios/1`                                                                   |

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
test 
http://localhost:8080/swagger-ui/index.html
