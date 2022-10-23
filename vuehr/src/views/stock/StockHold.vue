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
              <div style="padding-left: 60px;padding-bottom: 5px;">
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
                          prop="price"
                          label="价格"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="amount"
                          label="委托数量"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="tradeType"
                          label="交易类型"
                          :formatter="detailFormat"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="message"
                          label="委托/撤单状态信息"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="status"
                          :formatter="detailFormat"
                          label="委托/撤单状态"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="taskid"
                          label="任务/指令编号"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="taskstatus"
                          :formatter="detailFormat"
                          label="任务/指令状态"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="taskmsg"
                          label="任务/指令状态信息"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="taskpro"
                          label="任务进度"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="ordernum"
                          label="订单编号"
                          align="left">
                      </el-table-column>
                      <el-table-column
                          prop="timeUpdate"
                          label="更新时间"
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

          <!--<el-table-column
              fixed="right"
              width="120"
              label="操作">
            <template slot-scope="scope">
              <el-button type="info" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==2">恢复购买</el-button>
              <el-button type="warning" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==0">暂停购买</el-button>
              <el-button type="success" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==3">暂停卖出</el-button>
              <el-button type="danger" @click="" style="padding: 3px" size="mini" v-if="scope.row.status!=6">关闭交易</el-button>
            </template>
          </el-table-column>-->
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

    </div>
  </div>
</template>

<script>
export default {
  name: "StockBasicinfo",
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
        {prop: "status", label: "状态", show: true},
        {prop: "holdAmount", label: "持有数量", show: true}
      ],
      //下拉明细内容
      expands:[],
      expandRow: [],
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "tradeType") {
        // 交易类型：0买入，1卖出，2买入撤销，3卖出撤销
        return data == "0" ? "买入" : data == "1" ? "卖出" : data == "2" ?
            "买入撤销" : "卖出撤销";
      } else if (property == "status") {
        // 委托/撤单状态，1结束，0执行中，-1失败
        return data == "0" ? "执行中" : data == "1" ? "结束" : "失败"
      } else if (property == "taskStatus") {
        // 任务/指令状态，0 未知,1 等待,2 提交中,3 执行中,4 暂停,5 撤销中,6 异常撤销中,7 完成,8 已撤,9 打回,10  异常终止,11  放弃，目前用于组合交易中，放弃补单,12  强制终止
        return data == "0" ? "未知" : data == "1" ? "等待" : data == "2" ?
            "提交中" : data == "3" ? "执行中" : data == "4" ? "暂停" : data == "5"?
                "撤销中" : data == "6" ? "异常撤销中" : data == "7" ? "完成" : data == "8"?
                    "已撤" : data == "9" ? "打回" : data == "10" ? "异常终止" : data == "11"?
                        "放弃，目前用于组合交易中，放弃补单" : "强制终止";
      } else {
        return data;
      }
    },
    detailFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        // 状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束
        return data == "0" ? "未购买" : data == "1" ? "购买中" : data == "2" ?
            "暂停购买" : data == "3" ? "已购买" : data == "4" ? "卖出中" : data == "5"? "暂定卖出" :"交易结束";
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
</style>