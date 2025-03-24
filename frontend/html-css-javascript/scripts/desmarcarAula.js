import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function desmarcarAula() {
    const id = document.getElementById("aulaId").value;
    const motivo = document.getElementById("motivo").value;

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        return;
    }

    const cancelamento = {
        id: id,
        motivoCancelamento: motivo
    }

    fetch(`${CONFIG.API_URL}/aula`, {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(cancelamento)
    })
    .then(response => {
        if (response.ok) {
            exibirMensagem("success", "✅ Aula cancelada com sucesso!");
        } else {
            exibirMensagem("danger", "❌ Erro ao cancelar Aula!");
        }
    })
    .catch(error => {
        exibirMensagem("danger", "⚠️ Erro na requisição: " + error);
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", desmarcarAula)