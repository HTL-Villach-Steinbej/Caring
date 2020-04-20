
import { myFirebase } from "../../firebase/firebase";
import { db } from "../../firebase/firebase";

export const LOGIN_REQUEST = "LOGIN_REQUEST";
export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGIN_FAILURE = "LOGIN_FAILURE";

export const LOGOUT_REQUEST = "LOGOUT_REQUEST";
export const LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
export const LOGOUT_FAILURE = "LOGOUT_FAILURE";

export const VERIFY_REQUEST = "VERIFY_REQUEST";
export const VERIFY_SUCCESS = "VERIFY_SUCCESS";

export const USER_REQUEST = "USER_REQUEST";
export const USER_SUCCESS = "USER_REQUEST";

const requestLogin = () => {
  return {
    type: LOGIN_REQUEST
  };
};

const receiveLogin = user => {
  return {
    type: LOGIN_SUCCESS,
    user
  };
};

const loginError = () => {
  return {
    type: LOGIN_FAILURE
  };
};

const requestLogout = () => {
  return {
    type: LOGOUT_REQUEST
  };
};

const receiveLogout = () => {
  return {
    type: LOGOUT_SUCCESS
  };
};

const logoutError = () => {
  return {
    type: LOGOUT_FAILURE
  };
};

const verifyRequest = () => {
  return {
    type: VERIFY_REQUEST
  };
};

const verifySuccess = () => {
  return {
    type: VERIFY_SUCCESS
  };
};

const requestCurentUser = () => {
  return {
    type: USER_REQUEST
  };
}

const currentUserSuccess = (user) => {
  return{
    type: USER_SUCCESS,
    user
  };
}

export const loginUser = (email, password) => dispatch => {
  dispatch(requestLogin());
  myFirebase
    .auth()
    .signInWithEmailAndPassword(email, password)
    .then(user => {
      dispatch(receiveLogin(user));
    })
    .catch(error => {
      console.log(error);
      dispatch(loginError());
    });
};

export const registerUser = (email, password, username) => dispatch => {
  dispatch(requestLogin());
  myFirebase
    .auth()
    .createUserWithEmailAndPassword(email, password)
    .then(user => {
      dispatch(receiveLogin(user));
      let uid = myFirebase.auth().currentUser.uid;

      //Firebase DB
      db.collection("users").doc(uid).set({
        firstname: "",
        lastname: "",
        username: username,
        registeredSince: Date.now().toString(),
        email: email,
        uid: uid,
        paymethod: ""
      });

      //Webservice DB
      fetch('http://localhost:8080/Caring_WebService/Caring/users', { 
        method: 'POST',
        data: {
          id: uid
        }
      })
      .then(function(response) {
        return response.json()
      }).then(function(body) {
        console.log(body);
      });
    })
    .catch(error => {
      console.log(error);
      dispatch(loginError());
    });
}

export const logoutUser = () => dispatch => {
  dispatch(requestLogout());
  myFirebase
    .auth()
    .signOut()
    .then(() => {
      dispatch(receiveLogout());
    })
    .catch(error => {
      console.log(error);
      dispatch(logoutError());
    });
};

export const verifyAuth = () => dispatch => {
  dispatch(verifyRequest());
  myFirebase
    .auth()
    .onAuthStateChanged(user => {
      if (user !== null) {
        dispatch(receiveLogin(user));
      }
      dispatch(verifySuccess());
    });
};

export const getCurrentUser = () => dispatch => {
  dispatch(requestCurentUser());
  let docRef = db.collection("users").doc(myFirebase.auth().currentUser.uid);
  docRef.get().then(() => {
    dispatch(currentUserSuccess(docRef.data));
  }).catch((error) => {
    console.log(error);
  });
}