let paginaAtual = 0;
const tamanhoPagina = 10;

function formatarData(dataISO) {
    if (!dataISO) return "Data inválida";

    const data = new Date(dataISO);
    return new Intl.DateTimeFormat("pt-BR", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        hour12: false
    }).format(data);
}

function buscarAulas() {
    document.getElementById("loading").style.display = "block";

    const nomeAluno = document.getElementById("searchAluno").value.trim();
    
    if (nomeAluno === "") {
        document.getElementById("listaAulas").innerHTML = "<p>Digite o nome de um aluno para buscar aulas.</p>";
        document.getElementById("loading").style.display = "none";
        return;
    }

    fetch(`/api/aula/aluno/${encodeURIComponent(nomeAluno)}?page=${paginaAtual}&size=${tamanhoPagina}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById("loading").style.display = "none";

            const aulas = data.content || [];
            const lista = document.getElementById("listaAulas");
            lista.innerHTML = "";

            if (aulas.length === 0) {
                lista.innerHTML = "<p>Nenhuma aula encontrada para esse professor.</p>";
                return;
            }

            aulas.forEach(aula => {
                const item = document.createElement("li");
                const dataFormatada = formatarData(aula.data);
                item.style.textAlign = "left";
                item.innerHTML = `
                    <strong>🔢 ID:</strong> ${aula.id} <br>
                    <strong>👨‍🏫 Professor:</strong> ${aula.professorNome} <br>
                    <strong>🎓 Aluno:</strong> ${aula.alunoNome} <br>
                    <strong>🎵 Modalidade:</strong> ${aula.modalidade} <br>
                    <strong>📆 Data:</strong> ${dataFormatada}
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
            document.getElementById("loading").style.display = "none";
            alert("Erro ao carregar aulas. Verifique o console.");
        });
}

function proximaPagina() {
    paginaAtual++;
    buscarAulas();
}

function paginaAnterior() {
    if (paginaAtual > 0) {
        paginaAtual--;
        buscarAulas();
    }
}

document.getElementById("btn").addEventListener("click", buscarAulas);
document.getElementById("btnAnterior").addEventListener("click", paginaAnterior);
document.getElementById("btnProximo").addEventListener("click", proximaPagina);


document.getElementById("searchProfessor").addEventListener("input", () => {
    paginaAtual = 0;
});
