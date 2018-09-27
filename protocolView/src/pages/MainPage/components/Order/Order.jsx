import React, { Component } from 'react';
import { Tabs, Icon } from 'antd';
import OrderForm from './SubPage/OrderForm'

import * as ORDER_API from '../../../../utils/OrderApi/apis'


export default class Order extends Component {
    static displayName = 'Order';

    constructor(props) {
        super(props);
        this.state = {
            pro_list:[],
        };
    }
    render() {
        const TabPane = Tabs.TabPane;
        return (
            <div style = {styles.container}>
                <Tabs defaultActiveKey='1'>
                    <TabPane tab={<span><Icon type='shopping-cart'/>下单</span>} key = '1'>
                        <OrderForm type_list={this.state.pro_list} get_goods_list={this.get_good_list.bind(this)}/>
                    </TabPane>
                    <TabPane tab={<span><Icon type='search'/>查单</span>} key = '2'>
                    2</TabPane>
                    {/* <TabPane tab='Tab 1' key = '3'>3</TabPane> */}

                </Tabs>
            </div>
        )
    }
    componentDidMount() {
        this.get_pro_list();
    }
    /**
     * 获取项目列表
     */
    get_pro_list() {
        ORDER_API.get_pro_type().then((response) => {
            const data = response.data; 
            this.setState({
                pro_list: data.order_type.type_list
            });
            // console.log(data.order_type.type_list)
        });
    }
    /**
     * 获取商品的列表
     * @param {符号}} sign 
     */
    get_good_list(sign) {
        
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


// import React, { Component } from 'react';


// export default class Order extends Component {
//     static displayName = 'Order';

//     constructor(props) {
//         super(props);
//         this.state = {};
//     }
//     render() {
        
//         return (
//             <div style = {styles.container}>
//                 order
//             </div>
//         )
//     }
// }

// const styles = {
//     container: {
//         width:'100%',
//         height:'100%',
//         backgroundColor: '#fff',
//         boxShadow:'5px 5px 3px #888888',
//         marginBottom: '3%',
//     },
// }