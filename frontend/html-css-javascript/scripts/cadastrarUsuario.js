const btn = document.getElementById("btn");

import { exibirMensagem } from "./notificacao.js";

console.log("API URL:", CONFIG.API_URL);

function cadastrarUsuario() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const confirmarSenha = document.getElementById("confirmarSenha").value;

    if (!login) {
        exibirMensagem("danger", "⚠️ Por favor, digite um login!");
        return;
    }

    if (!senha) {
        exibirMensagem("danger", "⚠️ Por favor, digite uma senha!");
        return;
    }

    if (senha !== confirmarSenha) {
        exibirMensagem("danger", "⚠️ As senhas não coincidem!");
        return;
    }

    const usuario = {
        login: login,
        senha: senha
    };

    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    };

    console.log(JSON.stringify(usuario));

    fetch(`${CONFIG.API_URL}/login/cadastro`, options)
        .then(data => {
            console.log(data);
            exibirMensagem("success", "✅ Usuário cadastrado com sucesso!");
            setTimeout(() => {
                window.location.href = "login.html";
            }, 3000);
        })
        .catch(error => {
            console.log(error);
            exibirMensagem("danger", "❌ Erro ao cadastrar usuário!");
        });
}

btn.addEventListener("click", cadastrarUsuario);
