<template>
  <div>
    <!--search section begin-->
    <div style="display: flex;justify-content: space-between">
      <div>
      </div>
      <div>
        <el-button type="primary" icon="el-icon-plus" @click="showAddBeanView">
          添加流水记录
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
          :row-class-name="tableRowClassName"
          :cell-class-name="tableCellClassName"
          :summary-method="getSummaries"
          show-summary
          style="width: 100%">
        <el-table-column
            type="index"
            width="50">
        </el-table-column>
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
    <el-dialog
        :title="title"
        :visible.sync="dialogVisible"
        width="50%">
      <div>
        <el-form :model="bean" status-icon :rules="rules" ref="beanForm" label-width="140px">
          <el-form-item label="金额（元）:" prop="amount">
            <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.amount"
                      placeholder="金额（元）"></el-input>
          </el-form-item>
          <el-form-item label="交易类型:" prop="tradeType">
            <el-select v-model="bean.tradeType" placeholder="交易类型" size="mini" style="width: 200px;">
              <el-option
                  v-for="item in fundtradetype"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注:" prop="note">
            <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.note"
                      placeholder="备注"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddBean">确 定</el-button>
  </span>
    </el-dialog>
    <!-- edit dialog end -->
  </div>
</template>

<script>
export default {
  name: "fundflow",
  props: {
    flag: String,
  },
  data () {
    let validNumberPass1 = (rule, value, callback) => {//包含小数的数字
      // let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
      let reg = /^(0|([1-9]\d*))(\.\d+)?$/g;
      if (value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入数字'));
      } else if (value == 0){
        callback(new Error('金额不允许为0'));
      } else {
        callback();
      }
    };
    return {
      fundtradetype: [],
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
        "amount": 0,
        "tradeType": 1,
        "incomeExpense": 1,
        "timeCreate": "",
        "note": "",
      },
      title: '添加流水记录',
      dialogVisible: false,
      rules: {
        amount: [{validator:validNumberPass1, trigger: 'blur', required: true}]
      },
      tableColumns: [
        {prop: "amount", label: "金额", show: true},
        {prop: "tradeTypeNote", label: "交易类型", show: true},
        {prop: "timeCreate", label: "创建时间", show: true},
        {prop: "note", label: "备注", show: true},
      ],
    }
  },
  mounted() {
    this.initData();
    this.initBeanlist();
  },
  methods: {
    initData() {
      if (!window.sessionStorage.getItem("fundtradetype")) {
        this.getRequest('/stock/fundtradetype').then(resp => {
          if (resp) {
            this.fundtradetype = resp;
            window.sessionStorage.setItem("fundtradetype", JSON.stringify(resp));
          }
        })
      } else {
        this.fundtradetype = JSON.parse(window.sessionStorage.getItem("fundtradetype"));
      }
    },
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
    tableRowClassName({row, rowIndex}) {
      if (row["incomeExpense"]==1) {
        return 'profit-row';
      } else {
        return 'loss-row';
      }
    },
    tableCellClassName({row, column, rowIndex, columnIndex}) {

    },
    showAddBeanView() {
      this.emptyBean();
      this.title = '新增流水记录';
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑流水记录';
      this.bean = data;
      this.dialogVisible = true;
    },
    initBeanlist() {
      this.loading = true;
      let url = '/' + this.flag + '/fundflow/?page=' + this.page + '&size=' + this.size;
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
        "amount": 0,
        "tradeType": 1,
        "incomeExpense": 1,
        "timeCreate": "",
        "note": "",
      };
    },
    doAddBean() {
      if (this.bean.id) {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.putRequest('/' + this.flag + '/fundflow/', this.bean).then(resp => {
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
            this.postRequest('/' + this.flag + '/fundflow/', this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      }
    },
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总价';
          return;
        }
        if (column.property=="amount") {
          const values = data.map(item => Number(item['amount'])*Number(item['incomeExpense']));
          // const values = data.map(function(item) {
          //   console.log(item);
          //   return Number(item['amount']);
          // })
          // console.log(values)
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          sums[index] += ' 元';
        } else {
          sums[index] = '';
        }
      });
      return sums;
    },
  }
}
</script>

<style>
</style>