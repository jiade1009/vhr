<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入股票代码进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initBeanlist"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
            搜索
          </el-button>
        </div>
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
                          show-overflow-tooltip="true"
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
              :formatter="columnFormat"
              align="left">
          </el-table-column>

          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button type="info" @click.stop="viewDetail(scope.row)" style="padding: 3px" size="mini">查看交易</el-button>
              <el-button type="success" @click.stop="doCalculator(scope.row)" style="padding: 3px" size="mini">价格计算器</el-button>
              <!--
              <el-button type="success" @click.stop="runBuy(scope.row)" style="padding: 3px" size="mini" v-if="scope.row.status==0">购买</el-button>
              <el-button type="info" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==2">恢复购买</el-button>
              <el-button type="warning" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==0">暂停购买</el-button>
              <el-button type="success" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==3">暂停卖出</el-button>
              <el-button type="danger" @click="" style="padding: 3px" size="mini" v-if="scope.row.status!=6">关闭交易</el-button>-->
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
        <div>
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
          <el-row :gutter="20" :hidden="bean.sellStage<1">
            <el-col :span="6"><div class="grid-content grid-label">当前阶段卖出价格：</div></el-col>
            <el-col :span="6"><div class="grid-content">{{bean.sellPrice}}</div></el-col>
          </el-row>
        </div>
      </el-dialog>
      <!-- edit dialog end -->
    </div>
  </div>
</template>

<script>
export default {
  name: "StockHold",
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
      tableColumns: [
        {prop: "code", label: "代码", show: true},
        {prop: "timeCreate", label: "创建时间", show: true},
        {prop: "timeUpdate", label: "更新时间", show: true},
        {prop: "statusNote", label: "状态", show: true},
        {prop: "buyPrice", label: "买入价", show: true},
        {prop: "buyAmount", label: "买入数量", show: true},
        {prop: "holdAmount", label: "持有数量", show: true},
        {prop: "sellStage", label: "交易阶段", show: true},
        {prop: "note", label: "备注", show: true}
      ],
      //下拉明细内容
      expands:[],
      expandRow: [],
      dialogVisible: false,
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
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        // 委托/撤单状态，1结束，0执行中，-1失败
        // 状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束
        // return data;
        return row["statusNote"];
      } else {
        return data;
      }
    },
    detailFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "tradeType") {
        // 交易类型：0买入，1卖出，2买入撤销，3卖出撤销
        return data == "0" ? "买入" : data == "1" ? "卖出" : data == "2" ?
            "买入撤销" : "卖出撤销";
      } else if (property == "priceType") {
        if (data == "1") {
          return "最新价"
        } else if (data.substring(0, 1) == 'B') {
          return "买" + data.substring(1) + "价"
        } else if (data.substring(0, 1) == 'B') {
          return "卖" + data.substring(1) + "价"
        } else {
          return data;
        }
      } else {
        return data;
      }
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
      let url = '/stock/hold/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    viewDetail(row) {
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
    getDetailList(row) {
      // 根据weekly_result_id，获取对应的ema结果
      let url = "/stock/holdtrade/byholdid?hid="+row.id
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
    runBuy(row) {
      this.$confirm('确认购买该股票吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest("/stock/hold/runBuy?holdId=" + row.id).then(resp => {
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
      this.getRequest("/stock/sellrule/getRunning").then(resp =>{
        if (resp) {
          if (resp.obj) { // 存在正在运行的卖出策略
            this.sellRule = resp.obj
          }
        }
      });
    },
    doCalculator(row) {
      this.dialogVisible = true;
      let bean = row;
      let sellRule = this.sellRule;
      let p1Price = (bean.buyPrice * sellRule.p1Ratio).toFixed(2);
      let p2Price = (p1Price * sellRule.p2Ratio).toFixed(2);
      let p3Price = (p2Price * sellRule.p3Ratio).toFixed(2);
      let p4Price = (p3Price * sellRule.p4Ratio).toFixed(2);
      let p5Price = (p4Price * sellRule.p5Ratio).toFixed(2);

      let buyPrice = this.bean.buyPrice;
      let priceHigh = this.bean.priceHigh;
      let stage = 0;
      let spRatio = 0;
      if (parseFloat(priceHigh)<parseFloat(buyPrice)) stage = 0, spRatio = '无';
      else if (priceHigh>p1Price && priceHigh<p2Price) stage = 1, spRatio = sellRule.sp1Ratio;
      else if (priceHigh>p2Price && priceHigh<p3Price) stage = 2, spRatio = sellRule.sp2Ratio;
      else if (priceHigh>p3Price && priceHigh<p4Price) stage = 3, spRatio = sellRule.sp3Ratio;
      else if (priceHigh>p4Price && priceHigh<p5Price) stage = 4, spRatio = sellRule.sp4Ratio;
      else if (priceHigh>p5Price) stage = 5, spRatio = 1, priceHigh = p5Price;
      let sellPrice = spRatio == '无'? '暂无': (priceHigh*spRatio).toFixed(2);
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

.el-row {
  border-top: 1px solid #E4E7ED;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
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