import { editTicket } from './editTicket';

const displayTickets = () => {
    const req = new XMLHttpRequest();
    req.onreadystatechange = () => {
        if (req.status === 200 && req.readyState === 4) {
            if (req.getResponseHeader("Content-type") === "application/json") {
                console.log("JSON: " + req.responseText);
                let content = JSON.parse(req.response);
                content.forEach(el => {
                    let elem = document.createElement('div');
                    let info = document.createElement('p');
                    info.textContent = "Ticket ID: " + el.ticketId + "Title: " + el.title + " Description: " + el.description + " Date created: " + el.localDateTime + " Created By: " + el.trainee + "Solved Status: " + el.status;
                    let editButton = document.createElement("BUTTON");
                    editButton.innerHTML = "Edit";
                    editButton.onclick = editTicket();
                    let deleteButton = document.createElement("BUTTON");
                    deleteButton.innerHTML = "Delete";
                    elem.appendChild(info);
                    elem.appendChild(editButton);
                    elem.appendChild(deleteButton);
                    document.body.appendChild(elem);
                });
            } else {
                console.log("Doesn't seem to be JSON... " + req.responseText);
            }
        } else {
            console.log("Oh no... handle error");
        }
    };
    req.open("GET", "http://localhost:8080/viewAllTickets");
    req.send();
}