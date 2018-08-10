import React, { Component } from 'react';
import Subscribe from './components/Subscribe';

export default class UserPay extends Component {
  static displayName = 'UserPay';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="user-pay-page">
        <Subscribe />
      </div>
    );
  }
}
