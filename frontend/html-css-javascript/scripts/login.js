const btnLogin = document.getElementById("btnLogin");
const spinner = document.getElementById("spinner");

function loginUsuario() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const mensagemErro = document.getElementById("mensagemErro");

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

    fetch("/api/login", options)
        .then(response => {
            if (!response.ok) {
                throw new Error("\nUsuário ou senha inválidos!");
            }
            return response.json();
        })
        .then(data => {
            console.log("Token recebido:", data.token);
            localStorage.setItem("token", data.token);
            window.location.href = "index.html";
        })
        .catch(error => {
            mensagemErro.textContent = error.message;
            spinner.style.display = "none"; // Esconde o spinner em caso de erro
            btnLogin.disabled = false;
        });
}

btnLogin.addEventListener("click", loginUsuario);
