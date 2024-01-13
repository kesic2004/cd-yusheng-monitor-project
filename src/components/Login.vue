<template>
  <div>
    <img src="@/assets/login.svg" class="backgroundImageStyle" />
    <!-- class="LoginPageStyle" -->
    <el-form v-if="loginShow" status-icon ref="myForm" label-width="80px" class="LoginPageStyle" :model="loginForm" :rules="loginRules" @submit.native.prevent>
      <span style="font-size: 37pt;font-family: Kaiti;display: flex; align-items: center;"><img src="@/assets/logo.png" style="height: 15%;width: 15%;" />气瓶追溯管理</span>
      <el-form-item prop="myKey" label="用户名" style="margin-top: 1px;">
        <el-input autofocus type="text" v-model="loginForm.myKey" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item prop="myValue" label="密码" style="margin-top: 5px;">
        <el-input type="password" v-model="loginForm.myValue" placeholder="请输入密码，按回车键登录" />
      </el-form-item>
      <el-form-item style="margin-top: 11px;">
        <el-button type="primary" native-type="submit" @click="login('myForm')" style="margin-right: 45px;">登录</el-button>
        <el-button type="primary" @click="reset()" style="margin-left: 45px;">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        myKey: null,
        myValue: null
      },
      loginRules: {
        myKey: [
          {
            required: true,
            message: '用户名是秘填项',
            /**
             * 校验字段的值是否符合提交要求
             * @param {Object} rule 当前对象
             * @param {String} value 用户所输入的值
             * @param {function} callback 校验时所调用的函数，如果校验通过就调用callback()且不传参，如果校验不通过就调用callback(new Error('不通过的理由'))
             */
            validator: (rule, value, callback) => {
              if (value === null || value.trim().length === 0) {
                callback(new Error('用户名是必填'))
              }
              callback()
            },
            trigger: 'blur'
          }
        ],
        myValue: [
          {
            required: true,
            message: '密码是必填项',
            /**
             * 校验字段的值是否符合提交要求
             * @param {Object} rule 当前对象
             * @param {String} value 用户所输入的值
             * @param {function} callback 校验时所调用的函数，如果校验通过就调用callback()且不传参，如果校验不通过就调用callback(new Error('不通过的理由'))
             */
            validator: (rule, value, callback) => {
              if (value === null || value.trim().length === 0) {
                callback(new Error('密码是必填项'))
              }
              callback()
            },
            trigger: 'blur'
          }
        ]
      },
      loginShow: false
    }
  },
  mounted () {
    this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/authorize/login/getSessionUser', { }, { headers: { 'reference': this.constant.ROOT_PATH } }).then(res => {
      if (res.status === 200 && res.data.login !== null && Array.isArray(res.data.leftMenuCollapse)) { /* 双重非（!!）运算符 */
        res.data.login.millis = res.data.millis
        window.sessionStorage.setItem(this.constant.TOKEN, JSON.stringify(res.data.login))
        const menu = {
          leftMenuCollapse: res.data.leftMenuCollapse,
          leftMenuDefaultActive: res.data.leftMenuDefaultActive,
          leftMenuDefaultName: res.data.leftMenuDefaultName,
          leftMenuWelcome: res.data.leftMenuWelcome
        }
        window.sessionStorage.setItem(this.constant.MENU, JSON.stringify(menu))
        if (res.data.station !== null && Array.isArray(res.data.station)) {
          const localStation = Array(res.data.station.length)
          for (var stationIndex = 0; stationIndex < res.data.station.length; ++stationIndex) {
            localStation[stationIndex] = {
              code: res.data.station[stationIndex].code,
              value: res.data.station[stationIndex].id,
              label: res.data.station[stationIndex].name
            }
          }
          window.sessionStorage.setItem(this.constant.STATION, JSON.stringify(localStation))
        }
        /*
         * 获取用户信息成功后，刷新页面
         */
        this.$router.push({ path: '/' })
        location.reload()
      } else {
        window.sessionStorage.removeItem(this.constant.TOKEN)
        window.sessionStorage.removeItem(this.constant.MENU)
        window.sessionStorage.removeItem(this.constant.STATION)
        this.loginShow = true
      }
    }).catch(ex => {
      window.sessionStorage.removeItem(this.constant.TOKEN)
      window.sessionStorage.removeItem(this.constant.MENU)
      window.sessionStorage.removeItem(this.constant.STATION)
      this.loginShow = true
    })
  },
  methods: {
    login (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        } else {
          this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/authorize/login/checkKeyAndValue', this.loginForm).then(res => {
            if (res.status === 200 && res.data.login !== null && Array.isArray(res.data.leftMenuCollapse)) {
              res.data.login.millis = res.data.millis
              /*
               * 保存token到session
               */
              window.sessionStorage.setItem(this.constant.TOKEN, JSON.stringify(res.data.login))
              const menu = {
                leftMenuCollapse: res.data.leftMenuCollapse,
                leftMenuDefaultActive: res.data.leftMenuDefaultActive,
                leftMenuDefaultName: res.data.leftMenuDefaultName,
                leftMenuWelcome: res.data.leftMenuWelcome
              }
              /*
               * 保存菜单到session
               */
              window.sessionStorage.setItem(this.constant.MENU, JSON.stringify(menu))
              if (res.data.station !== null && Array.isArray(res.data.station)) {
                const localStation = Array(res.data.station.length)
                for (var stationIndex = 0; stationIndex < res.data.station.length; ++stationIndex) {
                  localStation[stationIndex] = {
                    code: res.data.station[stationIndex].code,
                    value: res.data.station[stationIndex].id,
                    label: res.data.station[stationIndex].name
                  }
                }
                window.sessionStorage.setItem(this.constant.STATION, JSON.stringify(localStation))
              }
              this.$router.push({ path: '/' })
              /*
               * 登录成功后，刷新页面
               */
              location.reload()
            } else {
              this.$message('登录失败')
            }
          }).catch(ex => {
            window.sessionStorage.removeItem(this.constant.TOKEN)
            window.sessionStorage.removeItem(this.constant.MENU)
            window.sessionStorage.removeItem(this.constant.STATION)
          })
          return true
        }
      })
    },
    reset (formName) {
      this.loginForm.myKey = null
      this.loginForm.myValue = null
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* 背景图片 */
.backgroundImageStyle {
  position: absolute; /* 绝对坐标 */
  left: 0; /* X起始坐标 */
  top: 0; /* Y起始坐标 */
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
}
/* 登录表单 */
.LoginPageStyle {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.5); /* 让登录框增加阴影 */
  border-radius: 8px; /* 增加登录框的圆弧角 */
  width: 400px;
  max-width: 100%;
  padding: 0px 35px 0; /* 内边距 */
  margin: auto;
  position: absolute; /* 绝对位置 */
  top: 50%;
  left: 50%;
  margin: -160px 0 0 -160px;
  /* ———————————————— */
  /* 版权声明：本文为CSDN博主「聪明不喝牛奶」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 */
  /* 原文链接：https://blog.csdn.net/ppphill_c/article/details/118763631 */
}
</style>
