import React, { Component } from 'react';
import OrderList from './OrderList';

export default class OrderData extends Component {
    static displayName = 'OrderData';

    constructor(props) {
        super(props);
        this.state = {

        };
    }
    render() {
        const orderData = this.handleFormat(this.props.orderData);
        // this.handleFormat(orderData);
        return (
            <div style = {styles.container}>
                <OrderList orderData = {orderData}/>
            </div>
        )
    }

    handleFormat(orderData) {
        // console.log(orderData);
        const goodsType = `商品类型：${orderData.goodsType}`;
        const orderCount = `商品数量：${orderData.orderCount}`;
        const orderNumber = `订单号码：${orderData.orderNumber}`;
        const videoId = `订单视频：${orderData.videoId}`;
        const orderTime = `下单时间：${orderData.time}`;
        const totalPrice = `订单总价：${orderData.totalPrice}`;
        return [goodsType, orderCount, orderNumber, videoId, orderTime, totalPrice];
    }
}

const styles = {
    container: {
        marginTop: '20px',
        width:'100%',
        height:'100%',
        backgroundColor: '#fff',
        boxShadow:'5px 5px 3px #888888',
        marginBottom: '3%',
    },
}
