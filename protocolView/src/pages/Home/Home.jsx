import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
import { Feedback } from '@icedesign/base';

import MainBlock from './components/MainBlock/AblityItems';
import * as userInfoActionsFromOtherFile from '../../actions/userinfo.js';


class Home extends Component {
  static displayName = 'Home';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div>
        <MainBlock />
      </div>
    );
  }

  componentDidMount() {
    const userinfo = this.props.userinfo;
    if (userinfo.userid == null) {
      Feedback.toast.error('尚未登录，请登录!');
      this.props.history.push('/login');
    } else {
      console.log('初始化');
    }
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
)(Home);
