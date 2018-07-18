import React, { Component } from 'react';

export default class AblityItems extends Component {
  static displayName = 'AblityItems';

  static propTypes = {};

  static defaultProps = {};

  render() {
    return (
      <div className="hy-ability" style={style.hyAbilityStyles}>
        <div className="hy-ability-item" style={style.hyAbilityItemStyle}>
          <h3 style={style.hyAbilityItemTitleStyle}>抖音协议版</h3>
          <p style={style.hyAbilityItemSubtitleStyle}>
            研发中
          </p>
        </div>

        <div className="hy-ability-item" style={style.hyAbilityItemStyle}>
          <h3 style={style.hyAbilityItemTitleStyle}>微信协议版</h3>
          <p style={style.hyAbilityItemSubtitleStyle}>
            尚未研发
          </p>
        </div>

        <div className="hy-ability-item" style={style.hyAbilityItemStyle}>
          <h3 style={style.hyAbilityItemTitleStyle}>微博协议版</h3>
          <p style={style.hyAbilityItemSubtitleStyle}>
            尚未研发
          </p>
        </div>
      </div>
    );
  }
}

const style = {
  hyAbilityStyles: {
    textAlign: 'center',
    background: '#fff',
    padding: '40px 0',
  },
  hyAbilityItemStyle: {
    display: 'inline-block',
    width: '360px',
    margin: '35px 0',
    verticalAlign: 'top',
  },
  hyAbilityItemImgStyle: {
    height: '62px',
  },
  hyAbilityItemTitleStyle: {
    fontSize: '20px',
    lineHeight: '26px',
    color: '#333',
    fontWeight: '400',
    margin: '18px 0 10px',
  },
  hyAbilityItemSubtitleStyle: {
    fontSize: '16px',
    color: '#999',
    lineHeight: '21px',
  },
};
