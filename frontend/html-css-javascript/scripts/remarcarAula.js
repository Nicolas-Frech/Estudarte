const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

document.getElementById("btnRemarcar").addEventListener("click", function () {
    const idAula = document.getElementById("idAula").value;
    const novaData = document.getElementById("novaData").value;
    const novoHorario = document.getElementById("novoHorario").value;
    const mensagem = document.getElementById("mensagem");
    const dataAtualizada = novaData + "T" + novoHorario + ":00"

    if (idAula === "" || novaData === "" || novoHorario === "") {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Preencha todos os campos!";
        return;
    }

    fetch(`${CONFIG.API_URL}/aula`, {
        method: "PUT",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ aulaId: idAula, data: dataAtualizada})
    })
    .then(response => {
        if (response.ok) {
            mensagem.style.color = "green";
            mensagem.textContent = `✅ Aula ID ${idAula} remarcada com sucesso!`;
        } else {
            mensagem.style.color = "red";
            mensagem.textContent = "❌ Erro ao remarcar a aula.";
        }
    })
    .catch(error => {
        mensagem.style.color = "red";
        mensagem.textContent = "⚠️ Erro na requisição: " + error;
    });
});