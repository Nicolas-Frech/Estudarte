const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

document.getElementById("btnBuscar").addEventListener("click", () => {
    const id = document.getElementById("alunoId").value;
    const resultado = document.getElementById("resultado");

    if (!id) {
        resultado.innerHTML = "<p style='color: red;'>⚠️ Por favor, insira um ID válido.</p>";
        return;
    }

    fetch(`/api/aluno/${id}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Aluno não encontrado");
            }
            return response.json();
        })
        .then(aluno => {
            resultado.innerHTML = `
                <p><strong>👤 Nome:</strong> ${aluno.nome}</p>
                <p><strong>🆔 CPF:</strong> ${aluno.cpf}</p>
                <p><strong>📞 Telefone:</strong> ${aluno.telefone}</p>
                <p><strong>📧 E-mail:</strong> ${aluno.email}</p>
                <p><strong>🎵 Modalidade:</strong> ${aluno.modalidade}</p>
            `;
        })
        .catch(error => {
            resultado.innerHTML = "<p style='color: red;'>❌ Aluno não encontrado.</p>";
        });
});