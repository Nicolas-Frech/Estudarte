const btn = document.getElementById("btn");

console.log("API URL:", CONFIG.API_URL);

function cadastrarUsuario() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const confirmarSenha = document.getElementById("confirmarSenha").value;

    if (login == null) {
        alert("⚠️ Por favor, digite um login!")
    }

    if (senha == null) {
        alert("⚠️ Por favor, digite uma senha!")
    }

    if (senha !== confirmarSenha) {
        alert("⚠️ As senhas não coincidem!");
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
        .then(response => {
            if (!response.ok) {
                throw Error(response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert("✅ Usuário cadastrado com sucesso!");
            window.location.href = "login.html";
        })
        .catch(error => {
            console.log(error);
            alert("❌ Erro ao cadastrar usuário!");
        });
}

btn.addEventListener("click", cadastrarUsuario);
