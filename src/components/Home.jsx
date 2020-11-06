import React, {useState, useEffect} from 'react';
import Navbar from './Navbar';
import Jumbotron from 'react-bootstrap/Jumbotron';
import axios from 'axios';
import TicketTemplate from './TicketTemplate';
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';
import { CardColumns } from 'react-bootstrap';


const Home = (props) => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [key, setKey] = useState('all-tickets');
    const [cohorts, setCohorts] = useState([]);
    const [user, setUser] = useState({data:{}});
    let newTickets = items.slice().reverse();
    const data = JSON.parse(localStorage.getItem("user"));
    //console.log(cohorts);
    //console.log(JSON.stringify(user.tickets));
    console.log(user);

    useEffect( () => {
        axios.get("http://localhost:8080/viewAllTickets")
        .then(res => res)
        .then(
            (res) => {
                setIsLoaded(true);
                setItems(res.data);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        )
        axios.get("http://localhost:8080/getAllCohorts")
        .then(res => res)
        .then(
            (res) => {
                setCohorts(res.data);
            }
        )
        axios.get(`http://localhost:8080/getTraineeById/${data.traineeId}`)
        .then(res => res)
        .then(
            (res) => {
                setUser(res.data);
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
                <h3>Welcome back {data.forename} {data.surname}</h3>
                <Tabs id="ticket-tabs" activeKey={key} onSelect={(k) => setKey(k)}>
                    <Tab eventKey="all-tickets" title="All Tickets">
                        <br/>
                        <CardColumns>
                            {items.map( (data) => (
                                data.status === false ? <TicketTemplate data={data} key={data.ticketId}/> : ""
                            ))}
                        </CardColumns>
                    </Tab>
                    {user.role === true ? <Tab eventKey="my-tickets" title="My Tickets">
                        <CardColumns>
                            <br/>
                            {cohorts.map(cohort => (
                                cohort.trainees.map(trainee => (
                                    trainee.traineeId === user.traineeId ?
                                        trainee.tickets.map(ticket => (
                                            <TicketTemplate data={ticket} key={ticket.ticketId} />
                                        ))
                                    : ""
                                ))
                            ))}
                        </CardColumns>
                    </Tab> : ""}
                    {cohorts.map( (cohort) => (
                        <Tab eventKey={cohort.cohortId} title={cohort.name} key={cohort.cohortId}>
                            <br/>
                            <CardColumns>
                                {cohort.trainees.map(trainee => (
                                    trainee.tickets.map(ticket => (
                                        ticket.status === false ? <TicketTemplate data={ticket} key={ticket.ticketId}/> : ""
                                    ))
                                ))}
                            </CardColumns>
                        </Tab>
                    ))}
                    <Tab eventKey="oldest" title="Oldest Tickets">
                        <CardColumns>
                            {items.map( (data) => (
                                data.status === false ? <TicketTemplate data={data} key={data.ticketId}/> : ""
                            ))}
                        </CardColumns>
                    </Tab>
                    <Tab eventKey="newest" title="Newest Tickets">
                        <CardColumns>
                            {newTickets.map( (data) => (
                                data.status === false ? <TicketTemplate data={data} key={data.ticketId}/> : ""
                            ))}
                        </CardColumns>
                    </Tab>
                    <Tab eventKey="solved" title="Solved Tickets">
                        <CardColumns>
                            {items.map(ticket => (
                                ticket.status === true ? <TicketTemplate data={ticket} key={ticket.ticketId} /> : ""
                            ))}
                        </CardColumns>
                    </Tab>
                </Tabs>
            </Jumbotron>
        )
    }
}

export default Home;