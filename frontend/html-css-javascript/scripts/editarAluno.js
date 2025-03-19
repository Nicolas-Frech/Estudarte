const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

document.getElementById("btn").addEventListener("click", function () {
    const idAluno = document.getElementById("idAluno").value;
    const idProfessor = document.getElementById("idProfessor").value;
    const modalidade = document.getElementById("modalidade").value;
    const mensagem = document.getElementById("mensagem");

    if (idAluno === "" || idProfessor === "" || modalidade === "") {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Preencha todos os campos!";
        return;
    }

    fetch(`/api/aluno`, {
        method: "PUT",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({alunoId: idAluno, professorId: idProfessor, modalidade: modalidade })
    })
    .then(response => {
        if (response.ok) {
            mensagem.style.color = "green";
            mensagem.textContent = `✅ Aluno ID ${idAluno} atualizado com sucesso!`;
        } else {
            mensagem.style.color = "red";
            mensagem.textContent = "❌ Erro ao atualizar o aluno.";
        }
    })
    .catch(error => {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
});