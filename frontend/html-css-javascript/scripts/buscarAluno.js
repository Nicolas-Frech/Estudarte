import { exibirMensagem } from "./notificacao.js";

const token = localStorage.getItem("token");

console.log("API URL:", CONFIG.API_URL);

if(!token) {
    exibirMensagem("danger", "Você precisa estar logado!");
    setTimeout(() => {
        window.location.href = "login.html";
    },  2000);
}

document.getElementById("btnBuscar").addEventListener("click", () => {
    const id = document.getElementById("alunoId").value;
    const resultado = document.getElementById("resultado");

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        resultado.innerHTML = ""
        return;
    }

    fetch(`${CONFIG.API_URL}/aluno/${id}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Aluno não encontrado");
            }
            return response.json();
        })
        .then(aluno => {
            resultado.innerHTML = `
                <p><strong>👤 Nome:</strong> ${aluno.nome}</p>
                <p><strong>🆔 CPF:</strong> ${aluno.cpf}</p>
                <p><strong>📞 Telefone:</strong> ${aluno.telefone}</p>
                <p><strong>📧 E-mail:</strong> ${aluno.email}</p>
                <p><strong>🎵 Modalidade:</strong> ${aluno.modalidade}</p>
            `;
        })
        .catch(error => {
            exibirMensagem("danger", "❌ Aluno não encontrado!");
            resultado.innerHTML = ""
        });
});