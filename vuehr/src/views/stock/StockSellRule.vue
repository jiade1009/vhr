<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" @click="showAddBeanView" v-show="showAddbtn">
            添加卖出策略
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
          width="70%">
        <div>
          <el-form :model="bean" status-icon :rules="rules" ref="beanForm" label-width="140px">
            <el-divider content-position="left">止损规则</el-divider>
            <div style="margin-bottom: 50px;">即股票价格低于当前的EMA_18，卖出所有股票</div>

            <el-divider content-position="left">止盈规则</el-divider>
            <el-form-item label="首次止盈卖出比例:" prop="sellRatio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sellRatio"
                        placeholder="卖出比例"></el-input>
              <div>首次止盈卖出所持股票数比例，默认0.5</div>
            </el-form-item>
            <el-form-item label="止盈P1阶段比例:" prop="p1Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.p1Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P1阶段梯度价格计算公式：股票买入价*止盈P1阶段比例，止盈P1阶段比例默认值：1.15</div>
            </el-form-item>
            <el-form-item label="止盈P2阶段比例:" prop="p2Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.p2Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P2阶段梯度价格计算公式：止盈P1阶段梯度价格*止盈P2阶段比例，止盈P2阶段比例默认值：1.1</div>
            </el-form-item>
            <el-form-item label="止盈P3阶段比例:" prop="p3Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.p3Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P3阶段梯度价格计算公式：止盈P2阶段梯度价格*止盈P3阶段比例，止盈P3阶段比例默认值：1.1</div>
            </el-form-item>
            <el-form-item label="止盈P4阶段比例:" prop="p4Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.p4Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P4阶段梯度价格计算公式：止盈P3阶段梯度价格*止盈P4阶段比例，止盈P4阶段比例默认值：1.1</div>
            </el-form-item>
            <el-form-item label="止盈P5阶段比例:" prop="p5Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.p5Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P5阶段梯度价格计算公式：止盈P4阶段梯度价格*止盈P5阶段比例，止盈P5阶段比例默认值：1.1</div>
            </el-form-item>

            <el-form-item label="止盈P1阶段卖出价比例:" prop="sp1Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sp1Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P1阶段卖出价格计算公式：股票近期最高价*止盈P1阶段卖出价比例，止盈P1阶段卖出价比例默认值：0.85</div>
            </el-form-item>
            <el-form-item label="止盈P2阶段卖出价比例:" prop="sp2Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sp2Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P2阶段卖出价格计算公式：股票近期最高价*止盈P2阶段卖出价比例，止盈P2阶段卖出价比例默认值：0.87</div>
            </el-form-item>
            <el-form-item label="止盈P3阶段卖出价比例:" prop="sp3Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sp3Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P3阶段卖出价格计算公式：股票近期最高价*止盈P3阶段卖出价比例，止盈P3阶段卖出价比例默认值：0.89</div>
            </el-form-item>
            <el-form-item label="止盈P4阶段卖出价比例:" prop="sp4Ratio">
              <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sp4Ratio"
                        placeholder="请输入比例"></el-input>
              <div>止盈P4阶段卖出价格计算公式：股票近期最高价*止盈P4阶段卖出价比例，止盈P4阶段卖出价比例默认值：0.91</div>
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
  name: "StockSellRule",
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
        "sellRatio": 0.5,
        "p1Ratio": 1.15,
        "p2Ratio": 1.1,
        "p3Ratio": 1.1,
        "p4Ratio": 1.1,
        "p5Ratio": 1.1,
        "sp1Ratio": 0.85,
        "sp2Ratio": 0.87,
        "sp3Ratio": 0.89,
        "sp4Ratio": 0.91,
        "status": 0,
      },
      title: '添加卖出策略',
      dialogVisible: false,
      rules: {
        sellRatio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        p1Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        p2Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        p3Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        p4Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        p5Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        sp1Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        sp2Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        sp3Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        sp4Ratio: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
      },
      showAddbtn: false, //是否显示新增按钮
      tableColumns: [
        {prop: "status", label: "状态"},
        {prop: "sellRatio", label: "卖出比例"},
        {prop: "p1Ratio", label: "P1比例"},
        {prop: "p2Ratio", label: "P2比例"},
        {prop: "p3Ratio", label: "P3比例"},
        {prop: "p4Ratio", label: "P4比例"},
        {prop: "p5Ratio", label: "P5比例"},
        {prop: "sp1Ratio", label: "SP1比例"},
        {prop: "sp2Ratio", label: "SP2比例"},
        {prop: "sp3Ratio", label: "SP3比例"},
        {prop: "sp4Ratio", label: "SP4比例"},
        {prop: "timeCreate", label: "创建时间"},
        {prop: "timeUpdate", label: "更新时间"},
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
      }
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
    showAddBeanView() {
      this.emptyBean();
      this.title = '新增卖出策略';
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑卖出策略';
      this.bean = data;
      this.dialogVisible = true;
    },
    initBeanlist() {
      this.loading = true;
      let url = '/stock/sellrule/?page=' + this.page + '&size=' + this.size;
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
        "sellRatio": 0.5,
        "p1Ratio": 1.15,
        "p2Ratio": 1.1,
        "p3Ratio": 1.1,
        "p4Ratio": 1.1,
        "p5Ratio": 1.1,
        "sp1Ratio": 0.85,
        "sp2Ratio": 0.87,
        "sp3Ratio": 0.89,
        "sp4Ratio": 0.91,
        "status": 0,
      };
    },
    //查看是否有存在草稿的规则，如果不存在，运行新增
    getDraft() {
      this.getRequest("/stock/sellrule/getDraft").then(resp =>{
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
            this.putRequest("/stock/sellrule/", this.bean).then(resp => {
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
            this.postRequest("/stock/sellrule/", this.bean).then(resp => {
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
      this.$confirm('确认将该卖出规则投入运行中？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.getRequest("/stock/sellrule/run/" + id).then(resp => {
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