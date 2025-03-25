import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

async function deletarProfessor() {
    const id = document.getElementById("professorId").value;

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
        const response = await fetch(`${CONFIG.API_URL}/professor/${id}`, options);
    
        let mensagemErro = "Erro ao deletar professor!";
        
        let data = await response.text();
    
        mensagemErro = data || mensagemErro;
        
        if (!response.ok) {
            exibirMensagem("danger", `❌ ${mensagemErro}`);
            return;
        }
    
        exibirMensagem("success", "✅ Professor deletado com sucesso!");
    
    } catch (error) {
        exibirMensagem("danger", "❌ Erro ao deletar professor!");
        console.error("Erro ao deletar professor:", error);
    }
}

const btn = document.getElementById("btn")
btn.addEventListener("click", deletarProfessor)