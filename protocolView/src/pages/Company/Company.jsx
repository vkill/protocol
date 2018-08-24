import { message } from 'antd';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
import CreativeLogin from './components/CreativeLogin';
import { companyLogin } from '../../utils/apis';

import * as companyInfoActionsFromOtherFile from '../../actions/companyinfo.js';

class Company extends Component {
  static displayName = 'Company';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="company-page">
        <CreativeLogin login={this.companyLoginHandler.bind(this)} />
      </div>
    );
  }

  /**
   * 工作室登陆的方法
   */
  companyLoginHandler(values) {
    // console.log(values);
    // 登陆情况
    companyLogin(values).then((res) => {
      const data = res.data;
      // console.log(data);
      if (data.success) {
        const account = data.account;
        // console.log(account);
        // {id: 1, account: "company1", password: "", sign: "COMcompany160"}
        // const companySign = account.sign;
        const companyinfo = this.props.companyinfo;
        const actions = this.props.companyInfoActions;
        companyinfo.companyinfo = account;
        actions.update(companyinfo);
        message.success('登陆成功');
        this.props.history.push('/companyMain');
      } else {
        message.error('帐号密码错误');
      }
    });
  }
}

// -----------------------------redux-react绑定-----------------------------------
function mapStateToProps(state) {
  return {
    companyinfo: state.companyinfo,
  };
}
function mapDispatchToProps(dispatch) {
  return {
    companyInfoActions: bindActionCreators(companyInfoActionsFromOtherFile, dispatch),
  };
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Company);
