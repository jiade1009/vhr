<template>
  <div>
    <div>
      <!--search section begin-->

      <!--search section end-->
      <!--list section begin-->
      <div style="margin-top: 10px">
        <el-table
            :data="beanlist"
            row-key="id"
            stripe
            border
            :default-sort = "{prop: 'marketValue', order: 'descending'}"
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            :row-class-name="tableRowClassName"
            :cell-class-name="tableCellClassName"
            style="width: 100%">
          <el-table-column
              v-for="column in tableColumns"
              :key="column.prop"
              :prop="column.prop"
              :label="column.label"
              :formatter="columnFormat"
              align="left">
          </el-table-column>
        </el-table>
      </div>
      <!--list section end-->

      <!-- edit dialog begin -->
      <!-- edit dialog end -->
    </div>
  </div>
</template>

<script>
export default {
  name: "StockPosition",
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
        {prop: "name", label: "名称", show: true},
        {prop: "amount", label: "持股数", show: true},
        {prop: "cost", label: "持仓成本", show: true},
        {prop: "costPrice", label: "成本价", show: true},
        {prop: "profitLoss", label: "盈亏", show: true},
        {prop: "marketValue", label: "市值", show: true},
        {prop: "usable", label: "可用数", show: true},
        {prop: "currentProfitLoss", label: "当日盈亏", show: true},
        {prop: "currentIncrease", label: "当日涨幅(%)", show: true}
      ],
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
      if (row["profitLoss"]>0) {
        return 'profit-row';
      } else if (row["profitLoss"]<0) {
        return 'loss-row';
      }
      return '';
    },
    tableCellClassName({row, column, rowIndex, columnIndex}) {
      let property = column.property;
      let data = row[property];

      if (property == "currentProfitLoss" || property == "currentIncrease" ) {
        console.log(data)
        if (data>0) {
          return 'profit-cell';
        } else {
          return 'loss-cell';
        }
      }
    },
    columnFormat(row, column) {
      let property = column.property;
      return row[property];
    },
    initBeanlist() {
      this.loading = true;
      let url = '/stock/position/?';
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp.obj) {
          this.beanlist = resp.obj;
          this.total = resp.total;
        }
      });
    },
  }
}
</script>

<style>
.el-table .profit-row, .profit-cell {
  color: #F56C6C;
}

.el-table .loss-row, .loss-cell {
  color: #67C23A;
}
</style>