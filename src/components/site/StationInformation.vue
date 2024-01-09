<template>
  <!--整个页面UPDATE `gas`.`auth_left_menu_tree` SET `path` = '/Site/StationInformation' WHERE (`id` = '14');-->
  <div style="padding: 0px; margin: 0px">
    <!--主要部分-->
    <el-container direction="vertical" style="position: relative; left: 0px; top: 0px; width: calc(100% - 2px); height: calc(100% - 2px); padding: 0px; margin: 0px;">
      <el-header :height="area1heightString" style="position: relative; left: 4px; top: 0px; width: calc(100% - 2px); background-color: #FFFFFF;">
        <el-divider content-position="left">基本信息</el-divider>
        <el-container direction="horizontal" style="position: relative; left: 0px; top: 0px; width:100%; height: 100%; padding: 0px; margin: 0px;">
          <!-- background-color: black; -->
          <el-aside :width="area1leftAreaWidthString" style="overflow: hidden;">
            <!-- status-icon -->
            <el-form ref="baseForm" label-width="135px" :model="baseForm" :rules="baseRules">
              <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 4px;">
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="corpname" label="单位名称" size="medium">
                    <el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.corpname" placeholder="请填写气站名称" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
                    <el-input v-else type="text" size="medium" v-model="baseForm.corpname" placeholder="请填写气站名称" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" />
                  </el-form-item>
                </el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"><!--一-->
                  <el-form-item required prop="creditcode" label="统一社会信用代码" size="medium">
                    <el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.creditcode" placeholder="请填写统一社会信用代码" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
                    <el-input v-else type="text" size="medium" v-model="baseForm.creditcode" placeholder="请填写统一社会信用代码" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" />
                  </el-form-item>
                </el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item prop="contactno" label="联系电话" size="medium">
                    <el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.contactno" placeholder="请填写联系电话" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
                    <el-input v-else type="text" size="medium" v-model="baseForm.contactno" placeholder="请填写联系电话" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="48" type="flex" justify="start" align="middle">
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="provincename" label="所在省" size="medium">
                    <!--el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.provincename" placeholder="请选择所在省" style="padding-left: 0px; padding-right: 0px; width: 200px;" /-->
                    <!--el-input v-else type="text" size="medium" v-model="baseForm.provincename" placeholder="请选择所在省" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" /-->
                    <el-select v-model="baseForm.provincename" placeholder="请选择所在省" no-data-text="暂时没有可选择的省" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseProvinceChange">
                      <el-option v-for="item in baseSelectableProvinceArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="cityname" label="所在市" size="medium">
                    <!--el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.cityname" placeholder="请选择所在市" style="padding-left: 0px; padding-right: 0px; width: 200px;" /-->
                    <!--el-input v-else type="text" size="medium" v-model="baseForm.cityname" placeholder="请选择所在市" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" /-->
                    <el-select v-model="baseForm.cityname" placeholder="请选择所在市" no-data-text="暂时没有可选择的城市" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseCityChange">
                      <el-option v-for="item in baseSelectableCityArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="districtname" label="所在区县" size="medium">
                    <el-select v-if="this.baseForm.bsnid === null" v-model="baseForm.districtname" placeholder="请选择所在区县" no-data-text="暂时没有可选择的区县" style="padding-left: 0px; padding-right: 0px; width: 200px;">
                      <el-option v-for="item in baseSelectableDistrictArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                    </el-select>
                    <el-select v-else v-model="baseForm.districtname" placeholder="请选择所在区县" no-data-text="暂时没有可选择的区县" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify">
                      <el-option v-for="item in baseSelectableDistrictArray" :key="item.value" :label="item.label" :value="item.value"><span style="float: left">{{ item.label }}</span></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="48" type="flex" justify="start" align="middle">
                <el-col :span="32" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="legalname" label="法人姓名" size="medium">
                    <el-input v-if="this.baseForm.bsnid === null" type="text" size="medium" v-model="baseForm.legalname" placeholder="请填写法人姓名" style="padding-left: 0px; padding-right: 0px; width: 200px;" />
                    <el-input v-else type="text" size="medium" v-model="baseForm.legalname" placeholder="请填写法人姓名" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeBaseModify" />
                  </el-form-item>
                </el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item required prop="address" label="详细地址" size="medium" style="margin-left: 48px;"><!---->
                    <el-input v-if="this.baseForm.bsnid === null" type="textarea" rows="1" v-model="baseForm.address" placeholder="请填写详细地址" style="padding-left: 0px; padding-right: 0px; width: 572px;" /><!-- width: 582px;-->
                    <el-input v-else type="textarea" rows="1" v-model="baseForm.address" placeholder="请填写详细地址" style="padding-left: 0px; padding-right: 0px; width: 572px;" @change="executeBaseModify" /><!-- width: 582px;-->
                  </el-form-item>
                </el-col>
                <!-- <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"><span style="display: none;"></span></el-col> -->
              </el-row>
              <el-row v-if="this.baseForm.bsnid === null" :gutter="48" type="flex" justify="end" align="middle" style="margin-top: 0px;">
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;"></el-col>
                <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                  <el-form-item label=" " size="medium">
                    <el-button round @click="executeBaseSubmit('baseForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">确定</el-button>
                    <el-button round @click="executeBaseCancel('baseForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">取消</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-aside>
          <!-- background-color: green; -->
          <el-main style="padding: 0px 0px 0px 0px; overflow-x: hidden; overflow-y: hidden;">
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
              v-show="basePicture.ymd === null || basePicture.uuid === null"
            >
              <el-button round size="small" style="padding: 0px 7px 0px 7px; margin: 1px 3px 0px 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px; height: 27px;">点击上传图片</el-button>
            </el-upload>
            <el-image v-show="basePicture.ymd !== null && basePicture.uuid !== null" fill="fill" :style="{width: area1height + 'px', height: area1height + 'px' }" :src="basePictureUri" @click="showBasePicture()" />
          </el-main>
        </el-container>
      </el-header>
      <!-- background-color: brown; -->
      <el-main style="position: relative; left: 4px; top: 50px; width: calc(100% - 2px); padding: 0px; margin: 0px; background-color: #FFFFFF; overflow: hidden;">
        <el-divider v-if="this.baseForm.bsnid !== null" content-position="left" style="padding-top: 0px; padding-bottom: 0px;">充装许可证</el-divider>
      </el-main>
      <!-- background-color: #FFFFFF; border: solid; border-color: aqua; border-width: 1pt; -->
      <el-footer v-if="this.baseForm.bsnid !== null" :height="area3heightString" style="position: relative; left: 4px; top: 50px; width: calc(100% - 2px);">
        <el-container direction="horizontal" style="position: relative; left: 0px; top: 0px; width: 100%; background-color: #FFFFFF;">
          <!-- style="background-color: bisque" -->
          <el-aside :width="area3leftAreaWidthString">
            <!-- background-color: #666666; -->
            <el-container direction="vertical" style="position: relative; left: 0px; top: 0px; width: 100%; height: calc(100% - 2px); overflow: hidden;">
              <el-header :height="area3my1heightString" style="position: relative; left: 0px; top: 0px; width: 100%; background-color: #FFFFFF">
                <el-form status-icon ref="licenseForm" label-width="130px" :model="licenseForm" :rules="licenseRules">
                  <el-row :gutter="48" type="flex" justify="start" align="middle" style="margin-top: 13px;">
                    <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                      <el-form-item prop="licenseno" label="充装许可证" size="medium">
                        <el-input type="text" size="medium" v-model="licenseForm.licenseno" placeholder="请填写充装许可证号" style="padding-left: 0px; padding-right: 0px; width: 200px;" @change="executeLicenseSubmit" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                      <el-form-item prop="permitted" label="许可证生效日期" size="medium">
                        <el-date-picker clearable v-model="licenseForm.permitted" type="date" placeholder="请选择许可证生效日期" size="small" style="width: 200px;" @change="executeLicenseSubmit" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="16" style="padding-left: 0px; padding-right: 0px;">
                      <el-form-item prop="expired" label="许可证过期日期" size="medium">
                        <el-date-picker clearable v-model="licenseForm.expired" type="date" placeholder="请选择许可证过期日期" size="small" style="width: 200px;" @change="executeLicenseSubmit" />
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </el-header>
              <!-- background-color: #000000; -->
              <el-main :height="area3my2heightString" style="position: relative; left: 0px; top: 0px; width: 100%; padding-top: 0px; padding-right: 15px; padding-bottom: 0px; padding-left: 0px; margin: 0px; overflow: hidden;">
                <el-divider content-position="left" style="padding-top: 0px; padding-bottom: 0px;">可充装的介质</el-divider>
              </el-main>
              <!-- background-color: brown; -->
              <el-footer :height="area3my3heightString" style="position: relative; left: 0px; top: 0px; width: 100%; overflow: hidden;">
                <el-container direction="horizontal" style="position: relative; left: 0px; top: 0px; width: 100%; height: 100%; padding: 0px; margin: 0px;">
                  <el-aside width="70px" style="overflow: hidden; padding: 0px; margin: 0px;">
                    <!-- 按钮 -->
                    <el-button round size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 5px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;" @click="showAddGasTypeDialog">新增</el-button>
                  </el-aside>
                  <el-main style="overflow: hidden; padding: 0px; margin: 0px;">
                    <el-table
                      v-if="this.gasTypeData.length !== 0"
                      border
                      highlight-current-row
                      highlight-selection-row
                      empty-text="目前暂时没有可查询的充装介质"
                      :data="gasTypeData"
                      :cell-style="tblCellStyle"
                      :height="tableHeightString"
                      style="overflow-y: hidden; width: 990px;"><!-- width: 1460px; -->
                      <el-table-column prop="gascatname"  width="290" min-width="290" :show-overflow-tooltip="true"  align="center"  header-align="center" label="气瓶类别" />
                      <el-table-column prop="gastypename" width="290" min-width="290" :show-overflow-tooltip="true"  align="center"  header-align="center" label="充装介质" />
                      <el-table-column prop="remark"      width="290" min-width="290" :show-overflow-tooltip="true"  align="left"    header-align="center" label="备注" />
                      <el-table-column                    width="118" min-width="118" :show-overflow-tooltip="false" align="center"  header-align="center" label="操作">
                        <template slot-scope="gasTypeOperator"><!-- gasTypeOperator是点击按钮时传给回调函数的参数 -->
                          <el-button round size="mini" icon="el-icon-close" style="height: 21px; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-radius: 3px;" @click="tbl_delete_gasType(gasTypeOperator)">删除</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                    <span v-else style="position: relative; top: 5px;">目前暂时没有可查询的充装介质</span>
                  </el-main>
                </el-container>
              </el-footer>
            </el-container>
          </el-aside>
          <el-main style="background-color:black;" />
        </el-container>
      </el-footer>
    </el-container>
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      width="50%"
      :show-close="gasTypeDialog.showClose"
      :style="{ width: gasTypeDialog.digWidth + 'px' }"
      :title="gasTypeDialog.title"
      :visible.sync="gasTypeDialog.visible">
      <!-- style="background-color: #66b1ff;" -->
      <div :style="{ width: gasTypeDialog.divWidth + 'px', height: gasTypeDialog.divHeight + 'px' }">
        <el-form status-icon ref="gasTypeForm" label-width="125px" :model="gasTypeForm" :rules="gasTypeFormRules">
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="gascatname" label="气瓶类别" size="medium">
                <el-input clearable type="text" v-model="gasTypeForm.gascatname" placeholder="请输入气瓶类别" style="padding-left: 0px; padding-right: 0px; width: 280px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item required prop="gastypename" label="充装介质" size="medium">
                <el-input clearable type="text" v-model="gasTypeForm.gastypename" placeholder="请输入充装介质" style="padding-left: 0px; padding-right: 0px; width: 280px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="start" align="middle" style="padding-left: 0px; padding-right: 0px;">
            <el-col :span="48" style="padding-left: 0px; padding-right: 0px;">
              <el-form-item prop="remark" label="备注" size="medium">
                <el-input type="textarea" rows="3" v-model="gasTypeForm.remark" style="padding-left: 0px; padding-right: 0px; width: 280px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="48" type="flex" justify="end" align="middle" style="padding: 0px; margin: 0px;">
            <el-button round @click="executeGasTypeInsert('gasTypeForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">新增</el-button>
            <el-button round @click="executeGasTypeCancel('gasTypeForm')" size="small" style="height: 24px; width: 60px; background: #E3E3E3; padding-top: 0px; padding-right: 7px; padding-bottom: 0px; padding-left: 7px; margin-top: 0px; margin-right: 3px; margin-bottom: 0px; margin-left: 3px; border-color: #66b1ff; border: solid; border-width: 1pt; line-height: 0px; border-radius: 4px;">取消</el-button>
          </el-row>
        </el-form>
      </div>
    </el-dialog>
    <el-dialog
      destroy-on-close
      lock-scroll
      modal
      show-close
      title="站点图片显示"
      top="0vh"
      width="100%"
      :visible="pictureShow.visible"
      :before-close="hidePicture"
      :style="{ width: pictureShow.dialogWidth + 'px', height: pictureShow.dialogHeight + 'px' }">
      <!--border: solid; border-color: brown; border-width: 1pt;-->
      <div :style="{ width: pictureShow.divWidth + 'px', height: pictureShow.divHeight + 'px' }" style="overflow-x: auto; overflow-y: auto;">
        <el-image :src="pictureShow.uri" fill="none" />
      </div>
    </el-dialog>
  </div>
