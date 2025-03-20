let paginaAtual = 0;
const tamanhoPagina = 10;

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

function buscarSalas() {
    document.getElementById("loading").style.display = "block";

    fetch(`${CONFIG.API_URL}/sala?page=${paginaAtual}&size=${tamanhoPagina}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById("loading").style.display = "none";

            const alunos = data.content || [];
            const lista = document.getElementById("listaSalas");
            lista.innerHTML = "";

            alunos.forEach(sala => {
                const item = document.createElement("li");
                item.style.textAlign = "left";
                item.style.listStyle = "none";
                item.innerHTML = `
                    <strong>🔢 ID:</strong> ${sala.id} <br>
                    <strong>🏫 Nome:</strong> ${sala.nome} <br>
                    <strong>🎵 Modalidade:</strong> ${sala.modalidade} <br>
                    <hr>
                `;
                lista.appendChild(item)
            });

            document.getElementById("paginaAtual").textContent = `Página ${data.number + 1} de ${data.totalPages}`;
            paginaAtual = data.number;

            document.getElementById("btnAnterior").disabled = paginaAtual === 0;
            document.getElementById("btnProximo").disabled = paginaAtual >= data.totalPages - 1;
        })
        .catch(error => {
            console.error("Erro:", error);
            document.getElementById("loading").style.display = "none";
            alert("Erro ao carregar salas. Verifique o console.");
        });
}

function proximaPagina() {
    paginaAtual++;
    buscarSalas();
}

function paginaAnterior() {
    if (paginaAtual > 0) {
        paginaAtual--;
        buscarSalas();
    }
}

const btnAnterior = document.getElementById("btnAnterior")
const btnProximo = document.getElementById("btnProximo")

btnAnterior.addEventListener("click", paginaAnterior)
btnProximo.addEventListener("click", proximaPagina)

buscarSalas();