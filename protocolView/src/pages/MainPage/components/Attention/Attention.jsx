import React, { Component } from 'react';
import { List } from 'antd';


export default class Attention extends Component {
  static displayName = 'Attention';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const data = ['1、本平台点赞关注稳定',
                  '2、可选择评论和点赞',
                  '3、填写您的视频分享链接即可自动生成视频id，无需您手动查找', 
                  '4、下单后必须等待完成方可继续下单'
                ];
    return (
      <div style={styles.container}>
        <List
          header={<div>注意事项</div>}
          footer={<div>联系我们</div>}
          bordered
          dataSource={data}
          renderItem={item => (<List.Item>{item}</List.Item>)}
        />
      </div>
    );
  }
}

const styles = {
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#fff',
    boxShadow: '5px 5px 3px #888888',
    marginBottom: '2%',
  },
};
