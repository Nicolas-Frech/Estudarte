const btn = document.getElementById("btn");

function cadastrarSala() {
    const nome = document.getElementById("nome").value;
    const modalidade = document.getElementById("modalidade").value;

    const sala = {
        nome: nome,
        modalidade: modalidade
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sala)
    };

    fetch('http://localhost:8080/sala', options)
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert("Sala cadastrada com sucesso!");
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao cadastrar a sala.");
        });
}

btn.addEventListener("click", cadastrarSala);