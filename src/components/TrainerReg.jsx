import React, {useState, useEffect} from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

const TrainerReg = () => {
    const [trainers, setTrainers] = useState([]);
    const [error, setError] = useState(null);

    const handleClick = (click) => {
        click.preventDefault();
        window.location.href="/home";
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
                    <div>
                        #######
                    <Button variant="primary" type="submit" onClick={handleClick}>
                        {trainer.forename} {trainer.surname} Testing
                    </Button>
                    </div>
                ))}
            </Jumbotron>
        );
    }

}

export default TrainerReg;