import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import DataDisplay from './components/DataDisplay';
import IncomeTable from './components/IncomeTable';
import Logout from './components/LogoutButton';

import * as companyInfoActionsFromOtherFile from '../../actions/companyinfo.js';

class CompanyMain extends Component {
  static displayName = 'CompanyMain';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="company-main-page">
        <DataDisplay />
        <IncomeTable />
        <Logout logout={this.logout.bind(this)} />
      </div>
    );
  }

  componentDidMount() {
    this.isLogin();
  }

  isLogin() {
    const companyinfo = this.props.companyinfo.companyinfo;
    if (companyinfo == null) {
      // this.props.history.push('/companyLogin');
    }
  }

  logout() {
    // logout
    const actions = this.props.companyInfoActions;
    actions.rm();
  }
}

// -----------------------------redux-react绑定-----------------------------------
function mapStateToProps(state) {
  return {
    companyinfo: state.companyinfo,
  };
}
function mapDispatchToProps(dispatch) {
  return {
    companyInfoActions: bindActionCreators(companyInfoActionsFromOtherFile, dispatch),
  };
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompanyMain);
