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

    fetch(`http://localhost:8080/aula?page=${paginaAtual}&size=${tamanhoPagina}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("loading").style.display = "none";

            const aulas = data.content || [];
            const lista = document.getElementById("listaAulas");
            lista.innerHTML = "";

            aulas.forEach(aula => {
                const item = document.createElement("li");
                const dataFormatada = formatarData(aula.data);
                item.textContent = `ID: ${aula.id} | Professor: ${aula.professorNome} | Aluno: ${aula.alunoNome} | Modalidade: ${aula.modalidade} | Data: ${dataFormatada}`;
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

const btnAnterior = document.getElementById("btnAnterior")
const btnProximo = document.getElementById("btnProximo")

btnAnterior.addEventListener("click", paginaAnterior)
btnProximo.addEventListener("click", proximaPagina)

buscarAulas();
