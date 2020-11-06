import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Button from 'react-bootstrap/Button';
import {useHistory} from 'react-router-dom';

const Navigationbar = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    const history = useHistory();

    const handleLogOut = (click) => {
        click.preventDefault();
        localStorage.removeItem("user");
        history.push("/");
    }

    return (
            <Navbar bg="primary" variant="dark">
                <Navbar.Brand>Help Queue</Navbar.Brand>
                <Nav>
                <Nav.Link href="/home">Home</Nav.Link>
                {user.role === true ? <Nav.Link href="/create-ticket">Create Ticket</Nav.Link> : ""}
                </Nav>
                <Button variant="outline-light" onClick={handleLogOut}>Log Out</Button>
            </Navbar>
    );
}

export default Navigationbar;