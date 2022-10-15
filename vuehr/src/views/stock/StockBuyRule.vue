<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" @click="showAddBeanView" v-show="showAddbtn">
            添加买入策略
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
              <el-button @click="showEditBeanView(scope.row)" style="padding: 3px" size="mini" v-if="scope.row.status==0">编辑</el-button>
              <el-button type="success" @click="toRunBean(scope.row.id)" style="padding: 3px" size="mini" v-if="scope.row.status==0">投入运行</el-button>
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

      <!-- edit dialog begin -->
      <el-dialog
          :title="title"
          :visible.sync="dialogVisible"
          width="50%">
        <div>
          <el-form :model="bean" status-icon :rules="rules" ref="beanForm" label-width="140px">
            <el-form-item label="上市时长（年）:" prop="timeMarket">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.timeMarket"
                        placeholder="请输入上市时长（年）"></el-input>
              <el-switch v-model="bean.timeMarketOption" style="margin-right: 10px"></el-switch>是否开启
            </el-form-item>
            <el-form-item label="策略周期（周）:" prop="rulePeriod">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.rulePeriod"
                        placeholder="策略周期"></el-input>
              例如计算在多少周之内，ema75>ema18
            </el-form-item>
            <el-form-item label="成交量比例:" prop="turnoverLimit">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.turnoverLimit"
                        placeholder="请输入成交量比例"></el-input>
              <el-switch v-model="bean.turnoverLimitOption" style="margin-right: 10px"></el-switch>是否开启
              <div>平均成交量/买入量>turnoverLimit</div>
            </el-form-item>
            <el-form-item label="收敛度:" prop="converLimit">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.converLimit"
                        placeholder="请输入收敛度"></el-input>
              <el-switch v-model="bean.converLimitOption" style="margin-right: 10px"></el-switch>是否开启
              <div>最大EMA75/当前ema18&lt;converLimit </div>
            </el-form-item>
            <el-form-item label="下跌幅度:" prop="shockLimit">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.shockLimit"
                        placeholder="请输入下跌幅度"></el-input>
              <el-switch v-model="bean.shockLimitOption" style="margin-right: 10px"></el-switch>是否开启
              <div>最低价/最高价<=shockLimit</div>
            </el-form-item>
            <el-input type="hidden" id="id"></el-input>
            <el-input type="hidden" id="status"></el-input>
            <el-input type="hidden" id="timeCreate"></el-input>
            <el-input type="hidden" id="timeUpdate"></el-input>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddBean">确 定</el-button>
  </span>
      </el-dialog>
      <!-- edit dialog end -->
    </div>
  </div>
</template>

<script>
export default {
  name: "StockBuyRule",
  data () {
    let validNumberPass1 = (rule, value, callback) => {//包含小数的数字
      // let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
      let reg = /^(0|([1-9]\d*))(\.\d+)?$/g;
      if (value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入数字'));
      } else {
        callback();
      }
    };
    let validNumberPass2 = (rule, value, callback) => {//正整数
      // let reg = /^[+]{0,1}(\d+)$/g;
      let reg = /^(?:[0-9]\d*)$/g;
      if (value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入0及0以上的整数'));
      } else {
        callback();
      }
    };
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
        "id": null,
        "timeMarket": 3,
        "timeMarketOption": 0,
        "rulePeriod": 52,
        "turnoverLimit": 0,
        "turnoverLimitOption": 0,
        "converLimit": 1.1,
        "converLimitOption": 0,
        "shockLimit": 0.45,
        "shockLimitOption": 0,
        "status": 0,
      },
      title: '添加买入策略',
      dialogVisible: false,
      rules: {
        timeMarket: [{validator:validNumberPass2, trigger: 'blur', required: true}],
        rulePeriod: [{validator:validNumberPass2, trigger: 'blur', required: true}],
        turnoverLimit: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
      },
      showAddbtn: false, //是否显示新增按钮
      tableColumns: [
        {prop: "status", label: "状态", show: true},
        {prop: "timeMarket", label: "上市时长/年", show: true},
        {prop: "timeMarketOption", label: "开启", show: true},
        {prop: "rulePeriod", label: "策略周期/周", show: true},
        {prop: "turnoverLimit", label: "成交量比例", show: true},
        {prop: "turnoverLimitOption", label: "开启", show: true},
        {prop: "converLimit", label: "收敛度", show: true},
        {prop: "converLimitOption", label: "开启", show: true},
        {prop: "shockLimit", label: "下跌幅度", show: true},
        {prop: "shockLimitOption", label: "开启", show: true},
        {prop: "timeCreate", label: "创建时间", show: true},
        {prop: "timeUpdate", label: "更新时间", show: true},
      ],
    }
  },
  mounted() {
    this.getDraft();
    this.initBeanlist();
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        return data == "0" ? "草稿" : data == "1" ? "运行" : "结束";
      } else if (property == "timeMarketOption" || property == "turnoverLimitOption"
          || property == "converLimitOption" || property == "shockLimitOption") {
        return data == "0" ? "否" : "是";
      } else if (property == "timeCreate" || property == "timeUpdate") {
        let dt = new Date(data)
        return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
      }
      return data;
    },
    formatterText(row, column) {
      let property = column.property;
      let data = row[property];
      if (property == "status") {
        return data == "0" ? "草稿" : data == "1" ? "运行" : "结束";
      } else if (property == "timeMarketOption" || property == "turnoverLimitOption"
          || property == "converLimitOption" || property == "shockLimitOption") {
        return data == "0" ? "否" : "是";
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
    showAddBeanView() {
      this.emptyBean();
      this.title = '新增买入策略';
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑买入策略';
      this.bean = data;
      this.dialogVisible = true;
    },
    initBeanlist() {
      this.loading = true;
      let url = '/stock/buyrule/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    emptyBean() {
      this.bean = {
        "id": null,
        "timeMarket": 3,
        "timeMarketOption": 0,
        "rulePeriod": 52,
        "turnoverLimit": 0,
        "turnoverLimitOption": 0,
        "converLimit": 1.1,
        "converLimitOption": 0,
        "shockLimit": 0.45,
        "shockLimitOption": 0,
        "status": 0,
      };
    },
    //查看是否有存在草稿的规则，如果不存在，运行新增
    getDraft() {
      this.getRequest("/stock/buyrule/getDraft").then(resp =>{
        if (resp) {
          if (resp.obj) { // 存在草稿
            this.showAddbtn = false;
          } else if (!!!resp.obj){
            this.showAddbtn = true;
          }
        }

      });
    },
    doAddBean() {
      if (this.bean.id) {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.putRequest("/stock/buyrule/", this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      } else {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.postRequest("/stock/buyrule/", this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      }
    },
    //启动该rule策略
    toRunBean(id) {
      this.$confirm('确认将该买入规则投入运行中？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.getRequest("/stock/buyrule/run/" + id).then(resp => {
          if (resp) {
            this.getDraft();
            this.initBeanlist();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消运行该规则'
        });
      });
    },
  }
}
</script>

<style scoped>

</style>