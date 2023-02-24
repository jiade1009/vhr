<template>
  <div>
    <!--search section begin-->
    <div style="display: flex;justify-content: space-between">
      <div>
        <el-input placeholder="请输入日期进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
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
  name: "tradedate",
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
        "tradeDate": "",
      },
      tableColumns: [
        {prop: "tradeDate", label: "交易日期", show: true},
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
      let url = '/' + this.flag + '/tradedate/?page=' + this.page + '&size=' + this.size;
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
</style>