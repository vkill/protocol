// 以下文件格式为描述路由的协议格式
// 你可以调整 routerConfig 里的内容
// 变量名 routerConfig 为 iceworks 检测关键字，请不要修改名称

import BasicLayout from './layouts/BasicLayout';
import Home from './pages/Home';

// import BlankLayout from './layouts/BlankLayout';

import BlankLayout from './layouts/BlankLayout';
import LoginPage from './pages/LoginPage';
import UserPay from './pages/UserPay';

import DyOperation from './pages/DyOperation';

import RegisterPage from './pages/RegisterPage';

import HeaderAsideFooterResponsiveLayout from './layouts/HeaderAsideFooterResponsiveLayout';
import Company from './pages/Company';

import HeaderFooterLayout from './layouts/HeaderFooterLayout';
import CompanyMain from './pages/CompanyMain';
import UserOrder from './pages/UserOrder';
import MainPage from './pages/MainPage';
import NotFound from './pages/NotFound';
import PayPage from './pages/PayPage';

const routerConfig = [
  {
    path: '/home',
    layout: BasicLayout,
    component: Home,
  },
  {
    path: '/login',
    layout: BlankLayout,
    component: LoginPage,
  },
  {
    path: '/pay',
    layout: BasicLayout,
    component: UserPay,
  },
  {
    path: '/dyOperation',
    layout: BasicLayout,
    component: DyOperation,
  },
  {
    path: '/registerPage',
    layout: BlankLayout,
    component: RegisterPage,
  },
  {
    path: '/companyLogin',
    layout: BlankLayout,
    component: Company,
  },
  {
    path: '/companyMain',
    layout: BlankLayout,
    component: CompanyMain,
  },
  {
    path: '/userorder',
    layout: BasicLayout,
    component: UserOrder,
  },
  {
    path: '/',
    layout: BlankLayout,
    component: MainPage,
  },
  {
    path:'/paypage',
    layout: BlankLayout,
    component: PayPage,
  },
  {
    path: '*',
    layout: BasicLayout,
    component: NotFound,
  },
];

export default routerConfig;
