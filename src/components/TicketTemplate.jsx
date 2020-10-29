import React from 'react';
import {useHistory} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

const TicketTemplate = (props) => {
    const history = useHistory();

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
        click.preventDefault();
        history.push({
            pathname: "/edit-ticket",
            state:{
                data:data
            }
        });    
    }

    return (
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
                <Button variant="primary">Delete</Button>
            </Card.Body>
        </Card>
    );
}

export default TicketTemplate;