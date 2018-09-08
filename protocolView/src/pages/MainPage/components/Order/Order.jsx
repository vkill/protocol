import React, { Component } from 'react';
import { Tabs, Icon } from 'antd';
import OrderForm from './SubPage/OrderForm'


export default class Order extends Component {
    static displayName = 'Order';

    constructor(props) {
        super(props);
        this.state = {};
    }
    render() {
        const TabPane = Tabs.TabPane;
        return (
            <div style = {styles.container}>
                <Tabs defaultActiveKey='1'>
                    <TabPane tab={<span><Icon type='shopping-cart'/>下单</span>} key = '1'>
                        <OrderForm/>
                    </TabPane>
                    <TabPane tab={<span><Icon type='search'/>查单</span>} key = '2'>
                    2</TabPane>
                    {/* <TabPane tab='Tab 1' key = '3'>3</TabPane> */}

                </Tabs>
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