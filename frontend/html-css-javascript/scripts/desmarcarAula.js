if(!localStorage.getItem("token")) {
    alert("Você precisa estar logado!");
    window.location.href = "login.html";
}

function desmarcarAula() {
    const id = document.getElementById("aulaId").value;
    const motivo = document.getElementById("motivo").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        mensagem.textContent = "Por favor, insira um ID válido.";
        return;
    }

    const cancelamento = {
        id: id,
        motivoCancelamento: motivo
    }

    fetch(`/api/aula`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cancelamento)
    })
    .then(response => {
        if (response.ok) {
            mensagem.textContent = "✅ Aula desmarcada com sucesso!";
        } else {
            mensagem.textContent = "❌ Erro ao desmarcar aula!.";
        }
    })
    .catch(error => {
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", desmarcarAula)