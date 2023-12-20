<template>
  <div id="app">
    <router-view v-if="!displayMain && displayOpenedFullPath" />
    <el-container v-if="displayMain" class="MyAppStyle" hidden="true">
      <el-header class="MyTopHeader" :height="leftMessageHeightString">
        <el-container>
          <el-aside :width="leftMessageWidthString" class="MyLeftSide MyLeftUserMessage">
            <el-row v-if="leftMenuIsCollapse" :gutter="40" type="flex" justify="start" align="middle" style="margin-left: 4px; margin-right: 0px;" v-bind:style="{ width: (leftMessageWidth - 4) + 'px'}">
              <el-col :span="30" style="padding: 0px; margin: 0px; width: 45px; height: 20px; line-height: 1;"></el-col>
              <el-col :span="10" style="padding: 0px; margin: 0px; width: 15px;">
                <el-button size="mini" icon="el-icon-arrow-right" style="padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin: 0px;" v-bind:style="{ height: (leftMessageHeight - 2) + 'px' }" @click="leftMenuChangeCollapse" />
              </el-col>
            </el-row>
            <el-row v-else :gutter="40" type="flex" justify="start" align="middle" style="margin-left: 5px; margin-right: 0px;" v-bind:style="{ width: (leftMessageWidth - 5) + 'px'}">
              <el-col :span="30" style="padding: 0px; margin: 0px; width: 180px; height: 20px; line-height: 1;">
                <el-dropdown @command="topMenuDropped">
                  <span style="color: #FFFFFF;">{{ myTitle }}<i class="el-icon-arrow-down el-icon--right"></i></span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="a">退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-col>
              <el-col :span="10" style="padding: 0px; margin: 0px; width: 15px;">
                <!-- style="padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 1px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; background-color: yellow;"-->
                <el-button size="mini" icon="el-icon-arrow-left" style="padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin: 0px;" v-bind:style="{ height: (leftMessageHeight - 2) + 'px' }" @click="leftMenuChangeCollapse" />
              </el-col>
            </el-row>
          </el-aside>
          <el-main style="padding: 0px; margin: 0px;">
            <el-container>
              <el-header class="MySubHeader" :height="mySubHeaderHeightString" style="padding-left: 0px;">
                <el-select clearable v-model="myStationIndex" placeholder="气站" no-data-text="暂时没有可选择的气站" style="padding-left: 0px; padding-right: 0px; margin-top: 3px; margin-left: 3px;">
                  <el-option v-for="item in globalVar.globalStationArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-header>
              <el-main class="MySubMain" style="padding-left: 0px;overflow-y: hidden;">
                <el-tabs
                  closable
                  tab-position="top"
                  v-model="myActiveClickedItem"
                  type="card"
                  @tab-click="myClickedItemClick"
                  @tab-remove="myClickedItemRemove"
                >
                  <el-tab-pane
                    v-for="item in myClickedItems"
                    :key="item.label"
                    :label="item.label"
                    :name="item.name"
                  />
                </el-tabs>
              </el-main>
            </el-container>
          </el-main>
        </el-container>
      </el-header>
      <el-container class="MyMiddleMain">
        <el-aside :width="leftMenuWidthString" class="MyLeftSide MyLeftMenuSide">
          <!-- ———————————————— -->
          <!-- 版权声明：本文为CSDN博主「xumengxia—xu」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 -->
          <!-- 原文链接：https://blog.csdn.net/qq_56514785/article/details/118404670 -->
          <!-- router=true 动态跳转 -->
          <el-menu
            router
            unique-opened
            background-color="#2f4050"
            text-color="#cccccc"
            active-text-color="#5FB878"
            v-for="item in leftMenuCollapse"
            style="overflow: hidden;text-align: left;text-indent: 5px; padding: 0px; margin-right: -1px;"
            :key="item.index"
            :collapse="leftMenuIsCollapse"
            :collapse-transition="true"
            :default-active="leftMenuDefaultActive"
            @select="leftMainMenuSelected"
          >
            <!-- style="border: solid;border-width: 1pt;border-color: red;" -->
            <el-submenu :index="item.index">
              <template slot="title">
                <i v-if="item.iconName !== null" :class="item.iconName" style="text-indent: 0px;" />
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item
                class="menusmall"
                v-for="i in item.items"
                :key="i.index"
                :index="i.index"
              >{{ i.name }}</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main class="MyMainSide">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      leftMessageWidth: 200, // 左上的消息栏宽度(单位像素)
      leftMessageHeight: 60, // 左上的消息栏的高度(单位像素)
      /* 左边的下拉菜单 */
      leftMenuCollapse: [],
      leftMenuDefaultActive: null, /* 默认跳转到的路径 */
      leftMenuDefaultName: null, /* 默认跳转到的路径名字 , */
      leftMenuWelcome: null, /* 不显示任何页面时显示的页面 */
      leftMenuWidth: 200, /* 左边菜单的宽度(单位像素) */
      leftMenuIsCollapse: false, /* 菜单是否收起来 */
      mySubHeaderHeight: 30, /* 标签栏的高度 */
      /*
       * name : path
       * label : 显示的名字
       */
      myClickedItems: [], /* 已点击的标签页 */
      myActiveClickedItem: null, /* 活动的标签页 */
      displayMain: false, /* 是否显示主页面 */
      displayOpenedFullPath: false, /* 是否显示开放页面 */
      myTitle: null, // 用户名
      myStationIndex: null, // 气站索引(当前选中的气站)
      pageReferencePath: this.constant.ROOT_PATH
    }
  },
  computed: {
    /**
     * 把左上的消息栏宽度由Number类型变成像素字符串
     */
    leftMessageWidthString: function () {
      return this.leftMessageWidth + 'px'
    },
    /**
     * 把左上的消息栏的高度由Number类型变成像素字符串
     */
    leftMessageHeightString: function () {
      return this.leftMessageHeight + 'px'
    },
    /**
     * 把左边菜单的宽度由Number类型变成像素字符串
     */
    leftMenuWidthString: function () {
      return this.leftMenuWidth + 'px'
    },
    /**
     * 把标签栏的高度由Number类型变成像素字符串
     */
    mySubHeaderHeightString: function () {
      return this.mySubHeaderHeight + 'px'
    }
  },
  watch: {
    myStationIndex: {
      handler: function (newValue, oldValue) {
        /*
         * 如果是整数，就传整数，否则传null
         */
        this.globalVar.globalStationIndex = Number.isInteger(newValue) ? newValue : null
      },
      deep: false
    }
  },
  /*
   * 页面加载完成时，需要根据session中的token判断进行具体的展示
   */
  mounted () {
    /*
     * 获取session中的token字符串
     */
    var currTokenString = window.sessionStorage.getItem(this.constant.TOKEN)
    var currMenuString = window.sessionStorage.getItem(this.constant.MENU)
    var localStationString = window.sessionStorage.getItem(this.constant.STATION)
    /*
     * 如果不为null，就把该字符串解析为JSON对象，如果解析出错就刷新页面
     * 如果为null，则从后端，如果获取失败，remove当前session中的token。
     * 如果路径不是开放路径，那么刷新页面
     */
    if (currTokenString === null || currMenuString === null || localStationString === null) {
      window.sessionStorage.removeItem(this.constant.TOKEN)
      window.sessionStorage.removeItem(this.constant.MENU)
      window.sessionStorage.removeItem(this.constant.STATION)
      this.globalVar.globalStationArray = []
      for (var i = 0; i < this.constant.OPENED_FULLPATH.length; ++i) {
        /*
         * 如果是开放的路径，则不需要刷新
         */
        if (this.constant.OPENED_FULLPATH[i] === this.$router.currentRoute.fullPath) {
          this.displayMain = false
          this.displayOpenedFullPath = true
          return
        }
      }
      location.reload()
    } else {
      try {
        /*
         * window.sessionStorage存储的TOKEN不一定是正确的，所以加上try...catch...
         */
        const currToken = JSON.parse(currTokenString)
        const currMenu = JSON.parse(currMenuString)
        const currStation = JSON.parse(localStationString)
        if (Date.now() - currToken.millis > 1500000) { /* 1500000即半小时减去五分钟 */
          window.sessionStorage.removeItem(this.constant.TOKEN)
          window.sessionStorage.removeItem(this.constant.MENU)
          window.sessionStorage.removeItem(this.constant.STATION)
          this.globalVar.globalStationArray = []
          location.reload()
          return
        }
        this.myTitle = currToken.myTitle
        this.leftMenuCollapse = currMenu.leftMenuCollapse
        this.leftMenuDefaultActive = currMenu.leftMenuDefaultActive
        this.leftMenuDefaultName = currMenu.leftMenuDefaultName
        this.leftMenuWelcome = currMenu.leftMenuWelcome
        this.globalVar.globalStationArray = currStation
        for (var j = 0; j < this.globalVar.globalStationArray.length; ++j) {
          if (Number.isInteger(this.globalVar.globalStationArray[j].value)) {
            this.myStationIndex = this.globalVar.globalStationArray[j].value
            break
          }
        }
      } catch (ex) {
        window.sessionStorage.removeItem(this.constant.TOKEN)
        window.sessionStorage.removeItem(this.constant.MENU)
        window.sessionStorage.removeItem(this.constant.STATION)
        this.globalVar.globalStationArray = []
        location.reload()
        return
      }
      this.displayMain = true
      this.displayOpenedFullPath = false
      this.myClickedItemAddDefault()
    }
  },
  methods: {
    /**
     * 新点击的菜单的路径
     * @param {String} index：点击子菜单的index属性(Path)
     * @param {Array} indexPath：点击菜单的index属性的路径，必须唯一
     * @param {Object} menu 菜单对象
     *                 menu._vnode.children[0].text是菜单名
     */
    leftMainMenuSelected (index, indexPath, menu) {
      for (var i = 0; i < this.myClickedItems.length; ++i) {
        if (this.myClickedItems[i].name === index) {
          this.myActiveClickedItem = index
          return
        }
      }
      var item = {}
      this.leftMenuDefaultActive = this.myActiveClickedItem = item.name = index
      item.label = menu._vnode.children[0].text
      this.myClickedItems.push(item)
    },
    /**
     * 点击标签
     * @param {Object} item 标签对象
     *            item._data.index是该标签的序号
     */
    myClickedItemClick (item) {
      this.modifyPath(item.name)
      this.leftMenuDefaultActive = this.myActiveClickedItem = item.name
    },
    /**
     * 移除标签
     * @param {String} targetName 标签name
     */
    myClickedItemRemove (targetName) {
      var oldArr = this.myClickedItems
      var newArr = []
      for (var i = 0; i < oldArr.length; ++i) {
        if (oldArr[i].name !== targetName) {
          newArr.push(oldArr[i])
        }
      }
      /*
       * 如果关闭的是活动标签
       */
      if (this.myActiveClickedItem === targetName) {
        this.myClickedItems = newArr
        /*
        * 如果标签已经没有了
        */
        if (newArr.length === 0) {
          this.modifyPath(this.leftMenuWelcome) // 修改为欢迎页面
          this.myActiveClickedItem = null
          /*
          * 如果关闭的是第一个标签，后移
          */
        } else if (oldArr[0].name === targetName) {
          for (var j = 1; j < oldArr.length; ++j) {
            if (oldArr[j].name !== targetName) {
              this.myActiveClickedItem = oldArr[j].name
              this.modifyPath(this.myActiveClickedItem) // 修改为后一个页面
              break
            }
          }
          /*
          * 否则前移
          */
        } else {
          for (var k = 0; k < oldArr.length; ++k) {
            if (oldArr[k].name === targetName) {
              --k
              for (; k > -1; --k) {
                if (oldArr[k] !== targetName) {
                  this.myActiveClickedItem = oldArr[k].name
                  this.modifyPath(this.myActiveClickedItem) // 修改为前一个页面
                  return
                }
              }
            }
          }
        }
      } else {
        this.myClickedItems = newArr
        /*
         * 如果标签已经没有了
         */
        if (newArr.length === 0) {
          this.myActiveClickedItem = null
          this.modifyPath(this.leftMenuWelcome) // 修改为欢迎页面
        }
      }
    },
    /**
     * 设定默认页面和默认标签
     */
    myClickedItemAddDefault () {
      if (this.leftMenuCollapse.length > 0) {
        const item = {}
        item.name = this.leftMenuDefaultActive
        item.label = this.leftMenuDefaultName
        this.myClickedItems.push(item)
        this.modifyPath(this.leftMenuDefaultActive)
        this.myActiveClickedItem = item.name
      } else {
        this.modifyPath(this.leftMenuWelcome)
      }
    },
    /**
     * 处理树形菜单的折叠
     */
    leftMenuChangeCollapse () {
      if (this.leftMenuIsCollapse) {
        this.leftMenuIsCollapse = false
        this.leftMessageWidth = 200
        this.leftMenuWidth = 200
      } else {
        this.leftMenuIsCollapse = true
        this.leftMessageWidth = 64
        this.leftMenuWidth = 64
      }
    },
    /**
     * 修改路径
     * @param {String} path 路径
     */
    modifyPath (path) {
      if (this.$router.currentRoute.fullPath !== path) {
        this.$router.push({ path: path })
      }
    },
    topMenuDropped (command) {
      switch (command) {
        case 'a' : /* 退出登录 */ {
          const reference = this.$router.currentRoute.fullPath
          this.$confirm('确定要退出登录吗？', '提示', { confirmButtonText: '退出', cancelButtonText: '取消' }).then(_ => {
            window.sessionStorage.removeItem(this.constant.TOKEN)
            window.sessionStorage.removeItem(this.constant.MENU)
            window.sessionStorage.removeItem(this.constant.STATION)
            this.globalVar.globalStationArray = []
            try {
              this.$axios.get(
                this.constant.GAS_SERVER_PREFIX + '/authorize/login/clearKey',
                {
                  headers: {
                    'reference': reference
                  }
                }
              ).then(res => {
                this.modifyPath('/Login')
                location.reload()
              }).catch(ex => {
                this.modifyPath('/Login')
                location.reload()
              })
            } catch (ex) {
              this.modifyPath('/Login')
              location.reload()
            }
          }).catch(ex => {
            location.reload()
          })
          break
        }
        default: {
          break
        }
      }
    }
  }
}
</script>

