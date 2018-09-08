import React, { Component } from 'react';
import { List } from 'antd';
import Item from 'antd/lib/list/Item';

export default class Attention extends Component {
  static displayName = 'Attention';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const data =['1','2','3','4']
    return (
      <div style={styles.container}>
        <List
            header={<div>注意事项</div>}
            footer = {<div>联系我们</div>}
            bordered
            dataSource = {data}
            renderItem={item => (<List.Item>{item}</List.Item>)}
        >
        </List> 
      </div>
    )
  }
}

const styles = {
    container: {
        width:'100%',
        height:'100%',
        backgroundColor: '#fff',
        boxShadow:'5px 5px 3px #888888',
        marginBottom: '3%',
    },
}
