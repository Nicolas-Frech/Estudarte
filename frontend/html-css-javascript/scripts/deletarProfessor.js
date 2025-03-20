import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function deletarProfessor() {
    const id = document.getElementById("professorId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        return;
    }

    fetch(`${CONFIG.API_URL}/professor/${id}`, {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        if (response.ok) {
            exibirMensagem("success", "✅ Professor deletado com sucesso!");
        } else {
            exibirMensagem("danger", "❌ Erro ao deletar Professor!");
        }
    })
    .catch(error => {
        exibirMensagem("danger", "⚠️ Erro na requisição: " + error);
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", deletarProfessor)