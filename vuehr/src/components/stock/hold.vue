<template>
  <div>
    <!--search section begin-->
    <div>
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入股票代码进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initBeanlist"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    :disabled="showAdvanceSearchView"
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-button icon="el-icon-search" type="primary" :disabled="showAdvanceSearchView" @click="initBeanlist">
            搜索
          </el-button>
          <el-button type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">
            <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
               aria-hidden="true"></i>
            高级搜索
          </el-button>
        </div>
        <div v-if="flag=='stock'">
          <el-button type="danger" icon="el-icon-switch-button" @click="changeBuySwitch($event)">
            {{autoBuy?'暂停股票自动买入':'开启股票自动买入'}}
          </el-button>
        </div>
      </div>
      <transition name="slide-fade">
        <div v-show="showAdvanceSearchView"
           style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
        <el-row style="margin-top: 10px">
          <el-col :span="10">
            状态:
            <el-select v-model="searchValue.status" placeholder="状态" size="mini"
                       style="width: 130px;">
              <el-option
                  label="------"
                  value="">
              </el-option>
              <el-option
                  v-for="item in holdstatus"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>

          <el-col :span="6" :offset="8">
            <el-button size="mini" @click="cancelSearch()">取消</el-button>
            <el-button size="mini" icon="el-icon-search" type="primary" @click="initBeanlist('advanced')">搜索</el-button>
          </el-col>
        </el-row>
      </div>
      </transition>
    </div>
    <!--search section end-->
    <!--list section begin-->
    <div style="margin-top: 10px">
      <el-table
          :data="beanlist"
          row-key="id"
          :expand-row-keys="expands"
          stripe
          border
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          @row-click="viewDetail"
          :row-class-name="getRowClassName"
          style="width: 100%">
        <el-table-column type="expand" width="1">
          <template slot-scope="props">
            <div style="padding-left: 10px;padding-bottom: 5px;">
              <div class="ema-content">
                <div v-if="!!props.row.detaillist && props.row.detaillist.length>0"
                     class="orangeShip">
                  <p style="color: #409EFF"><i class="el-icon-star-off"></i>交易明细记录</p>
                  <p>备注：交易类型为"分红配送"的，其中"委托数量/成交数量"对应"配送股票数"，"手续费"对应"派息"</p>
                  <el-table
                      border
                      :stripe="false"
                      :data="props.row.detaillist"
                      :header-cell-style="tb_header_style"
                      style="width: 100%">
                    <el-table-column
                        prop="timeCreate"
                        label="创建时间"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="cjTime"
                        label="交易时间"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="priceType"
                        label="委托价格"
                        :formatter="detailFormat"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="cjPrice"
                        label="成交价格"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="amount"
                        label="委托数量"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="cjAmount"
                        label="成交数量"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="cjTotal"
                        label="成交金额"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="cjFee"
                        label="手续费"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="tradeType"
                        label="交易类型"
                        :formatter="detailFormat"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="statusNote"
                        label="状态"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="message"
                        label="状态信息"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="taskstatusNote"
                        label="任务"
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="taskmsg"
                        label="任务信息"
                        show-overflow-tooltip
                        align="left">
                    </el-table-column>
                    <el-table-column
                        prop="taskpro"
                        label="任务进度"
                        align="left">
                    </el-table-column>
                  </el-table>
                </div>
                <div v-else>暂无交易明细记录</div>

              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            v-for="column in tableColumns"
            :key="column.prop"
            :prop="column.prop"
            :label="column.label"
            align="left">
          <template slot-scope="scope">
            <div v-if="column.prop == 'code' && flag == 'stock'">
              <span v-html="xbFormatter(scope.row.code, scope.column.property)"></span>
