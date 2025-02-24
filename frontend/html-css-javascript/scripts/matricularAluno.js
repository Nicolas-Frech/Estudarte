const btn = document.getElementById("btn");

function matricularAluno() {
  const nome = document.getElementById("nome").value;
  const cpf = document.getElementById("cpf").value;
  const telefone = document.getElementById("telefone").value;
  const email = document.getElementById("email").value;
  const modalidade = document.getElementById("modalidade").value;
  
  const aluno = {
    nome: nome,
    cpf: cpf,
    telefone: telefone,
    email: email,
    modalidade: modalidade
  };
    
  const options = {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json',
    },
    body: JSON.stringify(aluno),
  };

  console.log(JSON.stringify(aluno))

  fetch('http://app:8080/aluno', options)
  .then(data => {
      if (!data.ok) {
        throw Error(data.statusText);
       }

       return data.json();
      }).then(aluno => {
      console.log(aluno)
      alert("Aluno Matriculado!");

      }).catch(e => {
      console.log(e);
      });
}

btn.addEventListener('click', matricularAluno);
