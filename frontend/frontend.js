async function ajouterTicket() {
    const ticket = document.getElementById('ticketInput').value;
    const guichet = document.getElementById('guichetInput').value;
    const resultat = document.getElementById('ajouterResultat');
    if (ticket && guichet) {
        try {
            const response = await fetch('/tickets', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `ticket=${ticket}&numeroGuichet=${guichet}`
            });
            const texte = await response.text();
            resultat.textContent = texte;
            compterTickets(); 
        } catch (error) {
            resultat.textContent = 'Erreur : ' + error.message;
        }
    } else {
        resultat.textContent = 'Erreur : Ticket et numero de guichet requis';
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
        compterTickets(); 
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
setInterval(compterTickets, 2000); 