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
    const datalist = this.props.datalist;
    console.log(datalist);
    datalist.forEach((element) => {
      element.key = element.orderId;
    });
    // console.log(datalist);
    const columns = [
        {
            title: '订单号',
            dataIndex: 'orderId',
        },
        {
            title: '下单时间',
            dataIndex: 'makeOrderDate'
        },
        {
            title: '项目',
            dataIndex: 'projectType',
        },
        {
            title: '项目类型',
            dataIndex: 'operationType'
        },
        {
            title: '数量',
            dataIndex: 'count',
        },
        {
            title: '单价',
            dataIndex: 'price',
        },
        {
            title: '总价',
            dataIndex: 'totalPrice',
        },
        {
            title: '操作信息',
            dataIndex: 'operationData',
        },
        {
            title: '订单状态',
            dataIndex: 'orderStatus',
        },
        {
            title: '订单操作',
            dataIndex: 'operaOrder',
        }
    // {
    //   title: '日期',
    //   dataIndex: 'date',
    //   defaultSortOrder: 'descend',
    //   sorter: (a, b) => a.date - b.date,
    // }, {
    //   title: '收入',
    //   dataIndex: 'dayIncome',
    // }, 
    // {
    //   title: '项目',
    //   dataIndex: 'projectID',
    //   filters: [{
    //     text: '抖音',
    //     value: '抖音',
    //   }, {
    //     text: '微信',
    //     value: '微信',
    //   }],
    //   onFilter: (value, record) => record.projectID.indexOf(value) === 0,
    // }
    ];
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
