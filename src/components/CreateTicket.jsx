import React, { useState } from 'react';
import Navbar from './Navbar';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import {useHistory} from 'react-router-dom';

const CreateTicket = () => {
    const [title, setTitle] = useState([]);
    const [description, setDescription] = useState([]);
    const [topic, setTopic] = useState([]);
    const history = useHistory();

    const handleClick = (click) => {
        click.preventDefault();
        axios.post("http://localhost:8080/addTicket", {
            title: title,
            description: description,
            topic: topic
        })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
        history.push("/home");
    }

    const handleTitle = event => {
        setTitle(event.target.value);
    }

    const handleDescription = event => {
        setDescription(event.target.value);
    }

    const handleTopic = event => {
        setTopic(event.target.value);
    }

    return (
        <>
            <Jumbotron>
                <Navbar/><br/>
                <Form>
                    <Form.Group controlId="formTitle">
                        <Form.Label>Title</Form.Label>
                        <Form.Control type="text" placeholder="Enter Title" onChange={handleTitle}></Form.Control>
                        <Form.Text>
                            Concise identifier for the problem
                        </Form.Text>
                    </Form.Group>
                    <Form.Group controlId="formDescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" rows={5} placeholder="Enter Description" onChange={handleDescription}></Form.Control>
                        <Form.Text>
                            An explanation of what the issue is and all relevant information that can assist in solving the problem
                        </Form.Text>
                    </Form.Group>
                    <Form.Group controlId="formTopic">
                        <Form.Label>Topic</Form.Label>
                        <Form.Control type="text" placeholder="Enter Topic" onChange={handleTopic}></Form.Control>
                        <Form.Text>
                            What type of problem is it? (Examples: React, Angular, Spring Boot, Jenkins, Terraform, CLI)
                        </Form.Text>
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={handleClick}>
                        Submit
                    </Button>
                </Form>
            </Jumbotron>
        </>
    );
}

export default CreateTicket;