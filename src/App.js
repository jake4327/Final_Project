import React from 'react';
import { Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import CreateTicket from './components/CreateTicket';
import LandingPage from './components/LandingPage';
import TrainerReg from './components/TrainerReg';
import TraineeReg from './components/TraineeReg';
import EditTicket from './components/EditTicket';

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/" component={LandingPage} exact/>
          <Route path="/home" component={Home} exact />
          <Route path="/create-ticket" component={CreateTicket} />
          <Route path="/trainee" component={TraineeReg} />
          <Route path="/trainer" component={TrainerReg} />
          <Route path="/edit-ticket" component={EditTicket} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;