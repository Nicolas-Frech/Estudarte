import { exibirMensagem } from "./notificacao.js";

const btn = document.getElementById("btn");

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function validarCampos(professor, aluno, modalidade, data, horario, salaNome) {
  if (!professor.trim()) {
    exibirMensagem("danger", "⚠️ Por favor, digite um Professor!");
    return false;
  }
  
  if (!aluno.trim()) {
    exibirMensagem("danger", "⚠️ Por favor, digite um Aluno!");
    return false;
  }

  if (!modalidade) {
    exibirMensagem("danger", "⚠️ Escolha uma modalidade!");
    return false;
  }

  if (!data) {
    exibirMensagem("danger", "⚠️ O campo Data é obrigatório!");
    return false;
  }

  if (!horario) {
    exibirMensagem("danger", "⚠️ O campo Horário é obrigatório!");
    return false;
  }
  
  if (!salaNome) {
    exibirMensagem("danger", "⚠️ O campo Sala é obrigatório!");
    return false;
  }


  return true;
}

function agendarAula() {
  const professor = document.getElementById("professor").value;
  const aluno = document.getElementById("aluno").value;
  const modalidade = document.getElementById("modalidade").value;
  const data = document.getElementById("data").value;
  const horario = document.getElementById("horario").value;
  const salaNome = document.getElementById("salaNome").value;

  if (!validarCampos(professor, aluno, modalidade, data, horario, salaNome)) {
    return;
  }
  
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

  fetch(`${CONFIG.API_URL}/aula`, options)
  .then(data => {
    if (!data.ok) {
      throw Error(data.statusText);
    }
    return data.json();
  })
  .then(() => {
    exibirMensagem("success", "✅ Aula agendada com sucesso!");
  })
  .catch((e) => {
    exibirMensagem("danger", "❌ Erro ao agendar aula!");
    console.log(e);
  });
}

btn.addEventListener('click', agendarAula);
