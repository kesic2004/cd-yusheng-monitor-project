<template>
  <!--整个页面-->
  <div style="padding: 0px; margin: 0px">
    <!--查询表单和表格-->
    <el-collapse class="BottleArchiveMain">
      <el-header height="140px" class="BottleArchiveForm">
        <el-form status-icon ref="queryForm" label-width="80px" :model="queryForm" :rules="queryRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px;padding-top: 2px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="qrcode" label="条码编号" size="medium">
                <el-input type="text" v-model="queryForm.qrcode" placeholder="输入查询的条码编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="productno" label="钢印号" size="medium">
                <el-input type="text" v-model="queryForm.productno" placeholder="输入查询的钢印号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="producter" label="制造厂商" size="medium">
                <el-select v-model="queryForm.producter" placeholder="请选择制造厂家" no-data-text="暂时没有可选择的制造厂家" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableProducterArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="mediumname" label="充装介质" size="medium">
                <el-select v-model="queryForm.mediumname" placeholder="请选择充装介质" no-data-text="暂时没有可选择的充装介质" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableMediumnameArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="producted" label="出厂日期" size="medium">
                <el-date-picker v-model="queryForm.producted" type="monthrange" size="small" range-separator="—" start-placeholde="出厂日期起" end-placeholde="出厂日期止" align="left" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="expired" label="报废日期" size="medium">
                <el-date-picker v-model="queryForm.expired" type="monthrange" size="small" range-separator="—" start-placeholde="报废日期起" end-placeholde="报废日期止" align="left" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="lasttested" label="上检日期" size="medium">
                <el-date-picker v-model="queryForm.lasttested" type="monthrange" size="small" range-separator="—" start-placeholde="上检日期起" end-placeholde="上检日期止" align="left" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="nexttested" label="下检日期" size="medium">
                <el-date-picker v-model="queryForm.nexttested" type="monthrange" size="small" range-separator="—" start-placeholde="下检日期起" end-placeholde="下检日期止" align="left" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="norms" label="规格型号" size="medium">
                <el-select v-model="queryForm.norms" placeholder="请选择规格型号" no-data-text="暂时没有可选择的规格型号" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableNormsArray" :key="item.id" :label="item.label" :value="item.id"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="usedstatus" label="气瓶状态" size="medium">
                <el-radio-group v-model="queryForm.usedstatus">
                  <el-radio v-for="item in selectableUsedstatus4Form" :key="item.value" :label="item.label">{{ item.title }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item>
                <el-button round @click="executeQuery('queryForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">查询</el-button>
                <el-button round @click="executeReset('queryForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="width: 1126px">
            <el-col :span="6">
              <el-button size="mini" icon="el-icon-document-add" style="margin-bottom: 5px;" @click="tbl_add_data()" />
              <el-button size="mini" icon="el-icon-refresh-right" style="margin-bottom: 5px;" @click="executeQuery('queryForm')" />
              <el-button size="mini" icon="el-icon-download" style="margin-bottom:5px;" @click="executeExport('queryForm')" />
            </el-col>
            <el-col :span="6">
              <span v-if="queryCount !==0" style="font-family: 楷体;">总气瓶数：{{queryCount}}只</span>
              <span v-if="queryCount ===0" style="font-family: 楷体;">暂时没有可查询的气瓶档案</span>
            </el-col>
            <el-col :span="36">
              <el-pagination hide-on-single-page v-if="queryCount !== 0" layout="sizes, prev, pager, next" :current-page.sync="pagination.current" :page-sizes="selectablePageNumberArray" :page-size="pagination.size" :total="queryCount"
                @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
            </el-col>
          </el-row>
        </el-form>
      </el-header>
      <el-main>
        <el-table
          border
          highlight-current-row
          highlight-selection-row
          empty-text="目前暂时没有可查询的气瓶档案"
          :data="tableData"
          :cell-style="tblCellStyle"
          :height="tableHeightString"
          style="overflow-y: hidden; width: 1460px">
          <el-table-column prop="gastankid"   width="60"  min-width="60"  :show-overflow-tooltip="false" align="right"  header-align="center"   label="序号" />
          <el-table-column prop="qrcode"      width="100" min-width="100" :show-overflow-tooltip="false" align="right"  header-align="center"   label="条码编号" />
          <el-table-column prop="producter"   width="286" min-width="286" :show-overflow-tooltip="true"  align="left"   header-align="center"   label="制造厂商" />
          <el-table-column prop="productno"   width="125" min-width="125" :show-overflow-tooltip="false" align="left"   header-align="center"   label="钢印号"   />
          <el-table-column prop="producted"   width="63"  min-width="63"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_producted_formatter"   label="出厂日期" />
          <el-table-column prop="nexttested"  width="63"  min-width="63"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_nexttested_formatter"  label="下检日期" />
          <el-table-column prop="expired"     width="63"  min-width="63"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_expired_formatter"     label="报废日期" />
          <el-table-column prop="lasttested"  width="63"  min-width="63"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_lasttested_formatter"  label="上检日期" />
          <el-table-column prop="testcount"   width="50"  min-width="50"  :show-overflow-tooltip="false" align="center" header-align="center"   label="已检次"   />
          <el-table-column prop="maxuseyears" width="50"  min-width="50"  :show-overflow-tooltip="false" align="center" header-align="center" label="报废年"   />
          <el-table-column prop="mediumname"  width="63"  min-width="63"  :show-overflow-tooltip="false" align="right"  header-align="center" label="充装介质" />
          <el-table-column prop="norms"       width="100" min-width="100" :show-overflow-tooltip="false" align="left"   header-align="center" :formatter="tbl_norms_formatter"       label="规格型号" />
          <el-table-column prop="usedstatus"  width="50"  min-width="50"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_usedstatus_formatter"  label="状态"     />
          <el-table-column prop="maxcapacity" width="63"  min-width="63"  :show-overflow-tooltip="false" align="right"  header-align="center" :formatter="tbl_maxcapacity_formatter" label="最大充量" />
          <el-table-column prop="selfweight"  width="35"  min-width="35"  :show-overflow-tooltip="false" align="right"  header-align="center" :formatter="tbl_selfweight_formatter"  label="自重"     />
          <el-table-column prop="thickness"   width="35"  min-width="35"  :show-overflow-tooltip="false" align="right"  header-align="center" :formatter="tbl_thickness_formatter"   label="壁厚"     />
          <el-table-column prop="volume"      width="40"  min-width="40"  :show-overflow-tooltip="false" align="right"  header-align="center" :formatter="tbl_volume_formatter"      label="容积"     />
          <el-table-column prop="onlyused"    width="35"  min-width="35"  :show-overflow-tooltip="false" align="center" header-align="center" :formatter="tbl_onlyused_formatter"    label="专用"     />
          <el-table-column                    width="115" min-width="115" :show-overflow-tooltip="false" align="center" header-align="left"    label="操作"      label-class-name="TblBottleArchinveLastColumnHeader">
            <template slot-scope="gasOperator"><!-- gasOperator是点击按钮时传给回调函数的参数 -->
              <el-button round size="mini" icon="el-icon-document" style="height: 21px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-radius: 3px;" @click="tbl_view_data(gasOperator)" />
              <el-button round size="mini" icon="el-icon-edit" style="height: 21px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-radius: 3px;" @click="tbl_edit_data(gasOperator)" />
              <el-button round size="mini" icon="el-icon-set-up" style="height: 21px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-radius: 3px;" @click="tbl_modify_qrcode(gasOperator)" />
            </template>
          </el-table-column>
        </el-table>
        <el-pagination hide-on-single-page v-if="queryCount !== 0" layout="sizes, prev, pager, next" :current-page.sync="pagination.current" :page-sizes="selectablePageNumberArray" :page-size="pagination.size" :total="queryCount"
          @current-change="paginationCurrentChange" @size-change="paginationSizeChange" />
      </el-main>
    </el-collapse>
    <!--新增气瓶档案对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :visible.sync="addBottle.visible"
      :title="addBottle.title"
      :show-close="addBottleShowClose"
      :width="addBottleWidthString"
      :style="{ width: addBottleDialogWidthString, marginLeft: addBottleMarginLeftString }"
      :before-close="tbl_before_hide_add_dialog_only"
      :top="addBottleDialogTopString">
      <div :style="{ width: addBottleDivWidthString, height: addBottleDivHeightString, display: addBottleFormDisplayString }">
        <el-form status-icon ref="addForm" label-width="125px" :model="addBottleForm" :rules="addBottleRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qrcode" label="条码编号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.qrcode" placeholder="请输入条码编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="productno" label="钢印号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.productno" placeholder="请输入钢印号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producter" label="制造厂商" size="medium">
                <el-select clearable v-model="addBottleForm.producter" placeholder="请选择制造厂家" no-data-text="暂时没有可选择的制造厂家" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableProducterArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producted" label="出厂日期" size="medium">
                <el-date-picker clearable v-model="addBottleForm.producted" type="month" placeholder="请选择出厂日期" size="small" @change="addBottleDialogProducted" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lasttested" label="上检日期" size="medium">
                <el-date-picker clearable v-model="addBottleForm.lasttested" type="month" placeholder="请选择上检日期" size="small" @change="addBottleDialogLasttested" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="nexttested" label="下检日期" size="medium">
                <el-date-picker clearable v-model="addBottleForm.nexttested" type="month" placeholder="请选4择下检日期" size="small" @change="addBottleDialogNexttested" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="maxuseyears" label="报废年" size="medium">
                <el-select clearable v-model="addBottleForm.maxuseyears" no-data-text="暂时没有可选择的报废年" style="padding-left: 0px; padding-right: 0px;" @change="addBottleDialogMaxuseyears">
                  <el-option v-for="item in selectableMaxuseyears" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="expired" label="报废日期" size="medium">
                <el-date-picker clearable v-model="addBottleForm.expired" type="month" placeholder="请选择报废日期" size="small" @change="addBottleDialogExpired" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qualifiedno" label="检验合格环号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.qualifiedno" placeholder="请输入检验合格环号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="registno" label="使用登记证编号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.registno" placeholder="请输入使用登记证编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedlicenseno" label="使用登记证号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.usedlicenseno" placeholder="请输入使用登记证号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="elabel" label="电子标签编号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.elabel" placeholder="请输入电子标签编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="norms" label="规格型号" size="medium">
                <el-select clearable="" v-model="addBottleForm.norms" placeholder="请选择规格型号" no-data-text="暂时没有可选择的规格型号" @change="addBottleFieldNormsChange" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableNormsArray" :key="item.id" :label="item.label" :value="item.id"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvefactory" label="阀门厂家" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.valvefactory" placeholder="请输入阀门厂家" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvetype" label="阀门类型" size="medium">
                <el-select clearable v-model="addBottleForm.valvetype" placeholder="请选择阀门类型" no-data-text="暂时没有可供选择的阀门类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableValvetype" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="unitselfno" label="充装单位自编号" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.unitselfno" placeholder="请输入充装单位自编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="maxcapacity" label="最大充量" size="medium">
                <el-input clearable readonly type="text" v-model="addBottleForm.maxcapacity" style="padding-left: 0px; padding-right: 0px;" slot-type="suffix">
                  <i v-if="addBottleFormMisc.maxcapacitySuffix !== null" slot="suffix">{{ addBottleFormMisc.maxcapacitySuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="selfweight" label="自重" size="medium">
                <el-input readonly type="text" v-model="addBottleForm.selfweight" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="addBottleFormMisc.selfweightSuffix !== null" slot="suffix">{{ addBottleFormMisc.selfweightSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="thickness" label="壁厚" size="medium">
                <el-input readonly type="text" v-model="addBottleForm.thickness" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="addBottleFormMisc.thicknessSuffix !== null" slot="suffix">{{ addBottleFormMisc.thicknessSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="volume" label="容积" size="medium">
                <el-input readonly type="text" v-model="addBottleForm.volume" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="addBottleFormMisc.volumeSuffix !== null" slot="suffix">{{ addBottleFormMisc.volumeSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="hotway" label="热处理方式" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.hotway" placeholder="请输入热处理方式" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="bodystuff" label="瓶体材料" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.bodystuff" placeholder="请输入瓶体材料" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="mediumname" label="充装介质" size="medium">
                <el-select clearable v-model="addBottleForm.mediumname" placeholder="请选择充装介质" no-data-text="暂时没有可选择的充装介质" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableMediumnameArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="testcount" label="已检次" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.testcount" placeholder="已检次" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkresult" label="安评结果" size="medium">
                <el-input clearable type="text" v-model="addBottleForm.checkresult" placeholder="请输入安评结果" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkdate" label="安评日期" size="medium">
                <el-date-picker clearable v-model="addBottleForm.checkdate" type="date" placeholder="请选择安评日期" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedstatus" label="气瓶状态" size="medium">
                <el-select clearable v-model="addBottleForm.usedstatus" placeholder="气瓶状态" no-data-text="暂时没有可选择的气瓶状态" style="padding-left: 0px; padding-right: 0px;">
                  <el-option v-for="item in selectableUsedstatus4Dialog" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.title }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="onlyused" label="是否专用" size="medium">
                <el-radio-group v-model="addBottleForm.onlyused">
                  <el-radio v-for="item in selectableOnlyused" :key="item.value" :label="item.value">{{ item.title }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="files" label="气瓶图片" size="medium" v-show="addBottleForm.attachmentYmd !== null && addBottleForm.attachmentUuid !== null">
                <el-upload
                  drag
                  with-credentials
                  name="file"
                  :accept="addBottle.accept"
                  :action="addBottleUploadActionUrl"
                  :auto-upload="addBottle.autoUpload"
                  :multiple="addBottle.multipleUpload && addBottleForm.attachmentYmd !== null && addBottleForm.attachmentUuid !== null"
                  :file-list="addBottleAttachmentUploadList"
                  :limit="addBottle.uploadLimit"
                  :headers="addBottleUploadHeaders"
                  :show-file-list="addBottle.showFileList"
                  :on-change="addBottleImageChange"
                  :on-exceed="addBottleImageExceed"
                  :on-progress="addBottleImageProgress"
                >
                  <el-button
                    round
                    size="small"
                    style="height: 24px; width: 90px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;"
                  >上传气瓶附件</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item v-if="globalVar.globalStationArray.length > 1" required prop="stationid" label="站点" size="medium">
                <el-select clearable v-model="addBottleForm.stationid" placeholder="请选择站点" no-data-text="暂时没有可选择的站点" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in globalVar.globalStationArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lastused" label="最后使用" size="medium">
                <el-date-picker clearable v-model="addBottleForm.lastused" type="month" placeholder="请选择最后使用" size="small" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="Array.isArray(addBottleAttachmentShowList) && addBottleAttachmentShowList.length > 0" :gutter="48" type="flex" justify="start" align="middle" style="padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; height: 119px; overflow-x: auto; overflow-y: hidden; background-color: #FFFFFF;">
            <div style="position: relative; top: 5px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: addBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !addBottleImageBorderLine(index)}" v-for="(item, index) in addBottleAttachmentShowList" :key="item.id">
              <!-- ———————————————— -->
              <!-- 版权声明：本文为CSDN博主「喜大普奔⁶⁶⁶」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 -->
              <!-- 原文链接：https://blog.csdn.net/qq_38652871/article/details/115081612 -->
              <!-- ———————————————— --><!-- display: flex; justify-content: space-around; -->
              <span class="el-image-viewer__btn" @click="addBottleImageRemove(index)"><i class="el-icon-error" style="margin-left: 80px; margin-top: 0px; font-size: 14pt;"></i></span>
              <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" :src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: addBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !addBottleImageBorderLine(index) }" @click="addBottleImageClick(index)" />
            </div>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: addBottle.divWidth + 'px', display: addBottleFormDisplayString }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_add_data_submit('addForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
          <el-button round @click="tbl_add_data_reset('addForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
      <div :style="{ width: addBottleDivWidthString, height: addBottleDivHeightString, display: addBottlePictureDisplayString }">
        <el-container>
          <el-header :height="addBottlePictureRowHeightString" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: auto;">
            <span class="el-image-viewer__btn" v-if="Array.isArray(addBottleAttachmentShowList) && addBottleAttachmentShowList.length > 0" :style="{ display: addBottleChangeDisplayString }" @click="tbl_add_data_change_to_form" ><i class="el-icon-error" style="position: fixed; top: 9px; font-size: 18pt;" :style="{ left: addBottleChangeDisplayXOffsetString }"></i></span>
            <el-image :src="addBottleAttachmentShowList[addBottle.pictureIndex].uri" fill="none" v-if="Array.isArray(addBottleAttachmentShowList) && addBottleAttachmentShowList.length > 0" />
          </el-header>
          <el-main style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; height: 3px;" />
          <el-footer height="115px" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;">
            <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: hidden; background-color: #FFFFFF;">
              <div style="position: relative; top: 8px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: addBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !addBottleImageBorderLine(index)}" v-for="(item, index) in addBottleAttachmentShowList" :key="item.id">
                <span class="el-image-viewer__btn" @click="addBottleImageRemove(index)"><i class="el-icon-error" style="margin-left: 80px; margin-top: 0px; font-size: 14pt;"></i></span>
                <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" :src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: addBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !addBottleImageBorderLine(index) }" @click="addBottleImageDetailClick(index)" />
              </div>
            </el-row>
          </el-footer>
        </el-container>
      </div>
    </el-dialog>
    <!-- 修改气瓶档案对话框 -->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :visible.sync="editBottle.visible"
      :title="editBottle.title"
      :show-close="editBottleShowClose"
      :width="editBottleWidthString"
      :style="{ width: editBottleDialogWidthString, marginLeft: editBottleMarginLeftString }"
      :before-close="tbl_before_hide_edit_dialog_only"
      :top="editBottleDialogTopString">
      <div :style="{ width: editBottleDivWidthString, height: editBottleDivHeightString, display: editBottleFormDisplayString }">
        <el-form status-icon ref="editForm" label-width="125px" :model="editBottleForm" :rules="editBottleRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qrcode" label="条码编号" size="medium">
                <el-input readonly type="text" v-model="editBottleForm.qrcode" placeholder="请输入条码编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="productno" label="钢印号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.productno" placeholder="请输入钢印号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producter" label="制造厂商" size="medium">
                <el-select clearable v-model="editBottleForm.producter" placeholder="请选择制造厂家" no-data-text="暂时没有可选择的制造厂家" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableProducterArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producted" label="出厂日期" size="medium">
                <el-date-picker clearable v-model="editBottleForm.producted" type="month" placeholder="请选择出厂日期" size="small" @change="editBottleDialogProducted" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lasttested" label="上检日期" size="medium">
                <el-date-picker clearable v-model="editBottleForm.lasttested" type="month" placeholder="请选择上检日期" size="small" @change="editBottleDialogLasttested" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="nexttested" label="下检日期" size="medium">
                <el-date-picker clearable v-model="editBottleForm.nexttested" type="month" placeholder="请选4择下检日期" size="small" @change="editBottleDialogNexttested" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="maxuseyears" label="报废年" size="medium">
                <el-select clearable v-model="editBottleForm.maxuseyears" no-data-text="暂时没有可选择的报废年" style="padding-left: 0px; padding-right: 0px;" @change="editBottleDialogMaxuseyears">
                  <el-option v-for="item in selectableMaxuseyears" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="expired" label="报废日期" size="medium">
                <el-date-picker clearable v-model="editBottleForm.expired" type="month" placeholder="请选择报废日期" size="small" @change="editBottleDialogExpired" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qualifiedno" label="检验合格环号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.qualifiedno" placeholder="请输入检验合格环号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="registno" label="使用登记证编号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.registno" placeholder="请输入使用登记证编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedlicenseno" label="使用登记证号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.usedlicenseno" placeholder="请输入使用登记证号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="elabel" label="电子标签编号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.elabel" placeholder="请输入电子标签编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="norms" label="规格型号" size="medium">
                <el-select clearable v-model="editBottleForm.norms" placeholder="请选择规格型号" no-data-text="暂时没有可选择的规格型号" @change="editBottleFieldNormsChange" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableNormsArray" :key="item.id" :label="item.label" :value="item.id"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvefactory" label="阀门厂家" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.valvefactory" placeholder="请输入阀门厂家" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvetype" label="阀门类型" size="medium">
                <el-select clearable v-model="editBottleForm.valvetype" placeholder="请选择阀门类型" no-data-text="暂时没有可供选择的阀门类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableValvetype" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="unitselfno" label="充装单位自编号" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.unitselfno" placeholder="请输入充装单位自编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="maxcapacity" label="最大充量" size="medium">
                <el-input clearable readonly type="text" v-model="editBottleForm.maxcapacity" style="padding-left: 0px; padding-right: 0px;" slot-type="suffix">
                  <i v-if="editBottleFormMisc.maxcapacitySuffix !== null" slot="suffix">{{ editBottleFormMisc.maxcapacitySuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="selfweight" label="自重" size="medium">
                <el-input readonly type="text" v-model="editBottleForm.selfweight" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="editBottleFormMisc.selfweightSuffix !== null" slot="suffix">{{ editBottleFormMisc.selfweightSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="thickness" label="壁厚" size="medium">
                <el-input readonly type="text" v-model="editBottleForm.thickness" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="editBottleFormMisc.thicknessSuffix !== null" slot="suffix">{{ editBottleFormMisc.thicknessSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="volume" label="容积" size="medium">
                <el-input readonly type="text" v-model="editBottleForm.volume" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="editBottleFormMisc.volumeSuffix !== null" slot="suffix">{{ editBottleFormMisc.volumeSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="hotway" label="热处理方式" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.hotway" placeholder="请输入热处理方式" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="bodystuff" label="瓶体材料" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.bodystuff" placeholder="请输入瓶体材料" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="mediumname" label="充装介质" size="medium">
                <el-select clearable v-model="editBottleForm.mediumname" placeholder="请选择充装介质" no-data-text="暂时没有可选择的充装介质" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableMediumnameArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="testcount" label="已检次" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.testcount" placeholder="已检次" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkresult" label="安评结果" size="medium">
                <el-input clearable type="text" v-model="editBottleForm.checkresult" placeholder="请输入安评结果" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkdate" label="安评日期" size="medium">
                <el-date-picker clearable v-model="editBottleForm.checkdate" type="date" placeholder="请选择安评日期" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedstatus" label="气瓶状态" size="medium">
                <el-select clearable v-model="editBottleForm.usedstatus" placeholder="气瓶状态" no-data-text="暂时没有可选择的气瓶状态" style="padding-left: 0px; padding-right: 0px;">
                  <el-option v-for="item in selectableUsedstatus4Dialog" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.title }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="onlyused" label="是否专用" size="medium">
                <el-radio-group v-model="editBottleForm.onlyused">
                  <el-radio v-for="item in selectableOnlyused" :key="item.value" :label="item.value">{{ item.title }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="files" label="气瓶图片" size="medium" v-show="editBottleForm.attachmentYmd !== null && editBottleForm.attachmentUuid !== null">
                <el-upload
                  drag
                  with-credentials
                  name="file"
                  :accept="editBottle.accept"
                  :action="editBottleUploadActionUrl"
                  :auto-upload="editBottle.autoUpload"
                  :multiple="editBottle.multipleUpload && editBottleForm.attachmentYmd !== null && editBottleForm.attachmentUuid !== null"
                  :file-list="editBottleAttachmentUploadList"
                  :limit="editBottle.uploadLimit"
                  :headers="editBottleUploadHeaders"
                  :show-file-list="editBottle.showFileList"
                  :on-change="editBottleImageChange"
                  :on-exceed="editBottleImageExceed"
                  :on-progress="editBottleImageProgress"
                >
                  <el-button
                    round
                    size="small"
                    style="height: 24px; width: 90px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;"
                  >上传气瓶附件</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lastused" label="最后使用" size="medium">
                <el-date-picker clearable v-model="editBottleForm.lastused" type="month" placeholder="请选择最后使用" size="small" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="Array.isArray(editBottleAttachmentShowList) && editBottleAttachmentShowList.length > 0" :gutter="48" type="flex" justify="start" align="middle" style="padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; height: 119px; overflow-x: auto; overflow-y: hidden; background-color: #FFFFFF;">
            <div style="position: relative; top: 5px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: editBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !editBottleImageBorderLine(index)}" v-for="(item, index) in editBottleAttachmentShowList" :key="item.id">
              <!-- ———————————————— -->
              <!-- 版权声明：本文为CSDN博主「喜大普奔⁶⁶⁶」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 -->
              <!-- 原文链接：https://blog.csdn.net/qq_38652871/article/details/115081612 -->
              <!-- ———————————————— --><!-- display: flex; justify-content: space-around; -->
              <span class="el-image-viewer__btn" @click="editBottleImageRemove(index)"><i class="el-icon-error" style="margin-left: 80px; margin-top: 0px; font-size: 14pt;"></i></span>
              <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" v-bind:src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: editBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !editBottleImageBorderLine(index) }" @click="editBottleImageClick(index)" />
            </div>
          </el-row>
        </el-form>
      </div>
      <div style="height: 30px;" :style="{ width: editBottle.divWidth + 'px', display: editBottleFormDisplayString }">
        <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
          <el-button round @click="tbl_edit_data_submit('editForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          <el-button round @click="tbl_edit_data_reset('editForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">重置</el-button>
        </el-row>
      </div>
      <div :style="{ width: editBottleDivWidthString, height: editBottleDivHeightString, display: editBottlePictureDisplayString }">
        <el-container>
          <el-header :height="editBottlePictureRowHeightString" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: auto;">
            <span class="el-image-viewer__btn" v-if="Array.isArray(editBottleAttachmentShowList) && editBottleAttachmentShowList.length > 0" :style="{ display: editBottleChangeDisplayString }" @click="tbl_edit_data_change_to_form"><i class="el-icon-error" style="position: fixed; top: 9px; font-size: 18pt;" :style="{ left: editBottleChangeDisplayXOffsetString }"></i></span>
            <el-image :src="editBottleAttachmentShowList[editBottle.pictureIndex].uri" fill="none" v-if="Array.isArray(editBottleAttachmentShowList) && editBottleAttachmentShowList.length > 0" />
          </el-header>
          <el-main style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; height: 3px;" />
          <el-footer height="115px" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;">
            <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: hidden; background-color: #FFFFFF;">
              <div style="position: relative; top: 8px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: editBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !editBottleImageBorderLine(index)}" v-for="(item, index) in editBottleAttachmentShowList" :key="item.id">
                <span class="el-image-viewer__btn" @click="editBottleImageRemove(index)"><i class="el-icon-error" style="margin-left: 80px; margin-top: 0px; font-size: 14pt;"></i></span>
                <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" v-bind:src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: editBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !editBottleImageBorderLine(index) }" @click="editBottleImageDetailClick(index)" />
              </div>
            </el-row>
          </el-footer>
        </el-container>
      </div>
    </el-dialog>
    <!--查看气瓶档案-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :visible.sync="viewBottle.visible"
      :title="viewBottle.title"
      :show-close="viewBottleShowClose"
      :width="viewBottleWidthString"
      :style="{ width: viewBottleDialogWidthString, marginLeft: viewBottleMarginLeftString }"
      :before-close="tbl_before_hide_view_dialog_only"
      :top="viewBottleDialogTopString">
      <div :style="{ width: viewBottleDivWidthString, height: viewBottleDivHeightString, display: viewBottleFormDisplayString }">
        <el-form status-icon ref="viewForm" label-width="125px" :model="viewBottleForm">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qrcode" label="条码编号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.qrcode" placeholder="请输入条码编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="productno" label="钢印号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.productno" placeholder="请输入钢印号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producter" label="制造厂商" size="medium">
                <el-select readonly v-model="viewBottleForm.producter" placeholder="请选择制造厂家" no-data-text="暂时没有可选择的制造厂家" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableProducterArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="producted" label="出厂日期" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.producted" type="month" placeholder="请选择出厂日期" size="small" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lasttested" label="上检日期" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.lasttested" type="month" placeholder="请选择上检日期" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="nexttested" label="下检日期" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.nexttested" type="month" placeholder="请选4择下检日期" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="maxuseyears" label="报废年" size="medium">
                <el-select readonly v-model="viewBottleForm.maxuseyears" no-data-text="暂时没有可选择的报废年" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableMaxuseyears" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="expired" label="报废日期" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.expired" type="month" placeholder="请选择报废日期" size="small" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="qualifiedno" label="检验合格环号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.qualifiedno" placeholder="请输入检验合格环号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="registno" label="使用登记证编号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.registno" placeholder="请输入使用登记证编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedlicenseno" label="使用登记证号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.usedlicenseno" placeholder="请输入使用登记证号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="elabel" label="电子标签编号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.elabel" placeholder="请输入电子标签编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="norms" label="规格型号" size="medium">
                <el-select readonly v-model="viewBottleForm.norms" placeholder="请选择规格型号" no-data-text="暂时没有可选择的规格型号" @change="viewBottleFieldNormsChange" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableNormsArray" :key="item.id" :label="item.label" :value="item.id"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvefactory" label="阀门厂家" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.valvefactory" placeholder="请输入阀门厂家" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="valvetype" label="阀门类型" size="medium">
                <el-select readonly v-model="viewBottleForm.valvetype" placeholder="请选择阀门类型" no-data-text="暂时没有可供选择的阀门类型" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableValvetype" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="unitselfno" label="充装单位自编号" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.unitselfno" placeholder="请输入充装单位自编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="maxcapacity" label="最大充量" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.maxcapacity" style="padding-left: 0px; padding-right: 0px;" slot-type="suffix">
                  <i v-if="viewBottleFormMisc.maxcapacitySuffix !== null" slot="suffix">{{ viewBottleFormMisc.maxcapacitySuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="selfweight" label="自重" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.selfweight" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="viewBottleFormMisc.selfweightSuffix !== null" slot="suffix">{{ viewBottleFormMisc.selfweightSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="thickness" label="壁厚" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.thickness" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="viewBottleFormMisc.thicknessSuffix !== null" slot="suffix">{{ viewBottleFormMisc.thicknessSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="volume" label="容积" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.volume" style="padding-left: 0px; padding-right: 0px;">
                  <i v-if="viewBottleFormMisc.volumeSuffix !== null" slot="suffix">{{ viewBottleFormMisc.volumeSuffix }}</i>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="hotway" label="热处理方式" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.hotway" placeholder="请输入热处理方式" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="bodystuff" label="瓶体材料" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.bodystuff" placeholder="请输入瓶体材料" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="mediumname" label="充装介质" size="medium">
                <el-select readonly v-model="viewBottleForm.mediumname" placeholder="请选择充装介质" no-data-text="暂时没有可选择的充装介质" style="padding-left: 0px; padding-right: 0px;" >
                  <el-option v-for="item in selectableMediumnameArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="testcount" label="已检次" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.testcount" placeholder="已检次" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkresult" label="安评结果" size="medium">
                <el-input readonly type="text" v-model="viewBottleForm.checkresult" placeholder="请输入安评结果" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="checkdate" label="安评日期" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.checkdate" type="date" placeholder="请选择安评日期" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="usedstatus" label="气瓶状态" size="medium">
                <el-select readonly v-model="viewBottleForm.usedstatus" placeholder="气瓶状态" no-data-text="暂时没有可选择的气瓶状态" style="padding-left: 0px; padding-right: 0px;">
                  <el-option v-for="item in selectableUsedstatus4Dialog" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.title }}</span></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="onlyused" label="是否专用" size="medium">
                <el-radio-group v-model="viewBottleForm.onlyused">
                  <el-radio v-for="item in selectableOnlyused" :key="item.value" :label="item.value">{{ item.title }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;"><!--占位--></el-col>
            <el-col :span="12" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="lastused" label="最后使用" size="medium">
                <el-date-picker readonly v-model="viewBottleForm.lastused" type="month" placeholder="请选择最后使用" size="small" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="Array.isArray(viewBottleAttachmentShowList) && viewBottleAttachmentShowList.length > 0" :gutter="48" type="flex" justify="start" align="middle" style="padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; height: 119px; overflow-x: scroll; overflow-y: hidden; background-color: #FFFFFF;">
            <div style="position: relative; top: 5px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: viewBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !viewBottleImageBorderLine(index)}" v-for="(item,index) in viewBottleAttachmentShowList" :key="item.id">
              <!-- ———————————————— -->
              <!-- 版权声明：本文为CSDN博主「喜大普奔⁶⁶⁶」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。 -->
              <!-- 原文链接：https://blog.csdn.net/qq_38652871/article/details/115081612 -->
              <!-- ———————————————— --><!-- display: flex; justify-content: space-around; -->
              <!--<span class="el-image-viewer__btn"><i class="el-icon-error" style="margin-left: 80px; margin-top: 0px; font-size: 14pt;"></i></span>-->
              <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" v-bind:src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: viewBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !viewBottleImageBorderLine(index) }" @click="viewBottleImageClick(index)" />
            </div>
          </el-row>
        </el-form>
      </div>
      <div :style="{ width: viewBottleDivWidthString, height: viewBottleDivHeightString, display: viewBottlePictureDisplayString }">
        <el-container>
          <el-header :height="viewBottlePictureRowHeightString" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: auto;">
            <span class="el-image-viewer__btn" v-if="Array.isArray(viewBottleAttachmentShowList) && viewBottleAttachmentShowList.length > 0" :style="{ display: viewBottleChangeDisplayString }" @click="tbl_view_data_change_to_form"><i class="el-icon-error" style="position: fixed; top: 9px; font-size: 19pt;" :style="{ left: viewBottleChangeDisplayXOffsetString }"></i></span>
            <el-image :src="viewBottleAttachmentShowList[viewBottle.pictureIndex].uri" fill="none" v-if="Array.isArray(viewBottleAttachmentShowList) && viewBottleAttachmentShowList.length > 0" />
          </el-header>
          <el-main style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; height: 3px;" />
          <el-footer height="115px" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;">
            <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px; overflow-x: auto; overflow-y: hidden; background-color: #FFFFFF;">
              <div style="position: relative; top: 8px; padding: 0px; width: 100px; height: 115px;" :class="{BottleArchiveSelectedPictureMargin: viewBottleImageBorderLine(index), BottleArchiveUnselectedPictureMargin: !viewBottleImageBorderLine(index)}" v-for="(item,index) in viewBottleAttachmentShowList" :key="item.id">
                <el-image style="width: 100px; height: 100px; padding: 0px;" fill="fill" v-bind:src='item.uri' :class="{ BottleArchiveSelectedPictureBorder: viewBottleImageBorderLine(index), BottleArchiveUnselectedPictureBorder: !viewBottleImageBorderLine(index) }" @click="viewBottleImageDetailClick(index)" />
              </div>
            </el-row>
          </el-footer>
        </el-container>
      </div>
    </el-dialog>
    <!--修改气瓶编号对话框-->
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      :visible.sync="modifyQrcode.visible"
      :show-close="modifyQrcode.showClose"
      :title="modifyQrcode.title"
      :width="modifyQrcode.width"
      :style="{ width: modifyQrcode.digWidth + 'px' }"
      style="margin-left: 450px">
      <div :style="{ width: modifyQrcode.divWidth + 'px', height: modifyQrcode.divHeight + 'px' }">
        <el-form status-icon ref="modifyQrcodeForm" label-width="105px" :model="modifyQrcodeForm">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="oldQrcode" label="旧条码编号" size="medium">
                <el-input readonly type="text" v-model="modifyQrcodeForm.oldQrcode" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 15px; padding-right: 30px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="newQrcode" label="新条码编号" size="medium">
                <el-input clearable type="text" v-model="modifyQrcodeForm.newQrcode" placeholder="请输入新的条码编号" style="padding-left: 0px; padding-right: 0px;" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div style="height: 30px;" :style="{ width: modifyQrcode.divWidth + 'px' }">
          <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
            <el-button round @click="tbl_modify_qrcode_submit('modifyQrcodeForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">修改</el-button>
          </el-row>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
/*
 * https://www.npmjs.com/package/uuid ***
 * https://www.npmjs.com/package/uuid4?activeTab=code
 * https://juejin.cn/s/uuid%20is%20not%20a%20function%20node%20js
 * 3b48fe12-0f3c-44b8-83a4-404c512a0873 uuidv4.v4()
 * 55f186e588f9-4cf4-bceb-f09096fc763a  uuidv4.v4().replace('-', '')
 * 69e316f229d54b92917ece59f1245555     uuidv4.v4().replaceAll('-', '')
 */
/* import { v4 as uuidv4 } from 'uuid' */

export default {
  name: 'BottleArchive',
  data () {
    return {
      /*
       * 查询表单
       */
      queryForm: {
        qrcode: null, /* 条码编号 | 输入查询的条码编号 */
        productno: null, /* 钢印号 | 输入查询的钢印号 */
        producter: null, /* 制造厂商 | 请选择制造厂家 */
        mediumname: null, /* 充装介质 | 请选择充装介质 */
        norms: null, /* 规格型号 | 请选择规格型号 */
        producted: null, /*, 出厂日期 | 出厂日期，未选择日期时为null，选择日期后重置为长度是1的数组，且数组中下标是0的值为null，选择日期后为长度是2的数组，数组中的元素均为Date对象 */
        expired: null, /* 报废日期 | 报废日期，未选择日期时为null，选择日期后重置为长度是1的数组，且数组中下标是0的值为null，选择日期后为长度是2的数组，数组中的元素均为Date对象 */
        lasttested: null, /* 上检日期 | 上检日期，未选择日期时为null，选择日期后重置为长度是1的数组，且数组中下标是0的值为null，选择日期后为长度是2的数组，数组中的元素均为Date对象 */
        nexttested: null, /* 下检日期 | 下检日期，未选择日期时为null，选择日期后重置为长度是1的数组，且数组中下标是0的值为null，选择日期后为长度是2的数组，数组中的元素均为Date对象 */
        usedstatus: null /* 气瓶状态 | 气瓶状态 */
      },
      /*
       * 校验表单
       */
      queryRules: {},
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
      /*
       * 当前页的最大ID值
       */
      maxGasTankId: 0,
      /**
       * 表格数据
       */
      tableData: [],
      /**
       * 可供选择的每一页的数据条数
       */
      selectablePageNumberArray: [50, 80, 120, 200],
      /*
       * 可供选择的制造厂商
       */
      selectableProducterArray: [],
      /*
       * 可供选择的充装介质{ value: '液化石油气', label: '液化石油气' }, { value: '氢气', label: '氢气' }
       */
      selectableMediumnameArray: [],
      /*
       * 可供选择的规格型号
       */
      selectableNormsArray: [
        { id: 0, label: 'YSP4.7/2KG', value: { norms: 'YSP4.7', specs: 2, unitOfMeasurement: 'KG' }, maxcapacity: {value: 2, label: '(㎏)'}, selfweight: {value: 3.4, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 4.7, label: '(L)'} },
        { id: 1, label: 'YSP12/5KG', value: { norms: 'YSP12', specs: 5, unitOfMeasurement: 'KG' }, maxcapacity: {value: 5, label: '(㎏)'}, selfweight: {value: 6.5, label: '(㎏)'}, thickness: {value: 2, label: '(㎜)'}, volume: {value: 12, label: '(L)'} },
        { id: 2, label: 'YSP23.5/10KG', value: { norms: 'YSP23.5', specs: 10, unitOfMeasurement: 'KG' }, maxcapacity: {value: 10, label: '(㎏)'}, selfweight: {value: 13, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 23.5, label: '(L)'} },
        { id: 3, label: 'YSP26.2/12KG', value: { norms: 'YSP26.2', specs: 12, unitOfMeasurement: 'KG' }, maxcapacity: {value: 11, label: '(㎏)'}, selfweight: {value: 13.5, label: '(㎏)'}, thickness: {value: 2.4, label: '(㎜)'}, volume: {value: 26.2, label: '(L)'} },
        { id: 4, label: 'YSP28.6/13KG', value: { norms: 'YSP28.6', specs: 13, unitOfMeasurement: 'KG' }, maxcapacity: {value: 13, label: '(㎏)'}, selfweight: {value: 13, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 29.1, label: '(L)'} },
        { id: 5, label: 'YSP29.1/14KG', value: { norms: 'YSP29.1', specs: 14, unitOfMeasurement: 'KG' }, maxcapacity: {value: 14, label: '(㎏)'}, selfweight: {value: 14, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 29.1, label: '(L)'} },
        { id: 6, label: 'YSP35.5/15KG', value: { norms: 'YSP35.5', specs: 15, unitOfMeasurement: 'KG' }, maxcapacity: {value: 14.9, label: '(㎏)'}, selfweight: {value: 16.5, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 35.5, label: '(L)'} },
        { id: 7, label: 'YSP74/30KG', value: { norms: 'YSP74', specs: 30, unitOfMeasurement: 'KG' }, maxcapacity: {value: 30, label: '(㎏)'}, selfweight: {value: 16.5, label: '(㎏)'}, thickness: {value: 2.5, label: '(㎜)'}, volume: {value: 74, label: '(L)'} },
        { id: 8, label: 'YSP118/50KG', value: { norms: 'YSP118', specs: 50, unitOfMeasurement: 'KG' }, maxcapacity: {value: 49.5, label: '(㎏)'}, selfweight: {value: 45, label: '(㎏)'}, thickness: {value: 3.2, label: '(㎜)'}, volume: {value: 118, label: '(L)'} },
        { id: 9, label: 'YSP118II/50KG', value: { norms: 'YSP118II', specs: 50, unitOfMeasurement: 'KG' }, maxcapacity: {value: 49.5, label: '(㎏)'}, selfweight: {value: 45, label: '(㎏)'}, thickness: {value: 3.2, label: '(㎜)'}, volume: {value: 118, label: '(L)'} },
        { id: 10, label: '40L/40KG', value: { norms: '40L', specs: 40, unitOfMeasurement: 'KG' }, maxcapacity: {value: 40, label: '(㎏)'}, selfweight: {value: 43.4, label: '(㎏)'}, thickness: {value: 5, label: '(㎜)'}, volume: {value: 40, label: '(L)'} }
      ],
      /*
       * 可供选择的使用状态(Radio)
       */
      selectableUsedstatus4Form: [
        { label: '-1', value: '-1', title: '全部' },
        { label: '0', value: '0', title: '报废' },
        { label: '1', value: '1', title: '正常' },
        { label: '4', value: '4', title: '应送检' },
        { label: '8', value: '8', title: '丢失' },
        { label: '16', value: '16', title: '报检' }
      ],
      /*
       * 可供选择的使用状态
       */
      selectableUsedstatus4Dialog: [
        { label: '报废', value: 0, title: '报废' },
        { label: '正常', value: 1, title: '正常' },
        { label: '应送检', value: 4, title: '应送检' },
        { label: '丢失', value: 8, title: '丢失' },
        { label: '报检', value: 16, title: '报检' }
      ],
      /*
       * 可供选择的报废年限
       */
      selectableMaxuseyears: [ {value: 4, label: '4年'}, {value: 5, label: '5年'}, {value: 6, label: '6年'}, {value: 7, label: '7年'}, {value: 8, label: '8年'}, {value: 9, label: '9年'}, {value: 10, label: '10年'}, {value: 11, label: '11年'}, {value: 12, label: '12年'} ],
      /*
       * 可供选择的阀门类型
       */
      selectableValvetype: [ { value: '普通阀', label: '普通阀' }, { value: '智能阀', label: '智能阀' }, { value: '自闭阀', label: '自闭阀' } ],
      /*
       * 可供选择的是否专用(Radio)
       */
      selectableOnlyused: [ { value: 1, label: '1', title: '是' }, { value: 0, label: '0', title: '否' } ],
      /**
       * 新增气瓶档案对话框
         <el-upload
           drag
           with-credentials
           name="file"
           :accept="basePicture.accept"
           :action="baseUploadActionUrl"
           :auto-upload="basePicture.autoUpload"
           :multiple="basePicture.multipleUpload"
           :headers="baseUploadHeaders"
           :show-file-list="basePicture.showFileList"
           :on-change="baseImageChange"
           :on-progress="baseImageProgress"
         >
           <el-button
             round
             size="small"
             style="height: 24px; width: 90px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;"
           >上传气瓶附件</el-button>
         </el-upload>
       */
      addBottle: {
        title: '新增气瓶档案', // 对话框标题
        visible: false, // 对话框显示状态
        displayIndex: 0, // 对话框显示模式下标，下标为：0，显示表单，下标为：1，显示图片
        width: ['90%', '100%'], // 相对宽度
        digWidth: [1450, 0], // 对话框宽度(实际宽度×90%)
        digTop: ['15vh', '0vh'], // 到浏览器最上边的长度
        divWidth: [1255, 0], // 对话框主要区域宽度
        divHeight: [630, 0], // 对话框主要区域高度
        marginLeft: [300, 0], // (new)对话框的左边距
        formDisplay: ['block', 'none'], // (new)表单的显示方式
        pictureDisplay: ['none', 'block'], // (new)图片的显示方式
        pictureAreaTop: 46, // 图片区域上边距
        pictureAreaRight: 42, // 图片区域右边距
        pictureRowHeight: -133, // 顶部扣减
        changeToFormShow: ['none', 'block'], // 显示表单的按钮的显示方式
        pictureIndex: 0, // 显示第几张图
        showClose: [true, false], // 对话框是否显示关闭按钮
        showFileList: false, // 是否显示上传文件的列表
        autoUpload: true, // 气瓶档案是否自动上传
        multipleUpload: true, // 气瓶档案是否多文件上传
        accept: this.constant.GAS_SERVER_ATTACHMENT_PICTURE_EXT, // 目前只允许上传常用图片
        uploadChangeTimes: 0, // 上传时的on-upload回调次数
        uploadLimit: 7, // 限制上传的文件个数
        confirmTitle: '是否关闭新增气瓶档案' // 关闭对话框时的提示
      },
      /*
       * 新增气瓶档案对话框中的杂项
       */
      addBottleFormMisc: {
        maxcapacitySuffix: null, // 最大充量单位
        selfweightSuffix: null, // 自重单位
        thicknessSuffix: null, // 壁厚单位
        volumeSuffix: null // 容积单位
      },
      /*
       * 新增气瓶档案表单
       */
      addBottleForm: {
        qrcode: null, // String 条码编号
        productno: null, // String 钢印号
        lasttested: null, // Date 上检日期
        nexttested: null, // Date 下检日期
        producter: null, // String 制造厂商
        producted: null, // Date 出厂日期
        expired: null, // Date 报废日期
        maxuseyears: null, // Integer 报废年
        registno: null, // String 使用登记证编号
        unitselfno: null, // String 充装单位自编号
        mediumname: null, // String 充装介质
        thickness: null, // Float 壁厚
        norms: null, // String 规格型号(前半段)
        specs: null, // Float 规格型号(后半段)
        maxcapacity: null, // Float 最大充量
        volume: null, // Float 容积
        selfweight: null, // Float 自重
        valvetype: null, // String 阀门类型
        qualifiedno: null, // String 检验合格环号
        elabel: null, // String 电子标签编号
        usedlicenseno: null, // String 使用登记证号
        hotway: null, // String 热处理方式
        bodystuff: null, // String 瓶体材料
        valvefactory: null, // String 阀门厂家
        checkresult: null, // String 安评结果
        checkdate: null, // Date 安评日期
        onlyused: null, // Integer 是否专用
        attachmentUuid: null, // String 气瓶档案的图片UUID
        attachmentYmd: null, // String 气瓶档案的图片yyyymmdd
        testcount: null, // Integer 已检次
        usedstatus: null, // Integer 气瓶状态
        stationid: null, // 站点
        lastused: null // 最后使用
      },
      /*
       * 新增气瓶档案表单的校验
       */
      addBottleRules: {
        //
      },
      /*
       * 新增气瓶档案表单的附件(显示已经上传的列表中)
       */
      addBottleAttachmentShowList: [],
      /*
       * 新增气瓶档案表单的附件(el-upload控件中)
       */
      addBottleAttachmentUploadList: [],
      /**
       * 新增气瓶档案表单的附件(正在处理的附件)
       */
      addBottleAttachmentPreuploadList: [],
      /**
       * 修改气瓶档案对话框
       */
      editBottle: {
        title: '修改气瓶档案', // 对话框标题
        visible: false, // 对话框显示状态
        displayIndex: 0, // 对话框显示模式下标，下标为：0，显示表单，下标为：1，显示图片
        width: ['90%', '100%'], // 相对宽度
        digWidth: [1450, 0], // 对话框宽度(实际宽度×90%)
        digTop: ['15vh', '0vh'], // 到浏览器最上边的长度
        divWidth: [1255, 0], // 对话框主要区域宽度
        divHeight: [630, 0], // 对话框主要区域高度
        marginLeft: [300, 0], // (new)对话框的左边距
        formDisplay: ['block', 'none'], // (new)表单的显示方式
        pictureDisplay: ['none', 'block'], // (new)图片的显示方式
        pictureAreaTop: 46, // 图片区域上边距
        pictureAreaRight: 42, // 图片区域右边距
        pictureRowHeight: -133, // 顶部扣减
        changeToFormShow: ['none', 'block'], // 显示表单的按钮的显示方式
        pictureIndex: 0, // 显示第几张图
        showClose: [true, false], // 对话框是否显示关闭按钮
        showFileList: false, // 是否显示上传文件的列表
        autoUpload: true, // 气瓶档案是否自动上传
        multipleUpload: true, // 气瓶档案是否多文件上传
        accept: this.constant.GAS_SERVER_ATTACHMENT_PICTURE_EXT, // 目前只允许上传常用图片
        uploadChangeTimes: 0, // 上传时的on-upload回调次数
        uploadLimit: 7, // 限制上传的文件个数
        confirmTitle: '是否关闭修改气瓶档案', // 关闭对话框时的提示
        currentGasTankIndex: null // 当前正在编辑的气瓶档案在tableData数组中的下标
      },
      /*
       * 修改气瓶档案对话框中的杂项
       */
      editBottleFormMisc: {
        maxcapacitySuffix: null, // 最大充量单位
        selfweightSuffix: null, // 自重单位
        thicknessSuffix: null, // 壁厚单位
        volumeSuffix: null // 容积单位
      },
      /*
       * 修改气瓶档案表单
       */
      editBottleForm: {
        gastankid: null, // 主键
        qrcode: null, // String 条码编号
        productno: null, // String 钢印号
        lasttested: null, // Date 上检日期
        nexttested: null, // Date 下检日期
        producter: null, // String 制造厂商
        producted: null, // Date 出厂日期
        expired: null, // Date 报废日期
        maxuseyears: null, // Integer 报废年
        registno: null, // String 使用登记证编号
        unitselfno: null, // String 充装单位自编号
        mediumname: null, // String 充装介质
        thickness: null, // Float 壁厚
        norms: null, // String 规格型号(前半段)
        specs: null, // Float 规格型号(后半段)
        maxcapacity: null, // Float 最大充量
        volume: null, // Float 容积
        selfweight: null, // Float 自重
        valvetype: null, // String 阀门类型
        qualifiedno: null, // String 检验合格环号
        elabel: null, // String 电子标签编号
        usedlicenseno: null, // String 使用登记证号
        hotway: null, // String 热处理方式
        bodystuff: null, // String 瓶体材料
        valvefactory: null, // String 阀门厂家
        checkresult: null, // String 安评结果
        checkdate: null, // Date 安评日期
        onlyused: null, // Integer 是否专用
        attachmentUuid: null, // String 气瓶档案的图片UUID
        attachmentYmd: null, // String 气瓶档案的图片Ymd
        testcount: null, // Integer 已检次
        usedstatus: null, // Integer 气瓶状态
        lastused: null, // 最后使用
        attachmentOldUuid: null // String 气瓶档案的图片旧的UUID
      },
      /*
       * 修改气瓶档案表单的校验
       */
      editBottleRules: {},
      /*
       * 修改气瓶档案表单的附件(显示已经上传的列表中)
       */
      editBottleAttachmentShowList: [],
      /*
       * 修改气瓶档案表单的附件(el-upload控件中)
       */
      editBottleAttachmentUploadList: [],
      /**
       * 修改气瓶档案表单的附件(正在处理的附件)
       */
      editBottleAttachmentPreuploadList: [],
      /**
       * 查看气瓶档案对话框
       */
      viewBottle: {
        title: '查看气瓶档案', // 对话框标题
        visible: false, // 对话框显示状态
        displayIndex: 0, // 对话框显示模式下标，下标为：0，显示表单，下标为：1，显示图片
        width: ['90%', '100%'], // 相对宽度
        digWidth: [1450, 0], // 对话框宽度(实际宽度×90%)
        digTop: ['15vh', '0vh'], // 到浏览器最上边的长度
        divWidth: [1255, 0], // 对话框主要区域宽度
        divHeight: [630, 0], // 对话框主要区域高度
        marginLeft: [300, 0], // (new)对话框的左边距
        formDisplay: ['block', 'none'], // (new)表单的显示方式
        pictureDisplay: ['none', 'block'], // (new)图片的显示方式
        pictureAreaTop: 46, // 图片区域上边距
        pictureAreaRight: 42, // 图片区域右边距
        pictureRowHeight: -133, // 顶部扣减
        changeToFormShow: ['none', 'block'], // 显示表单的按钮的显示方式
        pictureIndex: 0, // 显示第几张图
        showClose: [true, false] // 对话框是否显示关闭按钮
      },
      /*
       * 查看气瓶档案对话框中的杂项
       */
      viewBottleFormMisc: {
        maxcapacitySuffix: null, // 最大充量单位
        selfweightSuffix: null, // 自重单位
        thicknessSuffix: null, // 壁厚单位
        volumeSuffix: null // 容积单位
      },
      /*
       * 查看气瓶档案表单
       */
      viewBottleForm: {
        gastankid: null, // 主键
        qrcode: null, // String 条码编号
        productno: null, // String 钢印号
        lasttested: null, // Date 上检日期
        nexttested: null, // Date 下检日期
        producter: null, // String 制造厂商
        producted: null, // Date 出厂日期
        expired: null, // Date 报废日期
        maxuseyears: null, // Integer 报废年
        registno: null, // String 使用登记证编号
        unitselfno: null, // String 充装单位自编号
        mediumname: null, // String 充装介质
        thickness: null, // Float 壁厚
        norms: null, // String 规格型号(前半段)
        specs: null, // Float 规格型号(后半段)
        maxcapacity: null, // Float 最大充量
        volume: null, // Float 容积
        selfweight: null, // Float 自重
        valvetype: null, // String 阀门类型
        qualifiedno: null, // String 检验合格环号
        elabel: null, // String 电子标签编号
        usedlicenseno: null, // String 使用登记证号
        hotway: null, // String 热处理方式
        bodystuff: null, // String 瓶体材料
        valvefactory: null, // String 阀门厂家
        checkresult: null, // String 安评结果
        checkdate: null, // Date 安评日期
        onlyused: null, // Integer 是否专用
        attachmentUuid: null, // String 气瓶档案的图片UUID
        testcount: null, // Integer 已检次
        usedstatus: null, // Integer 气瓶状态
        lastused: null // 最后使用
      },
      /*
       * 查看气瓶档案表单的附件
       */
      viewBottleAttachmentShowList: [],
      /**
       * 修改气瓶编号对话框
       */
      modifyQrcode: {
        title: '修改气瓶编号', // 对话框标题
        visible: false, // 对话框显示状态
        width: '90%', // 相对宽度
        digWidth: 370, // 对话框宽度(实际宽度×90%)
        divWidth: 290, // 对话框主要区域宽度
        divHeight: 100, // 对话框主要区域高度
        showClose: true, // 对话框是否显示关闭按钮
        currentGasTankIndex: null // 当前正在编辑的气瓶档案编号在tableData数组中的下标
      },
      modifyQrcodeForm: {
        gastankid: null, // 主键
        oldQrcode: null, // String 旧条码编号
        newQrcode: null // String 新条码编号
      },
      /**
       * 图片基本路径
       */
      imagePrefix: this.constant.GAS_IMAGE_PREFIX + '/BottleArchive',
      /**
       * 导出基本路径
       */
      exportPrefix: this.constant.GAS_EXPORT_PREFIX + '/BottleArchive'
    }
  },
  computed: {
    /*
     * 新增气瓶档案的图片时用的上传路径
     */
    addBottleUploadActionUrl: function () {
      const localList = this.addBottleAttachmentShowList
      if (localList.length > 0) {
        const localItem = localList[0]
        return this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/uploadOld/' + localItem.ymd + '/' + localItem.fileUuid
      }
      return (this.addBottleForm.attachmentYmd === null || this.addBottleForm.attachmentUuid === null) ? this.constant.GAS_SERVER_ATTACHMENT_PREFIX : this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/uploadNew/' + this.addBottleForm.attachmentYmd + '/' + this.addBottleForm.attachmentUuid
    },
    /*
     * 新增气瓶档案的图片时用的header
     */
    addBottleUploadHeaders: function () {
      return {
        'reference': this.$router.currentRoute.fullPath
      }
    },
    /*
     * 修改气瓶档案的图片时用的上传路径
     */
    editBottleUploadActionUrl: function () {
      const localList = this.editBottleAttachmentShowList
      if (localList.length > 0) {
        const localItem = localList[0]
        return this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/uploadOld/' + localItem.ymd + '/' + localItem.fileUuid
      }
      return (this.editBottleForm.attachmentYmd === null || this.editBottleForm.attachmentUuid === null) ? this.constant.GAS_SERVER_ATTACHMENT_PREFIX : this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/uploadNew/' + this.editBottleForm.attachmentYmd + '/' + this.editBottleForm.attachmentUuid
    },
    /*
     * 修改气瓶档案的图片时用的header
     */
    editBottleUploadHeaders: function () {
      return {
        'reference': this.$router.currentRoute.fullPath
      }
    },
    // 对话框显示比例
    addBottleWidthString: function () {
      return this.addBottle.width[this.addBottle.displayIndex]
    },
    // 对话框显示关闭
    addBottleShowClose: function () {
      return this.addBottle.showClose[this.addBottle.displayIndex]
    },
    // 对话框的宽
    addBottleDialogWidthString: function () {
      return this.addBottle.digWidth[this.addBottle.displayIndex] + 'px'
    },
    // 对话框到顶的距离
    addBottleDialogTopString: function () {
      return this.addBottle.digTop[this.addBottle.displayIndex]
    },
    // 对话框内div的宽
    addBottleDivWidthString: function () {
      return this.addBottle.divWidth[this.addBottle.displayIndex] + 'px'
    },
    // 对话框内div的高
    addBottleDivHeightString: function () {
      return this.addBottle.divHeight[this.addBottle.displayIndex] + 'px'
    },
    // 对话框左边距
    addBottleMarginLeftString: function () {
      return this.addBottle.marginLeft[this.addBottle.displayIndex] + 'px'
    },
    // 对话框表单的显示
    addBottleFormDisplayString: function () {
      return this.addBottle.formDisplay[this.addBottle.displayIndex]
    },
    // 对话框图片的显示
    addBottlePictureDisplayString: function () {
      return this.addBottle.pictureDisplay[this.addBottle.displayIndex]
    },
    // 对话框主要区域的高
    addBottlePictureRowHeightString: function () {
      return (this.addBottle.divHeight[this.addBottle.displayIndex] + this.addBottle.pictureRowHeight) + 'px'
    },
    // 显示表单的按钮的横坐标
    addBottleChangeDisplayXOffsetString: function () {
      return (this.addBottle.divWidth[this.addBottle.displayIndex] - 30) + 'px'
    },
    // 显示表单的按钮的显示
    addBottleChangeDisplayString: function () {
      return this.addBottle.changeToFormShow[this.addBottle.displayIndex]
    },
    // 对话框显示比例
    editBottleWidthString: function () {
      return this.editBottle.width[this.editBottle.displayIndex]
    },
    // 对话框显示关闭
    editBottleShowClose: function () {
      return this.editBottle.showClose[this.editBottle.displayIndex]
    },
    // 对话框的宽
    editBottleDialogWidthString: function () {
      return this.editBottle.digWidth[this.editBottle.displayIndex] + 'px'
    },
    // 对话框到顶的距离
    editBottleDialogTopString: function () {
      return this.editBottle.digTop[this.editBottle.displayIndex]
    },
    // 对话框内div的宽
    editBottleDivWidthString: function () {
      return this.editBottle.divWidth[this.editBottle.displayIndex] + 'px'
    },
    // 对话框内div的高
    editBottleDivHeightString: function () {
      return this.editBottle.divHeight[this.editBottle.displayIndex] + 'px'
    },
    // 对话框左边距
    editBottleMarginLeftString: function () {
      return this.editBottle.marginLeft[this.editBottle.displayIndex] + 'px'
    },
    // 对话框表单的显示
    editBottleFormDisplayString: function () {
      return this.editBottle.formDisplay[this.editBottle.displayIndex]
    },
    // 对话框图片的显示
    editBottlePictureDisplayString: function () {
      return this.editBottle.pictureDisplay[this.editBottle.displayIndex]
    },
    // 对话框主要区域的高
    editBottlePictureRowHeightString: function () {
      return (this.editBottle.divHeight[this.editBottle.displayIndex] + this.editBottle.pictureRowHeight) + 'px'
    },
    // 显示表单的按钮的横坐标
    editBottleChangeDisplayXOffsetString: function () {
      return (this.editBottle.divWidth[this.editBottle.displayIndex] - 30) + 'px'
    },
    // 显示表单的按钮的显示
    editBottleChangeDisplayString: function () {
      return this.editBottle.changeToFormShow[this.editBottle.displayIndex]
    },
    // 对话框显示比例
    viewBottleWidthString: function () {
      return this.viewBottle.width[this.viewBottle.displayIndex]
    },
    // 对话框显示关闭
    viewBottleShowClose: function () {
      return this.viewBottle.showClose[this.viewBottle.displayIndex]
    },
    // 对话框的宽
    viewBottleDialogWidthString: function () {
      return this.viewBottle.digWidth[this.viewBottle.displayIndex] + 'px'
    },
    // 对话框到顶的距离
    viewBottleDialogTopString: function () {
      return this.viewBottle.digTop[this.viewBottle.displayIndex]
    },
    // 对话框内div的宽
    viewBottleDivWidthString: function () {
      return this.viewBottle.divWidth[this.viewBottle.displayIndex] + 'px'
    },
    // 对话框内div的高
    viewBottleDivHeightString: function () {
      return this.viewBottle.divHeight[this.viewBottle.displayIndex] + 'px'
    },
    // 对话框左边距
    viewBottleMarginLeftString: function () {
      return this.viewBottle.marginLeft[this.viewBottle.displayIndex] + 'px'
    },
    // 对话框表单的显示
    viewBottleFormDisplayString: function () {
      return this.viewBottle.formDisplay[this.viewBottle.displayIndex]
    },
    // 对话框图片的显示
    viewBottlePictureDisplayString: function () {
      return this.viewBottle.pictureDisplay[this.viewBottle.displayIndex]
    },
    // 对话框主要区域的高
    viewBottlePictureRowHeightString: function () {
      return (this.viewBottle.divHeight[this.viewBottle.displayIndex] + this.viewBottle.pictureRowHeight) + 'px'
    },
    // 显示表单的按钮的横坐标
    viewBottleChangeDisplayXOffsetString: function () {
      return (this.viewBottle.divWidth[this.viewBottle.displayIndex] - 30) + 'px'
    },
    // 显示表单的按钮的显示
    viewBottleChangeDisplayString: function () {
      return this.viewBottle.changeToFormShow[this.viewBottle.displayIndex]
    },
    /*
     * 表格高度
     */
    tableHeightString: function () {
      return ((this.pagination.size + 1) * 29 + 1) + 'px'
    }
  },
  mounted () {
    /*
     * 厂家信息
     */
    this.$axios.get(this.constant.GAS_SERVER_PREFIX + '/systemData/systemdata/getSelectableProducterArray', { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
      this.selectableProducterArray = (res.status === 200 && Array.isArray(res.data)) ? res.data : []
    }).catch(ex => {
      this.selectableProducterArray = []
      console.log(ex)
    })
    /*
     * 充装介质
     */
    try {
      let localStationString = this.globalVar.globalStationArray.length > 0 ? this.globalVar.globalStationArray[0].value : ''
      for (var i = 1; i < this.globalVar.globalStationArray.length; ++i) {
        localStationString += ', ' + this.globalVar.globalStationArray[i].value
      }
      const localSelectableMediumnameForm = new FormData()
      localSelectableMediumnameForm.append('stationId', localStationString)
      this.$axios.postForm(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationgastype/queryByStationId', localSelectableMediumnameForm, { headers: { 'Content-Type': 'application/x-www-form-urlencoded', 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        this.selectableMediumnameArray = (res.status === 200 && Array.isArray(res.data)) ? res.data : []
      }).catch(ex => {
        this.selectableMediumnameArray = []
        console.log(ex)
      })
    } catch (ex) {
      this.selectableMediumnameArray = []
    }
  },
  methods: {
    executeQuery (formName) {
      const localProducted = this.convertDateArrayTo(this.queryForm.producted)
      const localExpired = this.convertDateArrayTo(this.queryForm.expired)
      const locallasttested = this.convertDateArrayTo(this.queryForm.lasttested)
      const localnexttexted = this.convertDateArrayTo(this.queryForm.nexttested)
      const localQueryForm = {
        qrcode: this.queryForm.qrcode,
        productno: this.queryForm.productno,
        producter: this.queryForm.producter,
        mediumname: this.queryForm.mediumname,
        norms: this.queryForm.norms === null ? null : this.selectableNormsArray[this.queryForm.norms].value,
        productedFrom: localProducted[0],
        productedTo: localProducted[1],
        expiredFrom: localExpired[0],
        expiredTo: localExpired[1],
        lasttestedFrom: locallasttested[0],
        lasttestedTo: locallasttested[1],
        nexttestedFrom: localnexttexted[0],
        nexttestedTo: localnexttexted[1],
        usedstatus: this.queryForm.usedstatus
      }
      const requestForm = new FormData()
      requestForm.append('form', JSON.stringify(localQueryForm))
      requestForm.append('page', JSON.stringify(this.pagination))
      this.$axios.postForm(
        this.constant.GAS_SERVER_PREFIX + '/gastankinfo/gastankinfo/queryByPage',
        requestForm,
        {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'reference': this.$router.currentRoute.fullPath
          }
        }
      ).then(res => {
        if (res.status === 200) {
          this.queryCount = res.data.totalCount
          const items = res.data.items
          var maxId = 0
          for (var i = 0; i < items.length; ++i) {
            items[i].producted = this.parseRemoteDateString(items[i].producted)
            items[i].lasttested = this.parseRemoteDateString(items[i].lasttested)
            items[i].nexttested = this.parseRemoteDateString(items[i].nexttested)
            items[i].expired = this.parseRemoteDateString(items[i].expired)
            items[i].lastused = this.parseRemoteDateString(items[i].lastused)
            items[i].created = this.parseRemoteDateString(items[i].created)
            items[i].modified = this.parseRemoteDateString(items[i].modified)
            items[i].checkdate = this.parseRemoteDateString(items[i].checkdate)
            if (maxId < items[i].gastankid) {
              maxId = items[i].gastankid
            }
          }
          this.tableData = items
          this.maxGasTankId = maxId
        } else {
          this.queryCount = 0
          this.tableData = []
          this.maxGasTankId = 0
        }
      }).catch(ex => {
        this.queryCount = 0
        this.tableData = []
        this.maxGasTankId = 0
      })
    },
    /**
     * 把表单的数据还原为初始状态
     * @param {String} formName 表单的引用信息
     */
    executeReset (formName) {
      this.$refs[formName].resetFields() // 或者：this.$refs.表单的引用信息.resetFields()
    },
    executeExport (formName) {
      const localProducted = this.convertDateArrayTo(this.queryForm.producted)
      const localExpired = this.convertDateArrayTo(this.queryForm.expired)
      const locallasttested = this.convertDateArrayTo(this.queryForm.lasttested)
      const localnexttexted = this.convertDateArrayTo(this.queryForm.nexttested)
      const localQueryForm = {
        qrcode: this.queryForm.qrcode,
        productno: this.queryForm.productno,
        producter: this.queryForm.producter,
        mediumname: this.queryForm.mediumname,
        norms: this.queryForm.norms === null ? null : this.selectableNormsArray[this.queryForm.norms].value,
        productedFrom: localProducted[0],
        productedTo: localProducted[1],
        expiredFrom: localExpired[0],
        expiredTo: localExpired[1],
        lasttestedFrom: locallasttested[0],
        lasttestedTo: locallasttested[1],
        nexttestedFrom: localnexttexted[0],
        nexttestedTo: localnexttexted[1],
        usedstatus: this.queryForm.usedstatus
      }
      this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/gastankinfo/gastankinfo/export', localQueryForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        if (res.status === 200) {
          window.open(this.exportPrefix + res.data, '气瓶档案导出')
          // this.$download(this.exportPrefix + res.data, '气瓶档案导出' + Date.now() + '.xlsx')
        }
      }).catch(ex => {
      })
    },
    /*
     * 每页数据条数变化时
     */
    paginationSizeChange (size) {
      this.pagination.size = size
      this.executeQuery('queryFrom')
    },
    /*
     * 当前第多少页变化时
     */
    paginationCurrentChange (current) {
      this.pagination.current = current
      this.executeQuery('queryForm')
    },
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
              return [this.dateToString(arr[0]), null]
            case 2:
              return [null, this.dateToString(arr[1])]
            case 3:
              return arr[0].getTime() < arr[1].getTime() ? [this.dateToString(arr[0]), this.dateToString(arr[1])] : [this.dateToString(arr[1]), this.dateToString(arr[0])]
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
    /*
     * 格式数据的函数起
     */
    /* tbl_qrcode_formatter (row, column, cellValue, index) {}, */
    /* tbl_producter_formatter (row, column, cellValue, index) {}, */
    /* tbl_productno_formatter (row, column, cellValue, index) {}, */
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_producted_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      const m = cellValue.getMonth() + 1
      return cellValue.getFullYear() + ((m > 9) ? '-' + m : '-0' + m)
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_nexttested_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      const m = cellValue.getMonth() + 1
      return cellValue.getFullYear() + ((m > 9) ? '-' + m : '-0' + m)
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_expired_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      const m = cellValue.getMonth() + 1
      return cellValue.getFullYear() + ((m > 9) ? '-' + m : '-0' + m)
    },
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_lasttested_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      const m = cellValue.getMonth() + 1
      return cellValue.getFullYear() + ((m > 9) ? '-' + m : '-0' + m)
    },
    /* tbl_testcount_formatter (row, column, cellValue, index) {}, */
    /* tbl_maxuseyears_formatter (row, column, cellValue, index) {}, */
    /* tbl_mediumname_formatter (row, column, cellValue, index) {}, */
    /**
     * 格式化类型字段
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_norms_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      if (cellValue.indexOf('/') === -1) {
        return row.norms + '/' + (row.specs === null ? 'KG' : Math.round(row.specs) + 'KG')
      }
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
    tbl_usedstatus_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      switch (cellValue) {
        case 0:
          return '报废'
        case 1:
          return '正常'
        case 4:
          return '应送检'
        case 8:
          return '丢失'
        case 16:
          return '报检'
        default:
          return null
      }
    },
    /**
     * 状态(格式化Number类型字段)
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_maxcapacity_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(cellValue).toFixed(1)
      }
      return null
    },
    /**
     * 自重(格式化Number类型字段)
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_selfweight_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(cellValue).toFixed(1)
      }
      return null
    },
    /**
     * 壁厚(格式化Number类型字段)
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_thickness_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(cellValue).toFixed(2)
      }
      return null
    },
    /**
     * 容积(格式化Number类型字段)
     * @param {Object} row 具体的某一行的数据
     * @param {Object} column 当前列的定义信息
     * @param {Object} cellValue 具体某一行某一列的数据
     * @param {Int} index 当前数据在表格数组中对应的下标
     * @return 格式化后的数据
     */
    tbl_volume_formatter (row, column, cellValue, index) {
      if (Number.isFinite(cellValue)) {
        return Number.parseFloat(cellValue).toFixed(1)
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
    tbl_onlyused_formatter (row, column, cellValue, index) {
      if (cellValue === null) {
        return null
      }
      switch (cellValue) {
        case 0:
          return '否'
        case 1:
          return '是'
        default:
          return null
      }
    },
    /*
     * 格式化数据的函数止
     */
    /*
     * 对话框方法起
     */
    /**
     * 关闭所有对话框
     */
    tbl_close_all_dialog () {
      this.addBottle.visible = false
      this.editBottle.visible = false
      this.viewBottle.visible = false
      this.modifyQrcode.visible = false
    },
    tbl_show_modify_qrcode_only () {
      this.addBottle.visible = false
      this.editBottle.visible = false
      this.viewBottle.visible = false
      this.modifyQrcode.visible = true
    },
    tbl_hide_modify_qrcode_only () {
      this.modifyQrcode.visible = false
    },
    tbl_show_edit_dialog_only () {
      this.addBottle.visible = false
      this.editBottle.visible = true
      this.viewBottle.visible = false
      this.modifyQrcode.visible = false
      this.editBottle.displayIndex = 0
      this.editBottle.pictureIndex = 0
    },
    tbl_hide_edit_dialog_only () {
      this.editBottle.visible = false
    },
    tbl_show_view_dialog_only () {
      this.addBottle.visible = false
      this.editBottle.visible = false
      this.viewBottle.visible = true
      this.modifyQrcode.visible = false
      this.viewBottle.displayIndex = 0
      this.viewBottle.pictureIndex = 0
    },
    tbl_hide_view_dialog_only () {
      this.viewBottle.visible = false
    },
    tbl_show_add_dialog_only () {
      this.addBottle.visible = true
      this.editBottle.visible = false
      this.viewBottle.visible = false
      this.modifyQrcode.visible = false
      this.addBottle.displayIndex = 0
      this.addBottle.pictureIndex = 0
    },
    tbl_hide_add_dialog_only () {
      this.addBottle.visible = false
    },
    tbl_before_hide_add_dialog_only (ok) {
      this.$confirm(this.addBottle.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
        this.tbl_add_data_finish()
        this.tbl_hide_add_dialog_only()
        this.tbl_add_data_reset()
      }).catch(_ => {})
    },
    tbl_before_hide_edit_dialog_only (ok) {
      this.$confirm(this.viewBottle.confirmTitle, '提示', { confirmButtonText: '关闭', cancelButtonText: '取消' }).then(_ => {
        ok()
        this.tbl_edit_data_finish()
        this.tbl_hide_edit_dialog_only()
        this.tbl_edit_data_reset()
      }).catch(_ => {})
    },
    tbl_before_hide_view_dialog_only (ok) {
      ok()
      this.tbl_view_data_clear()
      this.tbl_hide_view_dialog_only()
    },
    /*
     * 新增数据
     */
    tbl_add_data () {
      this.addBottleAttachmentShowList = []
      this.addBottleAttachmentUploadList = []
      this.tbl_add_data_before()
      this.tbl_show_add_dialog_only()
    },
    /**
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_edit_data ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.tbl_show_edit_dialog_only()
      this.editBottleAttachmentShowList = []
      this.editBottleAttachmentUploadList = []
      this.editBottleForm.gastankid = row.gastankid
      this.editBottle.currentGasTankIndex = $index
      this.tbl_edit_data_copy_value(row, this.editBottleForm)
      this.editBottleImageQueryByArchiveUuid(row.attachmentUuid)
      /*
       * 字符串转下标
       */
      this.editBottleForm.norms = this.normsToNumber(row.norms)
    },
    /**
     * 查看气瓶档案
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_view_data ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.viewBottleAttachmentShowList = []
      this.tbl_show_view_dialog_only()
      this.viewBottleForm.gastankid = row.gastankid
      this.tbl_view_data_copy_value(row, this.viewBottleForm)
      this.tbl_view_data_query_picture(row.attachmentUuid)
    },
    /**
     * @param scope所在行的信息，包含以下属性：
     *        $index scope所在行的数据在表格数组中的下标
     *        column scope所在列的信息
     *        isExpanded scope所在的行是否展开
     *        isSelected scope所在的行是否被选中
     *        row scope所在行的数据
     *        store
     *        _self 表格自身的数据等信息
     */
    tbl_modify_qrcode ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.tbl_show_modify_qrcode_only()
      this.modifyQrcodeForm.gastankid = row.gastankid
      this.modifyQrcodeForm.oldQrcode = row.qrcode
      this.modifyQrcode.currentGasTankIndex = $index
    },
    /*
     * 新增对话框事件方法起
     */
    addBottleDialogLasttested (newValue) {
      if (newValue !== null && this.addBottleForm.nexttested === null) {
        const localDate = new Date()
        this.dateAddYear(newValue, localDate, 4)
        this.addBottleForm.nexttested = localDate
      }
    },
    addBottleDialogNexttested (newValue) {
      if (newValue !== null && this.addBottleForm.lasttested === null) {
        const localDate = new Date()
        this.dateSubYear(newValue, localDate, 4)
        this.addBottleForm.lasttested = localDate
      }
    },
    addBottleDialogProducted (newValue) {
      if (newValue === null) {
        return
      }
      switch (((typeof this.addBottleForm.maxuseyears) === 'number' ? 2 : 0) + (this.addBottleForm.expired === null ? 0 : 1)) {
        case 0: {
          break
        }
        case 1: {
          const localValue = this.addBottleForm.expired.getFullYear() - newValue.getFullYear()
          for (var i = 0; i < this.selectableMaxuseyears.length; ++i) {
            if (this.selectableMaxuseyears[i].value === localValue) {
              this.addBottleForm.maxuseyears = localValue
              const localDateA = new Date()
              localDateA.setFullYear(newValue.getFullYear() + localValue, newValue.getMonth(), newValue.getDate())
              this.addBottleForm.expired = localDateA
              return
            }
          }
          this.addBottleForm.producted = null
          break
        }
        case 2: {
          const localDateB = new Date()
          this.dateAddYear(newValue, localDateB, this.addBottleForm.maxuseyears)
          this.addBottleForm.expired = localDateB
          break
        }
        case 3: {
          const localDateC = new Date()
          this.dateAddYear(newValue, localDateC, this.addBottleForm.maxuseyears)
          this.addBottleForm.expired = localDateC
          break
        }
        default: {
          break
        }
      }
    },
    addBottleDialogExpired (newValue) {
      if (newValue === null) {
        return
      }
      switch ((this.addBottleForm.producted === null ? 0 : 2) + ((typeof this.addBottleForm.maxuseyears) === 'number' ? 1 : 0)) {
        case 0: {
          break
        }
        case 1: {
          const localDateA = new Date()
          this.dateSubYear(newValue, localDateA, this.addBottleForm.maxuseyears)
          this.addBottleForm.producted = localDateA
          break
        }
        case 2: {
          const localValueB = newValue.getFullYear() - this.addBottleForm.producted.getFullYear()
          for (var i = 0; i < this.selectableMaxuseyears.length; ++i) {
            if (this.selectableMaxuseyears[i].value === localValueB) {
              this.addBottleForm.maxuseyears = localValueB
              const localDateB = new Date()
              localDateB.setFullYear(newValue.getFullYear() - localValueB, newValue.getMonth(), newValue.getDate())
              this.addBottleForm.producted = localDateB
              return
            }
          }
          this.addBottleForm.expired = null
          break
        }
        case 3: {
          const localValueC = newValue.getFullYear() - this.addBottleForm.producted.getFullYear()
          for (var j = 0; j < this.selectableMaxuseyears.length; ++j) {
            if (this.selectableMaxuseyears[j].value === localValueC) {
              this.addBottleForm.maxuseyears = localValueC
              const localDateC = new Date()
              localDateC.setFullYear(newValue.getFullYear() - localValueC, newValue.getMonth(), newValue.getDate())
              this.addBottleForm.producted = localDateC
              return
            }
          }
          this.addBottleForm.expired = null
          break
        }
        default: {
          break
        }
      }
    },
    addBottleDialogMaxuseyears (newValue) {
      if (newValue === null) {
        return
      }
      if ((typeof newValue) === 'number') {
        switch ((this.addBottleForm.producted === null ? 0 : 2) + (this.addBottleForm.expired === null ? 0 : 1)) {
          case 0: {
            break
          }
          case 1: {
            const localDateA = new Date()
            this.dateSubYear(this.addBottleForm.expired, localDateA, newValue)
            this.addBottleForm.producted = localDateA
            break
          }
          case 2: {
            const localDateB = new Date()
            this.dateAddYear(this.addBottleForm.producted, localDateB, newValue)
            this.addBottleForm.expired = localDateB
            break
          }
          case 3: {
            const localDateC = new Date()
            this.dateAddYear(this.addBottleForm.producted, localDateC, newValue)
            this.addBottleForm.expired = localDateC
            break
          }
          default: {
            break
          }
        }
      }
    },
    /*
     * 新增对话框事件方法止
     */
    /**
     * 请求Uuid
     */
    tbl_add_data_before () {
      if (this.addBottleForm.attachmentYmd === null || this.addBottleForm.attachmentUuid === null) {
        this.addBottleForm.attachmentYmd = null
        this.addBottleForm.attachmentUuid = null
        this.$axios.get(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/before', { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            this.addBottleForm.attachmentYmd = res.data.yyyymmdd
            this.addBottleForm.attachmentUuid = res.data.uuid
          } else {
            this.addBottleForm.attachmentYmd = null
            this.addBottleForm.attachmentUuid = null
          }
        }).catch(ex => {
          this.addBottleForm.attachmentYmd = null
          this.addBottleForm.attachmentUuid = null
        })
      }
    },
    /**
     * 新增气瓶档案
     */
    tbl_add_data_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localAddbottleForm = {
          qrcode: this.addBottleForm.qrcode,
          productno: this.addBottleForm.productno,
          lasttested: this.addBottleForm.lasttested === null ? null : this.dateToString(this.addBottleForm.lasttested),
          nexttested: this.addBottleForm.nexttested === null ? null : this.dateToString(this.addBottleForm.nexttested),
          producter: this.addBottleForm.producter,
          producted: this.addBottleForm.producted === null ? null : this.dateToString(this.addBottleForm.producted),
          expired: this.addBottleForm.expired === null ? null : this.dateToString(this.addBottleForm.expired),
          maxuseyears: this.addBottleForm.maxuseyears,
          registno: this.addBottleForm.registno,
          unitselfno: this.addBottleForm.unitselfno,
          mediumname: this.addBottleForm.mediumname,
          thickness: this.addBottleForm.thickness,
          norms: this.normsToString(this.addBottleForm.norms),
          specs: this.addBottleForm.specs,
          maxcapacity: this.addBottleForm.maxcapacity,
          volume: this.addBottleForm.volume,
          selfweight: this.addBottleForm.selfweight,
          valvetype: this.addBottleForm.valvetype,
          qualifiedno: this.addBottleForm.qualifiedno,
          elabel: this.addBottleForm.elabel,
          usedlicenseno: this.addBottleForm.usedlicenseno,
          hotway: this.addBottleForm.hotway,
          bodystuff: this.addBottleForm.bodystuff,
          valvefactory: this.addBottleForm.valvefactory,
          checkresult: this.addBottleForm.checkresult,
          checkdate: this.addBottleForm.checkdate === null ? null : this.dateToString(this.addBottleForm.checkdate),
          usedstatus: this.addBottleForm.usedstatus,
          onlyused: this.addBottleForm.onlyused,
          attachmentUuid: this.addBottleForm.attachmentUuid,
          testcount: this.addBottleForm.testcount,
          lastused: this.addBottleForm.lastused === null ? null : this.dateToString(this.addBottleForm.lastused),
          stationid: this.addBottleForm.stationid === null ? ((this.globalVar.globalStationArray.length === 1) ? this.globalVar.globalStationArray[0].value : null) : this.addBottleForm.stationid
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/gastankinfo/gastankinfo/saveSingle', localAddbottleForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data.success) {
            const obj = res.data.object
            if (this.maxGasTankId < obj.gastankid) {
              this.maxGasTankId = obj.gastankid
              obj.lasttested = this.parseRemoteDateString(obj.lasttested)
              obj.nexttested = this.parseRemoteDateString(obj.nexttested)
              obj.producted = this.parseRemoteDateString(obj.producted)
              obj.expired = this.parseRemoteDateString(obj.expired)
              obj.checkdate = this.parseRemoteDateString(obj.checkdate)
              obj.lastused = this.parseRemoteDateString(obj.lastused)
              obj.created = this.parseRemoteDateString(obj.created)
              this.tableData.unshift(obj)
              for (; this.tableData.length > this.pagination.size;) {
                this.tableData.pop()
              }
            } else {
              this.queryForm()
            }
            this.tbl_add_data_reset(formName)
            this.tbl_add_data_finish()
            this.tbl_hide_add_dialog_only()
          }
        }).catch(ex => {
          this.$message('新增失败')
        })
        return true
      })
    },
    tbl_add_data_reset (formName) {
      this.$refs[formName].resetFields()
    },
    /**
     * 取消Uuid
     */
    tbl_add_data_finish () {
      if (this.addBottleForm.attachmentYmd === null || this.addBottleForm.attachmentUuid === null) {
        this.addBottleForm.attachmentYmd = null
        this.addBottleForm.attachmentUuid = null
        return
      }
      this.$axios.get(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/finish/' + this.addBottleForm.attachmentYmd + '/' + this.addBottleForm.attachmentUuid, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
      }).catch(ex => {
      })
      this.addBottleForm.attachmentYmd = null
      this.addBottleForm.attachmentUuid = null
    },
    /**
     * 显示表单
     */
    tbl_add_data_change_to_form () {
      this.addBottle.displayIndex = 0
    },
    /**
     * @param file 文件信息
     * @param list 文件列表
     */
    addBottleImageChange (file, list) {
      switch (file.status) {
        case 'success' : {
          if (Array.isArray(file.response)) {
            const localArray = this.addBottleAttachmentShowList
            const localList = Array(file.response.length)
            for (var i = 0; i < file.response.length; ++i) {
              let found = false
              localList[i] = {
                uploadName: file.response[i].uploadName,
                filename: file.response[i].filename,
                fileUuid: file.response[i].fileUuid,
                ymd: file.response[i].yyyymmdd,
                ext: file.response[i].ext,
                size: file.response[i].size,
                width: file.response[i].width,
                height: file.response[i].height,
                seq: file.response[i].seq,
                uri: this.imagePrefix + '/' + file.response[i].yyyymmdd + '/' + file.response[i].fileUuid + '/' + file.response[i].filename + '.' + file.response[i].ext
              }
              for (var j = 0; j < localArray.length; ++j) {
                if (localArray[j].seq === localList[i].seq) {
                  localArray[j] = localList[i]
                  found = true
                  break
                }
              }
              if (found) {
                continue
              }
            }
            this.addBottleAttachmentShowList = localList.concat(this.addBottleAttachmentShowList)
          }
          break
        }
        case 'fail' : {
          this.$message('上传文件 ' + file.name + ' 失败')
          const localYmd = this.addBottleForm.attachmentYmd
          const localUuid = this.addBottleForm.attachmentUuid
          const localList = this.addBottleAttachmentShowList
          if (localYmd !== null && localUuid !== null && localList.length === 0) {
            this.$axios.get(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/check/' + localYmd + '/' + localUuid, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
              if (res.status === 200 && res.data === false) {
                this.addBottleForm.attachmentYmd = null
                this.addBottleForm.attachmentUuid = null
                this.tbl_add_data_before()
              }
            }).catch(ex => {
            })
          }
          break
        }
        case 'ready' : {
          break
        }
        default: {
          break
        }
      }
    },
    /**
     * @param files 超出的文件部分
     * @param list 已有文件列表
     */
    addBottleImageExceed (files, list) {
    },
    /**
     * @param evt 事件
     * @param file 文件
     * @param list 文件列表
     */
    addBottleImageProgress (evt, file, list) {
    },
    /**
     * 修改对话框的显示模式为显示图片
     * @param index 图片的下标
     * window.innerWidth 浏览器的窗口内宽
     * window.innerHeight 浏览器的窗口内高
     */
    addBottleImageClick (index) {
      this.addBottle.digWidth[1] = window.innerWidth
      this.addBottle.divWidth[1] = this.addBottle.digWidth[1] - this.addBottle.pictureAreaRight
      this.addBottle.divHeight[1] = window.innerHeight - this.addBottle.pictureAreaTop
      /*
       * 修改显示的模式为显示图片
       */
      this.addBottle.displayIndex = 1
      this.addBottle.pictureIndex = index
    },
    /**
     * 移除指定的图片
     */
    addBottleImageRemove (index) {
      this.$confirm('确定要删除该图片？', '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const oldArr = this.addBottleAttachmentShowList
        const newArr = Array(oldArr.length - 1)
        for (var i = 0, idx = 0; i < oldArr.length; ++i) {
          if (i === index) {
            this.$axios.post(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/removeById/' + oldArr[i].seq, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
            }).catch(ex => {
              this.$message('删除失败')
            })
            continue
          }
          newArr[idx] = oldArr[i]
          ++idx
        }
        this.addBottleAttachmentShowList = newArr
      }).catch(_ => {})
    },
    /**
     * 显示指定的图片
     */
    addBottleImageDetailClick (index) {
      this.addBottle.pictureIndex = index
    },
    /**
     * 图片是否显示边框
     */
    addBottleImageBorderLine (index) {
      return this.addBottle.pictureIndex === index
    },
    /**
     * 新增气瓶档案对话框中的规格型号控件发生变化时
     */
    addBottleFieldNormsChange (value) {
      if (Number.isInteger(value)) {
        // 输出选择的对象
        this.addBottleForm.maxcapacity = this.selectableNormsArray[value].maxcapacity.value
        this.addBottleFormMisc.maxcapacitySuffix = this.selectableNormsArray[value].maxcapacity.label
        this.addBottleForm.selfweight = this.selectableNormsArray[value].selfweight.value
        this.addBottleFormMisc.selfweightSuffix = this.selectableNormsArray[value].selfweight.label
        this.addBottleForm.thickness = this.selectableNormsArray[value].thickness.value
        this.addBottleFormMisc.thicknessSuffix = this.selectableNormsArray[value].thickness.label
        this.addBottleForm.volume = this.selectableNormsArray[value].volume.value
        this.addBottleFormMisc.volumeSuffix = this.selectableNormsArray[value].volume.label
        this.addBottleForm.specs = this.selectableNormsArray[value].value.specs
      } else {
        this.addBottleForm.maxcapacity = null
        this.addBottleFormMisc.maxcapacitySuffix = null
        this.addBottleForm.selfweight = null
        this.addBottleFormMisc.selfweightSuffix = null
        this.addBottleForm.thickness = null
        this.addBottleFormMisc.thicknessSuffix = null
        this.addBottleForm.volume = null
        this.addBottleFormMisc.volumeSuffix = null
        this.addBottleForm.specs = null
      }
    },
    /*
     * 修改对话框事件方法起
     */
    editBottleDialogLasttested (newValue) {
      if (newValue !== null && this.editBottleForm.nexttested === null) {
        const localDate = new Date()
        this.dateAddYear(newValue, localDate, 4)
        this.editBottleForm.nexttested = localDate
      }
    },
    editBottleDialogNexttested (newValue) {
      if (newValue !== null && this.editBottleForm.lasttested === null) {
        const localDate = new Date()
        this.dateSubYear(newValue, localDate, 4)
        this.editBottleForm.lasttested = localDate
      }
    },
    editBottleDialogProducted (newValue) {
      if (newValue === null) {
        return
      }
      switch (((typeof this.editBottleForm.maxuseyears) === 'number' ? 2 : 0) + (this.editBottleForm.expired === null ? 0 : 1)) {
        case 0: {
          break
        }
        case 1: {
          const localValue = this.editBottleForm.expired.getFullYear() - newValue.getFullYear()
          for (var i = 0; i < this.selectableMaxuseyears.length; ++i) {
            if (this.selectableMaxuseyears[i].value === localValue) {
              this.editBottleForm.maxuseyears = localValue
              const localDateA = new Date()
              localDateA.setFullYear(newValue.getFullYear() + localValue, newValue.getMonth(), newValue.getDate())
              this.editBottleForm.expired = localDateA
              return
            }
          }
          this.editBottleForm.producted = null
          break
        }
        case 2: {
          const localDateB = new Date()
          this.dateAddYear(newValue, localDateB, this.editBottleForm.maxuseyears)
          this.editBottleForm.expired = localDateB
          break
        }
        case 3: {
          const localDateC = new Date()
          this.dateAddYear(newValue, localDateC, this.editBottleForm.maxuseyears)
          this.editBottleForm.expired = localDateC
          break
        }
        default: {
          break
        }
      }
    },
    editBottleDialogExpired (newValue) {
      if (newValue === null) {
        return
      }
      switch ((this.editBottleForm.producted === null ? 0 : 2) + ((typeof this.editBottleForm.maxuseyears) === 'number' ? 1 : 0)) {
        case 0: {
          break
        }
        case 1: {
          const localDateA = new Date()
          this.dateSubYear(newValue, localDateA, this.editBottleForm.maxuseyears)
          this.editBottleForm.producted = localDateA
          break
        }
        case 2: {
          const localValueB = newValue.getFullYear() - this.editBottleForm.producted.getFullYear()
          for (var i = 0; i < this.selectableMaxuseyears.length; ++i) {
            if (this.selectableMaxuseyears[i].value === localValueB) {
              this.editBottleForm.maxuseyears = localValueB
              const localDateB = new Date()
              localDateB.setFullYear(newValue.getFullYear() - localValueB, newValue.getMonth(), newValue.getDate())
              this.editBottleForm.producted = localDateB
              return
            }
          }
          this.editBottleForm.expired = null
          break
        }
        case 3: {
          const localValueC = newValue.getFullYear() - this.editBottleForm.producted.getFullYear()
          for (var j = 0; j < this.selectableMaxuseyears.length; ++j) {
            if (this.selectableMaxuseyears[j].value === localValueC) {
              this.editBottleForm.maxuseyears = localValueC
              const localDateC = new Date()
              localDateC.setFullYear(newValue.getFullYear() - localValueC, newValue.getMonth(), newValue.getDate())
              this.editBottleForm.producted = localDateC
              return
            }
          }
          this.editBottleForm.expired = null
          break
        }
        default: {
          break
        }
      }
    },
    editBottleDialogMaxuseyears (newValue) {
      if (newValue === null) {
        return
      }
      if ((typeof newValue) === 'number') {
        switch ((this.editBottleForm.producted === null ? 0 : 2) + (this.editBottleForm.expired === null ? 0 : 1)) {
          case 0: {
            break
          }
          case 1: {
            const localDateA = new Date()
            this.dateSubYear(this.editBottleForm.expired, localDateA, newValue)
            this.editBottleForm.producted = localDateA
            break
          }
          case 2: {
            const localDateB = new Date()
            this.dateAddYear(this.editBottleForm.producted, localDateB, newValue)
            this.editBottleForm.expired = localDateB
            break
          }
          case 3: {
            const localDateC = new Date()
            this.dateAddYear(this.editBottleForm.producted, localDateC, newValue)
            this.editBottleForm.expired = localDateC
            break
          }
          default: {
            break
          }
        }
      }
    },
    /*
     * 修改对话框事件方法止
     */
    /**
     * 根据 archive uuid查询
     */
    editBottleImageQueryByArchiveUuid (uuid) {
      if (uuid === null) {
        this.editBottleForm.attachmentOldUuid = null
        this.editBottleForm.attachmentYmd = null
        this.editBottleForm.attachmentUuid = null
        const localGasTankId = this.editBottleForm.gastankid
        const requestForm = new FormData()
        requestForm.append('id', localGasTankId)
        this.$axios.postForm(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/modifyAttachmentUuidByGastankid', requestForm, { headers: { 'Content-Type': 'application/x-www-form-urlencoded', 'reference': this.$router.currentRoute.fullPath } }).then(resp => {
          if (resp.status === 200 && resp.data.success === true) {
            this.editBottleForm.attachmentYmd = resp.data.yyyymmdd
            this.tableData[this.editBottle.currentGasTankIndex].attachmentUuid = this.editBottleForm.attachmentUuid = resp.data.uuid
            if (Array.isArray(resp.data.items) && resp.data.items.length > 0) {
              const localArray = Array(resp.data.items.length)
              for (var idx = 0; idx < resp.data.items.length; ++idx) {
                localArray[idx] = {
                  uploadName: resp.data.items[idx].attachmentName,
                  filename: resp.data.items[idx].archiveName,
                  fileUuid: resp.data.items[idx].archiveUuid,
                  ext: resp.data.items[idx].archiveExt,
                  ymd: resp.data.items[idx].yyyymmdd,
                  size: resp.data.items[idx].archiveLength,
                  width: resp.data.items[idx].pictureWidth,
                  height: resp.data.items[idx].pictureHeight,
                  seq: resp.data.items[idx].id,
                  uri: this.imagePrefix + '/' + resp.data.items[idx].yyyymmdd + '/' + resp.data.items[idx].archiveUuid + '/' + resp.data.items[idx].archiveName + '.' + resp.data.items[idx].archiveExt
                }
              }
              this.editBottleAttachmentShowList = localArray.concat(this.editBottleAttachmentShowList)
            }
          } else {
            this.editBottleForm.attachmentYmd = null
            this.editBottleForm.attachmentUuid = null
          }
        }).catch(ex => {
          this.editBottleForm.attachmentYmd = null
          this.editBottleForm.attachmentUuid = null
        })
      } else {
        this.editBottleForm.attachmentOldUuid = uuid
        this.editBottleForm.attachmentYmd = null
        this.editBottleForm.attachmentUuid = null
        this.$axios.post(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/queryByArchiveUuid/' + uuid, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            if (Array.isArray(res.data) && res.data.length > 0) {
              this.editBottleForm.attachmentYmd = res.data[0].yyyymmdd
              this.editBottleForm.attachmentUuid = res.data[0].archiveUuid
              const localList = Array(res.data.length)
              for (var i = 0; i < res.data.length; ++i) {
                localList[i] = {
                  uploadName: res.data[i].attachmentName,
                  filename: res.data[i].archiveName,
                  fileUuid: res.data[i].archiveUuid,
                  ext: res.data[i].archiveExt,
                  ymd: res.data[i].yyyymmdd,
                  size: res.data[i].archiveLength,
                  width: res.data[i].pictureWidth,
                  height: res.data[i].pictureHeight,
                  seq: res.data[i].id,
                  uri: this.imagePrefix + '/' + res.data[i].yyyymmdd + '/' + res.data[i].archiveUuid + '/' + res.data[i].archiveName + '.' + res.data[i].archiveExt
                }
              }
              this.editBottleAttachmentShowList = localList.concat(this.editBottleAttachmentShowList)
            } else {
              this.editBottleImageQueryByArchiveUuid(null)
            }
          } else {
            this.editBottleForm.attachmentYmd = null
            this.editBottleForm.attachmentUuid = null
            this.editBottleForm.attachmentOldUuid = null
          }
        }).catch(ex => {
          this.editBottleForm.attachmentYmd = null
          this.editBottleForm.attachmentUuid = null
          this.editBottleForm.attachmentOldUuid = null
        })
      }
    },
    /**
     * 修改气瓶档案
     */
    tbl_edit_data_submit (formName) {
      const beforeCommitForm = { gastankid: this.editBottleForm.gastankid }
      this.tbl_edit_data_copy_value(this.editBottleForm, beforeCommitForm)
      /*
       * 下标转字符串
       */
      beforeCommitForm.norms = this.normsToString(this.editBottleForm.norms)
      if (this.tbl_edit_data_copy_compare(beforeCommitForm, this.tableData[this.editBottle.currentGasTankIndex])) {
        this.tbl_edit_data_clear()
        this.tbl_hide_edit_dialog_only()
        return true
      }
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localEditbottleForm = {
          gastankid: beforeCommitForm.gastankid,
          qrcode: beforeCommitForm.qrcode,
          productno: beforeCommitForm.productno,
          lasttested: beforeCommitForm.lasttested === null ? null : this.dateToString(beforeCommitForm.lasttested),
          nexttested: beforeCommitForm.nexttested === null ? null : this.dateToString(beforeCommitForm.nexttested),
          producter: beforeCommitForm.producter,
          producted: beforeCommitForm.producted === null ? null : this.dateToString(beforeCommitForm.producted),
          expired: beforeCommitForm.expired === null ? null : this.dateToString(beforeCommitForm.expired),
          maxuseyears: beforeCommitForm.maxuseyears,
          registno: beforeCommitForm.registno,
          unitselfno: beforeCommitForm.unitselfno,
          mediumname: beforeCommitForm.mediumname,
          thickness: beforeCommitForm.thickness,
          norms: beforeCommitForm.norms,
          specs: beforeCommitForm.specs,
          maxcapacity: beforeCommitForm.maxcapacity,
          volume: beforeCommitForm.volume,
          selfweight: beforeCommitForm.selfweight,
          valvetype: beforeCommitForm.valvetype,
          qualifiedno: beforeCommitForm.qualifiedno,
          elabel: beforeCommitForm.elabel,
          usedlicenseno: beforeCommitForm.usedlicenseno,
          hotway: beforeCommitForm.hotway,
          bodystuff: beforeCommitForm.bodystuff,
          valvefactory: beforeCommitForm.valvefactory,
          checkresult: beforeCommitForm.checkresult,
          checkdate: beforeCommitForm.checkdate === null ? null : this.dateToString(beforeCommitForm.checkdate),
          usedstatus: beforeCommitForm.usedstatus,
          onlyused: beforeCommitForm.onlyused,
          attachmentUuid: beforeCommitForm.attachmentUuid,
          testcount: beforeCommitForm.testcount,
          lastused: beforeCommitForm.lastused === null ? null : this.dateToString(beforeCommitForm.lastused),
          stationid: this.tableData[this.editBottle.currentGasTankIndex].stationid,
          cityid: this.tableData[this.editBottle.currentGasTankIndex].cityid,
          provinceid: this.tableData[this.editBottle.currentGasTankIndex].provinceid
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/gastankinfo/gastankinfo/updateSingle', localEditbottleForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200 && res.data.success) {
            const obj = res.data.object
            obj.lasttested = this.parseRemoteDateString(obj.lasttested)
            obj.nexttested = this.parseRemoteDateString(obj.nexttested)
            obj.producted = this.parseRemoteDateString(obj.producted)
            obj.expired = this.parseRemoteDateString(obj.expired)
            obj.checkdate = this.parseRemoteDateString(obj.checkdate)
            obj.lastused = this.parseRemoteDateString(obj.lastused)
            obj.created = this.parseRemoteDateString(obj.created)
            /*
             * 不转
             */
            this.tbl_edit_data_copy_value(obj, this.tableData[this.editBottle.currentGasTankIndex])
            this.tableData[this.editBottle.currentGasTankIndex].norms = obj.norms
            this.tbl_edit_data_finish()
            this.tbl_edit_data_clear()
            this.tbl_hide_edit_dialog_only()
          }
        }).catch(ex => {
          this.$message('修改失败')
        })
        return true
      })
    },
    tbl_edit_data_reset (formName) {
      this.$refs[formName].resetFields()
    },
    /**
     * 取消uuid
     */
    tbl_edit_data_finish () {
      if (this.editBottleForm.attachmentYmd !== null && this.editBottleForm.attachmentUuid !== null && this.editBottleForm.attachmentOldUuid === null) {
        this.$axios.get(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/finish/' + this.editBottleForm.attachmentYmd + '/' + this.editBottleForm.attachmentUuid, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        }).catch(ex => {
        })
      }
      this.editBottleForm.attachmentYmd = null
      this.editBottleForm.attachmentUuid = null
      this.editBottleForm.attachmentOldUuid = null
    },
    /**
     * 显示表单
     */
    tbl_edit_data_change_to_form () {
      this.editBottle.displayIndex = 0
    },
    /**
     * 把编辑气瓶档案表单中的数据清空
     */
    tbl_edit_data_clear () {
      this.editBottleForm.gastankid = null
      this.editBottleForm.qrcode = null
      this.editBottleForm.productno = null
      this.editBottleForm.lasttested = null
      this.editBottleForm.nexttested = null
      this.editBottleForm.producter = null
      this.editBottleForm.producted = null
      this.editBottleForm.expired = null
      this.editBottleForm.maxuseyears = null
      this.editBottleForm.registno = null
      this.editBottleForm.unitselfno = null
      this.editBottleForm.mediumname = null
      this.editBottleForm.thickness = null
      this.editBottleForm.norms = null
      this.editBottleForm.specs = null
      this.editBottleForm.maxcapacity = null
      this.editBottleForm.volume = null
      this.editBottleForm.selfweight = null
      this.editBottleForm.valvetype = null
      this.editBottleForm.qualifiedno = null
      this.editBottleForm.elabel = null
      this.editBottleForm.usedlicenseno = null
      this.editBottleForm.hotway = null
      this.editBottleForm.bodystuff = null
      this.editBottleForm.valvefactory = null
      this.editBottleForm.checkresult = null
      this.editBottleForm.checkdate = null
      this.editBottleForm.onlyused = null
      this.editBottleForm.testcount = null
      this.editBottleForm.usedstatus = null
      this.editBottleForm.lastused = null
      this.editBottle.currentGasTankIndex = null
    },
    /**
     * 把源对象的数据复制到目标对象
     * @param {Object} source 源对象
     * @param {Object} target 目标对象
     */
    tbl_edit_data_copy_value (source, target) {
      if (source.gastankid === target.gastankid) {
        target.qrcode = source.qrcode
        target.productno = source.productno
        target.lasttested = source.lasttested
        target.nexttested = source.nexttested
        target.producter = source.producter
        target.producted = source.producted
        target.expired = source.expired
        target.maxuseyears = source.maxuseyears
        target.registno = source.registno
        target.unitselfno = source.unitselfno
        target.mediumname = source.mediumname
        target.thickness = source.thickness
        /* target.norms = Number.isInteger(source.norms) ? this.normsToString(source.norms) : this.normsToNumber(source.norms) */
        target.specs = source.specs
        target.maxcapacity = source.maxcapacity
        target.volume = source.volume
        target.selfweight = source.selfweight
        target.valvetype = source.valvetype
        target.qualifiedno = source.qualifiedno
        target.elabel = source.elabel
        target.usedlicenseno = source.usedlicenseno
        target.hotway = source.hotway
        target.bodystuff = source.bodystuff
        target.valvefactory = source.valvefactory
        target.checkresult = source.checkresult
        target.checkdate = source.checkdate
        target.onlyused = source.onlyused
        target.testcount = source.testcount
        target.usedstatus = source.usedstatus
        target.lastused = source.lastused
      }
    },
    /**
     * 比较是否相同
     * @param {Object} source 源对象
     * @param {Object} target 目标对象
     */
    tbl_edit_data_copy_compare (source, target) {
      if (source.gastankid !== target.gastankid) { return false }
      if (source.productno !== target.productno) { return false }
      if (source.lasttested !== target.lasttested) { return false }
      if (source.nexttested !== target.nexttested) { return false }
      if (source.producter !== target.producter) { return false }
      if (source.producted !== target.producted) { return false }
      if (source.expired !== target.expired) { return false }
      if (source.maxuseyears !== target.maxuseyears) { return false }
      if (source.registno !== target.registno) { return false }
      if (source.unitselfno !== target.unitselfno) { return false }
      if (source.mediumname !== target.mediumname) { return false }
      if (source.thickness !== target.thickness) { return false }
      if (source.norms !== target.norms) { return false }
      if (source.specs !== target.specs) { return false }
      if (source.maxcapacity !== target.maxcapacity) { return false }
      if (source.volume !== target.volume) { return false }
      if (source.selfweight !== target.selfweight) { return false }
      if (source.valvetype !== target.valvetype) { return false }
      if (source.qualifiedno !== target.qualifiedno) { return false }
      if (source.elabel !== target.elabel) { return false }
      if (source.usedlicenseno !== target.usedlicenseno) { return false }
      if (source.hotway !== target.hotway) { return false }
      if (source.bodystuff !== target.bodystuff) { return false }
      if (source.valvefactory !== target.valvefactory) { return false }
      if (source.checkresult !== target.checkresult) { return false }
      if (source.checkdate !== target.checkdate) { return false }
      if (source.onlyused !== target.onlyused) { return false }
      if (source.attachmentUuid !== target.attachmentUuid) { return false }
      if (source.testcount !== target.testcount) { return false }
      if (source.usedstatus !== target.usedstatus) { return false }
      if (source.lastused !== target.lastused) { return false }
      return true
    },
    /**
     * @param file 文件信息
     * @param list 文件列表
     */
    editBottleImageChange (file, list) {
      switch (file.status) {
        case 'success': {
          if (Array.isArray(file.response)) {
            const localArray = this.editBottleAttachmentShowList
            const localList = Array(file.response.length)
            for (var i = 0; i < file.response.length; ++i) {
              let found = false
              localList[i] = {
                uploadName: file.response[i].uploadName,
                filename: file.response[i].filename,
                fileUuid: file.response[i].fileUuid,
                ymd: file.response[i].yyyymmdd,
                ext: file.response[i].ext,
                size: file.response[i].size,
                width: file.response[i].width,
                height: file.response[i].height,
                seq: file.response[i].seq,
                uri: this.imagePrefix + '/' + file.response[i].yyyymmdd + '/' + file.response[i].fileUuid + '/' + file.response[i].filename + '.' + file.response[i].ext
              }
              for (var j = 0; j < localArray.length; ++j) {
                if (localArray[j].seq === localList[i].seq) {
                  localArray[j] = localList[i]
                  found = true
                  break
                }
              }
              if (found) {
                continue
              }
            }
            this.editBottleAttachmentShowList = localList.concat(this.editBottleAttachmentShowList)
          }
          break
        }
        case 'fail': {
          this.$message('上传文件 ' + file.name + ' 失败')
          const localYmd = this.editBottleForm.attachmentYmd
          const localUuid = this.editBottleForm.attachmentUuid
          const localList = this.editBottleAttachmentShowList
          if (localYmd !== null && localUuid !== null && localList.length === 0) {
            this.$axios.get(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/check/' + localYmd + '/' + localUuid, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
              if (res.status === 200 && res.data === false) {
                this.editBottleForm.attachmentYmd = null
                this.editBottleForm.attachmentUuid = null
                this.editBottleImageQueryByArchiveUuid(localUuid)
              }
            }).catch(ex => {
            })
          }
          break
        }
        case 'ready': {
          break
        }
        default: {
          break
        }
      }
    },
    /**
     * @param files 超出的文件部分
     * @param list 已有文件列表
     */
    editBottleImageExceed (files, list) {
    },
    /**
     * @param evt 事件
     * @param file 文件
     * @param list 文件列表
     */
    editBottleImageProgress (evt, file, list) {
    },
    /**
     * 修改对话框的显示模式为显示图片
     * @param index 图片的下标
     * window.innerWidth 浏览器的窗口内宽
     * window.innerHeight 浏览器的窗口内高
     */
    editBottleImageClick (index) {
      this.editBottle.digWidth[1] = window.innerWidth
      this.editBottle.divWidth[1] = this.editBottle.digWidth[1] - this.editBottle.pictureAreaRight
      this.editBottle.divHeight[1] = window.innerHeight - this.editBottle.pictureAreaTop
      /*
       * 修改显示的模式为显示图片
       */
      this.editBottle.displayIndex = 1
      this.editBottle.pictureIndex = index
    },
    /**
     * 移除指定的图片
     */
    editBottleImageRemove (index) {
      this.$confirm('确认要删除该图片吗？', '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        const oldArr = this.editBottleAttachmentShowList
        const newArr = Array(oldArr.length - 1)
        for (var i = 0, idx = 0; i < oldArr.length; ++i) {
          if (i === index) {
            this.$axios.post(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/removeById/' + oldArr[i].seq, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
            }).catch(ex => {
              this.$message('删除失败')
            })
            continue
          }
          newArr[idx] = oldArr[i]
          ++idx
        }
        this.editBottleAttachmentShowList = newArr
      }).catch(_ => {})
    },
    /**
     * 显示指定的图片
     */
    editBottleImageDetailClick (index) {
      this.editBottle.pictureIndex = index
    },
    /**
     * 图片是否显示边框
     */
    editBottleImageBorderLine (index) {
      return this.editBottle.pictureIndex === index
    },
    /**
     * 修改气瓶档案对话框中的规格型号控件发生变化时
     */
    editBottleFieldNormsChange (value) {
      if (Number.isInteger(value)) {
        // 输出选择的对象
        this.editBottleForm.maxcapacity = this.selectableNormsArray[value].maxcapacity.value
        this.editBottleFormMisc.maxcapacitySuffix = this.selectableNormsArray[value].maxcapacity.label
        this.editBottleForm.selfweight = this.selectableNormsArray[value].selfweight.value
        this.editBottleFormMisc.selfweightSuffix = this.selectableNormsArray[value].selfweight.label
        this.editBottleForm.thickness = this.selectableNormsArray[value].thickness.value
        this.editBottleFormMisc.thicknessSuffix = this.selectableNormsArray[value].thickness.label
        this.editBottleForm.volume = this.selectableNormsArray[value].volume.value
        this.editBottleFormMisc.volumeSuffix = this.selectableNormsArray[value].volume.label
        this.editBottleForm.specs = this.selectableNormsArray[value].value.specs
      } else {
        this.editBottleForm.maxcapacity = null
        this.editBottleFormMisc.maxcapacitySuffix = null
        this.editBottleForm.selfweight = null
        this.editBottleFormMisc.selfweightSuffix = null
        this.editBottleForm.thickness = null
        this.editBottleFormMisc.thicknessSuffix = null
        this.editBottleForm.volume = null
        this.editBottleFormMisc.volumeSuffix = null
        this.editBottleForm.specs = null
      }
    },
    tbl_view_data_query_picture (uuid) {
      this.$axios.post(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/bottleArchiveAttachmentImage/queryByArchiveUuid/' + uuid, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        if (res.status === 200 && Array.isArray(res.data) && res.data.length > 0) {
          const localList = Array(res.data.length)
          for (var i = 0; i < res.data.length; ++i) {
            localList[i] = {
              uploadName: res.data[i].attachmentName,
              filename: res.data[i].archiveName,
              fileUuid: res.data[i].archiveUuid,
              ext: res.data[i].archiveExt,
              ymd: res.data[i].yyyymmdd,
              size: res.data[i].archiveLength,
              width: res.data[i].pictureWidth,
              height: res.data[i].pictureHeight,
              seq: res.data[i].id,
              uri: this.imagePrefix + '/' + res.data[i].yyyymmdd + '/' + res.data[i].archiveUuid + '/' + res.data[i].archiveName + '.' + res.data[i].archiveExt
            }
          }
          this.viewBottleAttachmentShowList = localList
        }
      }).catch(ex => {
      })
    },
    /**
     * 显示表单
     */
    tbl_view_data_change_to_form () {
      this.viewBottle.displayIndex = 0
    },
    /**
     * 把查看气瓶档案表单中的数据清空
     */
    tbl_view_data_clear () {
      this.viewBottleForm.gastankid = null
      this.viewBottleForm.qrcode = null
      this.viewBottleForm.productno = null
      this.viewBottleForm.lasttested = null
      this.viewBottleForm.nexttested = null
      this.viewBottleForm.producter = null
      this.viewBottleForm.producted = null
      this.viewBottleForm.expired = null
      this.viewBottleForm.maxuseyears = null
      this.viewBottleForm.registno = null
      this.viewBottleForm.unitselfno = null
      this.viewBottleForm.mediumname = null
      this.viewBottleForm.thickness = null
      this.viewBottleForm.norms = null
      this.viewBottleForm.specs = null
      this.viewBottleForm.maxcapacity = null
      this.viewBottleForm.volume = null
      this.viewBottleForm.selfweight = null
      this.viewBottleForm.valvetype = null
      this.viewBottleForm.qualifiedno = null
      this.viewBottleForm.elabel = null
      this.viewBottleForm.usedlicenseno = null
      this.viewBottleForm.hotway = null
      this.viewBottleForm.bodystuff = null
      this.viewBottleForm.valvefactory = null
      this.viewBottleForm.checkresult = null
      this.viewBottleForm.checkdate = null
      this.viewBottleForm.onlyused = null
      this.viewBottleForm.testcount = null
      this.viewBottleForm.usedstatus = null
      this.viewBottleForm.lastused = null
      this.viewBottle.currentGasTankIndex = null
    },
    /**
     * 把源对象的数据复制到目标对象
     * @param {Object} source 源对象
     * @param {Object} target 目标对象
     */
    tbl_view_data_copy_value (source, target) {
      if (source.gastankid === target.gastankid) {
        target.qrcode = source.qrcode
        target.productno = source.productno
        target.lasttested = source.lasttested
        target.nexttested = source.nexttested
        target.producter = source.producter
        target.producted = source.producted
        target.expired = source.expired
        target.maxuseyears = source.maxuseyears
        target.registno = source.registno
        target.unitselfno = source.unitselfno
        target.mediumname = source.mediumname
        target.thickness = source.thickness
        target.norms = this.normsToNumber(source.norms)
        this.viewBottleFieldNormsChange(target.norms)
        target.specs = source.specs
        target.maxcapacity = source.maxcapacity
        target.volume = source.volume
        target.selfweight = source.selfweight
        target.valvetype = source.valvetype
        target.qualifiedno = source.qualifiedno
        target.elabel = source.elabel
        target.usedlicenseno = source.usedlicenseno
        target.hotway = source.hotway
        target.bodystuff = source.bodystuff
        target.valvefactory = source.valvefactory
        target.checkresult = source.checkresult
        target.checkdate = source.checkdate
        target.onlyused = source.onlyused
        target.testcount = source.testcount
        target.usedstatus = source.usedstatus
        target.lastused = source.lastused
      }
    },
    /**
     * 修改气瓶档案对话框中的规格型号控件发生变化时
     */
    viewBottleFieldNormsChange (value) {
      if (Number.isInteger(value)) {
        // 输出选择的对象
        this.viewBottleFormMisc.maxcapacitySuffix = this.selectableNormsArray[value].maxcapacity.label
        this.viewBottleFormMisc.selfweightSuffix = this.selectableNormsArray[value].selfweight.label
        this.viewBottleFormMisc.thicknessSuffix = this.selectableNormsArray[value].thickness.label
        this.viewBottleFormMisc.volumeSuffix = this.selectableNormsArray[value].volume.label
      } else {
        this.viewBottleFormMisc.maxcapacitySuffix = null
        this.viewBottleFormMisc.selfweightSuffix = null
        this.viewBottleFormMisc.thicknessSuffix = null
        this.viewBottleFormMisc.volumeSuffix = null
      }
    },
    /**
     * 修改对话框的显示模式为显示图片
     * @param index 图片的下标
     * window.innerWidth 浏览器的窗口内宽
     * window.innerHeight 浏览器的窗口内高
     */
    viewBottleImageClick (index) {
      this.viewBottle.digWidth[1] = window.innerWidth
      this.viewBottle.divWidth[1] = this.viewBottle.digWidth[1] - this.viewBottle.pictureAreaRight
      this.viewBottle.divHeight[1] = window.innerHeight - this.viewBottle.pictureAreaTop
      /*
       * 修改显示的模式为显示图片
       */
      this.viewBottle.displayIndex = 1
      this.viewBottle.pictureIndex = index
    },
    /**
     * 显示指定的图片
     */
    viewBottleImageDetailClick (index) {
      this.viewBottle.pictureIndex = index
    },
    /**
     * 图片是否显示边框
     */
    viewBottleImageBorderLine (index) {
      return this.viewBottle.pictureIndex === index
    },
    tbl_modify_qrcode_submit (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        if (this.modifyQrcodeForm.gastankid === null) {
          return false
        }
        const localNewQrcode = this.modifyQrcodeForm.newQrcode.trim()
        const localOldQrcode = this.modifyQrcodeForm.oldQrcode.trim()
        if (localNewQrcode.length === 0 || localOldQrcode.length === 0) {
          return false
        }
        const localForm = new FormData()
        localForm.append('id', this.modifyQrcodeForm.gastankid)
        localForm.append('ov', localOldQrcode)
        localForm.append('nv', localNewQrcode)
        this.$axios.postForm(
          this.constant.GAS_SERVER_PREFIX + '/gastankinfo/gastankinfo/modifyQrcodeByGastankid',
          localForm,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
              'reference': this.$router.currentRoute.fullPath
            }
          }
        ).then(res => {
          if (res.status === 200 && res.data.success) {
            this.tableData[this.modifyQrcode.currentGasTankIndex].qrcode = res.data.qrcode
            this.tbl_hide_modify_qrcode_only()
            this.modifyQrcodeForm.gastankid = null
            this.modifyQrcodeForm.oldQrcode = null
            this.modifyQrcodeForm.newQrcode = null
            this.modifyQrcode.currentGasTankIndex = null
          }
        }).catch(ex => this.$message('修改气瓶编号失败'))
      })
    },
    /*
     * 对话框方法止
     */
    /**
     * 日期加年
     */
    dateAddYear (sourceDate, targetDate, year) {
      if (sourceDate === null || targetDate === null || ((typeof year) !== 'number') || year < 0) {
        return
      }
      targetDate.setFullYear(sourceDate.getFullYear() + year, sourceDate.getMonth(), sourceDate.getDate())
    },
    /**
     * 日期减年
     */
    dateSubYear (sourceDate, targetDate, year) {
      if (sourceDate === null || targetDate === null || ((typeof year) !== 'number') || year < 0) {
        return
      }
      targetDate.setFullYear(sourceDate.getFullYear() - year, sourceDate.getMonth(), sourceDate.getDate())
    },
    /**
     * @param row 所在行的原始数据
     * @param column 所在列的定义数据
     * @param rowIndex 从0开始的第多少行
     * @param columnIndex 从0开始的第多少列
     */
    tblCellStyle ({row, column, rowIndex, columnIndex}) {
      /*
       * 根据列返回样式
       */
      switch (column.label) {
        case '序号': {
          if (this.maxGastankid < 100) { return 'padding-right: 25px' }
          if (this.maxGasTankId < 1000) { return 'padding-right: 20px' }
          if (this.maxGasTankId < 10000) { return 'padding-right: 15px' }
          if (this.maxGasTankId < 100000) { return 'padding-right: 10px' }
          return 'padding-right: 5px'
        }
        case '条码编号': {
          return 'padding-right: 3px'
        }
        case '制造厂商' : {
          if (row.producter === null || row.producter.length < 21) {
            return 'padding-left:2px'
          }
          return 'padding-left:0px; font-size: 12px'
        }
        case '最大充量': {
          return 'padding-right: 15px'
        }
        case '自重': {
          return 'padding-right: 5px'
        }
        case '壁厚': {
          return 'padding-right: 4px'
        }
        case '容积': {
          return 'padding-right: 4px'
        }
        case '充装介质': {
          return 'font-size: 12px'
        }
        default: {
          return null
        }
      }
    },
    /**
     * 把气瓶型号由序号转为具体的字符串型号
     * @param index 序号
     */
    normsToString (index) {
      return Number.isInteger(index) ? this.selectableNormsArray[index].value.norms : null
    },
    /**
     * 把气瓶字符串型号转为下标
     * @param str 气瓶型号字符串
     */
    normsToNumber (str) {
      for (var i = 0; i < this.selectableNormsArray.length; ++i) {
        if (this.selectableNormsArray[i].value.norms === str) {
          return i
        }
      }
      return null
    }
  }
}
</script>

<style>
.BottleArchiveMain {
  position: relative;
  left: 0px;
  top: 0px;
  width: 100%;
  height: calc(100% - 2px);
  padding: 0px;
  margin: 0px;
}
.BottleArchiveForm {
  position: relative;
  left: 0px;
  top: 0px;
  width: 100%;
  background-color:  #FFFFFF;
}
.BottleArchiveSelectedPictureBorder {
  border: solid;
  border-width: 2pt;
  border-color: #e53e51;
}
.BottleArchiveUnselectedPictureBorder {
  border: none;
}
.BottleArchiveSelectedPictureMargin {
  margin-left: 3px;
  margin-bottom: 0px;
  margin-right: 6px;
  margin-top: 0px;
}
.BottleArchiveUnselectedPictureMargin {
  margin-left: 0px;
  margin-bottom: 0px;
  margin-right: 0px;
  margin-top: 0px;
}
/*
 * 操作列的表头样式
 */
.TblBottleArchinveLastColumnHeader {
  margin-left: 10px
}
</style>
