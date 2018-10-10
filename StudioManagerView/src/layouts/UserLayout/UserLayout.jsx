import React, { Component } from 'react';
import Layout from '@icedesign/layout';
import { Switch, Route, Redirect } from 'react-router-dom';
import Footer from './components/Footer';
import { routerData } from '../../routerConfig';
import './UserLayout.scss';

export default class UserLayout extends Component {
  static displayName = 'UserLayout';

  static propTypes = {};

  static defaultProps = {};

  render() {
    return (
      <Layout className="user-layout" style={styles.container}>
        <div className="header">
          <a href="#" className="meta">
            <img
              className="logo"
              src="http://193.112.9.83/upload/2018/10/5vbhghpii4hq5r9m3mq76vdsu2.png"
              alt="logo"
            />
            <span className="title">工作室管理</span>
          </a>
          <p className="desc">监控工作室内容</p>
        </div>

        <Switch>
          {routerData.map((item, index) => {
            return item.component ? (
              <Route
                key={index}
                path={item.path}
                component={item.component}
                exact={item.exact}
              />
            ) : null;
          })}

          <Redirect exact from="/user" to="/user/login" />
        </Switch>
        <Footer />
      </Layout>
    );
  }
}

const styles = {
  container: {
    position: 'relative',
    width: '100%',
    height: '100vh',
    paddingTop: '100px',
    background: '#f0f2f5',
    backgroundImage:
      'url(https://img.alicdn.com/tfs/TB1kOoAqv1TBuNjy0FjXXajyXXa-600-600.png)',
    backgroundSize: 'contain',
  },
};

// <Switch>
//   <Route path="/user/login" exact component={UserLogin} />
//   <Redirect to="/user/login" />
// </Switch>
