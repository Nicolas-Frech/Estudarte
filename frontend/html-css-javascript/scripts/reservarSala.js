import { exibirMensagem } from "./notificacao.js";

const btnReserva = document.getElementById("btnReserva");

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
    exibirMensagem("danger", "⚠️ Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function validarCampos(salaId, horario, data) {
    if (!salaId) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID!");
        return false;
    }

    if (!horario || !data) {
        exibirMensagem("danger", "⚠️ Por favor, insira todos os campos!");
        return false;
    }
    return true;
}

async function reservarSala() {
    const salaId = document.getElementById("salaId").value;
    const horario = document.getElementById("horario").value;
    const data = document.getElementById("data").value;

    if (!validarCampos(salaId, horario, data)) {
        return;
    }

    const reserva = {
        idSala: salaId,
        horarioReserva: data + "T" + horario + ":00"
    };

    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(reserva)
    };

    try {
        const response = await fetch(`${CONFIG.API_URL}/sala`, options);
    
        let mensagemErro = "Erro ao reservar sala!";
        
        let data = await response.text();
    
        mensagemErro = data || mensagemErro;
        
        if (!response.ok) {
            exibirMensagem("danger", `❌ ${mensagemErro}`);
            return;
        }
    
        exibirMensagem("success", "✅ Sala reservada com sucesso!");
    
    } catch (error) {
        exibirMensagem("danger", "❌ Erro ao reservar sala!");
        console.error("Erro ao reservar sala:", error);
    }
}

btnReserva.addEventListener("click", reservarSala);