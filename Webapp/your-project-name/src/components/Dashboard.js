import React from "react";
import { connect } from "react-redux";
import { fetchRents } from "../redux/actions";
import Header from "./Header";
import { myFirebase } from "../firebase/firebase";

class DashboardWindow extends React.Component {
  componentDidMount = () => {
    this.props.fetchRents();
  };
  render() {
    let u = null;
    let user = {
      Username: "Wezusir",
      Firstname: "Jonathan",
      Lastname: "Steinberger",
      registered_since: "20.02.2019",
      postcode: 9640,
      city: "Kötschach",
      adress: "Kötschach 7",
      country: "Austria",
      payment_method: "PayPal",
    };
    let user2 = {
      Username: "Wezusir2",
      Firstname: "Josef",
      Lastname: "Stein",
      registered_since: "20.02.2019",
      postcode: 9640,
      city: "Kötschach",
      adress: "Kötschach 283",
      country: "Austria",
      payment_method: "",
    };
    {myFirebase.auth().currentUser.uid === "DkTycfHAhYfYAgzOkUXiopMqcfn1" ? u = user : u = user2}
    return (
      <div className="dashboard-window">
        <Header component="Dashboard" />
        <div className="dashboard">
          <div className="dashboard-general">
            <h3 class="sub-dashboard-heading">General</h3>
            <div className="form-dashboard">
              <form>
                <label className="form-label">Username: </label>
                <p className="form-text">{u.Username}</p>
                <br></br>
                <label className="form-label">Firstname: </label>
                <p className="form-text">{u.Firstname}</p>
                <br></br>
                <label className="form-label">Lastname: </label>
                <p className="form-text">{u.Lastname}</p>
                <br></br>
                <label className="form-label">Registered since: </label>
                <p className="form-text">{u.registered_since}</p>
              </form>
            </div>
            <div className="form-dashboard-right">
              <form>
                <label className="form-label">Postcode: </label>
                <p className="form-text">{u.postcode}</p>
                <br></br>
                <label className="form-label">City: </label>
                <p className="form-text">{u.city}</p>
                <br></br>
                <label className="form-label">Street: </label>
                <p className="form-text">{u.adress}</p>
                <br></br>
                <label className="form-label">Country: </label>
                <p className="form-text">{u.country}</p>
              </form>
            </div>
          </div>
          <div className="dashboard-payment">
            <h3 className="sub-dashboard-heading">Payment</h3>
            <label className="paymethod-label">Current Paymentmethod: </label>
            <p className="form-text">{u.payment_method}</p>
          </div>
          <div className="dashboard-history">
            <h3 class="sub-dashboard-heading">Rent History</h3>
            <table>
              <thead>
                <th>Username</th>
                <th>Car</th>
                <th>Location</th>
                <th>Date</th>
                <th>Options</th>
              </thead>
              <tbody>
                {(this.props.rents) ? (this.props.rents.map((rent) => { 
                  return (
                    <tr>
                      <td>{rent.uid}</td>
                      <td>{rent.fid}</td>
                      <td>{rent.zid}</td>
                      <td>{rent.von}</td>
                      <td>
                        <img
                          src="info.png"
                          alt="info"
                          height="20px"
                          width="20px"
                        ></img>
                      </td>
                    </tr>
                  );})
                )
                : "No rents yet"}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    rents: state.rents
  };
};
const mapDispatchToProps = {
  fetchRents
};
export default connect(mapStateToProps, mapDispatchToProps)(DashboardWindow);
