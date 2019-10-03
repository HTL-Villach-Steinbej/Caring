import React from 'react';
import "./style.css";

function App() {
  return (
    <div className="App">
      <div className="header">
        <p className="headerText">Home</p>
        <p className="headerText">Book</p>
        <p className="headerText">Info</p>
        <p className="headerText">Problemsreport</p>
        <p className="headerText">About</p>
      </div>
      <div className="sidenav">
        <p>Sidenav</p>
      </div>
      <div className="body">
        <p>BODY</p> 
      </div>
    </div>
  );
}

export default App;
