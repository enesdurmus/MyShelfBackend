import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from "./pages/home/Home";
import Users from "./pages/user/Users";
import AppSettings from './pages/appSettings/AppSettings';
import Signin from './pages/signin/Signin';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Signin} />
          <Route path='/home' exact={true} component={Home} />
          <Route path='/users' exact={true} component={Users} />
          <Route path='/app_settings' exact={true} component={AppSettings} />
        </Switch>
      </Router>
    )
  }
}

export default App;