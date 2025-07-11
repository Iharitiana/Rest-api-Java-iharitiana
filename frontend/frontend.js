async function ajouterTicket() {
    const ticket = document.getElementById('ticketInput').value;
    const resultat = document.getElementById('ajouterResultat');
    try {
        const response = await fetch('/tickets', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `ticket=${ticket}`
        });
        const texte = await response.text();
        resultat.textContent = texte;
    } catch (error) {
        resultat.textContent = 'Erreur : ' + error.message;
    }
}

async function consulterTicket() {
    const resultat = document.getElementById('consulterResultat');
    try {
        const response = await fetch('/tickets/peek');
        const texte = await response.text();
        resultat.textContent = texte;
    } catch (error) {
        resultat.textContent = 'Erreur : ' + error.message;
    }
}

async function traiterTicket() {
    const resultat = document.getElementById('traiterResultat');
    try {
        const response = await fetch('/tickets/next');
        const texte = await response.text();
        resultat.textContent = texte;
    } catch (error) {
        resultat.textContent = 'Erreur : ' + error.message;
    }
}

async function compterTickets() {
    const resultat = document.getElementById('compterResultat');
    try {
        const response = await fetch('/tickets/count');
        const texte = await response.text();
        resultat.textContent = texte;
    } catch (error) {
        resultat.textContent = 'Erreur : ' + error.message;
    }
}