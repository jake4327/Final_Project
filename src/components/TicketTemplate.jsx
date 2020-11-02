import React, {useState} from 'react';
import {useHistory} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';

const TicketTemplate = (props) => {
    const history = useHistory();
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleStatus = (props) => {
        let status = props.status;
        if(status) {
            return("Solved");
        } else if(!status) {
            return("Unsolved");
        }
    }

    const handleEditClick = (click) => {
        let data = props.data;
        let user = props.user;
        click.preventDefault();
        history.push({
            pathname: "/edit-ticket",
            state:{
                data:data,
                userInfo:user
            }
        }); 
    }

    const deleteTicket = () => {
        let data = props.data;
        console.log(data);
        axios.delete(`http://localhost:8080/deleteTicket/${data.ticketId}`)
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
        history.go(0);
    }

    return (
        <>
            <Card style={{ width: '18rem' }}>
                <Card.Body>
                    <Card.Title>Title: {props.data.title}</Card.Title>
                    <Card.Text>
                        Description: {props.data.description}
                    </Card.Text>
                    <Card.Text>
                        Topic: {props.data.topic}
                    </Card.Text>
                    <Card.Text>
                        Solved: {handleStatus(props.data.status)}
                    </Card.Text>
                </Card.Body>
                <Card.Body>
                    <Button variant="primary" onClick={handleEditClick}>Edit</Button>
                    <Button variant="primary" onClick={handleShow}>Delete</Button>
                </Card.Body>
            </Card>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>Are you sure you want to delete this ticket?</Modal.Header>
                <Modal.Body>
                    <Button variant="primary" onClick={deleteTicket}>
                        Delete
                    </Button>
                </Modal.Body>
            </Modal>
        </>
    );
}

export default TicketTemplate;