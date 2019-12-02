import ReactMapboxGl, { Layer, Feature } from "react-mapbox-gl";
import { connect } from "react-redux";
import { fetchCars } from "../redux/actions";
import React, { Component } from "react";

const Map = ReactMapboxGl({ 
  accessToken: "pk.eyJ1Ijoid2V6dXNpciIsImEiOiJjazMxZDh5eTgwNTN6M2RxZjV1MnB0N3g2In0.YK5ODFfBnOXYAOGd5XCvvA"
});

class TrackWindow extends Component {
  componentDidMount = () => {
    this.props.fetchCars();
  }
  render(){
    return (
      <div className="track-window">
        <h2 className="track-heading">Tracking</h2>
          <Map
            style="mapbox://styles/mapbox/streets-v11"
            boxZoom={true}
            className="track-mainframe"
            containerStyle={{
              height: "88vh",
              width : "99vw"
            }}
            center={[13.843420, 46.601871]}>
            <Layer
              type="symbol"
              id="points"
              layout={{ "icon-image": "marker-11" }}>
                {this.props.cars.map(car => {
                  return (<Feature type="Point" key={"geo-x" + car.geo.x} coordinates={[car.geo.x, car.geo.y]} />)
                })
                }
            </Layer>
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
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrackWindow);

