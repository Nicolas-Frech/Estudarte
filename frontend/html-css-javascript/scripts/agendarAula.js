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
  if (!professor.trim() || !aluno.trim() || !modalidade || !data || !horario || !salaNome) {
    exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
    return false;
  }
  return true;
}

async function agendarAula() {
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

  try {
    const response = await fetch(`${CONFIG.API_URL}/aula`, options);

    let mensagemErro = "Erro ao agendar aula!";
    
    let data = await response.text();

    mensagemErro = data || mensagemErro;
    
    if (!response.ok) {
        exibirMensagem("danger", `❌ ${mensagemErro}`);
        return;
    }

    exibirMensagem("success", "✅ Aula agendada com sucesso!");

  } catch (error) {
    exibirMensagem("danger", "❌ Erro ao agendar aula!");
    console.error("Erro ao agendar aula:", error);
  }
}

btn.addEventListener('click', agendarAula);
