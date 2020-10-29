import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Button from 'react-bootstrap/Button';

const TrainerReg = () => {
    const handleClick = (click) => {
        click.preventDefault();
        window.location.href="/home";
    }
    return (
        <Jumbotron>
            <Button variant="primary" type="submit" size="lg" onClick={handleClick}>
                Placeholder 1
            </Button>
            <Button variant="primary" type="submit" size="lg" onClick={handleClick}>
                Placeholder 2
            </Button>
            <Button variant="primary" type="submit" size="lg" onClick={handleClick}>
                Placeholder 3
            </Button>
        </Jumbotron>
    );
}

export default TrainerReg;