import React, { Component } from 'react';
import Register from './components/Register';

import * as API from '../../utils/apis';
import { Feedback } from '../../../node_modules/@icedesign/base';
import { message } from 'antd';

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
    // console.log(values);
    API.register(values).then((response) => {
      console.log(response.data);
      const data = response.data;
      // 传回标示
      // TODO
      if (data.success) {
        message.success(data.message);
        this.props.history.push('/');
      } else {
        message.error(data.message);
      }
      return data.success;
    });
  }
}
