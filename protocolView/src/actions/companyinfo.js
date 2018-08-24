import * as actionTypes from '../constants/companyinfo';

export function update(data) {
  return {
    type: actionTypes.COMPANY_UPDATE,
    data,
  };
}
