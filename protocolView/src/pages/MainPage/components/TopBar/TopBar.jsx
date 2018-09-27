import React, { Component } from 'react';
import { Row } from 'antd';

export default class MainPage extends Component {
//   static displayName = 'MainPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div style={styles.container}>
        <Row>
          <div style={styles.rowTop} />
          {/* <Col span={4}>
            <img
              style={styles.logo}
              src={require('./images/hero.png')}
              alt="logo"
            />
          </Col>
          <Col span={1} />
          <Col span={19}>
            <div style={styles.title}>
              <h1 style={styles.font}>明星平台</h1>
            </div>
          </Col> */}
        </Row>
      </div>
    );
  }
}

const styles = {
  container: {
    backgroundImage: `url(${require('./images/hero.png')})`,
    backgroundSize: '100px 100px',
    width: '100%',
    height: '100px',
    backgroundColor: '#fff',
    boxShadow: '5px 5px 3px #888888',
    marginBottom: '3%',
  },
  title: {
    paddingTop: '2%',
  },
  font: {
    size: '20px',
    color: '#111',
  },
  logo: {
    width: '50%',
    height: '50%',
    paddingTop: '10%',
    paddingLeft: '10%',
  },
};

