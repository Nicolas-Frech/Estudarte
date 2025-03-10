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

    fetch(`/api/aula`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
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