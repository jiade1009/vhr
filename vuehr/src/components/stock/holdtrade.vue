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
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
            搜索
          </el-button>
        </div>
      </div>
    </div>
    <!--search section end-->
    <!--list section begin-->
    <div style="margin-top: 10px">
      <el-alert
          title='备注：交易类型为"分红配送"的，其中"委托数量/成交数量"对应"配送股票数"，"手续费"对应"派息"'
          type="info"
          show-icon
          :closable="false"
      >
      </el-alert>
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
            prop="code"
            label="代码"
            align="left">
        </el-table-column>
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
            :formatter="columnFormat"
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
            :formatter="columnFormat"
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

        <el-table-column
            fixed="right"
            width="100"
            label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click.stop="viewHold(scope.row)" style="padding: 3px" size="mini">查看股票</el-button>
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
  </div>
</template>

<script>
export default {
  name: "holdtrade",
  props: {
    flag: String,
  },
  computed: {
  },
  data() {
    return {
      //分页参数
      total: 0,
      page: 1,
      size: 50,
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
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
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initBeanlist('advanced');
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initBeanlist('advanced');
    },
    initBeanlist(type) {
      this.loading = true;
      let url = '/' + this.flag + '/holdtrade/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    viewHold(row) {
      let name = 'StockHold'
      let params = {'code': row.code}
      this.$router.push({name: name, params: params});
    },
  }
}
</script>

<style>
</style>