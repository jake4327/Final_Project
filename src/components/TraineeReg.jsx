import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TraineeReg = () => {
    const handleClick = (click) => {
        click.preventDefault();
        window.location.href="/home";
    }
    return (
        <Jumbotron>
            <h1>Who are you?</h1>
            <Form>
                <Form.Group controlId="formForename">
                    <Form.Label>Forename</Form.Label>
                    <Form.Control type="text"></Form.Control>
                </Form.Group>
                <Form.Group controlId="formSurname">
                    <Form.Label>Surname</Form.Label>
                    <Form.Control type="text"></Form.Control>
                </Form.Group>
                <Form.Group controlId="formCohort">
                    <Form.Label>Cohort</Form.Label>
                    <Form.Control as="select">
                        <option>placeholder1</option>
                        <option>placeholder2</option>
                        <option>placeholder3</option>
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={handleClick}>
                    Submit
                </Button>
            </Form>
        </Jumbotron>
    );
}

export default TraineeReg;