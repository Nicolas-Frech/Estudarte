# 💻 Sobre o Projeto

### Estud'arte - Projeto de Software para gestão de escolas de música, contemplando os domínios Professor, Aluno, Aula e Sala.

### A Aplicação com Front-end (API + Front-end) está sendo hospedada na url: http://3.145.46.54

### Alguns conceitos que o software aborda:
- API RESTful
- Autenticação e Autorização
- Princípios SOLID
- Desenvolvimento WEB
- Conexão API com Front-end
- Deploy utilizando instância EC2 AWS



 # <br/> ⚙️ Funcionalidades de Professor 👨‍🏫:

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



 # <br/> ⚙️ Funcionalidades de Aluno 🎓:

-  (GET) /aluno - Listar todos os Alunos:

   ![image](https://github.com/user-attachments/assets/d3a24d1b-596b-45e2-a4a0-29c66e09dda5)

-  (GET) /aluno/{id} - Buscar Aluno por Id:

   ![image](https://github.com/user-attachments/assets/7d5092bf-eda4-4fdc-9bee-ca3e3f3711d3)

-  (POST) /aluno - Matricular Aluno:
  
   ![image](https://github.com/user-attachments/assets/c38ddb42-6edc-49e4-9747-f4f652bf7c88)


- (DELETE) /aluno/{id} - Trancar a Matrícula do Aluno por Id:

  ![image](https://github.com/user-attachments/assets/6515607b-8dbd-4bae-863a-50d2bb81afe0)

- (PUT) /aluno - Alterar a Modalidade ou Adicionar um Professor ao Aluno, passando esse JSON:

  ![image](https://github.com/user-attachments/assets/04d48d7d-a450-427a-bb8a-cf3fe7441e32)



 # <br/> ⚙️ Funcionalidades de Aula 🎼:

-  (GET) /aula - Listar todos as Aulas:

   ![image](https://github.com/user-attachments/assets/37efab61-3ced-4664-a103-deb0de1caac0)

-  (GET) /aula/{id} - Buscar Aula por Id:

   ![image](https://github.com/user-attachments/assets/2abd552c-4988-4f14-8d07-d63c3fd1210a)

-  (GET) /aula/aluno/{nome} - Buscar Aulas por nome do Aluno:

   ![image](https://github.com/user-attachments/assets/048d27e9-7273-4faf-bfa0-72e697306d7a)

-  (GET) /aula/professor/{nome} - Buscar Aulas por nome do Professor:

   ![image](https://github.com/user-attachments/assets/11572a3d-e52c-4c03-ad5b-1732659528bd)

-  (POST) /aula - Agendar Aula:

   ![image](https://github.com/user-attachments/assets/d7b919af-0bd2-4c0a-bc06-fa8204896541)


- (PUT) /aula - Remarcar Aula:

  ![image](https://github.com/user-attachments/assets/548ed6bf-d96b-4f3d-8353-6a831f190164)


- (DELETE) /aula - Desmarcar Aula:

  ![image](https://github.com/user-attachments/assets/0e639696-529b-41f1-9000-dad272ff7fef)


 # <br/> ⚙️ Funcionalidades de Sala 🏫:

-  (GET) /sala - Listar todas as Salas:

    ![image](https://github.com/user-attachments/assets/047fa8b8-f333-4a12-a883-c750aba2f4bc)


-  (GET) /sala/{id} - Buscar Sala por Id:

   ![image](https://github.com/user-attachments/assets/c708c95d-3e7c-41d3-bc3b-11c7740bc628)


-  (POST) /sala - Cadastrar Sala:

   ![image](https://github.com/user-attachments/assets/1a5f8eb2-c186-499f-8726-e79962160bf1)


-  (DELETE) /sala/{id} - Deletar Sala por Id:

   ![image](https://github.com/user-attachments/assets/b59381b7-4669-49bb-85b1-219bcdc54d8a)


-  (PUT) /sala - Reservar Sala:

   ![image](https://github.com/user-attachments/assets/c6ced67f-19ad-47a6-a0cc-2e283785c3b6)






 # <br/> 🛠 Tecnologias


### As seguintes tecnologias foram utilizadas no desenvolvimento do projeto:


- Java 22

- HTML/CSS/JAVASCRIPT

- Spring Boot 3

- Spring Security

- JWT

- JPA

- Maven

- Lombok

- MySQL

- Hibernate

- Insomnia

- Docker e Docker Compose

- Nginx como Proxy Reverso

- Servidor Instância EC2 da Amazon
