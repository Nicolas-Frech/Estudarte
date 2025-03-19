const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

function deletarSala() {
    const id = document.getElementById("salaId").value;
    const mensagem = document.getElementById("mensagem");

    if (!id) {
        mensagem.textContent = "Por favor, insira um ID válido.";
        return;
    }

    fetch(`/api/sala/${id}`, {
        method: "DELETE",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
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