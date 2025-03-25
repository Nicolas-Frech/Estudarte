import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

async function desmarcarAula() {
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

    const options = {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(cancelamento)
    }

    try {
        const response = await fetch(`${CONFIG.API_URL}/aula`, options);
    
        let mensagemErro = "Erro ao cancelar aula!";
        
        let data = await response.text();
    
        mensagemErro = data || mensagemErro;
        
        if (!response.ok) {
            exibirMensagem("danger", `❌ ${mensagemErro}`);
            return;
        }
    
        exibirMensagem("success", "✅ Aula cancelada com sucesso!");
    
    } catch (error) {
        exibirMensagem("danger", "❌ Erro ao cancelar aula!");
        console.error("Erro ao cancelar aula:", error);
    }
}

const btn = document.getElementById("btn")
btn.addEventListener("click", desmarcarAula)