import axios from 'axios';

const indexUrl = '/api/index';
const userUrl = '/api/user';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// axios.defaults.transformRequest['Content-Type'] = 'application/x-www-form-urlencoded';

export const helloWorld = () => axios.get(`${indexUrl}/helloWorld`);

/**
 * 登陆的axios请求
 * @param {*} body 请求数据字段
 */
export const login = body => axios.post(`${userUrl}/login`, {
  account: body.account,
  pwd: body.pwd,
});

/**
 * 注册请求字段
 * @param {注册定参数}} body
 */
export const register = body => axios.post(`${userUrl}/regist`, {
  account: body.account,
  pwd: body.pwd,
});
