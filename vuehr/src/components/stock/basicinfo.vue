<template>
  <div>
    <!--search section begin-->
    <div style="display: flex;justify-content: space-between">
      <div>
        <el-input placeholder="请输入股票代码或名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                  clearable
                  @clear="initBeanlist"
                  style="width: 350px;margin-right: 10px" v-model="keyword"
                  @keydown.enter.native="initBeanlist"></el-input>
        <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
          搜索
        </el-button>
      </div>
      <!--        <div>-->
      <!--          <el-button type="primary" icon="el-icon-plus" @click="updateBean">-->
      <!--            重新更新股票信息-->
      <!--          </el-button>-->
      <!--          <el-button type="primary" icon="el-icon-refresh" @click="updateCalendar">-->
      <!--            刷新股票交易日历-->
      <!--          </el-button>-->
      <!--        </div>-->
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
            v-for="column in tableColumns"
            :key="column.prop"
            :prop="column.prop"
            :label="column.label"
            align="left">
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
  name: "basicinfo",
  props: {
    flag: String,
  },
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
      //新增参数
      bean: {
        "code": "",
        "name": "",
        "datePublish": "",
      },
      tableColumns: [
        {prop: "name", label: "名称", show: true},
        {prop: "code", label: "代码", show: true},
        {prop: "datePublish", label: "上市日期", show: true},
      ],
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
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
      let url = '/' + this.flag + '/basicinfo/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    updateBean() {
      this.$confirm('此操作将删除现有股票代码，并重新下载最新的股票代码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/stock/basicinfo/").then(resp => {
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
    updateCalendar() {
      this.$confirm('此操作将重新下载最新的股票交易日历, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.postRequest("/stock/basicinfo/calendar/").then(resp => {
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消更新'
        });
      });
    },
  }
}
</script>

<style>
</style>