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
    const id = document.getElementById("professorId").value;
    const resultado = document.getElementById("resultado");

    if (!id) {
        exibirMensagem("danger", "⚠️ Por favor, insira um ID válido!");
        resultado.innerHTML = ""
        return;
    }

    fetch(`${CONFIG.API_URL}/professor/${id}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Professor não encontrado");
            }
            return response.json();
        })
        .then(professor => {
            var salario = professor.salario;
            if(salario === null) {
                salario = ""
            }
            resultado.innerHTML = `
                <p><strong>👤 Nome:</strong> ${professor.nome}</p>
                <p><strong>🏢 CNPJ:</strong> ${professor.cnpj}</p>
                <p><strong>📞 Telefone:</strong> ${professor.telefone}</p>
                <p><strong>📧 E-mail:</strong> ${professor.email}</p>
                <p><strong>🎵 Modalidade:</strong> ${professor.modalidade}</p>
                <p><strong>💰 Salário:</strong> R$${salario}</p>
                <p><strong>🎓 Alunos:</strong> ${professor.alunos}</p>
            `;
        })
        .catch(error => {
            exibirMensagem("danger", "❌ Professor não encontrado!");
            resultado.innerHTML = ""
            return;
        });
});