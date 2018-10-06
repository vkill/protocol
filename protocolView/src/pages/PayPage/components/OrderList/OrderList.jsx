import React, { Component } from 'react';
import { List, Icon } from 'antd';



export default class Attention extends Component {
  static displayName = 'Attention';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const data = this.props.orderData;
    return (
      <div>
        <List
          header={<div><p style={styles.font}><Icon type="exclamation-circle" theme="filled" />请注意核对账单信息</p></div>}
          footer={<div><p style={styles.font}><Icon type="exclamation-circle" theme="filled" />订单信息错误概不退款</p></div>}
          bordered
          dataSource={data}
          renderItem={item => (<List.Item>{item}</List.Item>)}
        />
      </div>
    );
  }
}

const styles = {
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#fff',
    boxShadow: '5px 5px 3px #888888',
    marginBottom: '2%',
  },
  font:{
    fontStyle:'italic',
    color: '#F00',
    fontSize: '20px',
  }
};
