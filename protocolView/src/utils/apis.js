import axios from 'axios';

const indexUrl = '/api/index';
const userUrl = '/api/user';
const orderUrl = '/api/order';
const companyUrl = 'api/company';
const companyIncomeUrl = '/api/companyIncome';
const orderTypeUrl = 'api/orderType';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// axios.defaults.transformRequest['Content-Type'] = 'application/x-www-form-urlencoded';

export const helloWorld = () => axios.get(`${indexUrl}/helloWorld`);


// ============================= 登陆注册部分api =============================
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
  account: body.username,
  pwd: body.passwd,
  email: body.email,
});

// ============================= 用户获取操作列表 =============================

/**
 * 根据项目名字获取操作类型
 * @param {projectName:'abc'}} body
 */
export const getOperationList = body => axios.post(`${orderTypeUrl}/getTypeByName`, {
  projectName: body.projectName,
});


// ============================= 下订单部分api =============================
/**
 * 下订单
 * @param {body: {type:'typeID', dyUID:'x', dyVedio:'a', count: '100'}}} body
 */
export const dyOrder = body => axios.post(`${orderUrl}/dyOrder`, {
  type: body.type,
  dyUID: body.dyUID,
  dyVedio: body.dyVedio,
  count: body.count,
});

/**
 * 用于发送获取用户订单的api
 * @param {userid:'userid'} body
 */
export const getUserOrder = body => axios.post(`${orderUrl}/getOrderList`, {
  usersign:body.userid,
});

// ============================= 充值服务api =============================

// ============================= 工作室api =============================

export const companyLogin = body => axios.post(`${companyUrl}/login`, {
  account: body.name,
  password: body.passwd,
});
/**
 * 请求收入列表
 * @param {里面有companyid: 'company1'}} body
 */
export const getCompanyIncomeList = body => axios.post(`${companyIncomeUrl}/getIncome`, {
  companyid: body.companyid,
});
