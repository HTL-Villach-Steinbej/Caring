import React from "react";
import ReactMapboxGl, { Layer, Feature } from "react-mapbox-gl";

const Map = ReactMapboxGl({ 
  accessToken: "pk.eyJ1Ijoid2V6dXNpciIsImEiOiJjazMxZDh5eTgwNTN6M2RxZjV1MnB0N3g2In0.YK5ODFfBnOXYAOGd5XCvvA"
});

function TrackWindow(props) {
  return (
    <div className="track-window">
      <h2 className="track-heading">Tracking</h2>
      <div>
        <Map
          style="mapbox://styles/mapbox/streets-v9"
          containerStyle={{
            height: "100vh",
            width: "100vw"
          }}
          center={[13.843420, 46.601871]}>
          <Layer
            type="symbol"
            id="marker"
            layout={{ "icon-image": "marker-15" }}>
            <Feature coordinates={[13.843420, 46.601871]} />
          </Layer>
        </Map>
      </div>
    </div>
  );
}

export default TrackWindow;
