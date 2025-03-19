const btn = document.getElementById("btn");

function cadastrarUsuario() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const confirmarSenha = document.getElementById("confirmarSenha").value;

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

    console.log(JSON.stringify(usuario)); // Debug

    fetch("http://localhost:8080/login/cadastro", options)
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
