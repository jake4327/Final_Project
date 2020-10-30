import React, {useState, useEffect} from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

const TraineeReg = () => {
    const [items, setItems] = useState([]);
    const [error, setError] = useState(null);
    const [forename, setForename] = useState([]);
    const [surname, setSurname] = useState([]);
    const [cohort, setCohort] = useState([]);
 
    const handleClick = (click) => {
        click.preventDefault();
        axios.put("http://localhost:8080/addTrainee", {
            forename: forename,
            surname: surname,
            cohortId: cohort
        })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        }); 
        //window.location.href="/home";
    }

    const handleForename = event => {
        setForename(event.target.value);
    }
    
    const handleSurname = event => {
        setSurname(event.target.value);
    }

    const handleCohort = event => {
        setCohort(event.target.value);
    }

    useEffect( () => {
        axios.get("http://localhost:8080/getAllCohorts")
        .then(res => res)
        .then(
            (res) => {
                setItems(res.data);
            },
            (error) => {
                setError(error);
            }
        )
    },[]);
    if(error) {
        return <div>Oops... something happened... {error.message}</div>
    } else {
        return (
            <Jumbotron>
                <h1>Who are you?</h1>
                <Form>
                    <Form.Group controlId="formForename">
                        <Form.Label>Forename</Form.Label>
                        <Form.Control type="text" placeholder="Enter Forename" onChange={handleForename}></Form.Control>
                    </Form.Group>
                    <Form.Group controlId="formSurname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control type="text" placeholder="Enter Surname" onChange={handleSurname}></Form.Control>
                    </Form.Group>
                    <Form.Group controlId="formCohort">
                        <Form.Label>Cohort</Form.Label>
                        <Form.Control as="select" onSelect={handleCohort}>
                            {items.map( (data) => (
                                <option key={data.cohortId}>{data.name}</option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={handleClick}>
                        Submit
                    </Button>
                </Form>
            </Jumbotron>
        );
    }

}

export default TraineeReg;