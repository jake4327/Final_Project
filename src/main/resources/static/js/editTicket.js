const editTicket = () => {
    let elements = document.getElementById("displayedTickets").elements;
    let obj = {};
    for(let i = 0; i < elements.length - 1; i++) {
        let item = elements.item(i);
        obj[item.name] = item.value;
    }
    let ticketId = obj.ticketId;
    const req = new XMLHttpRequest();
    req.open("PUT", "http://localhost:8080/editTicket/" + ticketId);
    req.onload = () => {
        if (req.status === 200 && req.readyState === 4) {
            console.log("Server responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ title: obj.title, description: obj.description, trainee: obj.trainee } ));
}