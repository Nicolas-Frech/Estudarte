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

function cadastrarSala() {
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

    fetch(`${CONFIG.API_URL}/sala`, options)
    .then((data) => {
        if (!data.ok) {
          throw Error(data.statusText);
        }
        return data.json();
      })
      .then(() => {
        exibirMensagem("success", "✅ Sala cadastrada com sucesso!");
      })
      .catch((e) => {
        exibirMensagem("danger", "❌ Erro ao cadastrar sala!");
        console.log(e);
      });
}

btn.addEventListener("click", cadastrarSala);