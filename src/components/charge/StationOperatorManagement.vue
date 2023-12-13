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
              <el-form-item prop="name" label="操作员名称" size="medium">
                <el-input type="text" size="medium" v-model="queryForm.name" placeholder="请填写操作员名称" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
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
            <el-button round @click="tbl_add_operator()" size="small" style="margin-left: 6px;height: 24px; width:60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
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
          empty-text="目前暂时没有可查询的操作员"
          v-loading="tableLoading"
          element-loading-text="正在查询数据"
          :data="tableData"
          :cell-style="tblCellStyle"
          style="overflow-x: hidden; overflow-y: hidden; width: 411px;">
          <el-table-column prop="name"    width="150" min-width="150" :show-overflow-tooltip="true"  align="left"   header-align="center"                                    label="操作员" />
          <el-table-column prop="code"    width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center"                                    label="操作员码" />
          <el-table-column prop="enabled" width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_enabled_formatter" label="状态" />
          <el-table-column                width="120" min-width="120" :show-overflow-tooltip="false" align="center" header-align="center"                                    label="操作">
            <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
              <el-button round size="mini" icon="el-icon-setting" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_edit_operator(gasOperator)" />
              <el-button round size="mini" icon="el-icon-delete" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_remove_operator(gasOperator)" />
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer v-if="queryCount > 0" :height="pageHeightString">
        <el-pagination hide-on-single-page v-if="queryCount > 0" layout="sizes, prev, pager, next" :current-page.sync="queryPagination.current" :page-sizes="selectableQueryPageNumberArray" :page-size="queryPagination.size" :total="queryCount"
          @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
      </el-footer>
    </el-container>
    <!--新增操作员对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_add_dialog_only"
      :show-close="addOperator.showClose"
      :style="{ width: addOperator.digWidth + 'px' }"
      :title="addOperator.title"
      :visible.sync="addOperator.visible"
      :width="addOperator.width"
      style="margin-left: 300px">
      <div :style="{ width: addOperator.divWidth + 'px', height: addOperator.divHeight + 'px' }">
        <el-form status-icon ref="addOperatorForm" label-width="125px" :model="addOperatorForm" :rules="addOperatorRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="name" label="操作员姓名" size="medium">
                <el-input clearable type="text" v-model="addOperatorForm.name" placeholder="请输入操作员姓名" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="code" label="操作员编号" size="medium">
                <el-input clearable type="text" v-model="addOperatorForm.code" placeholder="请输入操作员编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="enabled" label="操作员状态" size="medium">
                <el-select clearable v-model="addOperatorForm.enabled" placeholder="请选择操作员状态" no-data-text="暂时没有可选择的操作员状态" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableOperatorStatus" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: addOperator.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_add_operator_submit('addOperatorForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">保存</el-button>
          <el-button round @click="tbl_add_operator_reset('addOperatorForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
    <!--修改操作员对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_upd_dialog_only"
      :show-close="updOperator.showClose"
      :style="{ width: updOperator.digWidth + 'px' }"
      :title="updOperator.title"
      :visible.sync="updOperator.visible"
      :width="updOperator.width"
      style="margin-left: 300px">
      <div :style="{ width: updOperator.divWidth + 'px', height: updOperator.divHeight + 'px' }">
        <el-form status-icon ref="updOperatorForm" label-width="125px" :model="updOperatorForm" :rules="updOperatorRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="name" label="操作员姓名" size="medium">
                <el-input clearable type="text" v-model="updOperatorForm.name" placeholder="请输入操作员姓名" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="code" label="操作员编号" size="medium">
                <el-input clearable type="text" v-model="updOperatorForm.code" placeholder="请输入操作员编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="enabled" label="操作员类型" size="medium">
                <el-select clearable v-model="updOperatorForm.enabled" placeholder="请选择操作员类型" no-data-text="暂时没有可选择的操作员类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableOperatorStatus" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: updOperator.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_upd_operator_submit('updOperatorForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          <el-button round @click="tbl_upd_operator_reset('updOperatorForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'StationOperatorManagerment',
  data () {
    return {
      /**
       * 查询表单
       */
      queryForm: {
        name: null
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
       * 可选择的操作员状态
       */
      selectableOperatorStatus: [ { value: 0, label: '禁用' }, { value: 1, label: '可用' } ],
      /*
       * 对话框属性起
       */
      /**
       * 新增操作员对话框
       */
      addOperator: {
        title: '新增操作员', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭新增操作员' // 关闭对话框时的提示
      },
      /**
       * 新增操作叫对话框的杂项
       */
      addOperatorMisc: {},
      /**
       * 新增操作员表单
       */
      addOperatorForm: {
        name: null, // 操作员姓名
        code: null, // 操作员编号
        enabled: null // 操作员状态
      },
      /**
       * 新增操作员表单校验
       */
      addOperatorRules: {
        'enabled': [
          {
            required: true,
            message: '操作员状态是必选项',
            validator: (rule, value, callback) => {
              if (Number.isInteger(value)) {
                for (var i = 0; i < this.selectableOperatorStatus.length; ++i) {
                  if (this.selectableOperatorStatus[i].value === value) {
                    callback()
                  }
                }
              }
              callback(new Error('操作员状态是必选项'))
            }
          }
        ]
      },
      /**
       * 修改操作员对话框
       */
      updOperator: {
        title: '修改操作员', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭修改操作员' // 关闭对话框时的提示
      },
      /**
       * 修改操作员对话框的杂项
       */
      updOperatorMisc: {},
      /**
       * 修改操作员表单
       */
      updOperatorForm: {
        id: null, // 主键
        name: null, // 操作员姓名
        code: null, // 操作员编号
        enabled: null, // 操作员状态
        $index: null // 下标
      },
      /**
       * 修改操作员表单校验
       */
      updOperatorRules: {
        'enabled': [
          {
            required: true,
            message: '操作员状态是必选项',
            validator: (rule, value, callback) => {
              if (Number.isInteger(value)) {
                for (var i = 0; i < this.selectableOperatorStatus.length; ++i) {
                  if (this.selectableOperatorStatus[i].value === value) {
                    callback()
                  }
                }
              }
              callback(new Error('操作员状态是必选项'))
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 删除操作员
       */
      delOperator: {
        confirmTitle: '是否删除操作员' // 关闭对话框时的提示
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
  },
  methods: {
    executeQuery (formName) {
      const localForm = {
        clientname: (this.queryForm.name === null || this.queryForm.name.length === 0) ? null : this.queryForm.name.trim(),
        size: this.queryPagination.size,
        page: this.queryPagination.current
      }
      this.tableData = []
      this.queryCount = 0
      this.tableLoading = true
      this.$axios.postForm(this.constant.GAS_SERVER_PREFIX + '/operator/operator/queryOperatorByStationIdsAndOperatorName', localForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
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
    tbl_remove_operator ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.$confirm(this.delOperator.confirmTitle + row.name, '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const localDeleteForm = { operatorid: row.operatorid, sid: row.sid }
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/operator/operator/delSingleOperator',
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
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_enabled_formatter (row, column, cellValue, index) {
      if (Number.isInteger(cellValue)) {
        for (var i = 0; i < this.selectableOperatorStatus.length; ++i) {
          if (this.selectableOperatorStatus[i].value === cellValue) {
            return this.selectableOperatorStatus[i].label
          }
        }
      }
      return null
    },
    tbl_add_operator () {
      // this.addOperatorForm.name = null
      // this.addOperatorForm.code = null
      // this.addOperatorForm.enabled = null
      this.addOperator.visible = true
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
    tbl_edit_operator ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.updOperatorForm.id = row.operatorid
      this.updOperatorForm.name = row.name
      this.updOperatorForm.code = row.code
      this.updOperatorForm.enabled = row.enabled
      this.updOperatorForm.$index = $index
      this.updOperator.visible = true
    },
    /**
     * 对话框方法开始
     */
    tbl_before_hide_add_dialog_only (ok) {
      this.$confirm(this.addOperator.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        this.$refs['addOperatorForm'].resetFields()
        ok()
      }).catch(_ => {})
    },
    tbl_add_operator_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localAddOperatorForm = {
          name: this.addOperatorForm.name,
          code: this.addOperatorForm.code,
          enabled: this.addOperatorForm.enabled,
          sid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/operator/operator/addSingleOperator', localAddOperatorForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data !== null && res.data !== '') {
            this.tableData.unshift(res.data)
            for (; this.tableData.length > this.queryPagination.size;) {
              this.tableData.pop()
            }
            this.$refs['addOperatorForm'].resetFields()
            this.addOperator.visible = false
          }
        }).catch(ex => {
          this.$message('新增失败')
        })
        return true
      })
    },
    tbl_add_operator_reset (formName) {
      this.$refs[formName].resetFields()
    },
    tbl_before_hide_upd_dialog_only (ok) {
      this.$confirm(this.updOperator.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
      }).catch(_ => {})
    },
    tbl_upd_operator_submit (formName) {
      console.log(this.updOperatorForm)
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localUpdOperatorForm = {
          operatorid: this.updOperatorForm.id,
          name: this.updOperatorForm.name,
          code: this.updOperatorForm.code,
          enabled: Number.isInteger(this.updOperatorForm.enabled) ? this.updOperatorForm.enabled : null,
          sid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        const localIndex = this.updOperatorForm.$index
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/operator/operator/updSingleOperator', localUpdOperatorForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            if (res.data === null || res.data === '') {
              this.executeQuery('queryForm')
            } else {
              this.tableData[localIndex].operatorid = res.data.operatorid
              this.tableData[localIndex].name = res.data.name
              this.tableData[localIndex].code = res.data.code
              this.tableData[localIndex].enabled = res.data.enabled
            }
            this.updOperator.visible = false
          }
        }).catch(ex => {
          this.$message('修改失败')
        })
        return true
      })
    },
    tbl_upd_operator_reset (formName) {
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
</style>
