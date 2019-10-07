import React from "react";
import UpperBody from "./UpperBody";
import LowerBody from "./LowerBody";

function MainFrame() {
  return(
    <div className="mainframe">
        <UpperBody />
        <LowerBody />
        <LowerBody />
        <LowerBody />
        <LowerBody />
        <LowerBody />
    </div>
  );
}
export default MainFrame;