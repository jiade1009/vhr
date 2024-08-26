<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入股票或代码进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initBeanlist"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-select v-model="status" placeholder="状态" size="mini"
                     style="width: 120px;margin-right: 10px"
                     @change="initBeanlist">
            <el-option
                label="--- 状态 ---"
                value="">
            </el-option>
            <el-option
                label="运行中"
                value="1">
            </el-option>
            <el-option
                label="已结束"
                value="0">
            </el-option>
          </el-select>
          <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="info" icon="el-icon-upload2" @click="showRecordView">
            交易记录录入
          </el-button>
          <el-button type="success" icon="el-icon-upload2" @click="showImportView">
            导入分级
          </el-button>
          <el-button type="primary" icon="el-icon-plus" @click="showAddBeanView">
            添加分级
          </el-button>
        </div>
      </div>
      <!--search section end-->
      <!--list section begin-->
      <div style="margin-top: 10px">
        <el-table
            :data="beanlist"
            row-key="id"
            stripe
            border
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            style="width: 100%">
          <el-table-column
              fixed
              width="140"
              align="left"
              label="名称"
              sortable
              prop="code"
          >
            <template slot-scope="scope">
              {{scope.row.name}}&nbsp;&nbsp;<el-tag>{{scope.row.code}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              width="350"
              align="left"
              label="止盈">
            <template slot-scope="scope">
              <div>
                <span class="pad-right" :style="scope.row.profitStage==1?'color:#67C23A':''">
                  P3：{{ scope.row['priceP3'] }}元 - {{ scope.row['amountP3'] }}股
                </span>
                <span :style="scope.row.profitStage==2?'color:#67C23A':''">
                  P5：{{ scope.row['priceP5'] }}元 - {{ scope.row['amountP5'] }}股
                </span>
              </div>
              <div>
                <span class="pad-right" :style="scope.row.profitStage==3?'color:#67C23A':''">
                  P8：{{ scope.row['priceP8'] }}元 - {{ scope.row['amountP8'] }}股
                </span>
                <span :style="scope.row.profitStage==4?'color:#67C23A':''">
                  P10：{{ scope.row['priceP10'] }}元 - {{ scope.row['amountP10'] }}股
                </span>
              </div>
              <div>
                <span class="pad-right" :style="scope.row.profitStage==5?'color:#67C23A':''">
                  P1051：{{ scope.row['priceP1051'] }}元 - {{ scope.row['amountP1051'] }}股
                </span>
                <span :style="scope.row.profitStage==6?'color:#67C23A':''">
                  P1052：{{ scope.row['priceP1052'] }}元 - {{ scope.row['amountP1052'] }}股
                </span>
              </div>
              <div>
                <span class="pad-right" :style="scope.row.profitStage==7?'color:#67C23A':''">
                  P1053：{{ scope.row['priceP1053'] }}元 - {{ scope.row['amountP1053'] }}股
                </span>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              align="left"
              label="成本"
              width="140"
          >
            <template slot-scope="scope">
              <div>{{ scope.row['priceCost'] }} * {{ scope.row['amountCost'] }} =
                {{ (scope.row['priceCost']*scope.row['amountCost']).toFixed(0)}}
              </div>
            </template>
          </el-table-column>
          <el-table-column
              prop="priceStopLoss"
              align="left"
              label="止损价格">
          </el-table-column>
          <el-table-column
              prop="amountAble"
              align="left"
              width="80"
              label="可用股票">
          </el-table-column>
          <el-table-column
              prop="amountSubstep"
              align="left"
              width="100"
              label="止盈股票数">
          </el-table-column>
          <el-table-column
              prop="amountCost"
              align="left"
              width="100"
              label="成本股票数">
          </el-table-column>
          <el-table-column
              prop="lastTradeTypeNote"
              align="left"
              label="最新交易">
          </el-table-column>
          <el-table-column
              prop="statusNote"
              width="80"
              align="left"
              label="状态">
          </el-table-column>
          <el-table-column
              prop="timeUpdate"
              align="left"
              label="更新时间">
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作">
            <template slot-scope="scope">
              <el-button type="info" @click.stop="showEditBeanView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
              <el-button type="success" @click.stop="viewTrade(scope.row)" style="padding: 3px" size="mini">交易</el-button>
              <el-button type="danger" @click="deleteBean(scope.row)" style="padding: 3px" size="mini">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="display: flex;justify-content: flex-end">
          <el-pagination
              background
              @current-change="currentChange"
              @size-change="sizeChange"
              layout="sizes, prev, pager, next, jumper, ->, total, slot"
              :total="total">
          </el-pagination>
        </div>
      </div>
      <!--list section end-->

      <!-- edit import file dialog begin -->
      <el-dialog
          :title="importTitle"
          :visible.sync="importDialogVisible"
          width="50%">
        <div>
          <el-row style="margin-top: 10px">
            <el-col :span="20">
              <el-upload
                  :show-file-list="false"
                  :before-upload="beforeUpload"
                  :on-success="onSuccess"
                  :on-error="onError"
                  :disabled="importDataDisabled"
                  style="display: inline-flex;margin-right: 8px"
                  :action="uploadUrl">
                <el-button :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon">
                  导入文件
                </el-button>
              </el-upload>
            </el-col>
          </el-row>
          <el-row style="margin-top: 10px;">
            <el-col :span="24">
              <a href="/static/substep_demo.xlsx" target="_blank">模板下载</a>
            </el-col>
          </el-row>
        </div>
      </el-dialog>
      <!-- edit import file dialog end -->

      <!-- edit profit dialog begin -->
      <el-dialog
          :title="title"
          :visible.sync="dialogVisible"
          width="60%">
        <div>
          <el-form :model="bean" status-icon :rules="rules" ref="beanForm" >
            <el-row>
              <el-col :span="12">
                <el-form-item label="名称:" prop="name">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.name"
                            placeholder="请输入股票名称" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="代码:" prop="code">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.code"
                            placeholder="请输入股票代码" clearable></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="成本价:" prop="priceCost">
                  <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit" v-model="bean.priceCost"
                            placeholder="请输入股票成本价格" clearable @input="handlePriceCost"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="成本股票数:" prop="amountCost">
                  <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit" v-model="bean.amountCost"
                            placeholder="请输入股票成本关联的股票数"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="止损价:" prop="priceStopLoss">
                  <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit" v-model="bean.priceStopLoss"
                            placeholder="请输入股票止损价格"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="分级统计股票数:" prop="amountSubstep">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountSubstep"
                            placeholder="请输入计算分级止盈所卖出的股票数量" clearable @input="handleAmountSubstep"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="可用股票数:" prop="amountAble">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountAble"
                            placeholder="请输入可用的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="P3阶段价格:" prop="priceP3">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP3"
                            placeholder="请输入P3阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P3阶段股票数:" prop="amountP3">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP3"
                            placeholder="请输入P3阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="P5阶段价格:" prop="priceP5">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP5"
                            placeholder="请输入P5阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P5阶段股票数:" prop="amountP5">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP5"
                            placeholder="请输入P5阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="P8阶段价格:" prop="priceP8">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP8"
                            placeholder="请输入P8阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P8阶段股票数:" prop="amountP8">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP8"
                            placeholder="请输入P8阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="P10阶段价格:" prop="priceP10">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP10"
                            placeholder="请输入P10阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P10阶段股票数:" prop="amountP10">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP10"
                            placeholder="请输入P10阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="P1051阶段价格:" prop="priceP1051">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP1051"
                            placeholder="请输入P1051阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P1051阶段股票数:" prop="amountP1051">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP1051"
                            placeholder="请输入P1051阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="P1052阶段价格:" prop="priceP1052">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP1052"
                            placeholder="请输入P1052阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P1052阶段股票数:" prop="amountP1052">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP1052"
                            placeholder="请输入P1052阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="P1053阶段价格:" prop="priceP1053">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.priceP1053"
                            placeholder="请输入P1053阶段的止盈价格"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="P1053阶段股票数:" prop="amountP1053">
                  <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="bean.amountP1053"
                            placeholder="请输入P1053阶段止盈卖出的股票数量"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="止盈阶段:" prop="profitStage">
                  <el-select v-model="bean.profitStage" placeholder="止盈阶段" size="mini" style="width: 150px;">
                    <el-option
                        v-for="item in stage_type_select"
                        :key="item.label"
                        :label="item.name"
                        :value="item.label">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="状态:" prop="status">
                  <el-switch
                      v-model="bean.status"
                      active-text="启用"
                      inactive-text="禁用"
                      @change="enabledChange(bean)"
                      active-color="#13ce66"
                      inactive-color="#ff4949"
                      :active-value="1"
                      :inactive-value="0"
                  >
                  </el-switch>
                </el-form-item>
              </el-col>
            </el-row>


            <el-input type="hidden" id="id"></el-input>
            <el-input type="hidden" id="timeCreate"></el-input>
            <el-input type="hidden" id="timeUpdate"></el-input>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="doAddBean">确 定</el-button>
        </span>
      </el-dialog>
      <!-- edit dialog end -->

      <!-- edit trade list dialog begin -->
      <el-dialog
          title="股票交易记录"
          :visible.sync="tradeDialogVisible"
          width="60%">
        <div style="margin-top: 10px">
          <el-table
              :data="tradeBeanlist"
              stripe
              border
              v-loading="loading"
              element-loading-text="正在加载..."
              element-loading-spinner="el-icon-loading"
              element-loading-background="rgba(0, 0, 0, 0.8)"
              :row-class-name="tableRowClassName"
              style="width: 100%">
            <el-table-column
                v-for="column in tradeTableColumns"
                :key="column.prop"
                :prop="column.prop"
                :label="column.label"
                align="left">
            </el-table-column>
          </el-table>
        </div>
      </el-dialog>
      <!-- edit trade dialog end -->

      <!-- edit record trade dialog begin -->
      <el-dialog
          :title="recordTitle"
          :visible.sync="recordDialogVisible"
          width="50%">
        <div>
          <el-form :model="bean_record" status-icon :rules="rules_record" ref="beanForm_record" >
            <el-row>
              <el-col :span="24">
                <el-form-item label="记录:" prop="recordText">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean_record.recordText"
                            type="textarea" rows="10" style="width:80%" placeholder="请录入股票交易记录"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row style="margin-top: 10px;">
              <el-col :span="24">
                <div>交易记录格式：交易日期	代码	名称	成交价	成交量	操作</div>
                <div>一行表示一条交易记录，且交易信息用空格分隔，操作包括：加仓、止盈、止损、降仓、调仓、清仓	。</div>
                <div>例如：</div>
                <div>20240809	600292	远达环保	4.82	4200	止盈</div>
                <div>20240809	600409	三友化工	5.64	1000	加仓</div>
                <div>20240809	600167	联美控股	5.12	1000	加仓</div>
              </el-col>
            </el-row>
          </el-form>
        </div>

        <span slot="footer" class="dialog-footer">
          <el-button @click="recordDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="recordTrades">确 定</el-button>
        </span>
      </el-dialog>
      <!-- edit dialog end -->

    </div>
  </div>
</template>

<script>
export default {
  name: "StockSubstepProfit",
  data () {
    return {
      //分页参数
      total: 0,
      page: 1,
      size: this.$ELEMENT.pagesize,
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
      bean: {
        "id": null,
        "name": null,
        "code": null,
        "profitStage": 0,
        "priceStopLoss": null,
        "priceCost": null,
        "amountCost": null,
        "amountAble": null,
        "amountSubstep": null,
        "status": 1,
        "priceP3": null,
        "amountP3": null,
        "priceP5": null,
        "amountP5": null,
        "priceP8": null,
        "amountP8": null,
        "priceP10": null,
        "amountP10": null,
        "priceP1051": null,
        "amountP1051": null,
        "priceP1052": null,
        "amountP1052": null,
        "priceP1053": null,
        "amountP1053": null,
      },
      rules: {
        name: [{required: true, message: '请输入名称', trigger: 'blur'}],
        code: [{required: true, message: '请输入代码', trigger: 'blur'}],
      },
      bean_record: {
        recordText: ''
      },
      rules_record: {
        recordText: [{required: true, message: '请输入交易记录', trigger: 'blur'}],
      },
      stageList: ['P3', 'P5', 'P8', 'P10', 'P1051', 'P1052', 'P1053'],
      title: '新增分级',
      dialogVisible: false,
      importTitle: '导入分级止盈',
      importDialogVisible: false,
      importDataBtnText: '导入数据',
      importDataBtnIcon: 'el-icon-upload2',
      importDataDisabled: false,
      recordTitle: '交易记录录入',
      recordDialogVisible: false,
      status: '',
      stage_type_select: [],
      //列表参数
      tradeBeanlist: [],
      tradeTableColumns: [
        {prop: "timeCreate", label: "创建时间", show: true},
        {prop: "dateTrade", label: "交易日期", show: true},
        {prop: "price", label: "成交价", show: true},
        {prop: "amount", label: "交易数", show: true},
        {prop: "typeNote", label: "交易类型", show: true},
      ],
      tradeDialogVisible: false,
    }
  },
  computed: {
    uploadUrl() {
      let baseUrl = '/stock/substepprofit/import';
      return `${baseUrl}`;
    },
  },
  mounted() {
    this.initBeanlist();
    this.initData();
    this.loadStageType();
  },
  methods: {
    initData() {
    },
    enabledChange() {
    },
    loadStageType() {
      let url = '/stock/substepprofit/stagetype';
      this.getRequest(url).then(resp => {
        if (resp) {
          let stage_type = resp.obj.stage_type;
          this.stage_type_select = stage_type;
          for (let i = 0; i < stage_type.length; i++) {
            this.stage_type_select[i] = {
              'name': stage_type[i],
              "label": i
            }
          }
        }
      });
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initBeanlist();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initBeanlist();
    },
    initBeanlist() {
      this.loading = true;
      let url = '/stock/substepprofit/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword + "&status=" + this.status;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    showImportView() {
      this.importDialogVisible = true;
    },
    showRecordView() {
      this.recordText = '';
      this.recordDialogVisible = true;
    },
    onError(err, file, fileList) {
      this.importDataBtnText = '导入数据';
      this.importDataBtnIcon = 'el-icon-upload2';
      this.importDataDisabled = false;
    },
    onSuccess(response, file, fileList) {
      this.importDataBtnText = '导入数据';
      this.importDataBtnIcon = 'el-icon-upload2';
      this.importDataDisabled = false;
      if (response.status == 200) {
        this.importDialogVisible = false;
        this.$message({
          type: 'info',
          message: '导入成功'
        });
        this.initBeanlist();
      } else {
        this.$message({
          type: 'error',
          message: response.msg
        });
      }

    },
    beforeUpload() {
      this.importDataBtnText = '正在导入';
      this.importDataBtnIcon = 'el-icon-loading';
      this.importDataDisabled = true;
    },
    showAddBeanView() {
      this.emptyBean();
      this.title = '新增分级';
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑分级';
      // this.bean = data;
      Object.assign(this.bean, data);
      this.dialogVisible = true;
    },
    emptyBean() {
      this.bean = {
        "id": null,
        "name": null,
        "code": null,
        "profitStage": 0,
        "priceStopLoss": null,
        "priceCost": null,
        "amountCost": null,
        "amountAble": null,
        "amountSubstep": null,
        "status": 1,
        "priceP3": null,
        "amountP3": null,
        "priceP5": null,
        "amountP5": null,
        "priceP8": null,
        "amountP8": null,
        "priceP10": null,
        "amountP10": null,
        "priceP1051": null,
        "amountP1051": null,
        "priceP1052": null,
        "amountP1052": null,
        "priceP1053": null,
        "amountP1053": null,
      };
    },
    handlePriceCost(value) {
      this.bean.priceP3 = Math.ceil(value*1.03*100)/100;
      this.bean.priceP5 = Math.ceil(value*1.05*100)/100;
      this.bean.priceP8 = Math.ceil(value*1.08*100)/100;
      this.bean.priceP10 = Math.ceil(value*1.10*100)/100;
      this.bean.priceP1051 = Math.ceil(this.bean.priceP10*1.05*100)/100;
      this.bean.priceP1052 = Math.ceil(this.bean.priceP1051*1.05*100)/100;
      this.bean.priceP1053 = Math.ceil(this.bean.priceP1052*1.05*100)/100;
    },
    handleAmountSubstep(value) {
      this.bean.amountP3 = Math.floor(value*0.1/100)*100;
      this.bean.amountP5 = Math.floor(value*0.1/100)*100;
      this.bean.amountP8 = Math.floor(value*0.1/100)*100;
      this.bean.amountP10 = Math.floor(value*0.2/100)*100;
      let remainder = value - (this.bean.amountP3 + this.bean.amountP5 + this.bean.amountP8 + this.bean.amountP10)
      this.bean.amountP1051 = Math.floor(remainder/3/100)*100;
      remainder -= this.bean.amountP1051
      this.bean.amountP1052 = Math.floor(remainder/4/100)*100;
      remainder -= this.bean.amountP1052
      this.bean.amountP1053 = Math.floor(remainder/5/100)*100;
    },
    doAddBean() {
      if (this.bean.id) {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.putRequest("/stock/substepprofit/", this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      } else {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.postRequest("/stock/substepprofit/", this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      }
    },
    viewTrade(row) {
      this.tradeDialogVisible = true;
      let stepId = row["id"];
      let url = '/stock/substeptrade/' + stepId;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.tradeBeanlist = resp.data;
        }
      });
    },
    tableRowClassName({row, rowIndex}) {
      if (row["type"]==0) {
        return 'profit-row';
      } else {
        return 'loss-row';
      }
    },
    deleteBean(data) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/stock/substepprofit/" + data.id).then(resp => {
          if (resp) {
            this.initBeanlist();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    recordTrades() {
      this.$refs['beanForm_record'].validate(valid => {
        if (valid) {
          this.$confirm('确认交易记录正确, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let data = {
              recordText: this.bean_record.recordText
            }
            this.postRequest("/stock/substeptrade/record", data).then(resp => {
              if (resp) {
                this.recordDialogVisible = false;
                //初始化变量
                this.initBeanlist();
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消交易记录录入'
            });
          });
        }
      });
    }
  }
}
</script>

<style>
.pad-right {
  padding-right: 10px;
  display: inline-block;
  width: 180px;
}
</style>