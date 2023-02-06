<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
        </div>
      </div>
      <!--search section end-->
      <!--list section begin-->
      <div style="margin-top: 10px">
        <el-table
            :data="beanlist"
            row-key="id"
            ref="evtTable"
            stripe
            border
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            style="width: 100%">
          <!--            -->
          <!--            :row-key="getRowKey"-->
          <el-table-column
              prop="timeCreate"
              label="创建日期"
              align="left">
          </el-table-column>
          <el-table-column
              prop="timeRunBegin"
              label="运行时间"
              align="left">
          </el-table-column>
          <el-table-column
              prop="emaIndex"
              label="EMA线"
              align="left">
          </el-table-column>
          <el-table-column
              prop="runStatus"
              label="状态"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="runStatusDesc"
              label="运行结果"
              min-width="200"
              align="left">
            <template slot-scope="scope">
              <span v-html="scope.row['runStatusDesc']"></span>
            </template>
          </el-table-column>
          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button v-if="scope.row.runStatus==0" type="danger" @click="runBuyRule(scope.row)" style="padding: 3px"size="mini">运行策略
              </el-button>
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
  </div>
</template>

<script>
export default {
  name: "StockWeeklylineEmaResult",
  data() {
    return {
      //分页参数
      total: 0,
      page: 1,
      size: this.$ELEMENT.pagesize,
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
      disabled: false,
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    formatterText(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "rehabilitationWay") {
        return data == "qfq" ? "前复权" : data == "hfq" ? "后复权" : "未复权";
      } else if (property == "result") {
        return data == "0" ? "成功" : data == "1" ? "失败" : "";
      } else if (property == "emaResult") {
        return data == "0" ? "未生成" : data == "1" ? "已生成" : "生成失败";
      } else if (property == "generateType") {
        return data == "0" ? "手动生成" : "自动生成";
      } else if (property == "status") {
        return data == "0" ? "成功" : "失败";
      } else if (property == "runStatus") {
        return data == "0" ? "未运行" : data == "1" ? "运行成功" : "运行失败";
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
      let url = '/stock/weeklylineemaresult/?page=' + this.page + '&size=' + this.size;
      // url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    // 根据ema数据运行购买策略
    runBuyRule(row) {
      this.$confirm('确认采用该EMA数据运行购买策略吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest("/stock/weeklylineemaresult/runBuyRule?emaid=" + row.id).then(resp => {
          if (resp) {
            // this.initBeanlist();
            //更新ema list信息
            this.getEmaList(this.expandRow);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
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