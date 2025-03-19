if(!localStorage.getItem("token")) {
    alert("Você precisa estar logado!");
    window.location.href = "login.html";
}

function deletarProfessor() {
    const id = document.getElementById("professorId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        mensagem.textContent = "Por favor, insira um ID válido.";
        return;
    }

    fetch(`/api/professor/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (response.ok) {
            mensagem.textContent = "✅ Professor deletado com sucesso!";
        } else {
            mensagem.textContent = "❌ Erro ao deletar o professor.";
        }
    })
    .catch(error => {
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
}

const btn = document.getElementById("btn")
btn.addEventListener("click", deletarProfessor)