<style>
/* 整个页面的区域 */
.MyAppStyle {
  position: absolute; /* 绝对坐标 */
  left: 0; /* X起始坐标 */
  top: 0; /* Y起始坐标 */
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  /* overflow-y: hidden; */
}

/* Header */
.MyTopHeader {
  position: relative;
  left: -3px;
  top: -3px;
  /* background-color: #E3C0D1; */
  padding: 0px;
  margin: 0px;
}

/* 中间的主要区域 */
.MyMiddleMain {
  position: relative;
  left: -3px;
  top: -3px;
  background-color: /* #B3C0D1*/ #FFFFFF;
  padding: 0px;
  margin: 0px;
}

/* 左边测边栏 */
.MyLeftSide {
  background-color: #2f4050;
  color: #FFFFFF;
}
.MyLeftUserMessage {
  background-color: #2f4050;
  text-align: center;
  line-height: 60px;
}
.MyLeftMenuSide {
  text-align: center;
  line-height: 200px;
  overflow-x: hidden;
}

.MyMainSide {
  padding: 0px;
  margin: 0px;
}
.MySubHeader {
  position: relative;
  left: 0px;
  top: 0px;
  background-color: #f0f0f0;
  padding: 0px;
  margin: 0px;
}
.MySubMain {
  position: relative;
  left: 0px;
  top: 0px;
  width: 100%;
  height: 30px; /* 减去this.mySubHeaderHeightString的值 */
  padding: 0px;
  margin: 0px;
}
/* .el-input__icon { */
/*   height: 100%; */
/*   width: 25px; */
/*   text-align: center; */
/*   -webkit-transition: all .3s; */
/*   transition: all .3s; */
/*   line-height: 25px; */
/* } */
/* .el-input--suffix .el-input__inner { */
/*   padding-right: 30px; */
/*   height: 25px; */
/* } */
/* .el-tabs__item { */
/*   padding-top: 8px; */
/*   margin-top: 0px; */
/*   height: 27px; */
/*   font-size: 14px; */
/*   line-height: 0px; */
/*   position: relative; */
/*   top: -1px; */
/*   left: 0px; */
/* } */
/* .el-tabs__item.is-active { */
/*   color: #E3C0D1; */
/*   font-size: 16px; */
/*   background-color: #409EFF; */
/*   height: 29px; */
/* } */
/**
 * 表格的外框(控制有和无)
 */
