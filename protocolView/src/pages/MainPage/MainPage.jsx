import React, { Component } from 'react';
import TopBar from './components/TopBar';
import Attention from './components/Attention';
import Order from './components/Order';
import Footer from './components/NormalFooter';
import NameBar from './components/NameBar';

export default class MainPage extends Component {
  static displayName = 'MainPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div style={styles.body}>
        <div style={styles.container}>
          <TopBar />
          <NameBar />
          <Attention />
          <Order props={this.props} />
          <Footer />
        </div>
      </div>
    );
  }
}

const styles = {
  body: {
    backgroundImage: `url(${require('./images/bg.jpg')})`,
    width: '100%',
    height: '100%',
  },
  container: {
    marginTop: '5%',
    marginLeft: '5%',
    marginRight: '5%',
  },
};
