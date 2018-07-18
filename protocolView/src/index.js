import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
// 载入默认全局样式 normalize 、.clearfix 和一些 mixin 方法等
import '@icedesign/base/reset.scss';
import router from './router';
import configureStore from './stores/configureStore';

const ICE_CONTAINER = document.getElementById('ice-container');
const store = configureStore();

if (!ICE_CONTAINER) {
  throw new Error('当前页面不存在 <div id="ice-container"></div> 节点.');
}


ReactDOM.render(
  <Provider store={store} >
    {router}
  </Provider>,
  ICE_CONTAINER);