/* .el-table--border { */
/*   border-right: none; */
/*   border-left: none; */
/* } */
/**
 * 表格的外框(控制线条粗细、什么样的线条(如实线、虚线、点线)、线条颜色)
 */
/*  .el-table--border, .el-table--group { */
/*   border-top: 1px solid #000000; */
/*   border-right: 1px solid #000000; */
/*   border-bottom: 1px solid #000000; */
/*   border-left: 1px solid #000000; */
/* } */
/**
 * 表格的外框(控制线条外边的背景色)
 */
/* .el-table--border::after, .el-table--group::after, .el-table::before { */
/*   content: ''; */
/*   position: absolute; */
/*   background-color: #FFFFFF; */
/*   z-index: 1; */
/* } */
/**
 * 表格的单元格的边框
 */
/* .el-table td.el-table__cell, .el-table th.el-table__cell.is-leaf { */
/*   border-top: none; */
/*   border-right: 1px solid #000000; */
/*   border-bottom: 1px solid #000000; */
/*   border-left: none; */
/* } */
/**
 * 表头的字体
 */
/* .el-table thead { */
/*   color: #000000; */
/*   font-weight: 500; */
/* } */
/**
 * 表格的字体
 */
/* .el-table { */
/*   position: relative; */
/*   overflow: hidden; */
/*   -webkit-box-sizing: border-box; */
/*   box-sizing: border-box; */
/*   -webkit-box-flex: 1; */
/*   -ms-flex: 1; */
/*   flex: 1; */
/*   width: 100%; */
/*   max-width: 100%; */
/*   font-size: 14px; */
/*   color: #000000; */
/* } */
.el-tabs__item {
  padding-top: 8px;
  margin-top: 0px;
  height: 27px;
  font-size: 14px;
  line-height: 0px;
  position: relative;
  top: -1px;
  left: 0px;
}
.el-tabs__item.is-active {
  color: #E3C0D1;
  font-size: 16px;
  background-color: #409EFF;
  height: 29px;
}

