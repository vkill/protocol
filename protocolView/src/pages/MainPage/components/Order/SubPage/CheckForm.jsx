import React, { Component } from 'react';
import { Input, Row, Col, Table, message } from 'antd';
import * as ORDER_API from '../../../../../utils/OrderApi/apis'

const Search = Input.Search;

export default class Attention extends Component {
  static displayName = 'Attention';
  

  constructor(props) {
    super(props);
    this.state = {
        datalist: [],
    };
  }



  render() {
    const columns = [
        {
            title: '订单号',
            dataIndex: 'orderNumber',
            // key: 'orderNumber',
        },{
            title: '视频id',
            dataIndex: 'videoId',
            // key: 'videoId',
        },{
            title: '下单时间',
            dataIndex: 'time',
            // key: 'orderDate',
        },
        // {
        //     title: '购买类别',
        //     dataIndex: 'type',
        //     // key: 'type',
        // },{
        //     title: '订单数',
        //     dataIndex: 'orderCount',
        //     // key: 'type',
        // },{
        //     title: '点赞/关注数',
        //     dataIndex: 'orderCount',
        //     // key: 'type',
        // },
         {
            title: '总价',
            dataIndex: 'totalPrice',
        }, {
            title: '订单状态',
            dataIndex: 'orderStatus'
        }
    ]
    return (
      <div style={styles.container}>
        <Row style={styles.search}>
            <Col span={3}>
            
            </Col>
            <Col span={18}>
                <Search 
                    placeholder='输入您要查询的视频id或者订单号'
                    onSearch={value => this.handleSearch(value)}
                />
            </Col>
            <Col span={3}>
            </Col>
        </Row>
        <Row style={styles.search}>
            <Col span={3}>
            
            </Col>
            <Col span={18}>
                <Table 
                    columns={columns}
                    bordered
                    dataSource={this.state.datalist}
                />
            </Col>
            <Col span={3}>
            </Col>
        </Row>
      </div>
    );
  }
  /**
     * 搜索的方法
     */
    handleSearch = (value) => {
       if (value!=='' && value.length>15) {
            if (/^[0-9]+$/.test(value)) {
                ORDER_API.search_order(value).then(res =>{
                    const data = res.data;
                    if (data.status === '0') {
                        const list = data.data; 
                        list.forEach((element) => {
                            element.key = element.id;
                          });
                        this.setState({
                            datalist: list
                        })
                    } else {
                        message.error(data.message);
                    }
                });
            }
        } else {
            message.error('请输入正确的订单号');
        }
    }
}

const styles = {
    container: {
      width: '100%',
      height: '300px',
      backgroundColor: '#fff',
    },
    search: {
      marginTop: '3%',
    },
    form: {
      paddingLeft: '5%',
      width: '80%',
    },
  };
