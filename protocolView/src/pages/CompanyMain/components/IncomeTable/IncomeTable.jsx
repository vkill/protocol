import React, { Component } from 'react';
import { Table } from 'antd';

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
    const datalist = this.props.data;
    datalist.forEach((element) => {
      element.key = element.id;
    });
    // console.log(datalist);
    const columns = [{
      title: '日期',
      dataIndex: 'date',
      defaultSortOrder: 'descend',
      sorter: (a, b) => a.date - b.date,
    }, {
      title: '收入',
      dataIndex: 'dayIncome',
    }, {
      title: '项目',
      dataIndex: 'projectID',
      filters: [{
        text: '抖音',
        value: '抖音',
      }, {
        text: '微信',
        value: '微信',
      }],
      onFilter: (value, record) => record.projectID.indexOf(value) === 0,
    }];
    return (
      <div className="income-table" style={{ marginLeft: '10%', marginRight: '10%' }}>
        <Table
          columns={columns}
          dataSource={datalist}
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
