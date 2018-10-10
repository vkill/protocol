import React, { Component } from 'react';
import IceContainer from '@icedesign/container';

// const data = [
//   {
//     count: 100,
//     title: '今日注册数',
//   },
//   {
//     count: 777,
//     title: '总共注册数',
//   },
//   {
//     count: 888,
//     title: '选中日期注册数',
//   },
// ];

export default class DataDisplay extends Component {
  static displayName = 'DataDisplay';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const count = this.props.count;
    const data = [
      {
        count: count.today,
        title: '今日注册数',
      },
      {
        count: count.total,
        title: '总共注册数',
      },
      {
        count: this.props.selectCount,
        title: '选中日期注册数',
      },
    ];
    return (
      <div className="data-display">
        <IceContainer>
          <div style={styles.items}>
            {data.map((item, index) => {
              return (
                <div style={styles.item} key={index}>
                  <h5 style={styles.count}>{item.count}</h5>
                  <span style={styles.splitLine} />
                  <p style={styles.title}>{item.title}</p>
                </div>
              );
            })}
          </div>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  items: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  item: {
    width: '33.3333%',
    margin: '5px 0',
    textAlign: 'center',
  },
  count: {
    margin: '12px 0',
    fontWeight: 'bold',
    fontSize: '32px',
    color: '#15A0FF',
  },
  title: {
    color: '#999',
  },
  splitLine: {
    display: 'block',
    margin: '0 auto',
    width: '24px',
    height: '1px',
    background: '#9B9B9B',
  },
};