<!--              //将表格数据格式化后，再用 template + v-html 展示出来-->
            </div>
            <div v-else-if="column.prop == 'generateType'">
              {{scope.row[scope.column.property]==0?"直接加入":"回头草"}}
            </div>
            <div v-else>
              {{ scope.row[scope.column.property] }}
            </div>
          </template>
        </el-table-column>

        <el-table-column
            fixed="right"
            width="200"
            label="操作" v-if="flag=='stock'" >
          <template slot-scope="scope">
            <el-button type="info" @click.stop="viewDetail(scope.row)" style="padding: 3px" size="mini">查看交易</el-button>
            <el-button type="success" @click.stop="viewProfit(scope.row)" style="padding: 3px" size="mini">查看盈亏</el-button>
            <el-button type="warning" @click.stop="doCalculator(scope.row)" style="padding: 3px" size="mini">价格计算器</el-button>
            <el-button type="danger" @click.stop="closeStock(scope.row)" style="padding: 3px" size="mini" v-if="scope.row.status==0 && flag=='stock'">关闭交易</el-button>
            <!--
            <el-button type="success" @click.stop="runBuy(scope.row)" style="padding: 3px" size="mini" v-if="scope.row.status==0">购买</el-button>
            <el-button type="info" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==2">恢复购买</el-button>
            <el-button type="warning" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==0">暂停购买</el-button>
            <el-button type="success" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==3">暂停卖出</el-button>
            -->
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

    <!-- edit dialog begin -->
    <el-dialog
        title="股票交易价格计算"
        :visible.sync="dialogVisible"
        width="60%">
      <div class="price_cal">
        <p>股票代码：{{bean.code}}</p>
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content grid-label">P1阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.p1Price}}</div></el-col>
          <el-col :span="6"><div class="grid-content grid-label">P2阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.p2Price}}</div></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content grid-label">P3阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.p3Price}}</div></el-col>
          <el-col :span="6"><div class="grid-content grid-label">P4阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.p4Price}}</div></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content grid-label">P5阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.p5Price}}</div></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content grid-label">当前阶段：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.sellStage}}</div></el-col>
          <el-col :span="6"><div class="grid-content grid-label">最高价：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.priceHigh}}</div></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content grid-label">卖出价格：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.sellPrice}}</div></el-col>
          <el-col :span="6"><div class="grid-content grid-label">止损价格：</div></el-col>
          <el-col :span="6"><div class="grid-content">{{bean.priceStop}}</div></el-col>
        </el-row>
      </div>
    </el-dialog>
    <!-- edit dialog end -->

    <!-- edit profit dialog begin -->
    <el-dialog
        title="股票盈亏记录"
        :visible.sync="profitDialogVisible"
        width="60%">
      <div style="margin-top: 10px">
        <el-table
            :data="profitBeanlist"
            stripe
            border
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            :row-class-name="tableRowClassName"
            :summary-method="getSummaries"
            show-summary
            style="width: 100%">
          <el-table-column
              v-for="column in profitTableColumns"
              :key="column.prop"
              :prop="column.prop"
              :label="column.label"
              :formatter="columnFormat"
              align="left">
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
    <!-- edit profit dialog end -->
  </div>
</template>

