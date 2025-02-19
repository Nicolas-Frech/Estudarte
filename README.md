💻 <b>Sobre o Projeto

Estud'arte - Projeto de Software para gestão de escolas de música, contemplando os domínios Professor, Aluno e Aula, em processo de produção.

Usando o Insomnia para testar o projeto, use o endpoint base: http://3.145.46.54


----------------------------------------------------------------------------------
⚙️ <B>Funcionalidades de Professor:

-  (GET) http://3.145.46.54/professor - Listar todos os Professores.
-  (GET) http://3.145.46.54/professor/{id} - Buscar Professor por Id.
-  (POST) http://3.145.46.54/professor - Cadastrar Professor, passando esse JSON:

      ![image](https://github.com/user-attachments/assets/fd90f63e-8d88-48d6-a137-392997f99846)

- (DELETE) http://3.145.46.54/professor/{id} - Deletar Professor por Id.
- (PUT) http://3.145.46.54/professor - Alterar Salário ou Modalidade do Professor, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/d3892053-4083-4c66-af25-032dff79a77a)

----------------------------------------------------------------------------------
⚙️ <B>Funcionalidades de Aluno:

-  (GET) http://3.145.46.54/aluno - Listar todos os Alunos.
-  (GET) http://3.145.46.54/aluno/{id} - Buscar Aluno por Id.
-  (POST) http://3.145.46.54/aluno - Matricular Aluno, passando esse JSON:
  
      ![image](https://github.com/user-attachments/assets/352aa244-bb91-4a9f-bf60-0fe5fceff9e2)

- (DELETE) http://3.145.46.54/aluno/{id} - Trancar a Matrícula do Aluno por Id.
- (PUT) http://3.145.46.54/aluno - Alterar a Modalidade ou Adicionar um Professor ao Aluno, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/3318389d-b037-4a2e-a943-60ac36ab7228)

----------------------------------------------------------------------------------
⚙️ <B>Funcionalidades de Aula:

-  (GET) http://3.145.46.54/aula - Listar todos as Aulas.
-  (GET) http://3.145.46.54/aula/{id} - Buscar Aula por Id.
-  (GET) http://3.145.46.54/aula/aluno/{nome} - Buscar Aulas por nome do Aluno
-  (GET) http://3.145.46.54/aula/professor/{nome} - Buscar Aulas por nome do Professor
-  (POST) http://3.145.46.54/aula - Agendar Aula, passando esse JSON:

   ![image](https://github.com/user-attachments/assets/96ad43c7-0a3c-4070-ae3a-665bf5620d5b)

- (PUT) http://3.145.46.54/aula - Remarcar Aula, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/b41f519e-4ece-46ce-b042-82854413ae3d)

- (DELETE) http://3.145.46.54/aula - Desmarcar Aula, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/9319e650-bad5-430d-b97d-c8e5540f623a)


----------------------------------------------------------------------------------
🛠 Tecnologias


As seguintes tecnologias foram utilizadas no desenvolvimento da API do projeto:


Java 22

Spring Boot 3

JPA

Maven

Lombok

MySQL

Hibernate

Insomnia

Docker e Docker Compose

Nginx como Proxy Reverso

Servidor Instância EC2 da Amazon
