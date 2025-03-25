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

function validarCampos(nome, cpf, telefone, email, modalidade) {
  if (!nome.trim() || !modalidade || !cpf || !telefone || !email) {
    exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
    return false;
  }
  
  if (!cpf.match(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/)) {
    exibirMensagem("danger", "⚠️ CPF inválido! Use o formato 000.000.000-00");
    return false;
  }

  if (!telefone.match(/^\(\d{2}\) \d{5}-\d{4}$/)) {
    exibirMensagem("danger", "⚠️ Telefone inválido! Use o formato (XX) XXXXX-XXXX");
    return false;
  }

  if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
    exibirMensagem("danger", "⚠️ E-mail inválido!");
    return false;
  }
  return true;
}

async function matricularAluno() {
  const nome = document.getElementById("nome").value;
  const cpf = document.getElementById("cpf").value;
  const telefone = document.getElementById("telefone").value;
  const email = document.getElementById("email").value;
  const modalidade = document.getElementById("modalidade").value;
  
  if (!validarCampos(nome, cpf, telefone, email, modalidade)) {
    return;
  }

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

  try {
    const response = await fetch(`${CONFIG.API_URL}/aluno`, options);

    let mensagemErro = "❌ Erro ao cadastrar aluno!";
    
    let data = await response.text();

    mensagemErro = data || mensagemErro;
    
    if (!response.ok) {
        exibirMensagem("danger", `❌ ${mensagemErro}`);
        return;
    }

    exibirMensagem("success", "✅ Aluno cadastrado com sucesso!");

  } catch (error) {
    exibirMensagem("danger", "❌ Erro ao cadastrar aluno!");
    console.error("Erro ao cadastrar aluno:", error);
  }
}


btn.addEventListener('click', matricularAluno);
