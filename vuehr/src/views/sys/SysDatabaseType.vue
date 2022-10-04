<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入基础数据进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                    clearable
                    @clear="initBeanlist"
                    style="width: 350px;margin-right: 10px" v-model="keyword"
                    @keydown.enter.native="initBeanlist"></el-input>
          <el-button icon="el-icon-search" type="primary" @click="initBeanlist">
            搜索
          </el-button>
        </div>
        <div>
          <el-button type="primary" icon="el-icon-plus" @click="showAddBeanView">
            添加基础数据
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
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              prop="name"
              fixed
              align="left"
              label="名称">
          </el-table-column>
          <el-table-column
              prop="code"
              label="编码"
              align="left">
          </el-table-column>
          <el-table-column
              prop="value"
              label="内容"
              align="left">
          </el-table-column>
          <el-table-column
              prop="sortOrder"
              width="95"
              align="left"
              label="顺序">
          </el-table-column>
          <el-table-column
              prop="type"
              align="left"
              label="类别">
          </el-table-column>

          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button @click="showEditBeanView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
              <el-button @click="deleteBean(scope.row)" style="padding: 3px" size="mini" type="danger">删除
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

      <!-- edit dialog begin -->
      <el-dialog
          :title="title"
          :visible.sync="dialogVisible"
          width="30%">
        <div>
          <el-form :model="bean" status-icon :rules="rules" ref="beanForm" label-position="right">
            <el-row>
              <el-col :span="24">
                <el-form-item label="名称:" prop="name">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.name"
                            placeholder="请输入名称"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="编码:" prop="code">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.code"
                            placeholder="请输入编码"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="内容:" prop="value">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.value"
                            placeholder="请输入内容"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="顺序:" prop="sortOrder">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.sortOrder"
                            placeholder="请输入排序序号（升序）"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="类型:" prop="type">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.type"
                            placeholder="请输入类型"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="备注:" prop="description">
                  <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.description" type="textarea" rows="3"
                            placeholder="请输入备注内容"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
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
  name: "SysDatabaseType",
  data () {
    let checkUniqueName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('名称不能为空'));
      } else {
        return this.checkUniqueByType(0, value, callback);
      }
    };
    let checkUniqueCode = (rule, value, callback) => {
      if (!value) {
        callback(new Error('编码不能为空'));
      } else {
        return this.checkUniqueByType(1, value, callback);
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
        "code": "",
        "name": "",
        "value": "",
        "description": "",
        "sortOrder": 0,
        "type": "",
      },
      title: '添加基础数据',
      dialogVisible: false,
      rules: {
        name: [{validator:checkUniqueName, trigger: 'blur', required: true}],
        code: [{validator:checkUniqueCode, trigger: 'blur', required: true}],
        value: [{required: true, message: '请输入内容', trigger: 'blur'}]
      },
    }
  },
  mounted() {
    this.initBeanlist();
  },
  methods: {
    //校验是否是唯一性，type=0，为name校验，type=1，为code校验
    checkUniqueByType(type, value, callback) {
      let url = '/system/databasetype/checkUnique?value=' + value + '&type=' + type + '&id=' + this.bean.id;
      this.getRequest(url).then(resp => {
        if (resp) {
          return callback();
        } else {
          let msg = type==0?"名称不能重复":"编码不能重复";
          return callback(new Error(msg));
        }
      });
    },
    showAddBeanView() {
      this.emptyBean();
      this.title = '添加基础数据';
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑基础数据';
      this.bean = data;
      this.dialogVisible = true;
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
      let url = '/system/databasetype/?page=' + this.page + '&size=' + this.size;
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
        "code": "",
        "name": "",
        "value": "",
        "description": "",
        "sortOrder": 0,
        "type": "",
      };
    },
    deleteBean(data) {
      this.$confirm('此操作将永久删除【' + data.name + '】, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/system/databasetype/" + data.id).then(resp => {
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
    doAddBean() {
      if (this.bean.id) {
        this.$refs['beanForm'].validate(valid => {
          if (valid) {
            this.putRequest("/system/databasetype/", this.bean).then(resp => {
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
            this.postRequest("/system/databasetype/", this.bean).then(resp => {
              if (resp) {
                this.dialogVisible = false;
                this.initBeanlist();
              }
            })
          }
        });
      }
    },
  }
}
</script>

<style scoped>

</style>