const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

document.getElementById("btnBuscar").addEventListener("click", () => {
    const id = document.getElementById("professorId").value;
    const resultado = document.getElementById("resultado");

    if (!id) {
        resultado.innerHTML = "<p style='color: red;'>⚠️ Por favor, insira um ID válido.</p>";
        return;
    }

    fetch(`/api/professor/${id}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Professor não encontrado");
            }
            return response.json();
        })
        .then(professor => {
            resultado.innerHTML = `
                <p><strong>👤 Nome:</strong> ${professor.nome}</p>
                <p><strong>🏢 CNPJ:</strong> ${professor.cnpj}</p>
                <p><strong>📞 Telefone:</strong> ${professor.telefone}</p>
                <p><strong>📧 E-mail:</strong> ${professor.email}</p>
                <p><strong>🎵 Modalidade:</strong> ${professor.modalidade}</p>
                <p><strong>💰 Salário:</strong> R$${professor.salario}</p>
                <p><strong>🎓 Alunos:</strong> ${professor.alunos}</p>
            `;
        })
        .catch(error => {
            resultado.innerHTML = "<p style='color: red;'>❌ Professor não encontrado.</p>";
        });
});