import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

async function deletarSala() {
    const id = document.getElementById("salaId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        return;
    }

    const options = {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    }

    try {
        const response = await fetch(`${CONFIG.API_URL}/sala/${id}`, options);
    
        let mensagemErro = "Erro ao deletar sala!";
        
        let data = await response.text();
    
        mensagemErro = data || mensagemErro;
        
        if (!response.ok) {
            exibirMensagem("danger", `❌ ${mensagemErro}`);
            return;
        }
    
        exibirMensagem("success", "✅ Sala deletada com sucesso!");
    
    } catch (error) {
        exibirMensagem("danger", "❌ Erro ao deletar sala!");
        console.error("Erro ao deletar sala:", error);
    }
}

const btn = document.getElementById("btn")
btn.addEventListener("click", deletarSala)