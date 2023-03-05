<template>
  <div>
    <!--search section begin-->
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
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
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