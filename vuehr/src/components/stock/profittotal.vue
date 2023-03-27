<template>
  <div>
    <!--search section begin-->
    <div style="display: flex;justify-content: space-between">
      <div>
        选择日期:
        <el-date-picker
            v-model="searchValue.beginDateScope"
            type="daterange"
            unlink-panels
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
        <el-button icon="el-icon-search" type="primary" @click="initBeanlist" style="margin-left: 20px">
          搜索
        </el-button>
      </div>
    </div>
    <!--search section end-->
    <!--list section begin-->
    <div style="margin-top: 10px">
      <el-table
          :data="beanlist"
          stripe
          border
          v-loading="loading"
          element-loading-text="正在加载..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          :row-class-name="tableRowClassName"
          :cell-class-name="tableCellClassName"
          :summary-method="getSummaries"
          show-summary
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
      <div style="display: flex;justify-content: flex-end">
        <el-pagination
            background
            @current-change="currentChange"
            @size-change="sizeChange"
            layout="sizes, prev, pager, next, jumper, ->, total, slot"
            :page-size="size"
            :total="total">
        </el-pagination>
      </div>
    </div>
    <!--list section end-->

    <!-- edit dialog begin -->
    <!-- edit dialog end -->
  </div>
</template>

<script>
export default {
  name: "profittotal",
  props: {
    flag: String,
  },
  computed: {
  },
  data() {
    return {
      searchValue: {
        beginDateScope: null
      },
      //分页参数
      total: 0,
      page: 1,
      size: 50,
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
      tableColumns: [
        {prop: "dateResearch", label: "日期", show: true},
        {prop: "total", label: "总市值", show: true},
        {prop: "profit", label: "当日盈亏", show: true},
        {prop: "profitRate", label: "当日盈亏率", show: true}
      ],
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "profitRate") {
        return (row["profitRate"]*100).toFixed(2) + '%';
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
    tableCellClassName({row, column, rowIndex, columnIndex}) {

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
      let url = '/' + this.flag + '/profittotal/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      if (this.searchValue.beginDateScope) {
        url += '&beginDateScope=' + this.searchValue.beginDateScope;
      }
      console.log(url);
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
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
              let tmp = prev + curr;
              tmp = Number(tmp.toFixed(2));
              return tmp;
            } else {
              return prev;
            }
          }, 0);
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
  }
}
</script>

<style>
</style>