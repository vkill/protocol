/*
 *
 * profile actions
 *
 */

import {
  USER_PROFILE_REQUEST,
  USER_PROFILE_FAILURE,
  USER_PROFILE_SUCCESS,
  USER_PROFILE_REMOVE,
} from './constants';
// import { getUserProfile } from '../../api';

const userProfileRequest = () => {
  return {
    type: USER_PROFILE_REQUEST,
    isLoading: true,
  };
};

const userProfileSuccess = (payload) => {
  return {
    type: USER_PROFILE_FAILURE,
    isLoading: false,
    payload,
  };
};

const userProfileFailure = (payload) => {
  return {
    type: USER_PROFILE_SUCCESS,
    isLoading: false,
    payload,
  };
};

const profileRemove = (payload) => {
  return {
    type: USER_PROFILE_REMOVE,
    // isLoading: false,
    payload,
  };
};

/**
 * 返回用户信息存到Profile
 * @param {用户信息} params
 */
export const userProfile = (params) => {
  // console.log('调用userprofile');
  return async (dispatch) => {
    dispatch(userProfileRequest());
    try {
      // console.log(`UserProfile文件夹：${params}`);
      // console.log(params);
      // console.log(params);
      dispatch(userProfileSuccess(params));
    } catch (error) {
      dispatch(userProfileFailure(error));
    }
  };
};

/**
 * 移除userProfile
 */
export const userProfileRemove = () => {
  return async (dispatch) => {
    try {
      // console.log(`UserProfile文件夹：${params}`);
      // console.log(params);
      // console.log(123);
      dispatch(profileRemove({ a: 'b' }));
    } catch (error) {
      // console.log(456);
      dispatch(userProfileFailure(error));
    }
  };
};
