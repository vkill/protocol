// 菜单配置
// headerMenuConfig：头部导航配置
// asideMenuConfig：侧边导航配置

const headerMenuConfig = [
  {
    name: '首页',
    path: '/',
    icon: 'home',
  },
  {
    name: '用户',
    path: '/user',
    external: true,
    // newWindow: true,
    icon: 'yonghu',
  },
];

const asideMenuConfig = [
  {
    name: '首页',
    path: '/',
    icon: 'home',
  },
  {
    name: '抖音协议',
    path: '/user',
    icon: 'yonghu',
    children: [
      {
        name: '抖音注册',
        path: '/dyRegister',
      },
      {
        name: '抖音操作',
        path: '/dyOperation',
      },
    ],
  },
  {
    name: '微信协议',
    path: '/user',
    icon: 'yonghu',
    // children: [
    //   {
    //     name: '抖音帐号注册',
    //     path: '/dyRegister',
    //   },
    //   {
    //     name: '抖音操作',
    //     path: '/dyOperation',
    //   },
    // ],
  },
  {
    name: '\u767B\u9646',
    path: '/login',
    icon: 'home',
  },
];

export { headerMenuConfig, asideMenuConfig };
