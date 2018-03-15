import React, { Component } from 'react';
import axios from "axios";
import JSONPretty from 'react-json-pretty';
import './App.css';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: "",
            selectors: "",
            result: ""
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    //********************************************************
    handleSubmit(event) {
        event.preventDefault();
        let self = this;
        axios.post('http://localhost:8080/web/run', {
            url : this.state.url,
            selectors: JSON.parse(this.state.selectors)
        })
            .then(function (response) {
                self.setState({result: JSON.stringify(response.data)})
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    //**********************************************************
    handleChangeInput = event => {
        this.setState({ url: event.target.value });
    };
    //**********************************************************
    handleChangeTextArea = event => {
        this.setState({ selectors: event.target.value });
    };
    //***********************************************************
  render() {
    return (
      <div>

          <div id="header">
              <h1>Web Scraper</h1>
          </div>

          <div id="inputs">
                <h1>Inputs</h1>
                <form onSubmit={this.handleSubmit} id="usrform">
                    <h3>Enter URL:</h3>
                    <input type="text" onChange={this.handleChangeInput}/>
                    <h3>Enter CSS selectors:</h3>
                    <textarea rows="30" cols="50" onChange={this.handleChangeTextArea} form="usrform"/>
                    <button type="submit">GO</button>
                </form>
          </div>

          <hr/>

          <div id="results">
              <h1>Results</h1>
              <JSONPretty id="json-pretty" json={this.state.result}/>
          </div>

      </div>
    );
  }
}

export default App;
