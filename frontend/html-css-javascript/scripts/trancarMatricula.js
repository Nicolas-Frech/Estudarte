function trancarMatricula() {
    const id = document.getElementById("alunoId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        mensagem.textContent = "Por favor, insira um ID válido.";
        return;
    }

    fetch(`/api/aluno/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (response.ok) {
            mensagem.textContent = "✅ Matrícula trancada com sucesso!";
        } else {
            mensagem.textContent = "❌ Erro ao trancar matrícula.";
        }
    })
    .catch(error => {
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", trancarMatricula)