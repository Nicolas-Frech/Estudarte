import { exibirMensagem } from "./notificacao.js";

const btnLogin = document.getElementById("btnLogin");
const spinner = document.getElementById("spinner");

console.log("API URL:", CONFIG.API_URL);

function loginUsuario() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const mensagemErro = document.getElementById("mensagemErro");

    if (!login) {
        exibirMensagem("danger", "⚠️ Por favor, digite um login!");
        return;
    }

    if (!senha) {
        exibirMensagem("danger", "⚠️ Por favor, digite uma senha!");
        return;
    }

    const usuario = {
        login: login,
        senha: senha
    };

    const options = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    };

    spinner.style.display = "block";
    btnLogin.disabled = true;

    fetch(`${CONFIG.API_URL}/login`, options)
        .then(response => {
            if (!response.ok) {
                exibirMensagem("danger", "⚠️ Usuário ou senha inválidos!");
            }
            return response.json();
        })
        .then(data => {
            console.log("Token recebido:", data.token);
            localStorage.setItem("token", data.token);
            window.location.href = "index.html";
        })
        .catch(error => {
            exibirMensagem("danger", "❌ Erro ao fazer login!");
            console.log(error);
            spinner.style.display = "none";
            btnLogin.disabled = false;
        });
}

btnLogin.addEventListener("click", loginUsuario);
