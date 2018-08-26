import React, { Component } from 'react';
import Register from './components/Register';

import * as API from '../../utils/apis';

export default class RegisterPage extends Component {
  static displayName = 'RegisterPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="register-page-page">
        <Register registeHandle={this.registHandle.bind(this)} />
      </div>
    );
  }

  /**
   * 注册
   * @param {values: {username: "abcs", email: "qq@qq.com", passwd: "11111111", rePasswd: "11111111"} values
   */
  registHandle(values) {
    console.log(values);
    API.register(values).then((response) => {
      console.log(response.data);
      // 传回标示
      // TODO
    });


    const flag = true;
    return flag;
  }
}
