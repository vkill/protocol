import React, { Component } from 'react';
import { Button } from 'antd';

export default class DataDisplay extends Component {
  constructor(props) {
    super(props);
    this.state = {

    };
  }
  render() {
    return (
      <div className="logout" style={{ marginLeft: '10%', marginRight: '10%' }}>
        <Button type="primary" style={{ marginRight: '40%', marginLeft: '20%' }}><a href="/">刷单主页</a></Button>
        <Button type="primary" onClick={this.logout.bind(this)}>退出登陆</Button>
      </div>
    );
  }
  logout() {
    this.props.logout();
    // console.log('logout');
  }
}
