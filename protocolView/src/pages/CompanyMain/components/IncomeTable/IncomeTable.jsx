import React, { Component } from 'react';
import { Table } from 'antd';

const data = [{
  key: '1',
  date: '20180529',
  income: 32,
  type: '抖音',
}, {
  key: '2',
  date: '20180530',
  income: 42,
  type: '抖音',
}, {
  key: '3',
  date: '20180601',
  income: 32,
  type: '微信',
}, {
  key: '4',
  date: '20180502',
  income: 32,
  type: '抖音',
}];
export default class DataDisplay extends Component {
  constructor(props) {
    super(props);
    this.state = {

    };
  }
  handleSearch = (selectedKeys, confirm) => () => {
    confirm();
  }

  handleReset = clearFilters => () => {
    clearFilters();
  }

  render() {
    const columns = [{
      title: '日期',
      dataIndex: 'date',
      defaultSortOrder: 'descend',
      sorter: (a, b) => a.date - b.date,
    }, {
      title: '收入',
      dataIndex: 'income',
    }, {
      title: '项目',
      dataIndex: 'type',
      filters: [{
        text: '抖音',
        value: '抖音',
      }, {
        text: '微信',
        value: '微信',
      }],
      onFilter: (value, record) => record.type.indexOf(value) === 0,
    }];
    return (
      <div className="income-table" style={{ marginLeft: '10%', marginRight: '10%' }}>
        <Table
          columns={columns}
          dataSource={data}
          bordered
        //   title={() => 'Header'}
        //   footer={() => 'Footer'}
        />
      </div>
    );
  }
}

// const styles = {

// };
