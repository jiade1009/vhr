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
        <div>
          <el-button type="success" @click="exportData" icon="el-icon-download">
            导出数据
          </el-button>
        </div>
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
          style="width: 100%">
        <el-table-column
            v-for="column in tableColumns"
            :key="column.prop"
            :prop="column.prop"
            :label="column.label"
            :formatter="columnFormat"
            :sortable="column.sortable"
            align="left">
          <template slot-scope="scope">
            <div v-if="column.prop == 'code'">
              <span v-html="xbFormatter(scope.row.code, scope.column.property)"></span>
              <!--              //将表格数据格式化后，再用 template + v-html 展示出来-->
            </div>
            <div v-else-if="column.prop == 'stockBasicInfo'">
              {{ scope.row['stockBasicInfo'].name }}
            </div>
            <div v-else-if="column.prop == 'stockQtHold'">
              {{ scope.row['stockQtHold'].generateType==0?"直接":"回头草" }}
            </div>
            <div v-else>
              {{ scope.row[scope.column.property] }}
            </div>
          </template>
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
  name: "profithold",
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
        {prop: "code", label: "代码", show: true},
        {prop: "stockBasicInfo", label: "名称", show: true},
        {prop: "totalBegin", label: "初始市值", show: true},
        {prop: "profit", label: "盈亏", show: true, sortable: true},
        {prop: "profitRate", label: "盈亏率", show: true},
        {prop: "holdDays", label: "持股天数", show: true},
        {prop: "timeBuy", label: "买入时间", show: true},
        {prop: "timeSell", label: "卖出时间", show: true, sortable: true},
        {prop: "stockQtHold", label: "加入方式", show: true, sortable: true}
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
      } else if(property == "stockBasicInfo") {
        return data['name'];
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
    getSummaries(param) {
      /*const { columns, data } = param;
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
      return sums;*/
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
      let url = '/' + this.flag + '/profithold/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          console.log(resp)
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    xbFormatter(value, row) {
      let url = this.$utils.getEastMoneyUrl(value);
      if (url == value) {
        return value
      } else {
        return "<a href='" + url + "' target='_blank'>" + value + "</a>"
      }
    },
    exportData() {
      let url = '/' + this.flag + '/profithold/export';
      window.open(url, '_parent');
    },
  }
}
</script>

<style>
</style>