import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Grid } from '@icedesign/base';

const { Row, Col } = Grid;

export default class NormalFooter extends Component {
  static displayName = 'NormalFooter';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="normal-footer" style={styles.container}>
        {/* <IceContainer style={styles.container}> */}
          <div style={{ ...styles.copyright, ...styles.pullCenter }}>
            {/* © Copyright 2018. All rights reserved. */}
            <div style={{paddingTop:'25px'}}>
              欢迎使用
            </div>
          </div>
        {/* </IceContainer> */}
      </div>
    );
  }
}

const styles = {
  container: {
    width:'100%',
    height:'70px',
    backgroundColor: '#fff',
    boxShadow:'5px 5px 3px #888888',
    marginBottom: '3%',
  },
  logo: {
    width: '86px',
  },
  pullCenter: {
    textAlign: 'center',
  },
  pullRight: {
    textAlign: 'right',
  },
  navLink: {
    marginRight: '20px',
    color: 'rgba(0, 0, 0, 0.7)',
  },
  socialImg: {
    width: '22px',
    height: '16px',
  },
  line: {
    margin: '16px 0',
    borderBottom: '1px solid rgba(120,130,140,.13)',
  },
  copyright: {
    color: '#999',
    fontSize: '15px',
  },
};
