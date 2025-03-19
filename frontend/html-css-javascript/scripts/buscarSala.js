const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
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
        resultado.innerHTML = "<p style='color: red;'>⚠️ Por favor, insira um ID válido.</p>";
        return;
    }

    fetch(`/api/sala/${id}`, {
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
            resultado.innerHTML = "<p style='color: red;'>❌ Sala não encontrada.</p>";
        });
});
