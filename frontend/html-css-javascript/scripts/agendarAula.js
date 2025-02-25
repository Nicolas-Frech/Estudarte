const btn = document.getElementById("btn");

function agendarAula() {
  const professor = document.getElementById("professor").value;
  const aluno = document.getElementById("aluno").value;
  const modalidade = document.getElementById("modalidade").value;
  const data = document.getElementById("data").value;
  const horario = document.getElementById("horario").value;
  
  const aula = {
    professorNome: professor,
    alunoNome: aluno,
    modalidade: modalidade,
    data: data + "T" + horario + ":00"
  };
    
  const options = {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json',
    },
    body: JSON.stringify(aula),
  };

  console.log(JSON.stringify(aula))

  fetch('/api/aula', options)
  .then(data => {
      if (!data.ok) {
        throw Error(data.status);
       }

       return data.json();
      }).then(aula => {
      console.log(aula)
      alert("Aula agendada!");

      }).catch(e => {
      console.log(e);
      });
}

btn.addEventListener('click', agendarAula);
