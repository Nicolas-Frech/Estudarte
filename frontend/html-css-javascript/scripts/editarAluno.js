import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "⚠️ Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}


function validarCampos(idAluno, idProfessor, modalidade) {
    if (!idAluno) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID!");
        return false;
    }

    if (!idProfessor && !modalidade) {
        exibirMensagem("danger", "⚠️ Por favor, insira algum dos campos!");
        return false;
    }
    return true;
}


document.getElementById("btn").addEventListener("click", function () {
    const idAluno = document.getElementById("idAluno").value;
    const idProfessor = document.getElementById("idProfessor").value;
    const modalidade = document.getElementById("modalidade").value;

    console.log(idProfessor);

    if (!validarCampos(idAluno, idProfessor, modalidade)) {
        return;
    }

    fetch(`${CONFIG.API_URL}/aluno`, {
        method: "PUT",
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({alunoId: idAluno, professorId: idProfessor, modalidade: modalidade })
    })
    .then(response => {
        if (response.ok) {
            exibirMensagem("success", "✅ Dados atualizados com sucesso!");
        } else {
            exibirMensagem("danger", "❌ Erro ao atualizar os dados.");
        }
    })
    .catch(error => {
        exibirMensagem("danger", "⚠️ Erro na requisição: " + error);    
    });
});