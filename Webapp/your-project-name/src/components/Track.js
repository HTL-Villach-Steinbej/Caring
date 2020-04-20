import ReactMapboxGl, { ZoomControl, RotationControl } from "react-mapbox-gl";
import { connect } from "react-redux";
import { fetchCars } from "../redux/actions";
import React, { Component } from "react";
import { Marker, Popup } from "react-mapbox-gl";
import Header from "./Header";

const Map = ReactMapboxGl({
  accessToken:
    "pk.eyJ1Ijoid2V6dXNpciIsImEiOiJjazMxZDh5eTgwNTN6M2RxZjV1MnB0N3g2In0.YK5ODFfBnOXYAOGd5XCvvA"
});

class TrackWindow extends Component {
  componentDidMount = () => {
    this.props.fetchCars();
  };
  render() {
    return (
      <div className="track-window">
        <Header component="Tracking"/>
        <Map
          style="mapbox://styles/mapbox/streets-v11"
          boxZoom={true}
          className="track-mainframe"
          containerStyle={{
            height: "92vh",
            width: "99vw"
          }}
          center={[13.84342, 46.601871]}
        >
          <ZoomControl />
          <RotationControl />
          {this.props.cars.map(car => {
            return (
              <div>
                <Marker 
                  key={car.location.x} 
                  coordinates={[car.location.x, car.location.y]} 
                  anchor="bottom"
                  onClick={() => {return (
                    <Popup
                    coordinates={[car.location.x, car.location.y]}
                    offset={{
                      'bottom-left': [12, -38],  'bottom': [0, -38], 'bottom-right': [-12, -38]
                    }}>
                    <h1>Popup</h1>
                  </Popup>)}}>
                  <img alt="marker" src="marker-2.png" width="20px"/>
                </Marker>
                
              </div>
            );
          })}
        </Map>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    cars: state.cars
  };
};

const mapDispatchToProps = {
    fetchCars
};
export default connect(mapStateToProps, mapDispatchToProps)(TrackWindow);
