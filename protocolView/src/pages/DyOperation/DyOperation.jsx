import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
import { Feedback } from '@icedesign/base';
import { Form, Select, Tooltip, Icon, Input, InputNumber, Tag, Button } from 'antd';

import * as API from '../../utils/apis';
import * as userInfoActionsFromOtherFile from '../../actions/userinfo.js';


const FormItem = Form.Item;
const Option = Select.Option;

class DyOperation extends Component {
  static displayName = 'DyOperation';

  constructor(props) {
    super(props);
    this.state = {
      typeArray: [],
      count: 0,
      price: 0,
      isDZ: false,
    };
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    };

    return (
      <Form onSubmit={this.handleSubmit} style={style.formStyle} className="login-form" >
        <FormItem
          {...formItemLayout}
          label={(
            <span>
              操作类型 &nbsp;
              <Tooltip title="选择所需业务">
                <Icon type="question-circle-o" />
              </Tooltip>
            </span>
          )}
        >
          {getFieldDecorator('select', {
            rules: [
              { require: true, message: '请选择你需要的业务' },
            ],
          })(
            <Select
              placeholder="请输入选择的业务"
              onChange={this.handlerSelectChange.bind(this)}
            >
              {
                this.state.typeArray.map((item, index) => {
                  return <Option value={`${item.operationType.split('-')[0]}-${item.price}`} key={index}> {item.operationType} - 单价 -{item.price}</Option>;
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
              单价 ：&nbsp;
              {/* <Tooltip title="根据不同业务进行不同收费">
                <Icon type="question-circle-o" />
              </Tooltip> */}
            </span>
          )}
        >
          <Tag color="#87d068" style={{ size: '100px' }}>{this.state.price}元/个</Tag>
        </FormItem>
        <FormItem
          {...formItemLayout}
          label={(
            <span>
              用户标示 &nbsp;
              <Tooltip title="根据用户的用户名获取">
                <Icon type="question-circle-o" />
              </Tooltip>
            </span>
          )}
        >
          {getFieldDecorator('dyAccount', {
            rules: [{ required: true, message: '输入抖音帐号', whitespace: true }],
          })(
            <Input />
          )}
        </FormItem>

        {this.state.isDZ ?
          <FormItem
            {...formItemLayout}
            label={(
              <span>
                视频标示 &nbsp;
                <Tooltip title="获取视频标示">
                  <Icon type="question-circle-o" />
                </Tooltip>
              </span>
            )}
          >
            {getFieldDecorator('dyVideo', {
              rules: [{ required: this.state.isDZ, message: '请输入视频标示', whitespace: true }],
            })(
              <Input />
            )}

          </FormItem>
        : ''
        }

        <FormItem
          {...formItemLayout}
          label={(
            <span>
              数目 &nbsp;
              <Tooltip title="刷赞关注数目">
                <Icon type="question-circle-o" />
              </Tooltip>
            </span>
          )}
        >

          {getFieldDecorator('dyCount', {
            rules: [{ type: 'number', required: true, message: '请输入数字', whitespace: true }],
          })(
            <InputNumber
              min={0}
              max={99999}
              onChange={this.handleNumberChange}
            />
          )}

        </FormItem>


        <FormItem
          {...formItemLayout}
          label={(
            <span>
              价钱 &nbsp;
              <Tooltip title="根据不同业务进行不同收费">
                <Icon type="question-circle-o" />
              </Tooltip>
            </span>
          )}
        >
          <Tag color="#87d068" style={{ size: '100px' }}>{this.state.count * this.state.price}元</Tag>
        </FormItem>

        <FormItem style={{ marginLeft: '10%' }}>
          <Button type="primary" onClick={this.handleReset}>
            重置
          </Button>

          <Button type="primary" htmlType="submit" style={{ marginLeft: '50%' }}>
            刷赞
          </Button>
        </FormItem>

      </Form>


    );
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        const userinfo = this.props.userinfo;
        const data = {
          type: values.select,
          count: values.dyCount,
          vedio: values.dyVideo,
          dyAccount: values.dyAccount,
          userAccount: userinfo.userid,
        };
        console.log(data);
      }
    });
  }

  handleReset = () => {
    this.props.form.resetFields();
  }

  componentDidMount = () => {
    this.isLogin();
    this.getData();
  }

  isLogin = () => {
    const userinfo = this.props.userinfo;
    if (userinfo.userid == null) {
      Feedback.toast.error('尚未登录，请登录!');
      // this.props.history.push('/login');
    }
  }

  handleNumberChange = (value) => {
    this.setState({
      count: value,
    });
  }

  getData() {
    // 网络请求
    // API.helloWorld().then((response) => {
    //   console.log(response.data);
    // });
    const projectName = { projectName: 'dy' };
    API.getOperationList(projectName).then((res) => {
      if (res.data.success) {
        const list = res.data.data;
        console.log(list);
        this.setState({
          typeArray: list,
        });
      } else {
        Feedback.toast.error('并无此业务');
      }
    });
  }
  handlerSelectChange(...args) {
    const data = args[0].split('-');
    const price = data[1];
    // console.log(price);
    this.setState({
      price,
    });
    if (data[0] === 'dygz') {
      this.setState({
        isDZ: false,
      });
    } else {
      this.setState({
        isDZ: true,
      });
    }
  }
}


const WrappedDYOperation = Form.create()(DyOperation);


const style = {
  formStyle: {
    width: '50%',
    marginLeft: '25%',
  },
};
// -----------------------------redux-react绑定-----------------------------------

function mapStateToProps(state) {
  return {
    userinfo: state.userinfo,
  };
}
function mapDispatchToProps(dispatch) {
  return {
    userInfoActions: bindActionCreators(userInfoActionsFromOtherFile, dispatch),
  };
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WrappedDYOperation);
