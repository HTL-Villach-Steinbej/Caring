import { FETCH_PEERS, SHOW_DIALOG_COMPONENT, HIDE_DIALOG } from "./actionTypes";

export const fetchPeers = () => ({
  type: FETCH_PEERS
});
export const showDialogComponent = (component) => ({
  type: SHOW_DIALOG_COMPONENT,
  component
});
export const hideDialog = () => ({
  type: HIDE_DIALOG
});