/*
 * 表单输入框
 */
.el-input--small .el-input__inner {
  height: 28px;
  line-height: 28px;
  width: 190px;
}
.el-input--medium .el-input__inner {
  height: 28px;
  line-height: 28px;
  width: 190px;
}
/*
 * 表单输入框
 */
.el-input__inner {
  background-color: #FFF;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #DCDFE6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  height: 28px;
  line-height: 40px;
  outline: 0;
  padding: 0 0px;
  -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
  transition: border-color .2s cubic-bezier(.645,.045,.355,1);
  width: 100%;
}
.el-input__icon {
  height: 100%;
  width: 25px;
  text-align: center;
  -webkit-transition: all .3s;
  transition: all .3s;
  line-height: 25px;
}
.el-input--suffix .el-input__inner {
  padding-right: 30px;
  height: 25px;
}

/*
 * 选择日期区间的日期控件
 */
.el-range-editor.el-input__inner {
  padding: 0px;
}
/*
 * 选择月的日期控件
 */
.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 190px;
  height: 28px;
}
.el-date-editor--monthrange.el-input, .el-date-editor--monthrange.el-input__inner {
  width: 190px;
  height: 28px;
}
/*
 * 选择日期区间的日期控件
 */
 .el-date-editor--daterange.el-input, .el-date-editor--daterange.el-input__inner, .el-date-editor--timerange.el-input, .el-date-editor--timerange.el-input__inner {
    width: 190px;
    height: 28px;
}
.el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange.el-input__inner {
  height: 28px;
  width: 190px;
}

