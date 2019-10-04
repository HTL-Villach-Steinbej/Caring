import React from "react";

function Toolbar(){
    return(
        <div className="toolbar">
            <div className="toolbar-left-item">
                <p className="toolbar-item-text">Home</p>
            </div>
            <div className="toolbar-left-item">
                <p className="toolbar-item-text">Book</p>
            </div>
            <div className="toolbar-left-item">
                <p className="toolbar-item-text">Info</p>
            </div>
            <div className="toolbar-left-item">
                <p className="toolbar-item-text">Problems</p>
            </div>
            <div className="toolbar-left-item">
                <p className="toolbar-item-text">About</p>
            </div>
        </div>
    )
}

export default Toolbar;