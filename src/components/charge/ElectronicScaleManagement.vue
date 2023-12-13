<template>
  <!--整个页面 border: solid; border-width: 1pt; border-color: aqua;-->
  <div style="padding: 0px; margin: 0px">
    <!--主要部分-->
    <el-container direction="vertical" style="position: relative; left: 0px; top: 0px; width: calc(100% - 3px); height: 100%; padding: 0px; margin: 0px;">
      <el-header :height="queryHeightString" style="position: relative; left: 3px; top: 3px; width: calc(100% - 3px); background-color: #FFFFFF;">
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 0px;">
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
            <el-button round @click="tbl_add_scale()" size="small" style="margin-left: 6px;height: 24px; width:60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
          </el-col>
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
        </el-row>
      </el-header>
      <!-- style="border: solid; border-color: ye; border-width: 2pt;" -->
      <el-main>
        <el-table
          border
          highlight-current-row
          highlight-selection-row
          empty-text="目前暂时没有可查询的站秤"
          v-loading="tableLoading"
          element-loading-text="正在查询数据"
          :data="tableData"
          :cell-style="tblCellStyle"
          style="overflow-x: hidden; overflow-y: hidden; width: 411px;">
          <el-table-column prop="addresscode" width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_addresscode_formatter" label="编号" />
          <el-table-column prop="addressname" width="150" min-width="150" :show-overflow-tooltip="true"  align="left"   header-align="center"                                        label="名称" />
          <el-table-column prop="degrossMode" width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_degrossMode_formatter" label="扣皮方式" />
          <el-table-column                    width="120" min-width="120" :show-overflow-tooltip="false" align="center" header-align="center"                                        label="操作">
            <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
              <el-button round size="mini" icon="el-icon-setting" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_edit_scale(gasOperator)" />
              <el-button round size="mini" icon="el-icon-delete" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_remove_scale(gasOperator)" />
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <!-- style="border: solid; border-color: yellow; border-width: 2pt;" -->
      <el-footer :height="pageHeightString">
        <el-pagination v-if="queryCount > 0" layout="sizes, prev, pager, next" :current-page.sync="queryPagination.current" :page-sizes="selectableQueryPageNumberArray" :page-size="queryPagination.size" :total="queryCount"
          @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
     </el-footer>
    </el-container>
    <!--新增站秤对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_add_dialog_only"
      :show-close="addScale.showClose"
      :style="{ width: addScale.digWidth + 'px' }"
      :title="addScale.title"
      :visible.sync="addScale.visible"
      :width="addScale.width"
      style="margin-left: 300px">
      <div :style="{ width: addScale.divWidth + 'px', height: addScale.divHeight + 'px' }">
        <el-form status-icon ref="addScaleForm" label-width="125px" :model="addScaleForm" :rules="addScaleRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="addresscode" label="编号" size="medium">
                <el-input clearable type="text" v-model="addScaleForm.addresscode" placeholder="请输入编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="addressname" label="名称" size="medium">
                <el-input clearable type="text" v-model="addScaleForm.addressname" placeholder="请输入名称" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="degrossMode" label="扣皮方式" size="medium">
                <el-select clearable v-model="addScaleForm.degrossMode" placeholder="请选择扣皮方式" no-data-text="暂时没有可选择的扣皮方式" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableDegrossModeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: addScale.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_add_scale_submit('addScaleForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">保存</el-button>
          <el-button round @click="tbl_add_scale_reset('addScaleForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
    <!--修改站秤对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_upd_dialog_only"
      :show-close="updScale.showClose"
      :style="{ width: updScale.digWidth + 'px' }"
      :title="updScale.title"
      :visible.sync="updScale.visible"
      :width="updScale.width"
      style="margin-left: 300px">
      <div :style="{ width: updScale.divWidth + 'px', height: updScale.divHeight + 'px' }">
        <el-form status-icon ref="updScaleForm" label-width="125px" :model="updScaleForm" :rules="updScaleRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="addresscode" label="编号" size="medium">
                <el-input clearable type="text" v-model="updScaleForm.addresscode" placeholder="请输入编号" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="addressname" label="名称" size="medium">
                <el-input clearable type="text" v-model="updScaleForm.addressname" placeholder="请输入名称" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="degrossMode" label="扣皮方式" size="medium">
                <el-select clearable v-model="updScaleForm.degrossMode" placeholder="请选择扣皮方式" no-data-text="暂时没有可选择的扣皮方式" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableDegrossModeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: updScale.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_upd_scale_submit('updScaleForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          <el-button round @click="tbl_upd_scale_reset('updScaleForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'ElectronicScaleManagement',
  data () {
    return {
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
      /**
       * 表格的加载状态
       */
      tableLoading: false,
      /**
       * 可选择的分页数
       */
      selectableQueryPageNumberArray: [10, 20, 30, 40, 50, 60, 70, 80, 90],
      /**
       * 可选择的扣皮方式
       */
      selectableDegrossModeArray: [ { value: '11', label: '标准扣皮' }, { value: '66', label: '真实扣皮' } ],
      /*
       * 对话框属性起
       */
      /**
       * 新增站秤对话框
       */
      addScale: {
        title: '新增站秤', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭新增站秤' // 关闭对话框时的提示
      },
      /**
       * 新增操作叫对话框的杂项
       */
      addScaleMisc: {},
      /**
       * 新增站秤表单
       */
      addScaleForm: {
        addresscode: null,
        addressname: null,
        degrossMode: null
      },
      /**
       * 新增站秤表单校验
       */
      addScaleRules: {
        addresscode: [
          {
            required: true,
            message: '站秤编号必须填自然数',
            validator: (rule, value, callback) => {
              const i = Number.isInteger(value) ? value : parseInt(value)
              if (Number.isInteger(i)) {
                callback()
                return
              }
              callback(new Error('站秤编号必须填自然数'))
            }
          }
        ],
        degrossMode: [
          {
            required: true,
            message: '扣皮方式错误',
            validator: (rule, value, callback) => {
              for (var i = 0; i < this.selectableDegrossModeArray.length; ++i) {
                if (this.selectableDegrossModeArray[i].value === value) {
                  callback()
                  return
                }
              }
              callback(new Error('扣皮方式错误'))
            }
          }
        ]
      },
      /**
       * 修改站秤对话框
       */
      updScale: {
        title: '修改站秤', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭修改站秤' // 关闭对话框时的提示
      },
      /**
       * 修改站秤对话框的杂项
       */
      updScaleMisc: {},
      /**
       * 修改站秤表单
       */
      updScaleForm: {
        gbid: null,
        stationId: null,
        stationcode: null,
        addresscode: null,
        addressname: null,
        degrossMode: null,
        $index: null // 下标
      },
      /**
       * 修改站秤表单校验
       */
      updScaleRules: {
        addresscode: [
          {
            required: true,
            message: '站秤编号必须填自然数',
            validator: (rule, value, callback) => {
              const i = Number.isInteger(value) ? value : parseInt(value)
              if (Number.isInteger(i)) {
                callback()
                return
              }
              callback(new Error('站秤编号必须填自然数'))
            }
          }
        ],
        degrossMode: [
          {
            required: true,
            message: '扣皮方式错误',
            validator: (rule, value, callback) => {
              for (var i = 0; i < this.selectableDegrossModeArray.length; ++i) {
                if (this.selectableDegrossModeArray[i].value === value) {
                  callback()
                  return
                }
              }
              callback(new Error('扣皮方式错误'))
            }
          }
        ]
      },
      /**
       * 删除站秤
        gbid: null,
        stationId: null
       */
      delScale: {
        confirmTitle: '是否删除站秤' // 关闭对话框时的提示
      },
      /*
       * 对话框属性止
       */
      /**
       * header的高
       */
      queryHeight: 24,
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
    this.executeQuery()
  },
  methods: {
    executeQuery () {
      const localForm = {
        size: this.queryPagination.size,
        page: this.queryPagination.current,
        stationId: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
      }
      this.tableData = []
      this.queryCount = 0
      this.tableLoading = true
      this.$axios.postForm(this.constant.GAS_SERVER_PREFIX + '/businessInformation/gasaddress/queryByStationId', localForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
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
     * 当前页数变化时
     * @param {Integer} current
     */
    paginationCurrentChange (current) {
      this.queryPagination.current = current
      this.executeQuery()
    },
    /**
     * 每页条数变化时
     * @param {Integer} size 每页条数
     */
    paginationSizeChange (size) {
      this.queryPagination.size = size
      this.executeQuery()
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_addresscode_formatter (row, column, cellValue, index) {
      return Number.isInteger(cellValue) ? (cellValue - (cellValue % 2)) / 2 : null
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_degrossMode_formatter (row, column, cellValue, index) {
      for (var i = 0; i < this.selectableDegrossModeArray.length; ++i) {
        if (this.selectableDegrossModeArray[i].value === cellValue) {
          return this.selectableDegrossModeArray[i].label
        }
      }
      return null
    },
    /**
     * 新增站秤
     */
    tbl_add_scale () {
      this.addScale.visible = true
    },
    /**
     * 编辑站秤
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_edit_scale ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.updScaleForm.gbid = row.gbid
      this.updScaleForm.stationId = row.stationId
      this.updScaleForm.stationcode = row.stationcode
      this.updScaleForm.addresscode = Number.isInteger(row.addresscode) ? row.addresscode / 2 : parseInt(row.addresscode) / 2
      this.updScaleForm.addressname = row.addressname
      this.updScaleForm.degrossMode = row.degrossMode
      this.updScaleForm.$index = $index
      this.updScale.visible = true
    },
    /*
     * 删除某个站秤
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_remove_scale ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.$confirm(this.delScale.confirmTitle, '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const localDeleteForm = { gbid: row.gbid, stationId: row.stationId }
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/businessInformation/gasaddress/removeScaleById',
          localDeleteForm,
          {
            headers: {
              'reference': this.$router.currentRoute.fullPath
            }
          }
        ).then(res => {
          this.executeQuery()
        }).catch(ex => {
          this.executeQuery()
        })
      }).catch(_ => {})
    },
    /**
     * 对话框方法开始
     */
    /**
     * 对话框方法开始
     */
    tbl_before_hide_add_dialog_only (ok) {
      this.$confirm(this.addScale.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        this.$refs['addScaleForm'].resetFields()
        ok()
      }).catch(_ => {})
    },
    tbl_add_scale_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localAddScaleForm = {
          addresscode: Number.isInteger(this.addScaleForm.addresscode) ? this.addScaleForm.addresscode * 2 : parseInt(this.addScaleForm.addresscode) * 2,
          addressname: this.addScaleForm.addressname,
          degrossMode: this.addScaleForm.degrossMode,
          stationId: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/gasaddress/addSingleScale', localAddScaleForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data !== null && res.data !== '') {
            this.tableData.unshift(res.data)
            this.$refs['addScaleForm'].resetFields()
            this.addScale.visible = false
          }
        }).catch(ex => {
          this.$message('新增失败')
        })
        return true
      })
    },
    tbl_add_scale_reset (formName) {
      this.$refs[formName].resetFields()
    },
    tbl_before_hide_upd_dialog_only (ok) {
      this.$confirm(this.updScale.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
      }).catch(_ => {})
    },
    tbl_upd_scale_submit (formName) {
      console.log(this.updScaleForm)
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localUpdScaleForm = {
          gbid: this.updScaleForm.gbid,
          stationId: this.updScaleForm.stationId,
          addresscode: Number.isInteger(this.updScaleForm.addresscode) ? this.updScaleForm.addresscode * 2 : parseInt(this.updScaleForm.addresscode) * 2,
          addressname: this.updScaleForm.addressname,
          degrossMode: this.updScaleForm.degrossMode
        }
        const localIndex = this.updScaleForm.$index
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/gasaddress/updSingleScale', localUpdScaleForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            if (res.data === null || res.data === '') {
              this.executeQuery()
            } else {
              this.tableData[localIndex].gbid = res.data.gbid
              this.tableData[localIndex].addresscode = Number.isInteger(res.data.addresscode) ? res.data.addresscode : parseInt(res.data.addresscode)
              this.tableData[localIndex].addressname = res.data.addressname
              this.tableData[localIndex].degrossMode = res.data.degrossMode
            }
            this.updScale.visible = false
          }
        }).catch(ex => {
          this.$message('修改失败')
        })
        return true
      })
    },
    tbl_upd_scale_reset (formName) {
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
