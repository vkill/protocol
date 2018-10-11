import React, { Component } from 'react';
import { Row } from 'antd';


export default class QRCode extends Component {
    static displayName = 'QRCode';

    constructor(props) {
        super(props);
        this.state = {
            payTime: 300
        };
    }
    render() {
        const payData = this.props.payData;
        const baseImg = `data:image/png;base64,${payData.order_qrcode}`;
        // console.log(payData);

        return (
            <div style = {styles.container}>
                <Row>
                    <div>
                        <p style={styles.orderNo}>订单号：{payData.order_no}</p>
                    </div>
                </Row>
                <Row>
                    <div>
                        <p style={styles.orderNo}>支付形式：支付宝</p>
                    </div>
                </Row>
                <Row>
                    <div>
                        <p style={styles.orderNo}> 付款倒计时: {this.state.payTime} 秒</p>
                    </div>
                </Row>
                <Row>
                    <div style={styles.qrcode}>
                        <img src={baseImg}/>
                    </div>
                </Row>
            </div>
        )
    }


    componentDidUpdate() {
        if (this.state.payTime <= 0) {
            // 停止
            clearInterval(this.time);
        }
    }

    componentDidMount() {
        this.time = setInterval(() => this.setState({payTime: (this.state.payTime-1)}),1000);
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
    orderNo: {
        paddingTop: '20px',
        textAlign: 'center',
        fontSize: '20px',
        color: 'red',
    },
    qrcode: {
        paddingBottom: '20px',
        textAlign: 'center',
    }
}
