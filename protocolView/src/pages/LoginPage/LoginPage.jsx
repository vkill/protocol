import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
import Login from './components/Login';

import * as API from '../../utils/apis';
import * as userInfoActionsFromOtherFile from '../../actions/userinfo.js';
import { Feedback } from '../../../node_modules/@icedesign/base';

class LoginPage extends Component {
  static displayName = 'LoginPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="login-page-page">
        <Login loginHandle={this.loginHandle.bind(this)} />
      </div>
    );
  }

  componentDidMount() {
    const userinfo = this.props.userinfo;
    if (userinfo.userid != null) {
      this.props.history.push('/');
    }
  }

  loginHandle(account, password) {
    // 请求登录
    const loginBody = {
      account,
      pwd: password,
    };
    API.login(loginBody).then((response) => {
      const data = response.data;
      // 假设登录成功的flag
      const flag = data.success;
      // console.log(flag);
      if (flag != null) {
        // 登录成功
        // console.log(data);
        const userid = data.registId
        const userinfo = this.props.userinfo;
        const actions = this.props.userInfoActions;
        userinfo.userid = userid;
        actions.update(userinfo);
        this.props.history.push('/');
      } else {
        Feedback.toast.error('帐号密码错误');
        // console.log('LoginPage', account, password);
      }
    });
  }
}

// -----------------------------redux-react绑定-----------------------------------
function mapStateToProps(state) {
  return {
    userinfo: state.userinfo,
  };
}
function mapDispatchToProps(dispatch) {
  return {
    userInfoActions: bindActionCreators(userInfoActionsFromOtherFile, dispatch),
  };
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginPage);
