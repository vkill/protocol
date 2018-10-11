import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Tab, Button, Grid } from '@icedesign/base';
import IceEllipsis from '@icedesign/ellipsis';
import axios from 'axios';

const { Row, Col } = Grid;
const { TabPane } = Tab;

export default class InfoDisplayTab extends Component {
  static displayName = 'InfoDisplayTab';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      tabData: {},
    };
  }

  /**
   * 异步获取数据
   */
  getData = () => {
    // axios
    //   .get('/mock/info-display-tab.json')
    //   .then((response) => {
    //     this.setState({
    //       tabData: response.data.data || {},
    //     });
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
      this.setState({
        // tabData: response.data.data || {},
        tabData: {
          "all": [
            {
              "title": "未完成",
              "desc": "未付款或者未移交订单"
            },
            {
              "title": "完成",
              "desc": "订单已经完成"
            },
            {
              "title": "未付款，订单失效",
              "desc": "在限定时间内订单未支付，导致订单失效"
            },
            {
              "title": "订单异常",
              "desc": "点赞关注账号出现异常，请联系管理员"
            }
          ]}
      });
  };

  componentDidMount() {
    this.getData();
  }

  renderContent = (data) => {
    return data.map((item, index) => {
      return (
        <Col xxs="24" s="12" l="8" key={index}>
          <div style={styles.columnCard}>
            <div style={styles.cardTitle}>{item.title}</div>
            <div style={styles.cardDescWrapper}>
              <div style={styles.cardDesc}>
                <IceEllipsis lineLimit={6} text={item.desc} />
              </div>
            </div>
          </div>
        </Col>
      );
    });
  };

  render() {
    const { tabData } = this.state;
    return (
      <div className="info-display-tab">
        <IceContainer>
          <Tab type="bar" onChange={this.callback}>
            <TabPane tab="注意事项" key="1">
              <Row wrap gutter={20}>
                {tabData.all ? this.renderContent(tabData.all) : '暂无数据'}
              </Row>
            </TabPane>
            {/* <TabPane tab="可申请频道" key="2">
              <Row wrap gutter={20}>
                {tabData.apply ? this.renderContent(tabData.apply) : '暂无数据'}
              </Row>
            </TabPane>
            <TabPane tab="已获得频道" key="3">
              <Row wrap gutter={20}>
                {tabData.existing
                  ? this.renderContent(tabData.existing)
                  : '暂无数据'}
              </Row>
            </TabPane> */}
          </Tab>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  columnCard: {
    overflow: 'hidden',
    boxShadow:
      '0px 0px 2px 0px rgba(0, 0, 0, 0.1),0px 2px 2px 0px rgba(0, 0, 0, 0.1)',
    background: '#fff',
    height: '100%',
    marginBottom: '20px',
  },
  cardDescWrapper: {
    marginTop: '20px',
  },
  cardTitle: {
    fontSize: '18px',
    textAlign: 'center',
    marginTop: '22px',
  },
  cardDesc: {
    textAlign: 'center',
    padding: '0 20px',
    height: '50px',
    overflow: 'hidden',
    lineHeight: '24px',
    fontSize: '14px',
    color: '#666',
    margin: '5px auto 0 auto',

  },
  cardBtnWrapper: {
    textAlign: 'center',
  },
};
