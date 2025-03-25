import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "⚠️ Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function validarCampos(idAula, novaData, novoHorario) {
    if (!idAula || !novaData || !novoHorario) {
      exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
      return false;
    }
    return true;
}

document.getElementById("btnRemarcar").addEventListener("click", async function () {
    const idAula = document.getElementById("idAula").value;
    const novaData = document.getElementById("novaData").value;
    const novoHorario = document.getElementById("novoHorario").value;
    const dataAtualizada = novaData + "T" + novoHorario + ":00"

    if (!validarCampos(idAula, novaData, novoHorario)) {
        return;
    }

    const options = {
        method: "PUT",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ aulaId: idAula, data: dataAtualizada})
    }

    try {
        const response = await fetch(`${CONFIG.API_URL}/aula`, options);
    
        let mensagemErro = "Erro ao reagendar aula!";
        
        let data = await response.text();
    
        mensagemErro = data || mensagemErro;
        
        if (!response.ok) {
            exibirMensagem("danger", `❌ ${mensagemErro}`);
            return;
        }
    
        exibirMensagem("success", "✅ Aula reagendada com sucesso!");
    
    } catch (error) {
        exibirMensagem("danger", "❌ Erro ao reagendar aula!");
        console.error("Erro ao reagendar aula:", error);
    }
});


