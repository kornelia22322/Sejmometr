import React, { Component } from 'react';
import {BrowserRouter, Route} from 'react-router-dom';

import Header from './header'
import ListofDeputies from './ListofDeputies'

class App extends Component {
  render() {
    return (
        <BrowserRouter>
            <div>
                <Header/>
                <Route exact path="/" component={ListofDeputies}/>
            </div>
        </BrowserRouter>
    );
  }
}

export default App;