/*
 * 表单控件提示信息
 */
.el-form-item {
  margin-bottom: 3px;
}
.el-form-item__error {
  color: #F56C6C;
  font-size: 12px;
  line-height: 0;
  padding-top: 3px;
  position: absolute;
  top: 100%;
  left: 0;
}
/*
 * 表单控件之间坚直距离
 */
.el-form-item--medium .el-form-item__content, .el-form-item--medium .el-form-item__label {
  line-height: 30px;
}
.el-date-editor--daterange.el-input, .el-date-editor--daterange.el-input__inner, .el-date-editor--timerange.el-input, .el-date-editor--timerange.el-input__inner {
  width: 190px;
}

/*
 * 表头中的小格
 */
.el-table--border .el-table__cell:first-child .cell {
  padding: 1px;
  margin-right: 2px;
}
/*
 * 表格中的每个小格
 */
.el-table .el-table__cell {
  padding: 1px;
}
/*
 * 表格中的每个小格中的字边距
 */
.el-table .cell {
  padding: 0px;
}
/*
 * 表头
 */
.el-table th.el-table__cell>.cell {
  padding: 2px
}
/*
 * 对话框
 */
/* .el-dialog { */
/*   position: relative; */
/*   margin: 0 auto 50px; */
/*   background: #FFF; */
/*   border-radius: 2px; */
/*   -webkit-box-shadow: 0 1px 3px rgba(0,0,0,.3); */
/*   box-shadow: 0 1px 3px rgba(0,0,0,.3); */
/*   -webkit-box-sizing: border-box; */
/*   box-sizing: border-box; */
/*   width: 90%; */
/* } */
/*
 * 对话框标题
 */
 .el-dialog__header {
    padding: 10px 20px 10px 20px;
}
/*
 * 对话框主要内容
 */
.el-dialog__body {
    padding: 0px 20px 10px 20px;
    color: #606266;
    font-size: 14px;
    word-break: break-all;
}
.el-upload-dragger {
  background-color: #fff;
  border: none;
  width: 98px;
  height: 28px;
  text-align: center;
  cursor: pointer;
  overflow: hidden;
}
</style>
