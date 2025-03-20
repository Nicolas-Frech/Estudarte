const btnReserva = document.getElementById("btnReserva");

console.log("API URL:", CONFIG.API_URL);

const token = localStorage.getItem("token");
if(!token) {
  alert("Você precisa estar logado!");
  window.location.href = "login.html";
}

function reservarSala() {
    const salaId = document.getElementById("salaId").value;
    const horario = document.getElementById("horario").value;
    const data = document.getElementById("data").value;

    if (!salaId || !horario) {
        alert("Por favor, preencha todos os campos.");
        return;
    }

    const reserva = {
        idSala: salaId,
        horarioReserva: data + "T" + horario + ":00"
    };

    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(reserva)
    };

    fetch(`${CONFIG.API_URL}/sala`, options)
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert("Reserva realizada com sucesso!");
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao realizar a reserva.");
        });
}

btnReserva.addEventListener("click", reservarSala);