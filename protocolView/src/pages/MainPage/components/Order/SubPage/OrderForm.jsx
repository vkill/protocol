import React, { Component } from 'react';

import { Form, Select, Input, Button, InputNumber, Tooltip, Icon, message } from 'antd';
import * as ORDER_API from '../../../../../utils/OrderApi/apis';
import * as HELPER_API from '../../../../../utils/HelperApis/HelperApi';

const FormItem = Form.Item;
const Option = Select.Option;

class OrderForm extends Component {
    static displayName = 'OrderForm';

    constructor(props) {
      super(props);
      this.state = {
        commodity: [],
        price: 0,
        orderCount: 1,
        shareUrl: '',
        videoId: '',
      };
    }
    render() {
      const { getFieldDecorator } = this.props.form;
      const typeList = this.props.type_list;
      const formItemLayout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
      };
      return (
        <div style={styles.container}>
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
                        getFieldDecorator('pro_type', {
                            rules: [
                            { require: true, message: '请选择你需要的业务' },
                            ],
                        })(
                          <Select
                            placeholder="请输入选择的业务"
                            onChange={this.handlerSelectChange.bind(this)}
                          >
                            {/* <Option key='1'>1-抖音点赞</Option>
                                <Option key='2'>2-关注</Option> */}
                            {

                                typeList.map((item, index) => {
                                        return <Option key={index} value={item.typeSign}>{item.typeDesc}</Option>;
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
                    选择商品
                </span>
                        )}
            >
              {
                        getFieldDecorator('goods', {
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
                                    return <Option value={item.goodsType} key={index}> {item.goodTypeDesc} - 单价 -{item.price}</Option>;
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
              <Input readOnly value={`${this.state.price}元`} />

            </FormItem>

            <FormItem
              {...formItemLayout}
              label={(
                <span>
                            视频分享链接 &nbsp;
                  <Tooltip title="视频分享链接">
                    <Icon type="question-circle-o" />
                  </Tooltip>
                </span>
                        )}
            >
              {/* {getFieldDecorator('videoid', {
                            rules: [{ required: true, message: '请输入视频分享链接', whitespace: true }],
                        })( */}
                <Input 
                    placeholder='请输入分享链接'
                    value={this.state.shareUrl}
                    onChange={this.handleVideoidChange}
                    onBlur={this.handleBlur}
                    ref="videoId"

                />
                {/* )} */}
            </FormItem>

            <FormItem
              {...formItemLayout}
              label={(
                <span>
                  视频id &nbsp;
                  <Tooltip title="自动提取的视频id">
                    <Icon type="question-circle-o" />
                  </Tooltip>
                </span>
                        )}
            >
              
                <Input 
                    readOnly
                    value={this.state.videoId}
                />
            </FormItem>

            <FormItem
              {...formItemLayout}
              label={
                <span>
                            订单数目 &nbsp;
                  <Tooltip title="最多下单100份">
                    <Icon type="question-circle-o" />
                  </Tooltip>
                </span>
                        }
            >

              <InputNumber
                min={1}
                max={100}
                value={this.state.orderCount}
                onChange={this.handleNumberChange}
                style={{ width: '100%' }}
              />


            </FormItem>

            <FormItem
              {...formItemLayout}
              label={(
                <span>
                                订单总价
                </span>
                        )}
            >
              <Input readOnly value={`${this.state.price * this.state.orderCount}元`} />

            </FormItem>

            <FormItem >
              <Button type="primary" htmlType="submit" style={{ width: '85%', marginLeft: '15%' }}>
                            下单
              </Button>

            </FormItem>

          </Form>
        </div>
      );
    }

    handleSubmit = (e) => {
      e.preventDefault();      

      this.props.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          if (values.pro_type != null || values.goods != null) {
            const postData = values;
            postData.order_count = this.state.orderCount;
            postData.videoid = this.state.videoId;
            if (this.state.videoId!=='') {
                // 下订单
                ORDER_API.make_order(postData).then((response) => {
                  const data = response.data;
                  if (data.status === '0') {
                    // 下单成功，跳转支付

                    message.success(data.message);
                    this.props.toOtherPage('/paypage',data)


                  } else {
                    message.error(data.message);
                  }
                });
            } else {
                message.error('请填写正确的分享链接');
            }
            
          } else {
            message.error('尚未选择商品，请选择商品后再试');
          }
        }
      });
    }

    handleBlur = () => {
        HELPER_API.toVideoId(this.state.shareUrl).then(response => {
            const data = response.data;
            if (data.status === "0") {
                this.setState({
                    videoId: data.video_id
                });
            } else {
                message.error(data.message);
            }
        });
    }

    /**
     * 获取
     */
    handleVideoidChange = (value) => {
        const url = value.target.value
        // HELPER_API.toVideoId(url).then(response => {
        //     const data = response.data;
        //     if (data.status === "0") {
        //         this.setState({
                    
        //             videoId: data.video_id
        //         });
        //     } else {
        //         message.error(data.message);
        //     }
        // });
        this.setState({
            shareUrl: url,
        })
        
    }

    /**
     * 改变价格
     * @param  {...any} args
     */
    handlerSelectCommodity(...args) {
      const type = args[0];
      this.state.commodity.forEach((item) => {
        if (item.goodsType === type) {
          this.setState({
            price: item.price,
          });
        }
      });
    }

    handlerSelectChange(...args) {
      const sign = args[0];
      ORDER_API.get_goods_list_by_type(sign).then((response) => {
        const data = response.data.data;
        if (data.status === '0') {
          //       成功
          this.setState({
            commodity: data.dataList,
          });
        }
      });
    }

    /**
     * 监控数值变化
     */
    handleNumberChange =(value) => {
      this.setState({
        orderCount: value,
      });
    }
}
const WrappedDYOperation = Form.create()(OrderForm);

const styles = {
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#fff',
  },
  form: {
    paddingLeft: '5%',
    width: '80%',
  },
};

export default WrappedDYOperation;
