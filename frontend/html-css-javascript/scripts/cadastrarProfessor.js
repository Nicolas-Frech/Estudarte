const btn = document.getElementById("btn");

function cadastrarProfessor() {
  const nome = document.getElementById("nome").value;
  const cnpj = document.getElementById("cnpj").value;
  const telefone = document.getElementById("telefone").value;
  const email = document.getElementById("email").value;
  const modalidade = document.getElementById("modalidade").value;
  
  const professor = {
    nome: nome,
    cnpj: cnpj,
    telefone: telefone,
    email: email,
    modalidade: modalidade
  };
    
  const options = {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json',
    },
    body: JSON.stringify(professor),
  };

  console.log(JSON.stringify(professor))

  fetch('/api/professor', options)
  .then(data => {
      if (!data.ok) {
        throw Error(data.statusText);
       }

       return data.json();
      }).then(professor => {
      console.log(professor)
      alert("Professor Cadastrado!");

      }).catch(e => {
      console.log(e);
      });
}

btn.addEventListener('click', cadastrarProfessor);
