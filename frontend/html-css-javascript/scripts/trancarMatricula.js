import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function trancarMatricula() {
    const id = document.getElementById("alunoId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        return;
    }

    fetch(`${CONFIG.API_URL}/aluno/${id}`, {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        if (response.ok) {
            exibirMensagem("success", "✅ Matrícula trancada com sucesso!");
        } else {
            exibirMensagem("danger", "❌ Erro ao trancar matrícula.");
        }
    })
    .catch(error => {
        exibirMensagem("danger", "⚠️ Erro na requisição: " + error);
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", trancarMatricula)