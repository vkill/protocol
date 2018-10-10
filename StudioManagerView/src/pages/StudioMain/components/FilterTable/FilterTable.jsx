/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
// import IceLabel from '@icedesign/label';
import FilterForm from './Filter';

@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    // url: '/mock/filter-table-list.json',
    url: '/api/actmgr/page',
    params: {
      page: 1,
    },
    defaultBindingData: {
      list: [],
      total: 1,
      pageSize: 1,
      currentPage: 1,
    },
  },
})
export default class EnhanceTable extends Component {
  static displayName = 'EnhanceTable';

  static defaultProps = {};

  constructor(props) {
    super(props);

    // 请求参数缓存
    this.queryCache = {};
    this.state = {
      filterFormValue: {},
    };
  }

  componentDidMount() {
    this.queryCache.page = 1;
    this.fetchData();
  }

  // shouldComponentUpdate

  componentDidUpdate() {
    setTimeout(() => {
      if (this.props.bindingData.tableData.pageSize !== 1) {
        this.props.selectChange(this.props.bindingData.tableData.total);
      }
    }, 10000);
  }

  fetchData = () => {
    this.props.updateBindingData('tableData', {
      params: this.queryCache,
    });
  };

  renderId = (value, index, record) => {
    return (
      <div style={styles.titleWrapper}>
        <span style={styles.title}>{record.id}</span>
      </div>
    );
  };

  editItem = (record, e) => {
    e.preventDefault();
    // TODO: record 为该行所对应的数据，可自定义操作行为
  };

  // renderOperations = (value, index, record) => {
  //   return (
  //     <div
  //       className="filter-table-operation"
  //       style={styles.filterTableOperation}
  //     >
  //       <a
  //         href="#"
  //         style={styles.operationItem}
  //         target="_blank"
  //         onClick={this.editItem.bind(this, record)}
  //       >
  //         解决
  //       </a>
  //       <a href="#" style={styles.operationItem} target="_blank">
  //         详情
  //       </a>
  //       <a href="#" style={styles.operationItem} target="_blank">
  //         分类
  //       </a>
  //     </div>
  //   );
  // };

  /**
   * 更换页面
   */
  changePage = (currentPage) => {
    // console.log(currentPage);
    this.queryCache.page = currentPage;

    this.fetchData();
  };

  filterFormChange = (value) => {
    this.setState({
      filterFormValue: value,
    });
  };

  /**
   * 提交表格
   */
  filterTable = () => {
    // 合并参数，请求数据
    this.queryCache = {
      // ...this.queryCache,
      ...this.state.filterFormValue,
    };
    // console.log(this.queryCache);
    this.fetchData();
  };

  resetFilter = () => {
    this.setState({
      filterFormValue: {},
    });
  };

  render() {
    const tableData = this.props.bindingData.tableData;
    const { filterFormValue } = this.state;

    return (
      <div className="filter-table">
        <IceContainer title="内容筛选">
          <FilterForm
            value={filterFormValue}
            onChange={this.filterFormChange}
            onSubmit={this.filterTable}
            onReset={this.resetFilter}
          />
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={tableData.list}
            isLoading={tableData.__loading}
            className="basic-table"
            style={styles.basicTable}
            hasBorder={false}
          >
            <Table.Column
              title="抖音号id"
              cell={this.renderId}
              width={85}
            />
            <Table.Column
              title="区号"
              dataIndex="phoneArea"
              width={85}
              // cell={this.renderStatus}
            />
            <Table.Column
              title="抖音号电话"
              dataIndex="dyPhone"
              width={85}
            />
            <Table.Column
              title="抖音号密码"
              dataIndex="pwd"
              width={85}
            />
            <Table.Column
              title="注册时间"
              dataIndex="date"
              width={85}
              // cell={this.renderStatus}
            />
            <Table.Column
              title="点赞数"
              dataIndex="diggCount"
              width={85}
              // cell={this.renderStatus}
            />
            {/* <Table.Column
              title="操作"
              dataIndex="operation"
              width={150}
              cell={this.renderOperations}
            /> */}
          </Table>
          <div style={styles.paginationWrapper}>
            <Pagination
              current={tableData.currentPage}
              pageSize={tableData.pageSize}
              total={tableData.total}
              onChange={this.changePage}
            />
          </div>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  filterTableOperation: {
    lineHeight: '28px',
  },
  operationItem: {
    marginRight: '12px',
    textDecoration: 'none',
    color: '#5485F7',
  },
  titleWrapper: {
    display: 'flex',
    flexDirection: 'row',
  },
  title: {
    marginLeft: '10px',
    lineHeight: '20px',
  },
  paginationWrapper: {
    textAlign: 'right',
    paddingTop: '26px',
  },
};