</template>
<script>
/* import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data' */
import { regionData, codeToText } from 'element-china-area-data'
export default {
  name: 'StationInformation',
  data () {
    return {
      /**
        * 基本信息表单
        */
      baseForm: {
        bsnid: null, // 主键
        corpname: null, // 单位名称
        creditcode: null, // 统一社会信用代码
        contactno: null, // 联系电话
        provincename: null, // 所在省
        cityname: null, // 所在市
        districtname: null, // 所在区县
        legalname: null, // 法人姓名
        address: null, // 详细地址
        attachmentUuid: null // 企业营业执照图片
      },
      baseFormData: {
        bsnid: null, // 主键
        corpname: null, // 单位名称
        creditcode: null, // 统一社会信用代码
        contactno: null, // 联系电话
        provincename: null, // 所在省
        cityname: null, // 所在市
        districtname: null, // 所在区县
        legalname: null, // 法人姓名
        address: null, // 详细地址
        attachmentUuid: null // 企业营业执照图片
      },
      /**
       * 基本信息表单校验
       */
      baseRules: {},
      /**
       * 基本信息中的省份数组
       */
      baseSelectableProvinceArray: [],
      /**
       * 基本信息中的城市数组
       */
      baseSelectableCityArray: [],
      /**
       * 基本信息中的区县旗数组
       */
      baseSelectableDistrictArray: [],
      /**
        * 许可证表单
        */
      licenseForm: {
        licid: null, // 主键
        licenseno: null, // 充装许可证
        permitted: null, // 许可证生效日期
        expired: null // 许可证过期日期
      },
      licenseFormData: {
        licid: null, // 主键
        licenseno: null, // 充装许可证
        permitted: null, // 许可证生效日期
        expired: null // 许可证过期日期
      },
      /**
       * 许可证表单校验
       */
      licenseRules: {},
      /**
        * 可允气体
        */
      gasTypeData: [],
      /**
       * 新增充装介质对话框
       */
      gasTypeDialog: {
        digWidth: 900, // 对话框宽度
        divWidth: 410, // 中间div的宽度
        divHeight: 210, // 中间div的高度
        title: '新增充装介质', // 对话框标题
        visible: false, // 对话框是否可见
        showClose: true // 是否显示关闭按钮
      },
      gasTypeForm: {
        gascatname: null,
        gastypename: null,
        remark: null
      },
      gasTypeFormRules: {},
      /**
       * 可供选择的站点
       */
      selectableStation: null,
      basePicture: {
        accept: this.constant.GAS_SERVER_ATTACHMENT_PICTURE_EXT, // 目前只允许上传常用图片
        autoUpload: true, // 气瓶档案是否自动上传
        multipleUpload: false, // 气瓶档案是否多文件上传
        showFileList: false, // 是否显示上传文件的列表
        ymd: null,
        uuid: null,
        archiveExt: null,
        name: null
      },
      /**
       * 显示图片的对话框
       */
      pictureShow: {
        dialogWidth: 0,
        dialogHeight: 0,
        divWidth: 0,
        divHeight: 0,
        uri: null,
        visible: false
      },
      /**
       * 长度变量
       */
      area1height: 150, // 第一块区域的高
      area1buttonHeight: 50, // 第一块区域的按钮高
      area1leftAreaWidth: 1100, // 第一块区域中左边部分的宽
      area3leftAreaWidth: 1100, // 第三块区域中左边部分的宽
      area3my1height: 60, // 第三块区域中第一行的高
      area3my2height: 40, // 第三块区域中第二行的高
      area3my3height: 40, // 第三块区域中第三行的高
      imagePrefix: this.constant.GAS_IMAGE_PREFIX + '/StationInformation' // 图片基本路径
    }
  },
  computed: {
    /**
     * 第一块区域的高
     */
    area1heightString: function () {
      return (this.area1height + this.area1buttonHeight) + 'px'
    },
    /**
     * 第一块区域中左边部分的宽
     */
    area1leftAreaWidthString: function () {
      return this.area1leftAreaWidth + 'px'
    },
    /**
     * 第三块区域的高
     */
    area3heightString: function () {
      return (this.area3my1height + this.area3my2height + (this.gasTypeData.length === 0 ? this.area3my3height : 0) + (this.gasTypeData.length + 1) * 50 + 10) + 'px'
    },
    /**
     * 第三块区域的图片高
     */
    area3pictureHeightString: function () {
      return (this.area3my1height + this.area3my2height + this.area3my3height + 10) + 'px'
    },
    /**
     * 第三块区域中左边部分的宽
     */
    area3leftAreaWidthString: function () {
      return this.area3leftAreaWidth + 'px'
    },
    /**
     * 第三块区域中第一行的高
     */
    area3my1heightString: function () {
      return this.area3my1height + 'px'
    },
    /**
     * 第三块区域中第二行的高
     */
    area3my2heightString: function () {
      return this.area3my2height + 'px'
    },
    /**
     * 第三块区域中第三行的高
     */
    area3my3heightString: function () {
      return (this.gasTypeData.length === 0 ? this.area3my3height : 0) + (this.gasTypeData.length + 1) * 50 + 'px'
    },
    /**
     * 表格的高
     */
    tableHeightString: function () {
      return (this.gasTypeData.length + 1) * 50 + 'px'
    },
    baseUploadActionUrl: function () {
      return this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/attachment/stationInformationAttachmentImage/uploadNew/' + this.basePicture.ymd + '/' + this.basePicture.uuid
    },
    baseUploadHeaders: function () {
      return {
        'reference': this.$router.currentRoute.fullPath
      }
    },
    basePictureUri: function () {
      return this.imagePrefix + '/' + this.basePicture.ymd + '/' + this.basePicture.uuid + '/' + this.basePicture.name + '.' + this.basePicture.archiveExt
    }
  },
  watch: {
    'baseForm.bsnid': {
      handler: function (n, o) {
        console.log(n)
        console.log(o)
        if (n === null || this.baseForm.attachmentUuid === null) {
          this.baseForm.attachmentUuid = null
          this.basePicture.ymd = null
          this.basePicture.uuid = null
          this.basePicture.archiveExt = null
          this.basePicture.name = null
          return
        }
        this.$axios.post(this.constant.GAS_SERVER_ATTACHMENT_PREFIX + '/stationInformationAttachmentImage/queryByArchiveUuid/' + this.baseForm.attachmentUuid, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          /*
           * [
           *     {
           *         "valid": "0",
           *         "pictureWidth": 927,
           *         "pictureHeight": 666,
           *         "id": 45,
           *         "uploadTime": 1702895414890,
           *         "archiveLength": 64599,
           *         "uploadUserId": 30,
           *         "archiveUuid": "0fe342916a974652b55c5c8cb1086c52",
           *         "archiveName": "82f0430a2fde4caba8f592511265a970",
           *         "yyyymmdd": "0231218",
           *         "archiveExt": "png",
           *         "note": "手动上传",
           *         "attachmentName": "18c7c79ee6a",
           *         "uploadUserName": "hainan"
           *     }
           * ]
           */
          if (res.status === 200 && Array.isArray(res.data) && res.data.length > 0) {
            this.basePicture.ymd = res.data[0].yyyymmdd
            this.basePicture.uuid = res.data[0].archiveUuid
            this.basePicture.archiveExt = res.data[0].archiveExt
            this.basePicture.name = res.data[0].archiveName
          } else {
            this.basePicture.ymd = null
            this.basePicture.uuid = null
            this.basePicture.archiveExt = null
            this.basePicture.name = null
          }
        }).catch(ex => {
          this.basePicture.ymd = null
          this.basePicture.uuid = null
          this.basePicture.archiveExt = null
          this.basePicture.name = null
        })
      }
    }
  },
  mounted () {
    this.initialProvince()
    this.baseSelectableProvinceArray = Array(regionData.length)
    for (var indexProvince = 0; indexProvince < regionData.length; ++indexProvince) {
      this.baseSelectableProvinceArray[indexProvince] = regionData[indexProvince]
    }
    this.baseSelectableProvinceArray.unshift({ value: null, label: '请选择所在省', children: [] })
    var localStationString = window.sessionStorage.getItem(this.constant.STATION)
    if (localStationString !== null) {
      try {
        this.selectableStation = JSON.parse(localStationString)
      } catch (ex) {
        this.selectableStation = []
      }
    }
    this.$axios.get(this.constant.GAS_SERVER_PREFIX + '/businessInformation/businessinfo/getMyStation?stationId=', { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
      if (res.status === 200) {
        const resData = res.data
        const baseData = resData.base
        const baseLicense = resData.license
        for (var i1 = 0; i1 < baseData.length; ++i1) {
          if (this.selectableStation[0].value === baseData[i1].stationid) {
            this.baseFormData.corpname = this.baseForm.corpname = baseData[i1].corpname
            this.baseFormData.creditcode = this.baseForm.creditcode = baseData[i1].creditcode
            this.baseFormData.contactno = this.baseForm.contactno = baseData[i1].contactno
            this.baseFormData.provincename = this.baseForm.provincename = this.convertProvinceNameToCode(baseData[i1].provincename)
            this.executeBaseProvinceChange(this.baseForm.provincename)
            this.baseFormData.cityname = this.baseForm.cityname = this.convertCityNameToCode(baseData[i1].cityname)
            this.executeBaseCityChange(this.baseForm.cityname)
            this.baseFormData.districtname = this.baseForm.districtname = this.convertDistrictNameToCode(baseData[i1].districtname)
            this.baseFormData.legalname = this.baseForm.legalname = baseData[i1].legalname
            this.baseFormData.address = this.baseForm.address = baseData[i1].address
            this.baseFormData.attachmentUuid = this.baseForm.attachmentUuid = baseData[i1].attachmentUuid
            this.baseFormData.bsnid = this.baseForm.bsnid = baseData[i1].bsnid
          }
        }
        for (var i2 = 0; i2 < baseLicense.length; ++i2) {
          if (this.selectableStation[0].value === baseLicense[i2].stationid) {
            this.licenseFormData.licid = this.licenseForm.licid = baseLicense[i2].licid
            this.licenseFormData.licenseno = this.licenseForm.licenseno = baseLicense[i2].licenseno
            this.licenseFormData.permitted = this.licenseForm.permitted = baseLicense[i2].permitted
            this.licenseFormData.expired = this.licenseForm.expired = baseLicense[i2].expired
          }
        }
        this.gasTypeData = resData.gasType
        this.area1buttonHeight = (this.baseForm.bsnid === null) ? 50 : 0
      }
    }).catch(ex => {
      console.log(ex)
    })
  },
  methods: {
    /**
     * 基本信息提交
     */
    executeBaseSubmit (formName) {
      console.log(formName)
    },
    /**
     * 基本信息重置
     */
    executeBaseCancel (formName) {
      this.$refs[formName].resetFields()
    },
    /**
     * 初始化省份
     */
    initialProvince () {
      const tempArray = Array(regionData.length)
      for (var indexProvince = 0; indexProvince < regionData.length; ++indexProvince) {
        tempArray[indexProvince] = regionData[indexProvince]
      }
      this.baseSelectableProvinceArray = tempArray
    },
    /**
     * 基本信息中的省份的值发生变化
     */
    executeBaseProvinceChange (val) {
      for (var i = 0; i < this.baseSelectableProvinceArray.length; ++i) {
        if (this.baseSelectableProvinceArray[i].value === val) {
          const tempArray = Array(this.baseSelectableProvinceArray[i].children.length)
          for (var j = 0; j < this.baseSelectableProvinceArray[i].children.length; ++j) {
            tempArray[j] = this.baseSelectableProvinceArray[i].children[j]
          }
          tempArray.unshift({ value: null, label: '请选择所在市', children: [] })
          this.baseSelectableCityArray = tempArray
          return
        }
      }
      this.baseSelectableCityArray = []
    },
    /**
     * 基本信息中的城市的值发生变化
     */
    executeBaseCityChange (val) {
      for (var i = 0; i < this.baseSelectableCityArray.length; ++i) {
        if (this.baseSelectableCityArray[i].value === val) {
          const tempArray = Array(this.baseSelectableCityArray[i].children.length)
          for (var j = 0; j < this.baseSelectableCityArray[i].children.length; ++j) {
            tempArray[j] = this.baseSelectableCityArray[i].children[j]
          }
          tempArray.unshift({ value: null, label: '请选择所在区县' })
          this.baseSelectableDistrictArray = tempArray
          return
        }
      }
      this.baseSelectableDistrictArray = []
    },
    executeBaseModify () {
      let modified = false
      let requestForm = {
        bsnid: this.baseFormData.bsnid
      }
      if (this.baseFormData.corpname !== this.baseForm.corpname) {
        requestForm.corpname = this.baseForm.corpname
        modified = true
      }
      if (this.baseFormData.creditcode !== this.baseForm.creditcode) {
        requestForm.creditcode = this.baseForm.creditcode
        modified = true
      }
      if (this.baseFormData.contactno !== this.baseForm.contactno) {
        requestForm.contactno = this.baseForm.contactno
        modified = true
      }
      if (this.baseFormData.provincename !== this.baseForm.provincename) {
        const localProvinceName = codeToText[this.baseForm.provincename]
        if (typeof localProvinceName === 'undefined') {
          return
        }
        requestForm.provincename = localProvinceName
        modified = true
      }
      if (this.baseFormData.cityname !== this.baseForm.cityname) {
        const localCityName = codeToText[this.baseForm.cityname]
        if (typeof localCityName === 'undefined') {
          return
        }
        requestForm.cityname = localCityName
        modified = true
      }
      if (this.baseFormData.districtname !== this.baseForm.districtname) {
        const localDistrictName = codeToText[this.baseForm.districtname]
        if (typeof localDistrictName === 'undefined') {
          return
        }
        requestForm.districtname = localDistrictName
        modified = true
      }
      if (this.baseFormData.legalname !== this.baseForm.legalname) {
        requestForm.legalname = this.baseForm.legalname
        modified = true
      }
      if (this.baseFormData.address !== this.baseForm.address) {
        requestForm.address = this.baseForm.address
        modified = true
      }
      if (modified) {
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/businessInformation/businessinfo/modifyById',
          requestForm,
          {
            headers: {
              'reference': this.$router.currentRoute.fullPath
            }
          }
        ).then(res => {
          if (res.status === 200) {
            const resData = res.data
            this.baseFormData.bsnid = this.baseForm.bsnid = resData.bsnid
            this.baseFormData.corpname = this.baseForm.corpname = resData.corpname
            this.baseFormData.creditcode = this.baseForm.creditcode = resData.creditcode
            this.baseFormData.contactno = this.baseForm.contactno = resData.contactno
            this.initialProvince()
            this.baseFormData.provincename = this.baseForm.provincename = this.convertProvinceNameToCode(resData.provincename)
            this.executeBaseProvinceChange(this.baseForm.provincename)
            this.baseFormData.cityname = this.baseForm.cityname = this.convertCityNameToCode(resData.cityname)
            this.executeBaseCityChange(this.baseForm.cityname)
            this.baseFormData.districtname = this.baseForm.districtname = this.convertDistrictNameToCode(resData.districtname)
            this.baseFormData.legalname = this.baseForm.legalname = resData.legalname
            this.baseFormData.address = this.baseForm.address = resData.address
          } else {
            this.$refs['baseForm'].resetFields()
          }
        }).catch(ex => {
          console.log(ex)
          this.$refs['baseForm'].resetFields()
        })
      }
    },
    executeLicenseSubmit () {
      if (this.licenseForm.licenseno === null || this.licenseForm.permitted === null || this.licenseForm.expired === null) {
        return
      }
      let modified = false
      let requestForm = {
        licid: this.licenseFormData.licid,
        stationid: this.selectableStation.length === 1 ? this.selectableStation[0].value : null
      }
      if (this.licenseFormData.licenseno !== this.licenseForm.licenseno) {
        requestForm.licenseno = this.licenseForm.licenseno
        modified = true
      }
      if (this.licenseFormData.permitted !== this.licenseForm.permitted) {
        requestForm.permitted = this.dateToString(this.licenseForm.permitted)
        modified = true
      }
      if (this.licenseFormData.expired !== this.licenseForm.expired) {
        requestForm.expired = this.dateToString(this.licenseForm.expired)
        modified = true
      }
      if (modified) {
        this.$axios.post(
          this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationlicense/saveLicense',
          requestForm,
          {
            headers: {
              'reference': this.$router.currentRoute.fullPath
            }
          }
        ).then(res => {
          if (res.status === 200) {
            const resData = res.data
            this.licenseFormData.licid = this.licenseForm.licid = resData.licid
            this.licenseFormData.licenseno = this.licenseForm.licenseno = resData.licenseno
            this.licenseFormData.permitted = this.licenseForm.permitted = resData.permitted
            this.licenseFormData.expired = this.licenseForm.expired = resData.expired
          } else {
            this.$refs['licenseForm'].resetFields()
          }
        }).catch(ex => {
          this.$refs['licenseForm'].resetFields()
        })
      }
    },
    baseImageChange (file, list) {
      switch (file.status) {
        case 'success' : {
          this.basePicture.ymd = file.response[0].yyyymmdd
          this.basePicture.uuid = file.response[0].fileUuid
          this.basePicture.archiveExt = file.response[0].ext
          this.basePicture.name = file.response[0].filename
          break
        }
        case 'fail' : {
          this.basePicture.ymd = null
          this.basePicture.uuid = null
          this.basePicture.archiveExt = null
          this.basePicture.name = null
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
    baseImageProgress (file, list) {
    },
    /**
     * 显示新增充装介质对话框
     */
    showAddGasTypeDialog () {
      this.gasTypeForm.gascatname = null
      this.gasTypeForm.gastypename = null
      this.gasTypeForm.remark = null
      this.gasTypeDialog.visible = true
    },
    /**
     * 新增充装介质
     */
    executeGasTypeInsert (formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) {
          return false
        }
        const localForm = {
          licid: this.licenseForm.licid,
          stationid: this.selectableStation.length === 1 ? this.selectableStation[0].value : null,
          gascatname: this.gasTypeForm.gascatname,
          gastypename: this.gasTypeForm.gastypename,
          remark: this.gasTypeForm.remark
        }
        this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationgastype/gasTypeInsert', localForm, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          if (res.status === 200) {
            this.gasTypeData.push(res.data)
            this.gasTypeDialog.visible = false
          } else {
            this.$message('新增失败')
            this.executeGasTypeQuery()
          }
        }).catch(ex => {
          this.$message('新增失败')
          this.executeGasTypeQuery()
        })
      })
    },
    executeGasTypeQuery () {
      if (this.licenseForm.licid === null) {
        this.gasTypeData = []
      }
      this.$axios.post(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationgastype/queryByLicenseId/' + this.licenseForm.licid, {}, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
        this.gasTypeData = res.status === 200 ? res.data : []
      }).catch(ex => {
        this.gasTypeData = []
      })
    },
    /**
     * 关闭新增充装介质对话框
     */
    executeGasTypeCancel (formName) {
      this.$refs[formName].resetFields()
      this.$refs[formName].clearValidate()
      this.gasTypeDialog.visible = false
    },
    /**
     * 移除允装介质
     */
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
    tbl_delete_gasType ({$index, column, isExpanded, isSelected, row, store, _self}) {
      this.$confirm('确认要删除吗？', '提示', { confirmButtonText: '删除', cancelButtonText: '取消' }).then(_ => {
        this.$axios.get(this.constant.GAS_SERVER_PREFIX + '/businessInformation/stationgastype/deleteById/' + row.gastypeid, { headers: { 'reference': this.$router.currentRoute.fullPath } }).then(res => {
          this.executeGasTypeQuery()
        }).catch(ex => {
          this.executeGasTypeQuery()
        })
      }).catch(_ => {})
    },
    showBasePicture () {
      console.log('show base picture')
      this.pictureShow.dialogWidth = window.innerWidth
      this.pictureShow.dialogHeight = window.innerHeight
      this.pictureShow.divWidth = this.pictureShow.dialogWidth - 42
      this.pictureShow.divHeight = this.pictureShow.dialogHeight - 46
      this.pictureShow.uri = this.basePictureUri
      this.pictureShow.visible = true
    },
    hidePicture (ok) {
      this.pictureShow.uri = null
      ok()
      this.pictureShow.visible = false
    },
    /**
     * 表格中的样式
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
      }
      return null
    },
    /**
     * 把省的名字转换为编码
     * @param {String} name
     */
    convertProvinceNameToCode (name) {
      for (var i = 0; i < this.baseSelectableProvinceArray.length; ++i) {
        if (this.baseSelectableProvinceArray[i].label === name) {
          return this.baseSelectableProvinceArray[i].value
        }
      }
      return null
    },
    /**
     * 把城市的名字转换为编码
     * @param {String} name
     */
    convertCityNameToCode (name) {
      for (var i = 0; i < this.baseSelectableCityArray.length; ++i) {
        if (this.baseSelectableCityArray[i].label === name) {
          return this.baseSelectableCityArray[i].value
        }
      }
      return null
    },
    /**
     * 把区县的名字转换为编码
     * @param {String} name
     */
    convertDistrictNameToCode (name) {
      for (var i = 0; i < this.baseSelectableDistrictArray.length; ++i) {
        if (this.baseSelectableDistrictArray[i].label === name) {
          return this.baseSelectableDistrictArray[i].value
        }
      }
      return null
    },
    /**
     * 把Date对象转换为yyyy-MM-dd hh:mm:ss的字符串
     * @param {Date} da
     */
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
    }
  }
}
</script>

<style>
/* .el-input__inner { */
/*   background-color: #FFF; */
/*   background-image: none; */
/*   border-radius: 4px; */
/*   border: 1px solid #DCDFE6; */
/*   -webkit-box-sizing: border-box; */
/*   box-sizing: border-box; */
/*   color: #606266; */
/*   display: inline-block; */
/*   height: 28px; */
/*   line-height: 40px; */
/*   outline: 0; */
/*   padding: 0 0px; */
/*   -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1); */
/*   transition: border-color .2s cubic-bezier(.645,.045,.355,1); */
/*   width: 100%; */
/* } */
/* .el-form-item { */
/*   margin-bottom: 18px; */
/* } */
</style>
