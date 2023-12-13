import Vue from 'vue'
import Router from 'vue-router'
/* import HelloWorld from '@/components/HelloWorld' */
import Base from '@/Base'
/****************************************************************************
 * 基本页面                                                                  *
 ****************************************************************************/
// 登录页面
import Login from '@/components/Login'
// 欢迎页面
import Welcome from '@/components/Welcome'

/****************************************************************************
 * 1.工作空间                                                              *
 ****************************************************************************/
// 控制台页面
import Console from '@/components/main/Console'

/****************************************************************************
 * 2.罐装系统                                                                *
 ****************************************************************************/
import ChargeDocuments from '@/components/charge/ChargeDocuments'
import ElectronicScaleManagement from '@/components/charge/ElectronicScaleManagement'
import StationStoreManagerment from '@/components/charge/StationStoreManagerment'
import StationPriceManagement from '@/components/charge/StationPriceManagement'
import StationOperatorManagement from '@/components/charge/StationOperatorManagement'

/****************************************************************************
 * 3.站点信息                                                                *
 ****************************************************************************/
// 气瓶档案
import BottleArchive from '@/components/site/BottleArchive'
import StationInformation from '@/components/site/StationInformation'

/****************************************************************************
 * 4.气瓶配送                                                                *
 ****************************************************************************/

Vue.use(Router)

export default new Router({
  /* 去掉URL中的# */
  mode: 'history',
  /*
   * 所有页面路径统一以“/” + 大写字母组成
   */
  routes: [
    // {
    //   path: '/', // 根页面
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path: '/', // 根页面
      name: 'Base',
      component: Base
    },
    {
      path: '/Login', // 登录页面
      name: 'Login',
      component: Login
    },
    {
      path: '/Welcome', // 欢迎页面
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/Main/Console', // 控制台页面
      name: 'Console',
      component: Console
    },
    {
      path: '/Charge/ChargeDocuments', // 灌装记录
      name: 'ChargeDocuments',
      component: ChargeDocuments
    },
    {
      path: '/Charge/StationStoreManagerment', // 机构管理
      name: 'StationStoreManagerment',
      component: StationStoreManagerment
    },
    {
      path: '/Charge/StationPriceManagement', // 价格管理
      name: 'StationPriceManagement',
      component: StationPriceManagement
    },
    {
      path: '/Charge/StationOperatorManagement', // 操作员管理
      name: 'StationOperatorManagement',
      component: StationOperatorManagement
    },
    {
      path: '/Charge/ElectronicScaleManagement', // 站秤管理
      name: 'ElectronicScaleManagement',
      component: ElectronicScaleManagement
    },
    {
      path: '/Site/BottleArchive', // 气瓶档案
      name: 'BottleArchive',
      component: BottleArchive
    },
    {
      path: '/Site/StationInformation', // 站点信息 git commit -m "20231127 commit"
      name: 'StationInformation',
      component: StationInformation
    }
  ]
})
