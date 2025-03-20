import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

document.getElementById("btnBuscar").addEventListener("click", () => {
    const id = document.getElementById("salaId").value;
    const resultado = document.getElementById("resultado");



    function formatarData(dataISO) {
        if (!dataISO) return "Data inválida";
    
        const data = new Date(dataISO);
        return new Intl.DateTimeFormat("pt-BR", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: false
        }).format(data);
    }

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        resultado.innerHTML = ""
        return;
    }

    fetch(`${CONFIG.API_URL}/sala/${id}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Sala não encontrada");
            }
            return response.json();
        })
        .then(sala => {
            const horariosFormatados = sala.horariosReserva
                .map(formatarData)
                .join("<br>");

            resultado.innerHTML = `
                <p><strong>🏫 Nome:</strong> ${sala.nome}</p>
                <p><strong>🎵 Modalidade:</strong> ${sala.modalidade}</p>
                <p><strong>📆 Horários Reserva:</strong><br> ${horariosFormatados}</p>
            `;
        })
        .catch(error => {
            exibirMensagem("danger", "❌ Aluno não encontrado!");
            resultado.innerHTML = ""
            return;
        });
});
