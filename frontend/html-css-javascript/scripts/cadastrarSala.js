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

function validarCampos(nome, modalidade) {
    if (!nome.trim() || !modalidade) {
      exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
      return false;
    }
    return true;
  }

async function cadastrarSala() {
    const nome = document.getElementById("nome").value;
    const modalidade = document.getElementById("modalidade").value;

    const sala = {
        nome: nome,
        modalidade: modalidade
    };

    if (!validarCampos(nome, modalidade)) {
        return;
    }

    const options = {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(sala)
    };

    try {
      const response = await fetch(`${CONFIG.API_URL}/sala`, options);
  
      let mensagemErro = "❌ Erro ao cadastrar sala!";
      
      let data = await response.text();
  
      mensagemErro = data || mensagemErro;
      
      if (!response.ok) {
          exibirMensagem("danger", `❌ ${mensagemErro}`);
          return;
      }
  
      exibirMensagem("success", "✅ Sala cadastrada com sucesso!");
  
    } catch (error) {
      exibirMensagem("danger", "❌ Erro ao cadastrar sala!");
      console.error("Erro ao cadastrar sala:", error);
    }
}

btn.addEventListener("click", cadastrarSala);