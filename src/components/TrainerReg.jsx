import React, {useState, useEffect} from 'react';
import {useHistory} from 'react-router-dom';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

const TrainerReg = () => {
    const [trainers, setTrainers] = useState([]);
    const [error, setError] = useState(null);
    const history = useHistory();

    const handleClick = (trainer) => {
        console.log(trainer);
        localStorage.setItem("user",JSON.stringify(trainer));
        history.push("/home");
    }
    
    useEffect( () => {
        axios.get("http://localhost:8080/getAllTrainers")
        .then(res => res)
        .then(
            (res) => {
                setTrainers(res.data);
            },
            (error) => {
                setError(error);
            }
        )
    },[]);
    if (error) {
        return <div>Oops... Something went wrong... {error.message}</div>
    } else {
        return (
            <Jumbotron>
                {trainers.map( trainer => (
                    <><Button variant="primary" type="submit" onClick={() => handleClick(trainer)} key={trainer.trainerId}>
                        {trainer.forename} {trainer.surname}
                    </Button>  </>
                ))}
            </Jumbotron>
        );
    }

}

export default TrainerReg;