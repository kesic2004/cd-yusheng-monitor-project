<template>
  <!-- <div>灌装记录页面</div> -->
  <el-collapse class="ChargeDocumentsMain">
    <el-header height="170px" class="ChargeDocumentsForm">
      <el-form status-icon ref="queryForm" label-width="80px" :model="queryForm" :rules="queryRules">
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px;padding-top: 2px;">
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="selStationcode" label="站点" size="medium">
              <el-select clearable v-model="queryForm.selStationcode" placeholder="请选择站点" no-data-text="暂时没有可选择的站点" style="padding-left: 0px; padding-right: 0px; width: 190px;">
                <el-option v-for="item in selectableSelStationcodeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="selClientid" label="客户名" size="medium">
              <el-select clearable v-model="queryForm.selClientid" placeholder="请选择客户名" no-data-text="暂时没有可选择的客户名" style="padding-left: 0px; padding-right: 0px; width: 190px;">
                <el-option v-for="item in selectableSelClientidArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="selOperatorid" label="操作员" size="medium">
              <el-select clearable v-model="queryForm.selOperatorid" placeholder="请选择操作员" no-data-text="暂时没有可选择的操作员" style="padding-left: 0px; padding-right: 0px; width: 190px;">
                <el-option v-for="item in selectableSelOperatoridArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; padding: 0px;">
            <el-form-item prop="addresscode" label="秤编号" size="medium">
              <el-input clearable type="text" v-model="queryForm.addresscode" placeholder="输入查询的秤编号" style="padding-left: 0px; padding-right: 0px;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="fscodeType" label="罐装方式" size="medium">
              <el-select clearable v-model="queryForm.fscodeType" placeholder="请选择罐装方式" no-data-text="暂时没有可选择的罐装方式" style="padding-left: 0px; padding-right: 0px;">
                <el-option v-for="item in selectableFscodeTypeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="gxCodeType" label="罐型" size="medium">
              <!--fgxCodeType-->
              <el-select clearable v-model="queryForm.gxCodeType" placeholder="请选择罐型" no-data-text="暂时没有可选择的罐型" style="padding-left: 0px; padding-right: 0px;">
                <el-option v-for="item in selectableGxCodeTypeArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="command" label="完成状态" size="medium">
              <el-select clearable v-model="queryForm.command" placeholder="请选择完成状态" no-data-text="暂时没有可选择的完成状态" style="padding-left: 0px; padding-right: 0px;">
                <el-option v-for="item in selectableCommandArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="curtime" label="时间范围" size="medium">
              <!-- curtime -->
              <el-date-picker clearable v-model="queryForm.curtime" type="datetimerange" size="mini" range-separator="-" start-placeholde="开始时间" end-placeholde="结束时间" align="left" :default-time="selectableDefaultTime" value-format="yyyy-MM-dd HH:mm:ss" style="font-size: 10pt;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="settingweight" label="设重" size="medium">
              <el-input clearable type="text" v-model="queryForm.settingweight" placeholder="输入查询设重" style="padding-left: 0px; padding-right: 0px;"><i slot="suffix" style="margin-right: 7px; font-style: normal;">(㎏)</i></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item prop="qrcode" label="条码" size="medium">
              <el-input clearable type="text" v-model="queryForm.qrcode" placeholder="输入查询条码" style="padding-left: 0px; padding-right: 0px;" />
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding: 0px; margin: 0px;">
            <el-form-item>
              <el-button round @click="executeQuery('queryForm')" size="small" style="margin-left: 6px;height: 24px; width:60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">查询</el-button>
              <el-button round @click="executeReset('queryForm')" size="small" style="margin-right: 6px;height: 24px; width: 60px; background: #E3E3E3; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="4" style="padding: 0px; margin: 0px;">
            <el-switch v-model="calcType.a" active-color="#5FB878" active-text="总提前" inactive-color="#d2d2d2" style="" />
          </el-col>
          <el-col :span="4" style="padding: 0px; margin: 0px;">
            <el-switch v-model="calcType.b" active-color="#5FB878" active-text="总残液" inactive-color="#d2d2d2" />
          </el-col>
          <el-col :span="4" style="padding: 0px; margin: 0px;">
            <el-switch v-model="calcType.c" active-color="#5FB878" active-text="总超充" inactive-color="#d2d2d2" />
          </el-col>
        </el-row>
        <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
          <el-col :span="9" class="ChargeDocumentsFormText" style="padding-top: 0px; padding-left: 12px; padding-right: 0px; height: 20px;"><!-- color: red; border: solid; border-width: 1pt; -->
            <span>总计：</span>
            <span v-if="querySummary.zsz !== null">总设重（{{ querySummary.zsz }}）</span>
            <span v-else>暂无总设重</span>
            <span v-if="querySummary.zjz !== null">总净重（{{ querySummary.zjz }}）</span>
            <span v-else>暂无总净重</span>
            <span v-if="querySummary.zps !== null">总瓶数（{{ querySummary.zps }}）</span>
            <span v-else>暂无总瓶数</span>
            <span v-if="querySummary.zjsz !== null">总结算量{{ querySummary.zjsz }}</span>
            <span v-else>暂无总结算量</span>
          </el-col>
          <el-col :span="6" class="ChargeDocumentsFormText" style="padding-top: 0px; padding-left: 0px; padding-right: 0px; height: 20px;"><!-- color:red; border: solid; border-width: 1pt; -->
            <span v-if="calcType.a === true && querySummary.zkc !== null">总提前量（{{ querySummary.zkc }}）</span>
            <span v-if="calcType.a === true && querySummary.zkc === null">暂无总提前量</span>
            <span v-if="calcType.b === true && querySummary.zcy !== null">总残液（{{ querySummary.zcy }}）</span>
            <span v-if="calcType.b === true && querySummary.zcy === null">暂无总残液</span>
            <span v-if="calcType.c === true && querySummary.zcc !== null">总超充量（{{ querySummary.zkc }}）</span>
            <span v-if="calcType.c === true && querySummary.zcc === null">暂无总超充量</span>
          </el-col>
          <el-col :span="9" style="padding-top: 0px; padding-left: 0px; padding-right: 0px; height: 20px;"><!-- border: solid; border-width: 1pt; -->
            <el-button size="mini" icon="el-icon-refresh-right" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="executeQuery('queryForm')" />
            <el-button size="mini" icon="el-icon-download"      style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_export('queryForm')" />
            <el-button size="mini" icon="el-icon-camera"        style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="tbl_preview('queryForm')" />
          </el-col>
        </el-row>
      </el-form>
    </el-header>
    <el-main style="padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-right: 0px; padding-left: 6px;">
      <el-table
        border
        highlight-current-row
        highlight-selection-row
        show-summary
        empty-text="目前暂时没有可查询的充装记录"
        v-loading="tableLoading"
        element-loading-text="正在查询数据"
        :data="tableData"
        :summary-method="summaryTableMethod"
        :cell-style="tblCellStyle"
        style="overflow-x: hidden; overflow-y: hidden; width: 1627px"><!-- 60 + 125 + 195 + 65 + 125 + 125 + 40 + 37 + 52 + 37 + 27 + 93 + 54 + 54 + 50 + 50 + 50 + 54 + 40 + 40 + 50 + 50 + 40 + 68 + 43 + 1 = 1625 --><!-- , -->
        <el-table-column prop="seq"             width="60"   min-width="60"   :show-overflow-tooltip="false" align="center" header-align="center"        label="序号" class-name="TblChargeDocumentsColumnId" label-class-name="TblChargeDocumentsHeadId" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_id_formatter"(row, column, cellValue, index) --><!-- 0 -->
        <el-table-column prop="operatorid"      width="65"   min-width="65"   :show-overflow-tooltip="true"  align="center" header-align="center" :formatter="tbl_operatorid_formatter"        label="员工号" label-class-name="TblChargeDocumentsHeadOperatorId" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 3 -->
        <el-table-column prop="clientid"        width="195"  min-width="195"  :show-overflow-tooltip="false" align="left"   header-align="center" :formatter="tbl_clientcode_formatter"        label="客户名" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 2 -->
        <el-table-column prop="curtime"         width="144"  min-width="144"  :show-overflow-tooltip="false" align="center" header-align="center"            label="开始时间" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_curtime_formatter"(row, column, cellValue, index) --><!-- 4 -->
        <el-table-column prop="recordtime"      width="144"  min-width="144"  :show-overflow-tooltip="false" align="center" header-align="center"         label="结束时间" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_recordtime_formatter"(row, column, cellValue, index) --><!-- 5 -->
        <el-table-column prop="usedtime"        width="40"   min-width="40"   :show-overflow-tooltip="false" align="center" header-align="center"         label="用时" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_usedtime_formatter"(row, column, cellValue, index) --><!-- 6 -->
        <el-table-column prop="addresscode"     width="37"   min-width="37"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_addresscode_formatter"     label="秤号" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 7 -->
        <el-table-column prop="machineno"       width="52"   min-width="52"   :show-overflow-tooltip="false" align="center" header-align="center"          label="MID" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_machineno_formatter"(row, column, cellValue, index) --><!-- 8 -->
        <el-table-column prop="ticket"          width="37"   min-width="37"   :show-overflow-tooltip="false" align="center" header-align="center"           label="票号" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_ticket_formatter"(row, column, cellValue, index) --><!-- 9 -->
        <el-table-column prop="qrcode"          width="115"  min-width="115"  :show-overflow-tooltip="false" align="center" header-align="center"          label="二维码" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_qrcode_formatter"(row, column, cellValue, index) --><!-- 11 -->
        <el-table-column prop="gxcode"          width="54"   min-width="54"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_gxcode_formatter"          label="罐型" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 12 -->
        <el-table-column prop="fscode"          width="54"   min-width="54"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_fscode_formatter"          label="方式" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 13 -->
        <el-table-column prop="grossweight"     width="50"   min-width="50"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_grossweight_formatter"     label="皮重" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 14 -->
        <el-table-column prop="settingweight"   width="50"   min-width="50"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_settingweight_formatter"   label="设重" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 15 -->
        <el-table-column prop="netweight"       width="50"   min-width="50"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_netweight_formatter"      label="净重" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_netweight_formatter"(row, column, cellValue, index) --><!-- 16 -->
        <el-table-column prop="endweight"       width="54"   min-width="54"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_endweight_formatter"       label="总重" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_endweight_formatter"(row, column, cellValue, index) --><!-- 17 -->
        <el-table-column prop="temperature"     width="40"   min-width="40"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_temperature_formatter"     label="温度" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_temperature_formatter"(row, column, cellValue, index) --><!-- 18 -->
        <el-table-column prop="pressure"        width="40"   min-width="40"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_pressure_formatter"        label="压力" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_pressure_formatter"(row, column, cellValue, index) --><!-- 19 -->
        <el-table-column prop="kouchengWeight"  width="50"   min-width="50"   :show-overflow-tooltip="false" align="center" header-align="center"   label="提前" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_kouchengWeight_formatter"(row, column, cellValue, index) --><!-- 20 -->
        <el-table-column prop="canyeWeight"     width="50"   min-width="50"   :show-overflow-tooltip="false" align="center" header-align="center"      label="残液" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_canyeWeight_formatter"(row, column, cellValue, index) --><!-- 21 -->
        <el-table-column prop="chaochongWeight" width="40"   min-width="40"   :show-overflow-tooltip="false" align="center" header-align="center"  label="超充" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_chaochongWeight_formatter"(row, column, cellValue, index) --><!-- 22 -->
        <el-table-column prop="command"         width="68"   min-width="68"   :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_command_formatter"         label="完成状态" /><!--  align="left/center/right" header-align="left/center/right" (row, column, cellValue, index) --><!-- 23 -->
        <el-table-column prop="frameid"         width="37"   min-width="37"   :show-overflow-tooltip="false" align="center" header-align="center"           label="帧序" /><!--  align="left/center/right" header-align="left/center/right" :formatter="tbl_frameid_formatter"(row, column, cellValue, index) --><!-- 10 -->
        <el-table-column                        width="100"  min-width="100"  :show-overflow-tooltip="false" align="center" header-align="center"                                              label="操作"><!-- 末尾按钮 --><!-- 24 -->
          <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
            <el-button round size="mini" icon="el-icon-delete" style="height: 18px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px;" @click="removeItem(gasOperator)" />
          </template>
        </el-table-column>
        </el-table>
      </el-main>
      <el-pagination hide-on-single-page v-if="queryCount !== 0" layout="sizes, prev, pager, next" :current-page.sync="pagination.current" :page-sizes="selectablePageNumberArray" :page-size="pagination.size" :total="queryCount"
        @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
  </el-collapse>
