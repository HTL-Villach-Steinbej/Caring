import { call, put, takeLatest } from "redux-saga/effects";
import { FETCH_PEERS, FETCH_PEERS_SUCCESS } from "./actionTypes";
import { getPeers } from "./mockData";

const HOST_NAME = "http://192.168.211.175:8080/";
const MOCK_MODE = true;

const get = url =>
  fetch(url)
    .then(response => response.json())
    .catch(e => console.log(e));

export function* fetchPeers(action) {
  try {
    let data = [];
    if (MOCK_MODE) {
      data = getPeers();
    } else {
      let peers = yield call(get, HOST_NAME + "/peers");

      for (let i = 0; i < peers.length; i++) {
        let peerName = peers[i];
        let incoming = yield call(
          get,
          HOST_NAME + "/incomingpeers/" + peerName
        );
        let outgoing = yield call(
          get,
          HOST_NAME + "/outgoingpeers/" + peerName
        );
        for (let j = 0; j < outgoing.length; j++) {
          outgoing[j].updates = Math.floor(Math.random() * 10);
          outgoing[j].latency = Math.floor(Math.random() * 150) + 10;
          outgoing[j].avgLatency = Math.floor(Math.random() * 150) + 10;
        }
        data.push({
          label: peerName,
          incomingPeers: incoming,
          outgoingPeers: outgoing
        });
      }
    }

    yield put({ type: FETCH_PEERS_SUCCESS, payload: { data } });
  } catch (ex) {
    console.log(ex);
  }
}

export function* fetchPeersSaga(params) {
  yield takeLatest(FETCH_PEERS, fetchPeers);
}
