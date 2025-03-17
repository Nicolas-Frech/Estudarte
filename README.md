# 💻 Sobre o Projeto

### Estud'arte - Projeto de Software para gestão de escolas de música, contemplando os domínios Professor, Aluno, Aula e Sala.

### A Aplicação com Front-end está sendo hospedada na url: http://3.145.46.54



 # <br/> ⚙️ Funcionalidades de Professor:

-  (GET) /professor - Listar todos os Professores:

    ![image](https://github.com/user-attachments/assets/946141d8-2ec0-4a03-bae8-385258e363e4)

-  (GET) /professor/{id} - Buscar Professor por Id:

   ![image](https://github.com/user-attachments/assets/dd9b3e18-b830-48e4-a804-6ab41d4fdf5a)


-  (POST) /professor - Cadastrar Professor:

   ![image](https://github.com/user-attachments/assets/a2dccbbc-4428-49f5-bc76-9e9c0b497ba1)


-  (DELETE) /professor/{id} - Deletar Professor por Id:

   ![image](https://github.com/user-attachments/assets/f49d9077-65c6-4371-9c23-f7fe004702f8)

-  (PUT) /professor - Alterar Salário ou Modalidade do Professor:

   ![image](https://github.com/user-attachments/assets/cb9f696b-7c95-440f-8661-7cb2f86a8d00)



 # <br/> ⚙️ Funcionalidades de Aluno:

-  (GET) /aluno - Listar todos os Alunos:
-  (GET) /aluno/{id} - Buscar Aluno por Id:
-  (POST) /aluno - Matricular Aluno:
  
      ![image](https://github.com/user-attachments/assets/352aa244-bb91-4a9f-bf60-0fe5fceff9e2)

- (DELETE) /aluno/{id} - Trancar a Matrícula do Aluno por Id:
- (PUT) /aluno - Alterar a Modalidade ou Adicionar um Professor ao Aluno, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/3318389d-b037-4a2e-a943-60ac36ab7228)


 # <br/> ⚙️ Funcionalidades de Aula:

-  (GET) /aula - Listar todos as Aulas.
-  (GET) /aula/{id} - Buscar Aula por Id.
-  (GET) /aula/aluno/{nome} - Buscar Aulas por nome do Aluno
-  (GET) /aula/professor/{nome} - Buscar Aulas por nome do Professor
-  (POST) /aula - Agendar Aula, passando esse JSON:

   ![image](https://github.com/user-attachments/assets/96ad43c7-0a3c-4070-ae3a-665bf5620d5b)

- (PUT) /aula - Remarcar Aula, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/b41f519e-4ece-46ce-b042-82854413ae3d)

- (DELETE) /aula - Desmarcar Aula, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/9319e650-bad5-430d-b97d-c8e5540f623a)


 # <br/> 🛠 Tecnologias


### As seguintes tecnologias foram utilizadas no desenvolvimento da API do projeto:


- Java 22

- HTML/CSS/JAVASCRIPT

- Spring Boot 3

- JPA

- Maven

- Lombok

- MySQL

- Hibernate

- Insomnia

- Docker e Docker Compose

- Nginx como Proxy Reverso

- Servidor Instância EC2 da Amazon
