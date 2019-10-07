import React from "react";

function Toolbar(){
    return(
        <div className="toolbar">
            <div className="toolbar-left-item" onClick="">
                <p className="toolbar-item-text">Home</p>
            </div>
            <div className="toolbar-left-item" onClick="">
                <p className="toolbar-item-text">Book</p>
            </div>
            <div className="toolbar-left-item" onClick="">
                <p className="toolbar-item-text">Info</p>
            </div>
            <div className="toolbar-left-item" onClick="">
                <p className="toolbar-item-text">Problems</p>
            </div>
            <div className="toolbar-left-item" onClick="">
                <p className="toolbar-item-text">About</p>
            </div>
            <div className="toolbar-right-item">
                <img
                    src="./icons/notification_icon.svg"
                    id="notification-logo"
                    alt="Notifications Icon"
                />
            </div>
        </div>
    )
}

export default Toolbar;