import * as actionTypes from '../constants/companyinfo';

export function update(data) {
  return {
    type: actionTypes.COMPANY_UPDATE,
    data,
  };
}

export function rm() {
  return {
    type: actionTypes.COMPANY_RM,
  };
}
