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

document.getElementById("btnRemarcar").addEventListener("click", function () {
    const idAula = document.getElementById("idAula").value;
    const novaData = document.getElementById("novaData").value;
    const novoHorario = document.getElementById("novoHorario").value;
    const dataAtualizada = novaData + "T" + novoHorario + ":00"

    if (!validarCampos(idAula, novaData, novoHorario)) {
        return;
    }

    fetch(`${CONFIG.API_URL}/aula`, {
        method: "PUT",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ aulaId: idAula, data: dataAtualizada})
    })
    .then(response => {
        if (response.ok) {
            exibirMensagem("success", "✅ Aula reagendada com sucesso!");
        } else {
            exibirMensagem("danger", "❌ Erro ao reagendar aula!");
        }
    })
    .catch(error => {
        exibirMensagem("danger", "⚠️ Erro na requisição: " + error);
    });
});