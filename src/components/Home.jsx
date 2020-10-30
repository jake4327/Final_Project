import React, {useState, useEffect} from 'react';
import Navbar from './Navbar';
import Jumbotron from 'react-bootstrap/Jumbotron';
import axios from 'axios';
import TicketTemplate from './TicketTemplate';

const Home = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    useEffect( () => {
        axios.get("http://localhost:8080/viewAllTickets")
        .then(res => res)
        .then(
            (res) => {
                setIsLoaded(true);
                console.log(res.data);
                setItems(res.data);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        )
    },[]);
    if(error) {
        return <div>Oops... something happened... {error.message}</div>
    } else if(!isLoaded) {
        return <div>Please wait... your data is still being loaded</div>
    } else {
        return (
            <Jumbotron>
                <Navbar/><br/>
                <h3>Welcome back!</h3>
                <h4>All Tickets</h4>
                {items.map( (data) => (
                    <TicketTemplate data={data} key={data.ticketId}/>
                ))}
            </Jumbotron>
        )
    }
}

export default Home;