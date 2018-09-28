import React, { Component } from 'react';

import './index.css';

export default class MainPage extends Component {
//   static displayName = 'MainPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div style={styles.container} className="container-main">
        <p className="gradient-main">空格点赞</p>
      </div>
    );
  }
}

const styles = {
  container: {

    height: '110px',
    backgroundColor: '#fff',
    boxShadow: '5px 5px 3px #888888',
    marginBottom: '2%',
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

