function deletarSala() {
    const id = document.getElementById("salaId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        mensagem.textContent = "Por favor, insira um ID válido.";
        return;
    }

    fetch(`/api/sala/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (response.ok) {
            mensagem.textContent = "✅ Sala deletada com sucesso!";
        } else {
            mensagem.textContent = "❌ Erro ao deletar a sala.";
        }
    })
    .catch(error => {
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", deletarSala)