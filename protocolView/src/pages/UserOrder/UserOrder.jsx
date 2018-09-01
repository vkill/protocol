import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
import OrderTable from './components/OrderTable';
import { message } from 'antd';

import * as API from '../../utils/apis' 
import * as userInfoActionsFromOtherFile from '../../actions/userinfo.js';

class UserOrder extends Component {
  static displayName = 'UserOrder';

  constructor(props) {
    super(props);
    this.state = {
      orderlist: []
    };
  }

  render() {
    return (
      <div>
      {
        (this.state.orderlist.length > 0) ? <OrderTable datalist={this.state.orderlist} /> : '数据为空' 
      }
      </div>
    )
  }

  componentDidMount() {
    this.isLogin();
    this.getOrderList();
  }

  getOrderList() {
    const userid = this.props.userinfo.userid;
    const body = {};
    body.userid = 'abc';
    API.getUserOrder(body).then((res) => {
      console.log(res);
      const data = res.data.data;
      this.setState({
        orderlist: data
      })

    });
  }

  isLogin = () => {
    const userinfo = this.props.userinfo;
    if (userinfo.userid == null) {
      // Feedback.toast.error('尚未登录，请登录!');
      message.error('尚未登录，请登录');
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
)(UserOrder);
