import { combineReducers } from 'redux';
import userinfo from './userinfo';
import companyinfo from './company';

export default combineReducers({
  userinfo,
  companyinfo,
});
