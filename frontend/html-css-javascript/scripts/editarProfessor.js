import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

function validarCampos(id, salario, modalidade, telefone, email) {
    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID!");
        return false;
    }

    if (!salario && !modalidade && !telefone && !email) {
        exibirMensagem("danger", "⚠️ Por favor, insira algum dos campos!");
        return false;
    }
    return true;
}

document.getElementById("btn").addEventListener("click", function () {
    const id = document.getElementById("id").value;
    const salario = document.getElementById("salario").value;
    const modalidade = document.getElementById("modalidade").value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById("email").value;


    if(!validarCampos(id, salario, telefone, email)) {
        return
    }

    fetch(`${CONFIG.API_URL}/professor`, {
        method: "PUT",
        headers: {             
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({idProfessor: id, salario: salario, modalidade: modalidade, telefone: telefone, email: email })
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