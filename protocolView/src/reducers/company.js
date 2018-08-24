import * as actionTypes from '../constants/companyinfo';

const initialState = {};

export default function companyInfo(state = initialState, action) {
  switch (action.type) {
    case actionTypes.COMPANY_UPDATE:
      return action.data;
    case actionTypes.COMPANY_RM:
      state = {};
      return state;
    default:
      return state;
  }
}
