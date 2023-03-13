<template>
  <div>
    <!--search section begin-->
    <div style="display: flex;justify-content: space-between">
      <div>
      </div>
      <div>
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

    <!-- edit dialog begin -->
    <!-- edit dialog end -->
  </div>
</template>

<script>
export default {
  name: "holdbonus",
  props: {
    flag: String,
  },
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
        {prop: "donateRate", label: "送股比例", show: true},
        {prop: "increaseRate", label: "转增比例", show: true},
        {prop: "bonusRate", label: "派息比例", show: true},
        {prop: "dateRegister", label: "股权登记日", show: true},
        {prop: "dateExRight", label: "除权日期", show: true},
        {prop: "dateExBonus", label: "除息日", show: true},
        {prop: "bonusNote", label: "分红说明", show: true},
        {prop: "bonusType", label: "分红类型", show: true},
        {prop: "dateReport", label: "报告时间", show: true},
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
      return data;
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
      let url = '/' + this.flag + '/holdbonus/?page=' + this.page + '&size=' + this.size;
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