import React, { Component } from 'react';
import {BrowserRouter, Route} from 'react-router-dom';

import Header from './Header'
import DeputiesWrapper from './DeputiesWrapper'

class App extends Component {
  render() {
    return (
        <BrowserRouter>
            <div>
                <Header/>
                <Route exact path="/" component={DeputiesWrapper}/>
            </div>
        </BrowserRouter>
    );
  }
}

export default App;
