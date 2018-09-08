import React, { Component } from 'react';

import { Form, Select, Input, } from 'antd';

const FormItem = Form.Item;
const Option = Select.Option;

class OrderForm extends Component {
    static displayName = 'OrderForm';

    constructor(props) {
        super(props);
        this.state = {
            commodity: [],
            price: 0,
            orderCount: 0,
        };
    }
    render() {
        const { getFieldDecorator } = this.props.form;
        const formItemLayout = {
            labelCol: { span: 3 },
            wrapperCol: { span: 21 },
        };
        return (
            <div style = {styles.container}>
                <Form onSubmit={this.handleSubmit} style={styles.form}>
                    <FormItem
                        {...formItemLayout}
                        label={(
                            <span>
                                操作类型 
                            </span>
                        )}
                    >
                    {
                        getFieldDecorator('select', {
                            rules: [
                            { require: true, message: '请选择你需要的业务' },
                            ],
                        })(
                            <Select
                            placeholder="请输入选择的业务"
                            onChange={this.handlerSelectChange.bind(this)}
                            >
                                <Option key='1'>1-抖音点赞</Option>
                                <Option key='2'>2-关注</Option>
                                {/* <Option>3</Option> */}
                            </Select>
                        )
                    }
                    </FormItem>

                    <FormItem
                        {...formItemLayout}
                        label={(
                            <span>
                                选择商品
                            </span>
                        )}
                    >
                    {
                        getFieldDecorator('select1', {
                            rules: [
                            { require: true, message: '请选择商品' },
                            ],
                        })(
                            <Select
                            placeholder="请选择商品"
                            onChange={this.handlerSelectCommodity.bind(this)}
                            >
                            {
                                this.state.commodity.map((item, index) => {
                                    return <Option value={item.name} key={index}> {item.name} - 单价 -{item.price}</Option>;
                                })
                            }
                            </Select>
                        )
                    }
                    </FormItem>

                    <FormItem
                        {...formItemLayout}
                        label={(
                            <span>
                                商品价格
                            </span>
                        )}
                    >
                    <Input readOnly value={this.state.price}/>

                    </FormItem>

                </Form>
            </div>
        )
    }

    handleSubmit = (e) => {
    
    }

    handlerSelectCommodity(...args) {
        console.log('a'+args[0])
    }

    handlerSelectChange (...args) {

        console.log(args[0])
        this.setState({
            commodity : [
                {name: '抖音100粉丝',price:'5'},
                {name: '抖音1000粉丝',price:'4'},
                {name: '抖音100粉丝,加急',price:'3'},
            ]
        });
    
    }
}
const WrappedDYOperation = Form.create()(OrderForm);

const styles = {
    container: {
        width:'100%',
        height:'100%',
        backgroundColor: '#fff',
    },
    form: {
        paddingLeft: '15%',
        width: '80%',
    }
}

export default WrappedDYOperation;