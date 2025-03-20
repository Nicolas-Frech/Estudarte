const btn = document.getElementById("btn");

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

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
    'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(aluno),
  };

  console.log(JSON.stringify(aluno))

  fetch(`${CONFIG.API_URL}/aluno`, options)
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
