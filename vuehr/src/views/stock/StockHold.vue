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
              :formatter="columnFormat"
              align="left">
          </el-table-column>
          <el-table-column
              fixed="right"
              width="120"
              label="操作">
            <template slot-scope="scope">
              <el-button type="info" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==1">恢复购买</el-button>
              <el-button type="warning" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==2">暂停交易</el-button>
              <el-button type="success" @click="" style="padding: 3px" size="mini" v-if="scope.row.status==3">恢复交易</el-button>
              <el-button type="danger" @click="" style="padding: 3px" size="mini" v-if="scope.row.status!=4">关闭交易</el-button>
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
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        return data == "0" ? "未购买" : data == "1" ? "暂停购买" : data == "2" ?
            "交易中" : data == "3" ? "暂停交易" : "交易结束";
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
  }
}
</script>

<style scoped>

</style>