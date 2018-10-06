import React, { Component } from 'react';
import OrderData from './components/OrderData';
import QRCode from './components/QRCode';
import BasicNotFound from './components/BasicNotFound';
import { message } from 'antd'
// import Footer from './components/Footer';
import NameBar from './components/NameBar';
import Footer from '../MainPage/components/NormalFooter';
import * as ORDER_API from '../../utils/OrderApi/apis';




export default class PayPage extends Component {
  static displayName = 'PayPage';

  constructor(props) {
    super(props);
    this.state = {
        orderData: {},
        payData: {},
        isPay: false,
    };
  }

  render() {
    const getData = this.props.location.state;
    return (
      <div style={styles.body}>
        <div style={styles.container}>
            {
                getData
                ?
                <div>
                    <NameBar name= '欢迎使用' />
                    <OrderData
                        orderData = {this.props.location.state.data} 
                    />
                    {
                        !this.state.isPay 
                        ? <QRCode
                            payData = {this.props.location.state.payData} 
                        />
                        :<NameBar name= '付款成功' />
                    }
                    <Footer />
                </div>
                :
                <div>
                    <BasicNotFound/>
                </div>
            }
        </div>
      </div>
    );
  }


  componentDidMount() {
    // console.log(this.props.location.state.data); 
    if (!this.props.location.state) {
        return;
    }

    const orderNo = this.props.location.state.data.orderNumber;
    // console.log(orderNo);
    // this.isPay(orderNo);
    this.interval = setInterval(() => this.isPay(orderNo),5000);
  }

  shouldComponentUpdate() {
      if (this.state.isPay) {
          // 成功付款，清楚计时器
          clearInterval(this.interval);
      }
      return this.state.isPay;
  }

  // 是否成功付账
  isPay(orderNo) {
    ORDER_API.pay_check(orderNo).then(response => {
        // console.log(response);
        const data = response.data;
        if (data.status === '0') {
            this.setState({
                isPay: true,
            });
            message.success('付款成功');
        } else {
            message.info('尚未付款');
        }
    });
  }




}


const styles = {
    body: {
      backgroundImage: `url(${require('./images/bg.jpg')})`,
      width: '100%',
      height: '100%',
    },
    container: {
      marginTop: '5%',
      marginLeft: '5%',
      marginRight: '5%',
      marginBottom: '5%',
    },
  };
