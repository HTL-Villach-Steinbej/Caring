import React from "react";
import { Map, GoogleApiWrapper } from "google-maps-react";

const mapStyles = {
  width: "100%",
  height: "100%"
};

function TrackWindow(props) {
  return (
    <div className="rent-window">
      <h2 className="track-heading">Tracking</h2>
      <div className="track-frame">
        <Map
          google={props.google}
          zoom={8}
          style={mapStyles}
          initialCenter={{ lat: 47.444, lng: -122.176 }}
        />
      </div>
    </div>
  );
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyBXbw4ILxWmD1-NNYAa-5IqY9apJqbums0"
})(TrackWindow);
