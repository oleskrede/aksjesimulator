import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import App from './App';
import Header from './common/Header';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
    <React.StrictMode>
      <BrowserRouter>
        <Header />
        <div className="mx-8 my-6">
          <Switch>
            <Route exact path="/" component={App} />
          </Switch>
        </div>
      </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root'),
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
