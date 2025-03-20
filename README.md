# 📚 Sistema de Gestão de Escola de Música

## 📌 Sobre o Projeto

Este projeto é um sistema web para o gerenciamento de Escolas de Música, permitindo o cadastro e a administração de Professores, Alunos, Salas e suas respectivas Aulas. Também é necessário ter um cadastro e login para acessar a aplicação como usuário, abordando conceitos de autenticação e autorização.

A aplicação é composta por:

- **Frontend:** HTML/CSS/JAVASCRIPT e Bootstrap 5
- **Backend:** Java com Spring Boot 3
- **Banco de Dados:** MySQL
- **Containerização:** Docker e Docker Compose

## 🚀 Tecnologias Utilizadas

Este software foi desenvolvido com as seguintes tecnologias:

- **Frontend:**

  - HTML/CSS/JAVASCRIPT
  - Bootstrap 5

- **Backend:**

  - Java
  - Spring Boot 3
  - Maven
  - JPA
  - Hibernate

- **Banco de Dados:**

  - MySQL

- **Outras Ferramentas:**

  - Docker e Docker Compose
  - Git e GitHub
  - Imsomnia
  - AWS Instância EC2
  - Spring Security
  - Lombok
  - JWT
 
## 🌐 Acesso à Aplicação

A aplicação está hospedada e pode ser acessada através do seguinte link:
🔗 URL: http://3.145.46.54

## 🔧 Funcionalidades

### 👨‍🏫 Funcionalidades do Professor

- **Listagem de Professores** 📋

  - Exibe uma lista com todos os professores cadastrados.
  - Paginação para melhor visualização.

- **Buscar Professor** 🔎

  - Permite pesquisar professores pelo ID.
  - Retorna detalhes como e-mail, telefone e modalidade.

- **Criar Professor** ➕

  - Cadastro de novos professores com informações detalhadas.

- **Editar Professor** ✏️

  - Atualização dos dados cadastrais de um professor existente.

- **Excluir Professor** 🗑️

  - Remoção de um professor do sistema.

### 🎓 Funcionalidades do Aluno

- **Listagem de Alunos** 📋

  - Exibe uma lista com todos os alunos cadastrados.
  - Paginação para melhor organização.

- **Buscar Aluno** 🔎

  - Permite pesquisar alunos pelo ID.
  - Retorna detalhes como e-mail, telefone e modalidade.

- **Criar Aluno** ➕

  - Cadastro de novos alunos no sistema.

- **Editar Aluno** ✏️

  - Atualização dos dados cadastrais de um aluno existente.

- **Excluir Aluno** 🗑️

  - Remoção de um aluno do sistema.

### 🏫 Funcionalidades de Sala

- **Listagem de Salas** 📋

  - Exibe uma lista com todas as salas cadastradas.
  - Paginação para facilitar a navegação.

- **Buscar Sala** 🔎

  - Permite pesquisar salas pelo ID.
  - Retorna detalhes como nome, modalidade e horários reservados.

- **Criar Sala** ➕

  - Cadastro de novas salas no sistema.

- **Editar Sala** ✏️

  - Atualização dos dados de uma sala existente.

- **Excluir Sala** 🗑️

  - Remoção de uma sala do sistema.

- **Reservar Sala** 📅

  - Permite reservar uma sala para estudo.

### 📚 Funcionalidades de Aula

- **Listagem de Aulas** 📋

  - Exibe todas as aulas cadastradas no sistema.
  - Possibilidade de busca por nome de professor ou aluno.

- **Buscar Aula** 🔎

  - Permite pesquisar aulas pelo ID.

* **Agendar Aula** 📅

  - Permite o agendamento de novas aulas, associando professor, alunos, sala, data e horário.
  - Assim que uma aula é agendada, o professor e o aluno recebem um e-mail avisando-os.
 
* **Reagendar Aula** 📅

  - Permite o reagendamento de aulas, associando professor, alunos, sala, modalidade data e horário.
  - Assim que uma aula é reagendada, o professor e o aluno recebem um e-mail avisando-os.

* **Cancelar Aula** ❌

  - Possibilita o cancelamento de uma aula do sistema, passando o motivo de cancelamento.
  - Assim que uma aula é cancelada, o professor e o aluno recebem um e-mail avisando-os.

## 🤝 Contribuição

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Faça um push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

💡 **Dica:** Para mais detalhes sobre a API, consulte a documentação no repositório.

