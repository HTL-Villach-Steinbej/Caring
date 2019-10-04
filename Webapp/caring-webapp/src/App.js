import React from 'react';
import Toolbar from './components/Toolbar';
import MainFrame from './components/MainFrame';
import BottomBar from './components/BottomBar';
import "./style.css";

function App() {
  return (
    <div className="App">
      <div className="header">
        <Toolbar />
      </div>
      <div className="body">
        <MainFrame />
      </div>
      <div className="footer">
        <BottomBar />
      </div>
    </div>
  );
}

export default App;
