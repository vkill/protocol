import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import React, { Component } from 'react';
// import { Feedback } from '@icedesign/base';
import { Form, Select, Tooltip, Icon, Input, InputNumber, Tag, Button } from 'antd';

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
            <Select placeholder="请输入选择的业务">
              {
                this.state.typeArray.map((item, index) => {
                  return <Option value={item.split('-')[0]} key={index}> {item}</Option>;
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
            rules: [{ required: true, message: '请输入视频标示', whitespace: true }],
          })(
            <Input />
          )}

        </FormItem>

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
          <Tag color="#87d068" style={{ size: '100px' }}>{this.state.count}元</Tag>
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
      }
    });
  }

  handleReset = () => {
    this.props.form.resetFields();
  }

  componentDidMount = () => {
    // const userinfo = this.props.userinfo;
    // if (userinfo.userid == null) {
    //   Feedback.toast.error('尚未登录，请登录!');
    //   this.props.history.push('/login');
    // } else {
    this.getData();
    // }
  }

  handleNumberChange = (value) => {
    this.setState({
      count: value,
    });
  }

  getData() {
    // 网络请求

    this.setState({
      typeArray: ['1-抖音刷赞', '2-抖音评论', '3-抖音关注'],
    });
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
