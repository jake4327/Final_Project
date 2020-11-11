import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Button from 'react-bootstrap/Button';

const LandingPage = () => {
    return (
        <Jumbotron>
            <h1>Who are you?</h1>
            <Button href="/trainee" size="lg" variant="warning" block>Trainee</Button>
            <Button href="/trainer" size="lg" variant="warning" block>Trainer</Button>
        </Jumbotron>
    );
}

export default LandingPage;
