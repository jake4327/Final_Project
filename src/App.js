import React from 'react';
import { Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import CreateTicket from './components/CreateTicket';

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route path="/" component={Home} exact />
          <Route path="/create-ticket" component={CreateTicket} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;