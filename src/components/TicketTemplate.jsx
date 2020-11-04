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
    let solution;

    if(props.data.status === false) {
        solution = "Unsolved";
    } else if(props.data.status === true) {
        solution = "Solved";
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

    const handleSolution = (click) => {
        let data = props.data;
        let user = props.user;
        click.preventDefault();
        history.push({
            pathname: "/edit-solution",
            state:{
                data:data,
                userInfo:user
            }
        })
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

    const formatTime = (time) => {
        let timestamp = time;
        timestamp = setCharAt(timestamp, 4, '/');
        timestamp = setCharAt(timestamp, 7, '/');
        timestamp = setCharAt(timestamp, 10, ' ');
        timestamp = setCharAt(timestamp, 19, ' ');
        timestamp = setCharAt(timestamp, 20, ' ');
        timestamp = setCharAt(timestamp, 21, ' ');
        timestamp = setCharAt(timestamp, 22, ' ');
        timestamp = setCharAt(timestamp, 23, ' ');
        timestamp = setCharAt(timestamp, 24, ' ');
        timestamp = setCharAt(timestamp, 25, ' ');
        return timestamp;
    }

    function setCharAt(str,index,chr) {
        if(index > str.length-1) return str;
        return str.substring(0,index) + chr + str.substring(index+1);
    }

    return (
        <>
            <Card style={{ width: '25rem' }}>
                <Card.Body>
                    <Card.Title>Title: {props.data.title}</Card.Title>
                    <Card.Text>
                        Description: {props.data.description}
                    </Card.Text>
                    <Card.Text>
                        Topic: {props.data.topic}
                    </Card.Text>
                    <Card.Text>
                        Solved: {solution}
                    </Card.Text>
                    <Card.Text>
                        Date and Time: {formatTime(props.data.localDateTime)}
                    </Card.Text>
                </Card.Body>
                <Card.Body>
                    {props.data.status === false ? <><Button variant="primary" onClick={handleEditClick}>Edit</Button>  </>: ""}
                    {props.data.status === false ? <><Button variant="primary" onClick={handleSolution}>Add Solution</Button>  </> : ""}
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