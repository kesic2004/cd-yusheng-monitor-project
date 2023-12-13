<template>
  <!--整个页面-->
  <div style="padding: 0px; margin: 0px">
    <!--主要部分 border: solid; border-width: 1pt; border-color: aqua;-->
    <el-container direction="vertical" style="position: relative; left: 0px; top: 0px; width: calc(100% - 3px); height: 100%; padding: 0px; margin: 0px;">
      <!--表单 border-bottom: solid; border-width: 1pt; border-color: aqua;-->
      <el-header :height="queryHeightString" style="position: relative; left: 3px; top: 0px; width: calc(100% - 3px); background-color: #FFFFFF;">
        <el-form status-icon ref="queryForm" label-width="130px" :model="queryForm" :rules="queryRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 0px;">
            <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="clientname" label="门店名称" size="medium">
                <el-input type="text" size="medium" v-model="queryForm.clientname" placeholder="请填写门店名称" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
              </el-form-item>
            </el-col>
            <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
            </el-col>
            <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
              <el-button round @click="executeQuery('queryForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">查询</el-button>
              <el-button round @click="executeReset('queryForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
            </el-col>
          </el-row>
        </el-form>
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 0px;">
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
            <el-button round @click="tbl_add_store()" size="small" style="margin-left: 6px;height: 24px; width:60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
          </el-col>
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
        </el-row>
      </el-header>
      <!-- style="border: solid; border-color: red; border-width: 2pt;" -->
      <el-main>
        <el-table
          border
          highlight-current-row
          highlight-selection-row
          empty-text="目前暂时没有可查询的门店"
          v-loading="tableLoading"
          element-loading-text="正在查询数据"
          :data="tableData"
          :cell-style="tblCellStyle"
          style="overflow-x: hidden; overflow-y: hidden; width: 712px;">
          <el-table-column prop="clientname" width="150" min-width="150" :show-overflow-tooltip="true"  align="center" header-align="center"                                     label="客户姓名" />
          <el-table-column prop="clientcode" width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center"                                     label="客户编号" />
          <el-table-column prop="discount"   width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_discount_formatter" label="客户折扣" />
          <el-table-column prop="clienttype" width="100" min-width="100" :show-overflow-tooltip="false" align="center" header-align="center"                                     label="客户类型" />
          <el-table-column prop="contactman" width="100" min-width="100" :show-overflow-tooltip="false" align="center" header-align="center"                                     label="联系人" />
          <el-table-column prop="contacttel" width="100" min-width="100" :show-overflow-tooltip="false" align="center" header-align="center"                                     label="联系电话" />
          <el-table-column                   width="120" min-width="120" :show-overflow-tooltip="false" align="center" header-align="center"                                     label="操作">
            <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
              <el-button round size="mini" icon="el-icon-setting" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_edit_store(gasOperator)" />
              <el-button round size="mini" icon="el-icon-delete" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_remove_store(gasOperator)" />
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer v-if="queryCount > 0" :height="pageHeightString">
        <el-pagination hide-on-single-page v-if="queryCount > 0" layout="sizes, prev, pager, next" :current-page.sync="queryPagination.current" :page-sizes="selectableQueryPageNumberArray" :page-size="queryPagination.size" :total="queryCount"
          @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
      </el-footer>
    </el-container>
    <!--新增门店对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_add_dialog_only"
      :show-close="addStore.showClose"
      :style="{ width: addStore.digWidth + 'px' }"
      :title="addStore.title"
      :visible.sync="addStore.visible"
      :width="addStore.width"
      style="margin-left: 300px">
      <div :style="{ width: addStore.divWidth + 'px', height: addStore.divHeight + 'px' }">
        <el-form status-icon ref="addStoreForm" label-width="125px" :model="addStoreForm" :rules="addStoreRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clientname" label="客户姓名" size="medium">
                <el-input clearable type="text" v-model="addStoreForm.clientname" placeholder="请输入客户姓名" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clientcode" label="客户编号" size="medium">
                <el-input clearable type="text" v-model="addStoreForm.clientcode" placeholder="请输入客户编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="discount" label="客户折扣" size="medium">
                <el-input clearable type="text" v-model="addStoreForm.discount" placeholder="请输入客户折扣" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clienttype" label="客户类型" size="medium">
                <el-select clearable v-model="addStoreForm.clienttype" placeholder="请选择客户类型" no-data-text="暂时没有可选择的客户类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableClienttypeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="contactman" label="联系人" size="medium">
                <el-input clearable type="text" v-model="addStoreForm.contactman" placeholder="请输入联系人" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="contacttel" label="联系电话" size="medium">
                <el-input clearable type="text" v-model="addStoreForm.contacttel" placeholder="请输入联系电话" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: addStore.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_add_store_submit('addStoreForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">保存</el-button>
          <el-button round @click="tbl_add_store_reset('addStoreForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
    <!--修改门店对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_upd_dialog_only"
      :show-close="updStore.showClose"
      :style="{ width: updStore.digWidth + 'px' }"
      :title="updStore.title"
      :visible.sync="updStore.visible"
      :width="updStore.width"
      style="margin-left: 300px">
      <div :style="{ width: updStore.divWidth + 'px', height: updStore.divHeight + 'px' }">
        <el-form status-icon ref="updStoreForm" label-width="125px" :model="updStoreForm" :rules="updStoreRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clientname" label="客户姓名" size="medium">
                <el-input clearable type="text" v-model="updStoreForm.clientname" placeholder="请输入客户姓名" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clientcode" label="客户编号" size="medium">
                <el-input clearable type="text" v-model="updStoreForm.clientcode" placeholder="请输入客户编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="discount" label="客户折扣" size="medium">
                <el-input clearable type="text" v-model="updStoreForm.discount" placeholder="请输入客户折扣" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="clienttype" label="客户类型" size="medium">
                <el-select clearable v-model="updStoreForm.clienttype" placeholder="请选择客户类型" no-data-text="暂时没有可选择的客户类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableClienttypeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="contactman" label="联系人" size="medium">
                <el-input clearable type="text" v-model="updStoreForm.contactman" placeholder="请输入联系人" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="contacttel" label="联系电话" size="medium">
                <el-input clearable type="text" v-model="updStoreForm.contacttel" placeholder="请输入联系电话" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: updStore.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_upd_store_submit('updStoreForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          <el-button round @click="tbl_upd_store_reset('updStoreForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'StationStoreManagerment',
  data () {
    return {
      /**
       * 查询表单
       */
      queryForm: {
        clientname: null
      },
      /**
       * 表单校验
       */
      queryRules: {},
      /**
       * 查询分页参数
       */
      queryPagination: {
        current: 1, /* 当前第xxx页 */
        size: 10 /* 每一页数据条数，默认值是50条数据 */
      },
      /**
       * 总数据条数
       */
      queryCount: 0,
      /**
       * 表格数据
       */
      tableData: [],
      tableLoading: false,
      /**
       * 可选择的分页数
       */
      selectableQueryPageNumberArray: [10, 20, 30, 40, 50, 60, 70, 80, 90],
      /**
       * 可选择的客户类型
       */
      selectableClienttypeArray: [],
      /*
       * 对话框属性起
       */
      /**
       * 新增门店对话框
       */
      addStore: {
        title: '新增门店', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 210, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭新增门店' // 关闭对话框时的提示
      },
      /**
       * 新增门店对话框的杂项
       */
      addStoreMisc: {},
      /**
       * 新增门店表单
       */
      addStoreForm: {
        clientname: null, // 客户姓名
        clientcode: null, // 客户编号
        discount: null, // 客户折扣
        clienttype: null, // 客户类型
        contactman: null, // 联系人
        contacttel: null // 联系电话
      },
      /**
       * 新增门店表单校验
       */
      addStoreRules: {
        discount: [
          {
            required: true,
            message: '客户折扣必须是数值',
            validator: (rule, value, callback) => {
              try {
                const num = parseFloat(value)
                if (isNaN(num)) {
                  callback(new Error('客户折扣必须是数值'))
                  return
                }
                if (Number.isFinite(num) && num >= 0.0) {
                  callback()
                  return
                }
                callback(new Error('客户折扣必须是数值'))
              } catch (ex) {
                callback(new Error('客户折扣必须是数值'))
              }
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 修改门店对话框
       */
      updStore: {
        title: '修改门店', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 210, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭修改门店' // 关闭对话框时的提示
      },
      /**
       * 修改门店对话框的杂项
       */
      updStoreMisc: {},
      /**
       * 修改门店表单
       */
      updStoreForm: {
        id: null, // 主键
        clientname: null, // 客户姓名
        clientcode: null, // 客户编号
        discount: null, // 客户折扣
        clienttype: null, // 客户类型
        contactman: null, // 联系人
        contacttel: null, // 联系电话
        $index: null // 下标
      },
      /**
       * 修改门店表单校验
       */
      updStoreRules: {
        discount: [
          {
            required: true,
            message: '客户折扣必须是数值',
            validator: (rule, value, callback) => {
              try {
                const num = parseFloat(value)
                if (isNaN(num)) {
                  callback(new Error('客户折扣必须是数值'))
                  return
                }
                if (Number.isFinite(num) && num >= 0.0) {
                  callback()
                  return
                }
                callback(new Error('客户折扣必须是数值'))
              } catch (ex) {
                callback(new Error('客户折扣必须是数值'))
              }
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 删除门店
       */
      delStore: {
        confirmTitle: '是否删除门店' // 关闭对话框时的提示
      },
      /*
       * 对话框属性止
       */
      /**
       * header的高
       */
      queryHeight: 63,
      /**
       * footer的高
       */
      pageHeight: 39
    }
  },
  computed: {
    queryHeightString: function () {
      return this.queryHeight + 'px'
    },
    pageHeightString: function () {
      return this.pageHeight + 'px'
    }
  },
  mounted () {
    this.executeQuery('queryForm')
    this.initialData()
  },
  methods: {
    executeQuery (formName) {
      const localForm = {
        clientname: (this.queryForm.clientname === null || this.queryForm.clientname.length === 0) ? null : this.queryForm.clientname.trim(),
        size: this.queryPagination.size,
        page: this.queryPagination.current
      }
      this.tableData = []
      this.queryCount = 0
      this.tableLoading = true
      this.$axios.postForm(this.constant.GAS_SERVER_PREFIX + '/clientuser/clientuser/queryStoreByStationIdsAndClientName', localForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        this.tableLoading = false
        if (res.status === 200) {
          this.tableData = res.data.items
          this.queryCount = res.data.totalCount
        } else {
          this.tableData = []
          this.queryCount = 0
        }
      }).catch(ex => {
        this.tableLoading = false
        this.tableData = []
        this.queryCount = 0
      })
    },
    /**
     * 重置表单
     * @param {String} formName 表单名
     */
    executeReset (formName) {
      this.$refs[formName].resetFields() // 或者：this.$refs.表单的引用信息.resetFields()
      this.queryPagination.current = 1
    },
    /**
     * 每页条数变化时
     * @param {Integer} size 每页条数
     */
    paginationSizeChange (size) {
      this.queryPagination.size = size
      this.executeQuery('queryForm')
    },
    /**
     * 当前页数变化时
     * @param {Integer} current
     */
    paginationCurrentChange (current) {
      this.queryPagination.current = current
      this.executeQuery('queryForm')
    },
    /*
     * 删除行
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_remove_store ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.$confirm(this.delStore.confirmTitle + row.clientname, '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const localDeleteForm = { id: row.id, stationid: row.stationid }
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/clientuser/clientuser/delSingleClientUser',
          localDeleteForm,
          {
            headers: {
              'reference': this.$router.currentRoute.fullPath
            }
          }
        ).then(res => {
          this.executeQuery('queryForm')
        }).catch(ex => {
          this.executeQuery('queryForm')
        })
      }).catch(_ => {})
    },
    /*
     * 新增数据
     */
    tbl_add_store () {
      this.addStore.visible = true
    },
    /**
     * 编辑数据
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_edit_store ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.updStoreForm.id = row.id
      this.updStoreForm.clientname = row.clientname
      this.updStoreForm.clientcode = row.clientcode
      this.updStoreForm.discount = row.discount
      this.updStoreForm.clienttype = row.clienttype
      this.updStoreForm.contactman = row.contactman
      this.updStoreForm.contacttel = row.contacttel
      this.updStoreForm.$index = $index
      this.updStore.visible = true
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_discount_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(cellValue).toFixed(2)
      }
      return null
    },
    initialData () {
      this.$axios.get(this.constant.GAS_SERVER_PREFIX + '/systemData/systemdata/getByCategoryId?categoryId=11', { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        if (res.status === 200 && Array.isArray(res.data)) {
          const localClientTypeArray = Array(res.data.length)
          for (var i = 0; i < res.data.length; ++i) {
            localClientTypeArray[i] = {
              value: res.data[i].name,
              label: res.data[i].name
            }
          }
          this.selectableClienttypeArray = localClientTypeArray
        }
      }).catch(ex => {
        console.log(ex)
      })
    },
    /**
     * 对话框方法开始
     */
    tbl_before_hide_add_dialog_only (ok) {
      this.$confirm(this.addStore.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        this.$refs['addStoreForm'].resetFields()
        ok()
      }).catch(_ => {})
    },
    tbl_add_store_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localAddStoreForm = {
          clientname: this.addStoreForm.clientname,
          clientcode: this.addStoreForm.clientcode,
          discount: this.addStoreForm.discount === null ? null : parseFloat(this.addStoreForm.discount),
          clienttype: this.addStoreForm.clienttype,
          contactman: this.addStoreForm.contactman,
          contacttel: this.addStoreForm.contacttel,
          stationid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/clientuser/clientuser/addSingleClientUser', localAddStoreForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data !== null && res.data !== '') {
            this.tableData.unshift(res.data)
            for (; this.tableData.length > this.queryPagination.size;) {
              this.tableData.pop()
            }
            this.$refs['addStoreForm'].resetFields()
            this.addStore.visible = false
          }
        }).catch(ex => {
          this.$message('新增失败')
        })
        return true
      })
    },
    tbl_add_store_reset (formName) {
      this.$refs[formName].resetFields()
    },
    tbl_before_hide_upd_dialog_only (ok) {
      this.$confirm(this.updStore.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
      }).catch(_ => {})
    },
    tbl_upd_store_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localUpdStoreForm = {
          id: this.updStoreForm.id,
          clientname: this.updStoreForm.clientname,
          clientcode: this.updStoreForm.clientcode,
          discount: this.updStoreForm.discount === null ? null : parseFloat(this.updStoreForm.discount),
          clienttype: this.updStoreForm.clienttype,
          contactman: this.updStoreForm.contactman,
          contacttel: this.updStoreForm.contacttel,
          stationid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        const localIndex = this.updStoreForm.$index
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/clientuser/clientuser/updSingleClientUser', localUpdStoreForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            if (res.data === null || res.data === '') {
              this.executeQuery('queryForm')
            } else {
              this.tableData[localIndex].id = res.data.id
              this.tableData[localIndex].clientname = res.data.clientname
              this.tableData[localIndex].clientcode = res.data.clientcode
              this.tableData[localIndex].discount = res.data.discount
              this.tableData[localIndex].clienttype = res.data.clienttype
              this.tableData[localIndex].contactman = res.data.contactman
              this.tableData[localIndex].contacttel = res.data.contacttel
            }
            this.updStore.visible = false
          }
        }).catch(ex => {
          this.$message('修改失败')
        })
        return true
      })
    },
    tbl_upd_store_reset (formName) {
      this.$refs[formName].resetFields()
    },
    /**
     * 对话框方法结束
     */
    /**
     * @param row 所在行的原始数据
     * @param column 所在列的定义数据
     * @param rowIndex 从0开始的第多少行
     * @param columnIndex 从0开始的第多少列
     */
    tblCellStyle ({row, column, rowIndex, columnIndex}) {
    }
  }
}
</script>

<style>
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
/* .el-table--border, .el-table--group { */
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
</style>
