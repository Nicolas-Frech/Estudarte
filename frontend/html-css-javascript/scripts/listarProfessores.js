let paginaAtual = 0;
const tamanhoPagina = 10;

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

function buscarProfessores() {
    document.getElementById("loading").style.display = "block";

    fetch(`${CONFIG.API_URL}/professor?page=${paginaAtual}&size=${tamanhoPagina}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById("loading").style.display = "none";

            const professores = data.content || [];
            const lista = document.getElementById("listaProfessores");
            lista.innerHTML = "";

            professores.forEach(professor => {
                const item = document.createElement("li");
                item.style.textAlign = "left";
                item.style.listStyle = "none";
                item.innerHTML = `
                    <strong>🔢 ID:</strong> ${professor.id} <br>
                    <strong>👤 Nome:</strong> ${professor.nome} <br>
                    <strong>🎵 Modalidade:</strong> ${professor.modalidade} <br>
                    <strong>💰 Salário:</strong> R$${professor.salario}
                    <hr>
                `;
                lista.appendChild(item);
            });

            document.getElementById("paginaAtual").textContent = `Página ${data.number + 1} de ${data.totalPages}`;
            paginaAtual = data.number;

            document.getElementById("btnAnterior").disabled = paginaAtual === 0;
            document.getElementById("btnProximo").disabled = paginaAtual >= data.totalPages - 1;
        })
        .catch(error => {
            console.error("Erro:", error);
            document.getElementById("loading").style.display = "none"; // Oculta "Carregando..."
            alert("Erro ao carregar professores. Verifique o console.");
        });
}

function proximaPagina() {
    paginaAtual++;
    buscarProfessores();
}

function paginaAnterior() {
    if (paginaAtual > 0) {
        paginaAtual--;
        buscarProfessores();
    }
}

const btnAnterior = document.getElementById("btnAnterior")
const btnProximo = document.getElementById("btnProximo")

btnAnterior.addEventListener("click", paginaAnterior)
btnProximo.addEventListener("click", proximaPagina)

buscarProfessores();
