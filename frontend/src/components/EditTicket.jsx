import React, { useState } from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {useHistory} from 'react-router-dom';
import axios from 'axios';

const EditTicket = (props) => {
    const data = props.location.state.data;
    const [title, setTitle] = useState([]);
    const [description, setDescription] = useState([]);
    const [topic, setTopic] = useState([]);
    const history = useHistory();

    const handleClick = (click) => {
        click.preventDefault();
        axios.put(`http://${process.env.REACT_APP_BACKEND_API}:8080/editTicket/${data.ticketId}`, {
            title: title,
            description: description,
            topic: topic
        })
        .then(function (response) {
            console.log(response);
            history.push({pathname: "/home"});
        })
        .catch(function (error) {
            console.log(error);
        });
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

    return(
        <Jumbotron>
            <h1>Edit Ticket</h1>
            <Form>
                <Form.Group controlId="title">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text" placeholder={data.title} onChange={handleTitle}>
                    </Form.Control>
                    <Form.Text>Enter a new title</Form.Text>
                </Form.Group>
                <Form.Group controlId="description">
                    <Form.Label>Description</Form.Label>
                    <Form.Control as="textarea" placeholder={data.description} onChange={handleDescription} rows={5}>   
                    </Form.Control>
                    <Form.Text>Enter a new description</Form.Text>
                </Form.Group>
                <Form.Group controlId="topic">
                    <Form.Label>Topic</Form.Label>
                    <Form.Control type="text" placeholder={data.topic} onChange={handleTopic}>
                    </Form.Control>
                    <Form.Text>Enter a new topic</Form.Text>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={handleClick}>Edit Ticket</Button>
            </Form>
        </Jumbotron>
    );
}

export default EditTicket;