<script>
export default {
  name: "hold",
  props: {
    flag: String,
  },
  computed: {
    baseCode: function () {
      return this.flag == 'stock'?'stk_auto_order':'stk_h_auto_order'
    }
  },
  data() {
    return {
      autoBuy: false,   // 是否自动买入
      searchValue: {
        status: "",
      },
      holdstatus: [],
      showAdvanceSearchView: false,
      //分页参数
      total: 0,
      page: 1,
      size: this.$ELEMENT.pagesize,
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
      tableColumns: [
        {prop: "code", label: "代码", show: true},
        {prop: "timeCreate", label: "创建时间", show: true},
        {prop: "timeUpdate", label: "更新时间", show: true},
        {prop: "statusNote", label: "状态", show: true},
        {prop: "buyPrice", label: "买入价", show: true},
        {prop: "buyAmount", label: "买入数量", show: true},
        {prop: "holdAmount", label: "持有数量", show: true},
        {prop: "sellStage", label: "交易阶段", show: true},
        {prop: "generateType", label: "加入方式", show: true},
        {prop: "note", label: "备注", show: true}
      ],
      //列表参数
      profitBeanlist: [],
      profitTableColumns: [
        {prop: "dateResearch", label: "日期", show: true},
        {prop: "priceClose", label: "收盘价", show: true},
        {prop: "amountHold", label: "持股数", show: true},
        {prop: "total", label: "总市值", show: true},
        {prop: "profit", label: "当日盈亏", show: true},
        {prop: "profitRate", label: "当日盈亏率", show: true}
      ],

      //下拉明细内容
      expands:[],
      expandRow: [],
      dialogVisible: false,
      profitDialogVisible: false,
      sellRule: {
        sellRatio: 0.5,
        p1Ratio: 1.15,
        p2Ratio: 1.1,
        p3Ratio: 1.1,
        p4Ratio: 1.1,
        p5Ratio: 1.1,
        sp1Ratio: 0.85,
        sp2Ratio: 0.87,
        sp3Ratio: 0.89,
        sp4Ratio: 0.91,
      },
      bean: {
        "id": null,
        "buyPrice": null,
        "holdAmount": 100,
        "sellStage": 0,
        "priceClose": null,
        "priceHigh": null,
        "priceLow": null,
        "priceSellHigh": null,
        "priceSellLow": null,
        "closeEma18": null,
        "p1Price": null,
        "p1SellPrice": null,
        "p2Price": null,
        "p2SellPrice": null,
        "p3Price": null,
        "p3SellPrice": null,
        "p4Price": null,
        "p4SellPrice": null,
        "p5Price": null,
      },

    }
  },
  mounted() {
    this.initBeanlist();
    this.getSellRule();
    this.getBuySwitch();
    this.initData();
  },
  methods: {
    initData() {
      if (!window.sessionStorage.getItem("holdstatus")) {
        this.getRequest('/stock/holdstatus').then(resp => {
          if (resp) {
            this.holdstatus = resp;
            window.sessionStorage.setItem("holdstatus", JSON.stringify(resp));
          }
        })
      } else {
        this.holdstatus = JSON.parse(window.sessionStorage.getItem("holdstatus"));
      }
    },
    getBuySwitch() {
      // 查询是否关闭买入交易
      this.getRequest("/system/databasetype/getByCode?code="+this.baseCode).then(resp =>{
        if (resp) {
          if (resp.obj) {
            this.autoBuy = resp.obj.value==0?false:true;
          }
        }
      });
    },
    changeBuySwitch(e) {
      let o = e.currentTarget;
      let msg = this.autoBuy ? "是否关闭自动购买交易功能，请确认" : "确认开启自动购买交易功能";
      this.$confirm(msg, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          code: this.baseCode,
          val: this.autoBuy?"0":"1"
        }
        this.disabled = true;
        this.postRequest("/system/databasetype/updateByCode", data).then(resp => {
          this.disabled = false
          if (resp) {
            this.autoBuy = !this.autoBuy
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    },
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        // 委托/撤单状态，1结束，0执行中，-1失败
        // 状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束
        // return data;
        return row["statusNote"];
      } else if (property == "generateType"){
        return data == "0"?"直接加入":"回头草";
      } else {
        return data;
      }
    },
    xbFormatter(value, row) {
      let url = this.$utils.getEastMoneyUrl(value);
      if (url == value) {
        return value
      } else {
        return "<a href='" + url + "' target='_blank'>" + value + "</a>"
      }
    },
    detailFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "tradeType") {
        // 交易类型：0买入，1卖出，2买入撤销，3卖出撤销
        return data == "0" ? "买入" : data == "1" ? "卖出" : data == "2" ?
            "买入撤销" : data== "3" ? "卖出撤销" : data=="4"? "分红配送":"未知";
      } else if (property == "priceType") {
        if (data == "1") {
          return "最新价"
        } else if (data.substring(0, 1) == 'B') {
          return "买" + data.substring(1) + "价"
        } else if (data.substring(0, 1) == 'S') {
          return "卖" + data.substring(1) + "价"
        } else {
          return data;
        }
      } else {
        return data;
      }
    },
    tableRowClassName({row, rowIndex}) {
      if (row["profit"]>=0) {
        return 'profit-row';
      } else {
        return 'loss-row';
      }
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initBeanlist('advanced');
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initBeanlist('advanced');
    },
    cancelSearch() {
      this.searchValue.status = "";
      this.page = 1;
      this.size = this.$ELEMENT.pagesize;
      this.initBeanlist('advanced');
    },
    initBeanlist(type) {
      this.loading = true;
      let url = '/' + this.flag + '/hold/?page=' + this.page + '&size=' + this.size;
      if (type && type == 'advanced') {
        url += '&status=' + this.searchValue.status;
      } else {
        url += "&keyword=" + this.keyword;
      }
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    viewDetail(row) {
      if (this.flag != 'stock') return false;
      if (!!!row.detaillist) {
        this.getDetailList(row);
      } else {
        if (this.expands.length>0 && this.expands[0] == row.id) {
          this.expands = [];
          this.expandRow = [];
        } else {
          this.expands = [row.id];
          this.expandRow = [row];
        }
      }
    },
    viewProfit(row) {
      this.profitDialogVisible = true;
      let code = row["code"];
      let url = '/' + this.flag + '/profit/?page=-1&size=-1' + "&keyword=" + code;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.profitBeanlist = resp.data;
        }
      });
    },
    getDetailList(row) {
      // 根据weekly_result_id，获取对应的ema结果
      let url = '/' + this.flag + '/holdtrade/byholdid?hid='+row.id
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          row.detaillist = resp;
          this.expands = [row.id];
          this.expandRow = [row];
        }
        // that.$refs.evtTable.toggleRowExpansion(row);
      });
    },
    tb_header_style(){
      //设置明细内表格标题样式
      let s = {
        'background-color': 'rgb(160, 207, 255)',
        'color': '#000',
      }
      return s;
    },
    // 通过样式隐藏expand图标
    getRowClassName() {
      return 'row-expand-cover'
    },
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总价';
          return;
        }
        if (column.property=="profit") {
          const values = data.map(item => Number(item['profit']));
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          sums[index] = sums[index].toFixed(2);
          // sums[index] += ' 元';
        } else if (column.property=="profitRate") {
          // 获取股票的最初成本市值，即最初始的盈亏记录的市值+盈亏
          if (data.length>0) {
            let first = data[data.length-1];
            let total_buy = Number(first["total"]) - Number(first["profit"])
            sums[index] = (sums[index-1]/total_buy*100).toFixed(2) + '%';
          }
        } else {
          sums[index] = '';
        }
      });
      return sums;
    },
    runBuy(row) {
      this.$confirm('确认购买该股票吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest('/' + this.flag + '/hold/runBuy?holdId=' + row.id).then(resp => {
          if (resp) {
            //更新list信息
            this.initBeanlist();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    },
    closeStock(row) {
      this.$confirm('确认关闭该股票吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest('/' + this.flag + '/hold/close?holdId=' + row.id).then(resp => {
          if (resp) {
            //更新list信息
            this.initBeanlist();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    },
    getSellRule() {
      if (this.flag=="stock") {
        this.getRequest('/' + this.flag + '/sellrule/getRunning').then(resp =>{
          if (resp) {
            if (resp.obj) { // 存在正在运行的卖出策略
              this.sellRule = resp.obj
            }
          }
        });
      }
    },
    doCalculator(row) {
      this.dialogVisible = true;
      this.bean = row;
      let sellRule = this.sellRule;
      let p1Price = (this.bean.buyPrice * sellRule.p1Ratio).toFixed(2);
      let p2Price = (p1Price * sellRule.p2Ratio).toFixed(2);
      let p3Price = (p2Price * sellRule.p3Ratio).toFixed(2);
      let p4Price = (p3Price * sellRule.p4Ratio).toFixed(2);
      let p5Price = (p4Price * sellRule.p5Ratio).toFixed(2);
      let priceHigh = this.bean.priceHigh;
      let stage = this.bean.sellStage;
      let spRatio = 0;
      let sellPrice = 0;
      if (stage == 1)spRatio = sellRule.sp1Ratio;
      else if (stage == 2)spRatio = sellRule.sp2Ratio;
      else if (stage == 3)spRatio = sellRule.sp3Ratio;
      else if (stage == 4)spRatio = sellRule.sp4Ratio;
      else if (stage == 5)spRatio = 1, priceHigh = p5Price;
      if (stage == -1) {
        sellPrice = this.bean.priceStop;
      } else {
        sellPrice = stage == 0? p1Price: (priceHigh*spRatio).toFixed(2);
      }
      let result = this.$utils.twoJsonMerge(this.bean, {
        p1Price: p1Price,
        p2Price: p2Price,
        p3Price: p3Price,
        p4Price: p4Price,
        p5Price: p5Price,
        sellStage: stage,
        spRatio: spRatio,
        sellPrice: sellPrice,
      });
      this.bean = result;
    },
  }
}
</script>

<style>
.row-expand-cover .el-table__expand-icon{
  visibility: hidden!important;
}
.row-expand-cover .el-table__expand-column {
  visibility: hidden!important;
}

.price_cal .el-row {
  border-radius: 4px;
  border-top: 1px solid #E4E7ED;
}
.price_cal .el-row:last-child {
  margin-bottom: 0;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  line-height: 36px;
  padding-left: 4px;
}
.grid-label {
  text-align: right;
}
</style>