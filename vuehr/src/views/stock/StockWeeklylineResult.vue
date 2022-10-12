<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>

        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" @click="reloadWeekly">
            生成A股周线
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
            style="width: 100%">
          <el-table-column
              prop="dateWeeklyResearch"
              fixed
              label="调研日期"
              align="left">
          </el-table-column>
          <el-table-column
              prop="dateWeeklyStart"
              label="起始日期"
              align="left">
          </el-table-column>
          <el-table-column
              prop="rehabilitationWay"
              label="复权方式"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="result"
              label="周线导入结果"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="emaResult"
              label="EMA结果"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="generateType"
              label="生成方式"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="resultDesc"
              label="备注"
              align="left">
          </el-table-column>
          <el-table-column
              prop="timeCreate"
              label="运行开始时间"
              :formatter="formatterTime"
              align="left">
          </el-table-column>
          <el-table-column
              prop="timeEnd"
              label="运行结束时间"
              :formatter="formatterTime"
              align="left">
          </el-table-column>
          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button :hidden="scope.row.emaResult!=0" @click="generateEma(scope.row)" style="padding: 3px" size="mini">生成EMA</el-button>
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
  name: "StockWeeklylineResult",
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
        return data=="qfq"?"前复权":data=="hfq"?"后复权":"未复权";
      } else if (property == "result") {
        return data=="0"?"成功":data=="1"?"失败":"";
      } else if (property == "emaResult") {
        return data=="0"?"未生成":data=="1"?"已生成":"生成失败";
      } else if (property == "generateType") {
        return data=="0"?"手动生成":"自动生成";
      }
    },
    formatterTime(row, column) {
      let property = column.property;
      let data = row[property];
      if (!!data) {
        let dt = new Date(data)
        return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
      } else {
        return "";
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
      let url = '/stock/weeklylineresult/?page=' + this.page + '&size=' + this.size;
      // url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    reloadWeekly() {
      this.$confirm('此操作将需要时间生成A股周线历史数据和EMA数据，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/stock/weeklylineresult/").then(resp => {
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
  }
}
</script>

<style scoped>

</style>