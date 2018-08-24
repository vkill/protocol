import React, { Component } from 'react';
import CreativeLogin from './components/CreativeLogin';

export default class Company extends Component {
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
    console.log('a', values);
    // 登陆情况
  }
}
