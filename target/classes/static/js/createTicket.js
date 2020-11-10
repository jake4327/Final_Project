const createTicket = () => {
    let elements = document.getElementById("ticketForm").elements;
    let obj = {};
    for (let i = 0; i < elements.length - 1; i++) {
        let item = elements.item(i);
        obj[item.name] = item.value;
    }
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/addTicket");
    req.onload = () => {
        if (req.status === 200 && req.readyState === 4) {
            console.log("Server responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ title: obj.title, description: obj.description, trainee: obj.trainee }));
}