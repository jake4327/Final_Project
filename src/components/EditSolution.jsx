import React, { useState } from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {useHistory} from 'react-router-dom';
import axios from 'axios';

const EditSolution = (props) => {
    const data = props.location.state.data;
    const [solution, setSolution] = useState([]);
    const history = useHistory();

    const handleClick = (click) => {
        click.preventDefault();
        axios.put(`http://localhost:8080/editSolution/${data.ticketId}`, {
            solution: solution
        })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
        axios.put(`http://localhost:8080/updateStatus/${data.ticketId}`, {
            status: true
        })
        .then(function (response) {
            console.log(response);
        })
        .then(function (error) {
            console.log(error);
        });
        history.push({pathname: "/home"});
        history.go(0);
    }

    const handleSolution = event => {
        setSolution(event.target.value);
    }

    return (
        <Jumbotron>
            <h1>Add Solution</h1>
            <Form>
                <Form.Group controlId="solution">
                    <Form.Control as="textarea" placeholder="Enter Solution Here" onChange={handleSolution} rows={5}>
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={handleClick}>Add Solution</Button>
            </Form>
        </Jumbotron>
    );
}

export default EditSolution;