import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';

const Navigationbar = () => {
    return (
        <Jumbotron>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>Help Queue</Navbar.Brand>
                <Nav>
                <Nav.Link href="/home">Home</Nav.Link>
                <Nav.Link href="/create-ticket">Create Ticket</Nav.Link>
                </Nav>
            </Navbar>
        </Jumbotron>
    );
}

export default Navigationbar;