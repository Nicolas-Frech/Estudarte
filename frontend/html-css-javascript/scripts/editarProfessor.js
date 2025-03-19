const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

document.getElementById("btn").addEventListener("click", function () {
    const id = document.getElementById("id").value;
    const salario = document.getElementById("salario").value;
    const modalidade = document.getElementById("modalidade").value;
    const mensagem = document.getElementById("mensagem");

    if (id === "" || salario === "" || modalidade === "") {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Preencha todos os campos!";
        return;
    }

    fetch(`/api/professor`, {
        method: "PUT",
        headers: {             
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({idProfessor: id, salario: salario, modalidade: modalidade })
    })
    .then(response => {
        if (response.ok) {
            mensagem.style.color = "green";
            mensagem.textContent = `✅ Professor ID ${id} atualizado com sucesso!`;
        } else {
            mensagem.style.color = "red";
            mensagem.textContent = "❌ Erro ao atualizar o professor.";
        }
    })
    .catch(error => {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
});