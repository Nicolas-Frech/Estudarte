if(!localStorage.getItem("token")) {
    alert("Você precisa estar logado!");
    window.location.href = "login.html";
}

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

document.getElementById("btnBuscar").addEventListener("click", () => {
    const id = document.getElementById("aulaId").value;
    const resultado = document.getElementById("resultado");

    if (!id) {
        resultado.innerHTML = "<p style='color: red;'>⚠️ Por favor, insira um ID válido.</p>";
        return;
    }


    fetch(`/api/aula/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Aluno não encontrado");
            }
            return response.json();
        })
        .then(aula => {
            const dataFormatada = formatarData(aula.data);
            resultado.innerHTML = `
                <p><strong>👤 Professor:</strong> ${aula.professorNome}</p>
                <p><strong>🎓 Aluno:</strong> ${aula.alunoNome}</p>
                <p><strong>🎵 Modalidade:</strong> ${aula.modalidade}</p>
                <p><strong>🏫 Sala:</strong> ${aula.salaNome}</p>
                <p><strong>📆 Data:</strong> ${dataFormatada}</p>
            `;
        })
        .catch(error => {
            resultado.innerHTML = "<p style='color: red;'>❌ Aula não encontrada.</p>";
        });
});