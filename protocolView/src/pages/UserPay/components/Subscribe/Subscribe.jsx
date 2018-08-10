import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Input, Button, Grid } from '@icedesign/base';
import {
  FormBinderWrapper,
  FormBinder,
  FormError,
} from '@icedesign/form-binder';

const { Row, Col } = Grid;

export default class Subscribe extends Component {
  static displayName = 'Subscribe';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      formValue: {
        payCount: '',
      },
    };
  }

  formChange = (newValue) => {
    this.setState({
      formValue: newValue,
    });
  };

  subscribe = () => {
    this.form.validateAll((errors, values) => {
      console.log('errors', errors, 'values', values);
    });
  };

  validateMoney = () => {
    // 检查错误
  }

  wechatPay = () => {
    // 跳转微信
    console.log('微信支付');
  }

  zfbPay = () => {
    // 跳转微信
    console.log('微信支付');
  }

  render() {
    return (
      <IceContainer>
        <FormBinderWrapper
          ref={(form) => {
            this.form = form;
          }}
          value={this.state.formValue}
          onChange={this.formChange}
        >
          <Row wrap style={{ marginTop: '10%' }}>
            <Col xxs="1" s="6" l="8" />
            <Col xxs="16" s="8" l="6">
              <Row>
                <h1>充值金额:</h1>
              </Row>
              <Row wrap>
                <Col span="24">
                  <FormBinder type="string" message="请输入数字" required>
                    <Input
                      size="large"
                      name="payCount"
                      placeholder="请输入您要充值的金额..."
                      style={{ width: '100%' }}
                    />
                  </FormBinder>
                </Col>
                <Col span="24" style={styles.error}>
                  <FormError name="pay" />
                </Col>
              </Row>
            </Col>
          </Row>
        </FormBinderWrapper>
        <Row wrap style={{ marginTop: '40px' }}>
          <Col xxs="1" s="6" l="8" />
          <Col xxs="16" s="8" l="6">
            <Button type="primary" onClick={this.zfbPay} > 支付宝 </Button>
            <Button type="primary" onClick={this.wechatPay} style={{ marginLeft: '100px' }}> 微信 </Button>
          </Col>
        </Row>
      </IceContainer>
    );
  }
}

const styles = {
  error: {
    marginTop: '5px',
  },
};
