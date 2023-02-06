<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>

        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" :disabled="disabled" @click="reloadWeekly($event)">
            生成A股周线
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
            ref="evtTable"
            stripe
            border
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            @row-click="viewEma"
            :row-class-name="getRowClassName"
            style="width: 100%">
          <!--            -->
          <!--            :row-key="getRowKey"-->
          <el-table-column type="expand" width="1">
            <template slot-scope="props">
              <div style="padding-left: 60px;padding-bottom: 5px;">
                <div class="ema-content">
                  <div v-if="!!props.row.emalist && props.row.emalist.length>0"
                      class="orangeShip">
                    <p style="color: #409EFF"><i class="el-icon-star-off"></i>EMA生成记录</p>
                    <el-table
                        border
                        :stripe="false"
                        :data="props.row.emalist"
                        :header-cell-style="tb_header_style"
                        style="width: 100%">
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
                          min-width="300"
                          align="left">
                        <template slot-scope="scope">
                          <span v-html="scope.row['runStatusDesc']"></span>
                        </template>
                      </el-table-column>
                      <el-table-column
                          fixed="right"
                          label="操作">
                        <template slot-scope="scope">
                          <el-button v-if="scope.row.runStatus==0" type="danger" @click="runBuyRule(scope.row)" style="padding: 3px"size="mini">运行策略
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </div>
                  <div v-else>暂无EMA生成记录</div>

                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              prop="dateWeeklyResearch"
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
              label="导入结果"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="resultDesc"
              label="备注"
              min-width="300"
              align="left">
          </el-table-column>
          <el-table-column
              prop="generateType"
              label="生成方式"
              :formatter="formatterText"
              align="left">
          </el-table-column>
          <el-table-column
              prop="timeCreate"
              label="运行开始时间"
              align="left">
          </el-table-column>
          <el-table-column
              prop="timeEnd"
              label="运行结束时间"
              align="left">
          </el-table-column>
          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button @click.stop="viewEma(scope.row)" style="padding: 3px"size="mini">查看EMA
              </el-button>
              <el-button type="success" @click.stop="generateEma(scope.row)" style="padding: 3px"size="mini">生成EMA
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
  name: "StockWeeklylineResult",
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
      expands:[],
      expandRow: [],
      disabled: false,
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    tb_header_style(){
      let s = {
        'background-color': 'rgb(160, 207, 255)',
        'color': '#fff',
      }
      return s;
    },
    getRowKey(row) {
      return 'w' + row.id
    },
    // 通过样式隐藏expand图标
    getRowClassName() {
      return 'row-expand-cover'
    },
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
    reloadWeekly(e) {
      let o = e.currentTarget;
      this.$confirm('如果时间未超过下午15点，则生成上一个交易日的周线；此操作将需要时间生成A股周线历史数据和EMA数据，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          date_start: '',
          date_research: ''
        }
        this.disabled = true;
        this.postRequest("/stock/weeklylineresult/", data).then(resp => {
          this.disabled = false
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
    viewEma(row) {
      if (!!!row.emalist) {
        this.getEmaList(row);
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
    getEmaList(row) {
      // 根据weekly_result_id，获取对应的ema结果
      let url = "/stock/weeklylineemaresult/byweekly?wid="+row.id
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          row.emalist = resp;
          this.expands = [row.id];
          this.expandRow = [row];
        }
        // that.$refs.evtTable.toggleRowExpansion(row);
      });
    },
    expandChange(row, expandedRows) {
      // expandedRows 已经展开的rows
      const isExpend = expandedRows.some(r => r.id === row.id) // 判断当前行展开状态
    },
    // 根据周线数据生成新的ema数据
    generateEma(row) {
      this.$confirm('确认用该历史行情，生成新的EMA数据吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.putRequest("/stock/weeklylineemaresult/newema?wid=" + row.id).then(resp => {
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
            this.getEmaList(this.expandRow[0]);
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

<style scoped>
.row-expand-cover .el-table__expand-icon{
  visibility: hidden!important;
}
.row-expand-cover .el-table__expand-column {
  visibility: hidden!important;
}

</style>