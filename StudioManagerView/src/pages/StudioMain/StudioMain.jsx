import React, { Component } from 'react';
import DataDisplay from './components/DataDisplay';
import FilterTable from './components/FilterTable';
import * as ACT_API from '../../api/AccountApi/ActApi';

export default class StudioMain extends Component {
  static displayName = 'StudioMain';

  constructor(props) {
    super(props);
    this.state = {
      selectCount: 0,
      count: {
      },
    };
  }

  render() {
    return (
      <div className="studio-main-page">
        <DataDisplay selectCount={this.state.selectCount} count={this.state.count} />
        <FilterTable selectChange={this.selectChange.bind(this)} />
      </div>
    );
  }

  componentDidMount() {
    this.getCount();
  }

  getCount = () => {
    ACT_API.getTotalAndTodayCount().then(res => {
      const data = res.data;
      this.setState({
        count: data.data,
      });
    });
  }

  /**
   * 选中的个数的多少
   * @param {选中的个数} count
   */
  selectChange(count) {
    // console.log(count);
    this.setState({
      selectCount: count,
    });
    return true;
  }
}
