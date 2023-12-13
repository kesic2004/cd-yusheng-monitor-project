<template>
  <!--整个页面，暂不忙做-->
  <div style="padding: 0px; margin: 0px">
    <!--主要部分 border: solid; border-width: 1pt; border-color: aqua;-->
    <el-container direction="vertical" style="position: relative; left: 0px; top: 0px; width: calc(100% - 3px); height: 100%; padding: 0px; margin: 0px;">
      <!-- <span style="font-size: 12px; position: relative; text-align: center;">功能正在开发中</span> -->
      <el-header :height="queryHeightString" style="position: relative; left: 3px; top: 3px; width: calc(100% - 3px); background-color: #FFFFFF;">
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 0px;">
          <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
            <el-button round @click="tbl_add_price()" size="small" style="margin-left: 6px;height: 24px; width:60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
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
          empty-text="目前暂时没有可查询的价格"
          v-loading="tableLoading"
          element-loading-text="正在查询数据"
          :data="tableData"
          :cell-style="tblCellStyle"
          style="overflow-x: hidden; overflow-y: hidden; width: 411px;">
          <el-table-column prop="name"    width="150" min-width="150" :show-overflow-tooltip="true"  align="left"   header-align="center"                                  label="名称" />
          <el-table-column prop="price"   width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_price_formatter" label="价格" />
          <el-table-column prop="unit"    width="70"  min-width="70"  :show-overflow-tooltip="false" align="center" header-align="center"                                  label="单位" />
          <el-table-column                width="120" min-width="120" :show-overflow-tooltip="false" align="center" header-align="center"                                  label="操作">
            <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
              <el-button round size="mini" icon="el-icon-setting" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_edit_price(gasOperator)" />
              <el-button round size="mini" icon="el-icon-delete" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_remove_price(gasOperator)" />
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
    <!--新增价格对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_add_dialog_only"
      :show-close="addPrice.showClose"
      :style="{ width: addPrice.digWidth + 'px' }"
      :title="addPrice.title"
      :visible.sync="addPrice.visible"
      :width="addPrice.width"
      style="margin-left: 300px">
      <div :style="{ width: addPrice.divWidth + 'px', height: addPrice.divHeight + 'px' }">
        <el-form status-icon ref="addPriceForm" label-width="125px" :model="addPriceForm" :rules="addPriceRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="name" label="名称" size="medium">
                <el-input clearable type="text" v-model="addPriceForm.name" placeholder="请输入名称" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="price" label="价格" size="medium">
                <el-input clearable type="text" v-model="addPriceForm.price" placeholder="请输入价格" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="unit" label="单位" size="medium">
                <el-input clearable type="text" v-model="addPriceForm.unit" placeholder="请输入单位" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: addPrice.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_add_price_submit('addPriceForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">保存</el-button>
          <el-button round @click="tbl_add_price_reset('addPriceForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
    <!--修改价格对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :before-close="tbl_before_hide_upd_dialog_only"
      :show-close="updPrice.showClose"
      :style="{ width: updPrice.digWidth + 'px' }"
      :title="updPrice.title"
      :visible.sync="updPrice.visible"
      :width="updPrice.width"
      style="margin-left: 300px">
      <div :style="{ width: updPrice.divWidth + 'px', height: updPrice.divHeight + 'px' }">
        <el-form status-icon ref="updPriceForm" label-width="125px" :model="updPriceForm" :rules="updPriceRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="name" label="名称" size="medium">
                <el-input clearable type="text" v-model="updPriceForm.name" placeholder="请输入名称" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="price" label="价格" size="medium">
                <el-input clearable type="text" v-model="updPriceForm.price" placeholder="请输入价格" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="unit" label="单位" size="medium">
                <el-input clearable type="text" v-model="updPriceForm.unit" placeholder="请输入单位" size="medium" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: updPrice.divWidth + 'px' }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_upd_price_submit('updPriceForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          <el-button round @click="tbl_upd_price_reset('updPriceForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'StationPriceManagement',
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
      /*
       * 对话框属性起
       */
      /**
       * 新增价格对话框
       */
      addPrice: {
        title: '新增价格', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭新增价格' // 关闭对话框时的提示
      },
      /**
       * 新增操作叫对话框的杂项
       */
      addPriceMisc: {},
      /**
       * 新增价格表单
       */
      addPriceForm: {
        name: null, // 价格
        price: null, // 价格
        unit: null // 单位
      },
      /**
       * 新增价格表单校验
       */
      addPriceRules: {
        price: [
          {
            required: true,
            message: '价格必须填数字',
            validator: (rule, value, callback) => {
              try {
                const num = parseFloat(value)
                if (isNaN(num)) {
                  callback(new Error('价格必须填数字'))
                  return
                }
                if (Number.isFinite(num) && num >= 0.0) {
                  callback()
                  return
                }
                callback(new Error('价格必须填数字'))
              } catch (ex) {
                callback(new Error('价格必须填数字'))
              }
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 修改价格对话框
       */
      updPrice: {
        title: '修改价格', // 对话框标题
        visible: false, // 对话框显示状态
        width: '30%', // 对话框的相对宽度
        digWidth: 1450, // 对度框宽度(实际宽度×90%)
        divWidth: 390, // 对话框主要区域宽度
        divHeight: 110, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        confirmTitle: '是否关闭修改价格' // 关闭对话框时的提示
      },
      /**
       * 修改价格对话框的杂项
       */
      updPriceMisc: {},
      /**
       * 修改价格表单
       */
      updPriceForm: {
        spid: null, // 主键
        name: null, // 价格
        price: null, // 价格
        unit: null, // 单位
        stationid: null, // 站点
        $index: null // 下标
      },
      /**
       * 修改价格表单校验
       */
      updPriceRules: {
        price: [
          {
            required: true,
            message: '价格必须填数字',
            validator: (rule, value, callback) => {
              try {
                const num = parseFloat(value)
                if (isNaN(num)) {
                  callback(new Error('价格必须填数字'))
                  return
                }
                if (Number.isFinite(num) && num >= 0.0) {
                  callback()
                  return
                }
                callback(new Error('价格必须填数字'))
              } catch (ex) {
                callback(new Error('价格必须填数字'))
              }
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 删除价格
       */
      delPrice: {
        confirmTitle: '是否删除价格' // 关闭对话框时的提示
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
        stationid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
      }
      this.tableData = []
      this.queryCount = 0
      this.tableLoading = true
      this.$axios.postForm(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationprice/queryByStationId', localForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
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
    tbl_price_formatter (row, column, cellValue, index) {
      return Number.isFinite(cellValue) ? Number.parseFloat(cellValue).toFixed(2) : null
    },
    /**
     * 新增价格
     */
    tbl_add_price () {
      this.addPrice.visible = true
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
    tbl_edit_price ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.updPriceForm.spid = row.spid
      this.updPriceForm.name = row.name
      this.updPriceForm.price = row.price
      this.updPriceForm.unit = row.unit
      this.updPriceForm.stationid = row.stationid
      this.updPriceForm.$index = $index
      this.updPrice.visible = true
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
    tbl_remove_price ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.$confirm(this.delPrice.confirmTitle, '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const localDeleteForm = { spid: row.spid, stationid: row.stationid }
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationprice/removePriceById',
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
    tbl_before_hide_add_dialog_only (ok) {
      this.$confirm(this.addPrice.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        this.$refs['addPriceForm'].resetFields()
        ok()
      }).catch(_ => {})
    },
    tbl_add_price_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localAddPriceForm = {
          name: this.addPriceForm.name,
          price: this.addPriceForm.price,
          unit: this.addPriceForm.unit,
          stationid: this.globalVar.globalStationArray.length === 1 ? this.globalVar.globalStationArray[0].value : null
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationprice/addSinglePrice', localAddPriceForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data !== null && res.data !== '') {
            this.tableData.unshift(res.data)
            this.$refs['addPriceForm'].resetFields()
            this.addPrice.visible = false
          }
        }).catch(ex => {
          this.$message('新增失败')
        })
        return true
      })
    },
    tbl_add_price_reset (formName) {
      this.$refs[formName].resetFields()
    },
    tbl_before_hide_upd_dialog_only (ok) {
      this.$confirm(this.updPrice.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
      }).catch(_ => {})
    },
    tbl_upd_price_submit (formName) {
      console.log(this.updPriceForm)
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localUpdPriceForm = {
          spid: this.updPriceForm.spid,
          name: this.updPriceForm.name,
          price: this.updPriceForm.price,
          unit: this.updPriceForm.unit,
          stationid: this.updPriceForm.stationid
        }
        const localIndex = this.updPriceForm.$index
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationprice/updSinglePrice', localUpdPriceForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            if (res.data === null || res.data === '') {
              this.executeQuery()
            } else {
              this.tableData[localIndex].spid = res.data.spid
              this.tableData[localIndex].name = res.data.name
              this.tableData[localIndex].price = res.data.price
              this.tableData[localIndex].unit = res.data.unit
            }
            this.updPrice.visible = false
          }
        }).catch(ex => {
          this.$message('修改失败')
        })
        return true
      })
    },
    tbl_upd_price_reset (formName) {
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
