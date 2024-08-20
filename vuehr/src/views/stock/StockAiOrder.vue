<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入股票或代码进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initBeanlist"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-select v-model="status" placeholder="状态" size="mini"
                     style="width: 120px;margin-right: 10px"
                     @change="initBeanlist">
            <el-option
                label="--- 状态 ---"
                value="">
            </el-option>
            <el-option
                label="监控中"
                value="1">
            </el-option>
            <el-option
                label="已结束"
                value="0">
            </el-option>
          </el-select>
          <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" @click="showImportView">
            更新智能订单
          </el-button>
        </div>
      </div>
      <!--search section end-->
      <!--list section begin-->
      <div style="margin-top: 10px">
        <el-table
            :data="beanlist"
            row-key="id"
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
              :width="column.width"
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
          <el-row style="margin-top: 10px">
            <el-col :span="10">
              AI订单来源:
              <el-select v-model="selected" placeholder="AI订单来源" size="mini"
                         style="width: 130px;">
                <el-option
                    v-for="item in orderSources"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row style="margin-top: 10px">
            <el-col :span="10">
              <el-upload
                  :show-file-list="false"
                  :before-upload="beforeUpload"
                  :on-success="onSuccess"
                  :on-error="onError"
                  :disabled="importDataDisabled"
                  style="display: inline-flex;margin-right: 8px"
                  :action="uploadUrl">
                <el-button :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon">
                  导入文件
                </el-button>
              </el-upload>
            </el-col>
          </el-row>
        </div>
      </el-dialog>
      <!-- edit dialog end -->
    </div>
  </div>
</template>

<script>
export default {
  name: "StockAiOrder",
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
        {prop: "name", label: "名称", width: 0},
        {prop: "code", label: "代码", width: 0},
        {prop: "timeCreate", label: "创建时间", width: 0},
        {prop: "statusNote", label: "状态", width: 0},
        {prop: "processNote", label: "进度", width: 0},
        {prop: "triggerCondition", label: "触发条件"},
        {prop: "priceEntrust", label: "委托价格", width: 0},
        {prop: "amountEntrust", label: "委托数量", width: 0},
        {prop: "autoEntrust", label: "自动委托", width: 0},
        {prop: "dateBegin", label: "开始日期", width: 0},
        {prop: "dateExpire", label: "截止日期", width: 0},
        {prop: "orderNo", label: "订单号", width: 0},
        {prop: "sourceNote", label: "来源备注", width: 0},
        {prop: "note", label: "备注", width: 0},
        {prop: "orderSource", label: "订单来源", width: 0},
      ],
      title: '导入智能订单',
      dialogVisible: false,
      importDataBtnText: '导入数据',
      importDataBtnIcon: 'el-icon-upload2',
      importDataDisabled: false,
      orderSources: ["国泰君安"],  //AI订单来源集合
      selected: '国泰君安',
      status: '',
    }
  },
  computed: {
    uploadUrl() {
      let baseUrl = '/stock/aiorder/import';
      // 根据条件返回不同的上传 URL
      if (this.selected=='国泰君安') {
        // let params = Object.keys(this.customParams)
        //     .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(this.customParams[k])}`)
        //     .join('&');
        let params = "orderSource=" + encodeURIComponent(this.selected);
        console.log(params)
        return `${baseUrl}?${params}`;
      } else {
        return `${baseUrl}`;
      }
    },
  },
  mounted() {
    this.initBeanlist();
    this.initData()
  },
  methods: {
    initData() {
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
      let url = '/stock/aiorder/?page=' + this.page + '&size=' + this.size;
      url += "&keyword=" + this.keyword + "&status=" + this.status;
      this.getRequest(url).then(resp => {
        this.loading = false;
        if (resp) {
          this.beanlist = resp.data;
          this.total = resp.total;
        }
      });
    },
    showImportView() {
      this.dialogVisible = true;
    },
    onError(err, file, fileList) {
      this.importDataBtnText = '导入数据';
      this.importDataBtnIcon = 'el-icon-upload2';
      this.importDataDisabled = false;
    },
    onSuccess(response, file, fileList) {
      this.importDataBtnText = '导入数据';
      this.importDataBtnIcon = 'el-icon-upload2';
      this.importDataDisabled = false;
      if (response.status == 200) {
        this.dialogVisible = false;
        this.$message({
          type: 'info',
          message: '导入成功'
        });
        this.initBeanlist();
      } else {
        this.$message({
          type: 'error',
          message: response.msg
        });
      }
      console.log(response);

    },
    beforeUpload() {
      this.importDataBtnText = '正在导入';
      this.importDataBtnIcon = 'el-icon-loading';
      this.importDataDisabled = true;
    },
  }
}
</script>

<style>
</style>