// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

/* 导入Element UI */
import ElementUI from 'element-ui'
/* 导入Element UI样式 */
import 'element-ui/lib/theme-chalk/index.css'
/* 导入axios */
import axios from 'axios'
/* 导入全局常量 */
import constant from '@/constant/index'
/* 导入全局变量 */
import globalVariable from '@/GlobalVariable'

/* Use ElementUI */
Vue.use(ElementUI)
/* 全局绑定axios */
Vue.prototype.$axios = axios
/* 绑定全局常量，通过this.constant进行访问 */
Vue.prototype.constant = constant
/* 绑定全局变量，通过this.globalVar进行访问 */
Vue.prototype.globalVar = globalVariable

Vue.config.productionTip = false

/**
 * to 将要跳转到的路径
 * from 从某个具体和路径跳转来
 * next 回调函数
 */
router.beforeEach((to, from, next) => {
  /*
   * 判断session中是否存在token
   */
  if (window.sessionStorage.getItem(constant.TOKEN) != null) {
    /*
     * 如果存在Token，跳转到/
     */
    if (to.fullPath === constant.LOGIN_PATH) {
      next(constant.ROOT_PATH)
    } else {
      next()
    }
  } else {
    /*
     * 如果不存在token，判断是否是开放路径
     * 如果不是就跳转到/Login
     */
    for (var i = 0; i < constant.OPENED_FULLPATH.length; ++i) {
      if (constant.OPENED_FULLPATH[i] === to.fullPath) {
        next()
        return
      }
    }
    next(constant.LOGIN_PATH)
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