</template>

<script>
export default {
  name: 'ChargeDocuments',
  data () {
    return {
      /*
       * 查询表单
       */
      queryForm: {
        selStationcode: null, /* 站点 */
        selClientid: null, /* 客户名 */
        selOperatorid: null, /* 操作员 */
        addresscode: null, /* 秤编号 */
        fscodeType: null, /* 罐装方式 */
        gxCodeType: null, /* 罐型 */
        command: null, /* 完成状态 */
        curtime: null, /* 时间范围 */
        settingweight: null, /* 设重 */
        qrcode: null /* 条码 */
      },
      /*
       * 校验表单
       */
      queryRules: {
        addresscode: [
          {
            required: false,
            message: '秤编号必须为自然数',
            /**
             * 校验字段的值是否符合提交要求
             * @param {Object} rule 当前对象
             * @param {String} value 用户所输入的值
             * @param {function} callback 校验时所调用的函数，如果校验通过就调用callback()且不传参，如果校验不通过就调用callback(new Error('不通过的理由'))
             */
            validator: (rule, value, callback) => {
              if (value === null || value.length === 0) {
                callback()
                return
              }
              if (/[0-9]/.test(value)) {
                callback()
                return
              }
              if (/^([1-9])([0-9]+)$/.test(value)) {
                callback()
                return
              }
              callback(new Error('秤编号必须为自然数'))
            },
            trigger: 'blur'
          }
        ],
        settingweight: [
          {
            required: false,
            message: '设重必须为数字',
            /**
             * 校验字段的值是否符合提交要求
             * @param {Object} rule 当前对象
             * @param {String} value 用户所输入的值
             * @param {function} callback 校验时所调用的函数，如果校验通过就调用callback()且不传参，如果校验不通过就调用callback(new Error('不通过的理由'))
             */
            validator: (rule, value, callback) => {
              if (value === null || value.length === 0) {
                callback()
                return
              }
              if (/[0-9]/.test(value)) {
                callback()
                return
              }
              if (/^([1-9])([0-9]+)$/.test()) {
                callback()
                return
              }
              if (/^([1-9][0-9]*)\u002E([0-9]+)$/.test(value)) {
                callback()
                return
              }
              callback(new Error('设重必须为数字'))
            },
            trigger: 'blur'
          }
        ]
      },
      /**
       * 分页参数
       */
      pagination: {
        current: null, /* 当前第xxx页 */
        size: 50 /* 每一页数据条数，默认值是50条数据 */
      },
      /**
       * 查询到的数据条数
       */
      queryCount: 0,
      /**
       * 表格数据
       */
      tableData: [],
      /*
       * 加载数据
       */
      tableLoading: false,
      /**
       * 可供选择的每一页的数据条数
       */
      selectablePageNumberArray: [50, 80, 120, 200],
      /*
      /*
       * 可供选择的站点
       */
      selectableSelStationcodeArray: [],
      /*
      * 可供选择的客户名
      */
      selectableSelClientidArray: [],
      /*
       * 可供选择的操作员
       */
      selectableSelOperatoridArray: [],
      /*
       * 可供选择的罐装方式
       */
      selectableFscodeTypeArray: [ { value: '0', label: '净含重' }, { value: '1', label: '总重量' } ],
      /*
       * 可供选择的罐型
       */
      selectableGxCodeTypeArray: [ { value: '0', label: 'LPG-05' }, { value: '1', label: 'LPG-15' }, { value: '2', label: 'LPG-50' } ],
      /*
       * 可供选择的完成状态
       */
      selectableCommandArray: [{ value: 4, label: '正常完成' }, { value: 5, label: '掉电数据' }, { value: 6, label: '瓶被拿下' }, { value: 7, label: '违规操作' }, { value: 8, label: '更换轻瓶' }],
      /**
       * 可供选择的默认时间
       */
      selectableDefaultTime: ['00:00:00', '23:59:59'],
      /*
       * 表单中的滑块
       */
      calcType: {
        a: false, // 扣秤量
        b: false, // 残液量
        c: false // 超充量
      },
      /*
       * 表单中的统计信息
       */
      querySummary: {
        zsz: null, // 总设重
        zjz: null, // 总净重
        zps: null, // 总瓶数
        zjsz: null, // 总结算重量
        zkc: null, // 总扣称量
        zcy: null, // 总残液
        zcc: null // 总超充量
      }
    }
  },
  mounted () {
    this.$axios.post(
      this.constant.GAS_SERVER_PREFIX + '/tbldata/tbldata/queryPageElSelect',
      {},
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'reference': this.$router.currentRoute.fullPath
        }
      }
    ).then(res => {
      if (res.status === 200) {
        const data = res.data
        this.selectableSelStationcodeArray = data.station
        this.selectableSelClientidArray = data.clientUsers
        this.selectableSelOperatoridArray = data.operators
      } else {
        this.selectableSelStationcodeArray = []
        this.selectableSelClientidArray = []
        this.selectableSelOperatoridArray = []
      }
    }).catch(ex => {
      this.selectableSelStationcodeArray = []
      this.selectableSelClientidArray = []
      this.selectableSelOperatoridArray = []
    })
    const localQueryForm = {
      curtimeForm: this.dateToYyyyMmDd(new Date(Date.now())) + ' 00:00:00',
      curtimeTo: this.dateToYyyyMmDd(new Date(Date.now() + 86400000)) + ' 00:00:00'
    }
    this.querySummary.zsz = null
    this.querySummary.zjz = null
    this.querySummary.zps = null
    this.queryCount = 0
    this.tableData = []
    const requestForm = new FormData()
    requestForm.append('form', JSON.stringify(localQueryForm))
    requestForm.append('page', JSON.stringify(this.pagination))
    this.$axios.postForm(
      this.constant.GAS_SERVER_PREFIX + '/tbldata/tbldata/queryByPage',
      requestForm,
      {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'reference': this.$router.currentRoute.fullPath
        }
      }
    ).then(res => {
      this.tableLoading = false
      if (res.status === 200) {
        /*
         * 总瓶数和查询总数
         */
        this.querySummary.zsz = Number.parseFloat(Number.parseFloat(res.data.settingweightCount) / 100).toFixed(2) // 总设重
        this.querySummary.zjz = Number.parseFloat(Number.parseFloat(res.data.endweightCount - res.data.grossweightCount) / 100).toFixed(2) // 总净重
        this.querySummary.zps = this.queryCount = res.data.totalCount
        this.tableData = res.data.items
      } else {
        this.querySummary.zsz = null
        this.querySummary.zjz = null
        this.querySummary.zps = null
        this.queryCount = 0
        this.tableData = []
      }
    }).catch(ex => {
      this.tableLoading = false
      this.querySummary.zsz = null
      this.querySummary.zjz = null
      this.querySummary.zps = null
      this.queryCount = 0
      this.tableData = []
    })
  },
  methods: {
    /**
     * 执行分页查询
     * @param {String} formName 表单名
     */
    executeQuery (formName) {
      const localCurtime = this.convertDateArrayTo(this.queryForm.curtime)
      const localQueryForm = {
        selStationcode: this.queryForm.selStationcode, /* 站点 */
        selClientid: this.queryForm.selClientid, /* 客户名 */
        selOperatorid: this.queryForm.selOperatorid, /* 操作员 */
        addresscode: (this.queryForm.addresscode === null || this.queryForm.addresscode.length === 0) ? null : parseInt(this.queryForm.addresscode) * 2, /* 秤编号 */
        fscodeType: this.queryForm.fscodeType, /* 罐装方式 */
        gxCodeType: this.queryForm.gxCodeType, /* 罐型 */
        command: Number.isInteger(this.queryForm.command) ? this.queryForm.command + 160 : null, /* 完成状态 */
        curtimeForm: localCurtime[0] === null ? this.dateToYyyyMmDd(new Date(Date.now())) + ' 00:00:00' : localCurtime[0], /* 时间范围 */
        curtimeTo: localCurtime[1] === null ? this.dateToYyyyMmDd(new Date(Date.now() + 86400000)) + ' 00:00:00' : localCurtime[1], /* 时间范围 */
        settingweight: (this.queryForm.settingweight === null || this.queryForm.settingweight.length === 0) ? null : Number.parseFloat(this.queryForm.settingweight) * 100, /* 设重 */
        qrcode: this.queryForm.qrcode === null ? null : this.queryForm.qrcode.trim()
      }
      this.querySummary.zsz = null
      this.querySummary.zjz = null
      this.querySummary.zps = null
      this.queryCount = 0
      this.tableData = []
      const requestForm = new FormData()
      requestForm.append('form', JSON.stringify(localQueryForm))
      requestForm.append('page', JSON.stringify(this.pagination))
      this.tableLoading = true
      this.$axios.postForm(
        this.constant.GAS_SERVER_PREFIX + '/tbldata/tbldata/queryByPage',
        requestForm,
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'reference': this.$router.currentRoute.fullPath
          }
        }
      ).then(res => {
        this.tableLoading = false
        if (res.status === 200) {
          /*
           * 总瓶数和查询总数
           */
          this.querySummary.zsz = Number.parseFloat(Number.parseFloat(res.data.settingweightCount) / 100).toFixed(2) // 总设重
          this.querySummary.zjz = Number.parseFloat(Number.parseFloat(res.data.endweightCount - res.data.grossweightCount) / 100).toFixed(2) // 总净重
          this.querySummary.zps = this.queryCount = res.data.totalCount
          this.tableData = res.data.items
        } else {
          this.querySummary.zsz = null
          this.querySummary.zjz = null
          this.querySummary.zps = null
          this.queryCount = 0
          this.tableData = []
        }
      }).catch(ex => {
        this.tableLoading = false
        this.querySummary.zsz = null
        this.querySummary.zjz = null
        this.querySummary.zps = null
        this.queryCount = 0
        this.tableData = []
      })
    },
    /**
     * 重置表单
     * @param {String} formName 表单名
     */
    executeReset (formName) {
      this.$refs[formName].resetFields() // 或者：this.$refs.表单的引用信息.resetFields()
    },
    /*
     * 删除行
     */
    removeItem (scope) {
      console.log(scope)
    },
    summaryTableMethod (arg) {
      const { columns, data } = arg
      let middleGrossweight = 0
      let middleSettingweight = 0
      let middleEndweight = 0
      const result = new Array(columns.length)
      for (var i = 0; i < columns.length; ++i) {
        switch (i) {
          case 12:
          case 13:
          case 14:
          case 15: {
            break
          }
          default: {
            result[i] = ''
            break
          }
        }
      }
      for (var j = 0; j < data.length; ++j) {
        middleGrossweight += data[j].grossweight
        middleSettingweight += data[j].settingweight
        middleEndweight += data[j].endweight
      }
      result[12] = Number.parseFloat(Number.parseFloat(middleGrossweight) / 100).toFixed(2)
      result[13] = Number.parseFloat(Number.parseFloat(middleSettingweight) / 100).toFixed(2)
      result[14] = Number.parseFloat(Number.parseFloat(middleEndweight - middleGrossweight) / 100).toFixed(2)
      result[15] = Number.parseFloat(Number.parseFloat(middleEndweight) / 100).toFixed(2)
      /* this.querySummary.zsz = result[15] */
      return result
    },
    /**
     * 导出数据
     * @param {String} formName 表单名
     */
    tbl_export (formName) {
      // TODO
    },
    /**
     * 打印预览
    * @param {String} formName 表单名
     */
    tbl_preview (formName) {
      // TODO
    },
    /*
    * 格式数据的函数起
    */
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_stationcode_formatter (row, column, cellValue, index) {
      for (var i = 0; i < this.selectableSelStationcodeArray.length; ++i) {
        if (cellValue === this.selectableSelStationcodeArray[i].value) {
          return this.selectableSelStationcodeArray[i].label
        }
      }
      return null
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_clientcode_formatter (row, column, cellValue, index) {
      if (Number.isInteger(cellValue)) {
        for (var i = 0; i < this.selectableSelClientidArray.length; ++i) {
          if (cellValue === this.selectableSelClientidArray[i].value) {
            return this.selectableSelClientidArray[i].label
          }
        }
        return cellValue + '号门店'
      }
      return null
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_operatorid_formatter (row, column, cellValue, index) {
      if (Number.isInteger(cellValue)) {
        for (var i = 0; i < this.selectableSelOperatoridArray.length; ++i) {
          if (cellValue === this.selectableSelOperatoridArray[i].value) {
            return this.selectableSelOperatoridArray[i].label
          }
        }
        return cellValue + '号员工'
      }
      return null
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_curtime_formatter (row, column, cellValue, index) {
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_recordtime_formatter (row, column, cellValue, index) {
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_usedtime_formatter (row, column, cellValue, index) {
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
      return cellValue === null ? null : cellValue / 2
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_machineno_formatter (row, column, cellValue, index) {
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_ticket_formatter (row, column, cellValue, index) {
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_frameid_formatter (row, column, cellValue, index) {
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_qrcode_formatter (row, column, cellValue, index) {
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_gxcode_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      if (Number.isInteger(cellValue)) {
        switch (cellValue % 4) {
          case 0:
            return 'LPG-05'
          case 1:
            return 'LPG-15'
          case 2:
            return 'LPG-50'
          default:
            return null
        }
      }
      return null
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_fscode_formatter (row, column, cellValue, index) {
      if (row.gxcode === null) {
        return null
      }
      if (Number.isInteger(row.gxcode)) {
        switch ((row.gxcode - (row.gxcode % 8)) / 8) {
          case 0:
            return '净含重'
          case 1:
            return '总重量'
          default:
            return null
        }
      }
      return null
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_grossweight_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(Number.parseFloat(cellValue) / 100).toFixed(2)
      }
      return null
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_settingweight_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(Number.parseFloat(cellValue) / 100).toFixed(2)
      }
      return null
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_netweight_formatter (row, column, cellValue, index) {
      /*
       * 完成重量 - 皮重 = 净重
       */
      return (Number.isFinite(row.endweight) && Number.isFinite(row.grossweight)) ? Number.parseFloat(Number.parseFloat(row.endweight - row.grossweight) / 100).toFixed(2) : null
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_endweight_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      return Number.parseFloat(Number.parseFloat(cellValue) / 100).toFixed(2)
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_temperature_formatter (row, column, cellValue, index) {
      return cellValue
    },
    tbl_pressure_formatter (row, column, cellValue, index) {
      return cellValue
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_kouchengWeight_formatter (row, column, cellValue, index) {
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_canyeWeight_formatter (row, column, cellValue, index) {
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_chaochongWeight_formatter (row, column, cellValue, index) {
    },
    /**
    * 格式化类型字段
    * @param {Object} row 具体的某一行的数据
    * @param {Object} column 当前列的定义信息
    * @param {Object} cellValue 具体某一行某一列的数据
    * @param {Int} index 当前数据在表格数组中对应的下标
    * @return 格式化后的数据
    */
    tbl_command_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      switch (cellValue) {
        case 164:
          return '正常完成'
        case 165:
          return '掉电数据'
        case 166:
          return '瓶被拿下'
        case 167:
          return '违规操作'
        case 168:
          return '更换轻瓶'
        default:
          return null
      }
    },
    /*
    * 格式化数据的函数止
    */
    convertDateArrayTo (arr) {
      if (arr == null) {
        return [null, null]
      }
      switch (arr.length) {
        case 0:
          return [null, null]
        case 1:
          return [null, null]
        default: {
          switch ((arr[1] === null ? 0 : 2) + (arr[0] === null ? 0 : 1)) {
            case 0:
              return [null, null]
            case 1:
              return [this.arr[0], null]
            case 2:
              return [null, arr[1]]
            case 3:
              return this.parseRemoteDateString(arr[0]).getTime() < this.parseRemoteDateString(arr[1]).getTime() ? [arr[0], arr[1]] : [arr[1], arr[0]]
            default:
              return [null, null]
          }
        }
      }
    },
    dateToString (da) {
      const m = da.getMonth() + 1
      const d = da.getDate()
      const h = da.getHours()
      const i = da.getMinutes()
      const s = da.getSeconds()
      return da.getFullYear() + ((m > 9) ? '-' + m : '-0' + m) + ((d > 9) ? '-' + d : '-0' + d) + ((h > 9) ? ' ' + h : ' 0' + h) + ((i > 9) ? ':' + i : ':0' + i) + ((s > 9) ? ':' + s : ':0' + s)
    },
    dateToYyyyMmDd (da) {
      const m = da.getMonth() + 1
      const d = da.getDate()
      return da.getFullYear() + ((m > 9) ? '-' + m : '-0' + m) + ((d > 9) ? '-' + d : '-0' + d)
    },
    parseRemoteDateString (str) {
      if (str === null) {
        return null
      }
      try {
        const dateAndTimeStringArray = str.split(' ')
        const yearMonthDayArray = dateAndTimeStringArray[0].split('-')
        const hourMinuteSecondArray = dateAndTimeStringArray[1].split(':')
        return new Date(parseInt(yearMonthDayArray[0]), parseInt(yearMonthDayArray[1]) - 1, parseInt(yearMonthDayArray[2]), parseInt(hourMinuteSecondArray[0]), parseInt(hourMinuteSecondArray[1]), parseInt(hourMinuteSecondArray[2]))
      } catch (ex) {
        return null
      }
    },
    /**
     * 设备单页的大小，然后刷新表格中的数据
     * @param size 单页的大小
     */
    paginationSizeChange (size) {
      this.pagination.size = size
      this.executeQuery('queryFrom')
    },
    /**
     * 设置当前第xx页，然后刷新表格中的数据
     */
    paginationCurrentChange (current) {
      this.pagination.current = current
      this.executeQuery('queryForm')
    },
    /**
     * @param row 所在行的原始数据
     * @param column 所在列的定义数据
     * @param rowIndex 从0开始的第多少行
     * @param columnIndex 从0开始的第多少列
     */
    tblCellStyle ({row, column, rowIndex, columnIndex}) {
      switch (column.label) {
        /* case '序号': { */
        /* return 'padding-right: 7px' */
        /* } */
        case '员工号': {
          return Number.isInteger(row.operatorid) ? (this.tbl_operatorid_formatter(row, column, row.operatorid, rowIndex).length > 3 ? 'padding-left: 0px; padding-right: 0px; font-size: 9pt;' : 'padding-left: 3px; padding-right: 0px; font-size: 10.5pt;') : null
        }
        case '客户名': {
          return 'padding-left: 3px; padding-right: 0px'
        }
        /* case '用时': { */
        /* return 'padding-right: 11px' */
        /* } */
        /* case '帧': { */
        /* return 'padding-right: 3px' */
        /* } */
        default: {
          return null
        }
      }
    }
  }
}
</script>

<style>
/*
 * 表单输入框
 */
/* .el-input--medium .el-input__inner { */
/*   height: 28px; */
/*   line-height: 28px; */
/*   width: 190px; */
/* } */
/*
 * 表单输入框
 */
/* .el-input__inner { */
/*   height: 28px; */
/* } */
/* .el-range-editor--small.el-input__inner { */
    /* height: 28px; */
    /* width: 190px; */
/* } */
/* .el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange.el-input__inner { */
/*   height: 28px; */
/*   width: 190px; */
/* } */
/* .el-range-editor.el-input__inner { */
/*   padding: 0px; */
/* } */
/*
 * 表单控件提示信息
 */
/* .el-form-item__error { */
/*   color: #F56C6C; */
/*   font-size: 12px; */
/*   line-height: 0; */
/*   padding-top: 3px; */
/*   position: absolute; */
/*   top: 100%; */
/*   left: 0; */
/* } */
/* .el-date-editor--daterange.el-input, .el-date-editor--daterange.el-input__inner, .el-date-editor--timerange.el-input, .el-date-editor--timerange.el-input__inner { */
/*   width: 190px; */
/* } */
/*
 * 页面样式
 */
.ChargeDocumentsMain {
  position: relative;
  left: 0px;
  top: 0px;
  width: 100%;
  height: calc(100% - 2px);
  padding: 0px;
  margin: 0px;
  /* background-color: black; */
  /* overflow-y:visible; */
}
/*
 * 页面表单的样式
 */
.ChargeDocumentsForm {
  position: relative;
  left: 0px;
  top: 0px;
  /* min-width: 1024px; */
  /* overflow-x: auto; */
  width: 100%;
  background-color: #ffffff; /* #B3C0D1*/
}
.ChargeDocumentsFormText {
  font-size: 11pt;
  text-emphasis-color: red;
}
/* .ChargeDocumentsTableHeaderA {
  background: oldlace;
}
.ChargeDocumentsTableHeaderB {
  background: olivedrab;
}
.ChargeDocumentsTableHeaderC {
  background: brown;
}
.ChargeDocumentsTableHeaderD {
  background: burlywood;
} */
/* .el-form-item { */
/*     margin-bottom: 3px; */
/* } */
/*
 * 表头中的小格
 */
/* .el-table--border .el-table__cell:first-child .cell { */
/*   padding: 1px; */
/*   margin-right: 2px; */
/* } */
/*
 * 表格中的每个小格
 */
/* .el-table .el-table__cell { */
/*   padding: 1px; */
/* } */
/*
 * 表格中的每个小格中的字边距
 */
/* .el-table .cell { */
/*   padding: 0px; */
/* } */
/*
 * 表头
 */
/* .el-table th.el-table__cell>.cell { */
/*   padding: 2px */
/* } */
/*
 * 序号这一列的样式
 */
.TblChargeDocumentsColumnId {
  font-size: 9pt;
}
/*
 * 序号这一列表头的样式
 */
.TblChargeDocumentsHeadId {
  font-size: 10.5pt;
}
/*
 * 操作员这一列的样式
 */
 .TblChargeDocumentsHeadOperatorId {
  font-size: 10.5pt;
}
</style>
