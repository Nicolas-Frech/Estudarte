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

function validarCampos(nome, cnpj, telefone, email, modalidade) {
  if (!nome.trim() || !cnpj || !telefone || !email || !modalidade) {
    exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
    return false;
  }
  
  if (!cnpj.match(/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/)) {
    exibirMensagem("danger", "⚠️ CNPJ inválido! Use o formato 00.000.000/0000-00");
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

async function cadastrarProfessor() {
  const nome = document.getElementById("nome").value;
  const cnpj = document.getElementById("cnpj").value;
  const telefone = document.getElementById("telefone").value;
  const email = document.getElementById("email").value;
  const modalidade = document.getElementById("modalidade").value;

  if (!validarCampos(nome, cnpj, telefone, email, modalidade)) {
    return;
  }

  const professor = { nome, cnpj, telefone, email, modalidade };

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(professor),
  };

  console.log(JSON.stringify(professor));
  
  try {
    const response = await fetch(`${CONFIG.API_URL}/professor`, options);

    let mensagemErro = "Erro ao cadastrar professor!";
    
    let data = await response.text();

    mensagemErro = data || mensagemErro;
    
    if (!response.ok) {
        exibirMensagem("danger", `❌ ${mensagemErro}`);
        return;
    }

    exibirMensagem("success", "✅ Professor cadastrado com sucesso!");

  } catch (error) {
    exibirMensagem("danger", "❌ Erro ao cadastrar professor!");
    console.error("Erro ao cadastrar professor:", error);
  }
}

btn.addEventListener("click", cadastrarProfessor);