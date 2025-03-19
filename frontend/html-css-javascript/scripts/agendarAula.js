const btn = document.getElementById("btn");

const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

function agendarAula() {
  const professor = document.getElementById("professor").value;
  const aluno = document.getElementById("aluno").value;
  const modalidade = document.getElementById("modalidade").value;
  const data = document.getElementById("data").value;
  const horario = document.getElementById("horario").value;
  const salaNome = document.getElementById("salaNome").value;
  
  const aula = {
    professorNome: professor,
    alunoNome: aluno,
    modalidade: modalidade,
    data: data + "T" + horario + ":00",
    salaNome: salaNome
  };


  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(aula),
  };

  console.log(JSON.stringify(aula))

  fetch('/api/aula', options)
  .then(data => {
      if (!data.ok) {
        throw new Error(`Erro ${data.status}: Não foi possível agendar a aula.`);
      }
       return data.json();
      }).then(aula => {
      console.log(aula)
      alert("Aula agendada!");

      }).catch(e => {
        console.error("Erro ao agendar aula:", e);
        alert("Erro ao agendar aula. Verifique os dados e tente novamente.");
      });
}

btn.addEventListener('click', agendarAula);
