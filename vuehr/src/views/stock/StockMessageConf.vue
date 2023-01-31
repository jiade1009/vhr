<template>
  <div>
    <div>
      <!--search section begin-->
      <div style="display: flex;justify-content: space-between">
        <div>
          <el-input placeholder="请输入员工名进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
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
            新增
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
              prop="employee"
              fixed
              align="left"
              :formatter="columnFormat"
              label="员工">
          </el-table-column>
          <el-table-column
              prop="messageType"
              align="left"
              label="接收的消息">
            <template slot-scope="scope">
              <el-tag v-for="(item, i) in scope.row.messageType" v-if="item=='1'" type="success"
                      class="msg_tag" disable-transitions>{{msg_type_label[i]}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="sendType"
              align="left"
              label="接收方式">
            <template slot-scope="scope">
              <el-tag v-for="(item, i) in scope.row.sendType" v-if="item=='1'" type="primary"
                      class="send_tag" disable-transitions>{{send_type_label[i]}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="status"
              align="left"
              :formatter="columnFormat"
              label="是否生效">
          </el-table-column>
          <el-table-column
              fixed="right"
              width="200"
              label="操作">
            <template slot-scope="scope">
              <el-button type="info" @click="showEditBeanView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
              <el-button type="warning" @click="changeStatus(scope.row)" style="padding: 3px" size="mini">{{scope.row.status?'停止':'激活'}}</el-button>
              <el-button type="danger" @click="deleteBean(scope.row)" style="padding: 3px" size="mini">删除</el-button>
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
          <el-form :model="bean" status-icon ref="beanForm" label-width="140px">
            <el-form-item label="员工" v-if="action=='insert'">
              <el-select
                  v-model="empids"
                  multiple
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请输入员工名字"
                  :remote-method="getEmployees"
                  :loading="loading">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="接收消息类型:">
              <el-checkbox-group v-model="msg_type_checked">
                <el-checkbox v-for="item in msg_type_checkbox" :key="item.label" :label="item.label">{{item.name}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="接收消息方式:">
              <el-checkbox-group v-model="send_type_checked">
                <el-checkbox v-for="item in send_type_checkbox" :label="item.label" :key="item.label">{{item.name}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="状态:">
              <el-switch v-model="bean.status" style="margin-right: 10px"></el-switch>是否生效
            </el-form-item>
            <el-input type="hidden" id="empid"></el-input>
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
  name: "StockMessageConf",
  data () {
    return {
      //分页参数
      total: 0,
      page: 1,
      size: this.$ELEMENT.pagesize,
      msg_type_label: ["发现信号", "买入通知", '卖出通知'],
      send_type_label: ["短信", "邮件", '微信'],
      //列表参数
      keyword: '',
      loading: false,
      beanlist: [],
      //新增参数
      bean: {
        "empids": [],
        "empid": null,
        "messageType": "",
        "sendType": "",
        "status": true,
      },
      msg_type_checked: [],
      msg_type_checkbox: [
      ],
      send_type_checked: [],
      send_type_checkbox: [
      ],
      action: 'insert',
      title: '新增',
      dialogVisible: false,
      options: [],
      empids: [],
    }
  },
  mounted() {
    this.loadMessageConfType();
    this.initBeanlist();
  },
  methods: {
    columnFormat(row, column) {
      let property = column.property;
      let data = row[property];
      if (property=="employee"){
        return data['name'];
      } else if (property=="status"){
        return data?"生效":"未生效"
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
      this.title = '新增消息配置';
      this.action = "insert";
      this.dialogVisible = true;
    },
    showEditBeanView(data) {
      this.title = '编辑消息配置';
      this.action = "edit";
      this.emptyBean();
      this.bean = data;
      //将messageType、sendType数据转换
      let msg_type = data.messageType;
      let send_type = data.sendType;
      if (!!msg_type) {
        for (let i=0; i<msg_type.length; i++) {
          if(msg_type.charAt(i)==1) this.msg_type_checked[this.msg_type_checked.length] = i+""
        }
      }
      if (!!send_type) {
        for (let i=0; i<send_type.length; i++) {
          if(send_type.charAt(i)==1) this.send_type_checked[this.send_type_checked.length] = i+""
        }
      }
      this.dialogVisible = true;
    },
    changeStatus(data) {
      this.$confirm('改变【' + data.employee.name + '】接收消息的有效性, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let vo = {
          'empid': data.empid,
          'status': !data.status,
        }
        this.putRequest("/stock/messageconf/", vo).then(resp => {
          if (resp) {
            this.initBeanlist();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    },
    deleteBean(data) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/stock/messageconf/" + data.empid).then(resp => {
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
    loadMessageConfType() {
      let url = '/stock/messageconf/type';

      this.getRequest(url).then(resp => {
        console.log(resp);
        if (resp) {
          let msg_type = resp.obj.msg_type;
          let send_type = resp.obj.send_type;
          this.msg_type_label = msg_type;
          this.send_type_label = send_type;
          for (let i = 0; i < msg_type.length; i++) {
            this.msg_type_checkbox[i] = {
              'name': msg_type[i],
              "label": i+""
            }
          }
          for (let i = 0; i < send_type.length; i++) {
            this.send_type_checkbox[i] = {
              'name': send_type[i],
              "label": i+""
            }
          }

        }
      });
    },
    initBeanlist() {
      this.loading = true;
      let url = '/stock/messageconf/?page=' + this.page + '&size=' + this.size;
      if (!!this.keyword) {
        url += "&employee.name=" + this.keyword;
      }

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
        "empids": [],
        "empid": null,
        "messageType": "",
        "sendType": "",
        "status": true,
      };
      this.msg_type_checked = [];
      this.send_type_checked = [];
      this.options = [];
      this.empids = [];
    },
    doAddBean() {
      let msg_checked = this.msg_type_checked;
      let msgType = ['0','0','0'];
      if (!!msg_checked) {
        msg_checked = msg_checked.sort((x,y)=>x-y)
        for (let key in msg_checked) {
          msgType[parseInt(msg_checked[key])]='1';
        }
      }
      let msgType_str = msgType.join('');

      let send_checked = this.send_type_checked;
      let sendType = ['0','0','0'];
      if (!!send_checked) {
        send_checked = send_checked.sort((x,y)=>x-y)
        for (let key in send_checked) {
          sendType[parseInt(send_checked[key])]='1';
        }
      }
      let sendType_str = sendType.join('');

      if (this.bean.empid) {
        let data = {
          "empid": this.bean.empid,
          "messageType": msgType_str,
          "sendType": sendType_str,
          "status": this.bean.status
        };
        //更新
        this.putRequest("/stock/messageconf/", data).then(resp => {
          if (resp) {
            this.dialogVisible = false;
            this.initBeanlist();
          }
        })
      } else {
        if (this.empids.length>0) {
          this.bean.empids = this.empids;
          this.bean.messageType = msgType_str;
          this.bean.sendType = sendType_str;
          this.postRequest("/stock/messageconf/", this.bean).then(resp => {
            if (resp) {
              this.dialogVisible = false;
              this.initBeanlist();
            }
          })
        }
      }
    },
    getEmployees(query) {
      if (query !== '') {
        let url = "/employee/basic/list?name=" + query;
        this.getRequest(url).then(resp => {
          if (resp && resp.total>0) {
            this.options = resp.data.map(item => {
              return { value: `${item.id}`, label: `${item.name}` };
            });
          }
        });
      } else {
        this.options = [];
      }
    },
  }
}
</script>

<style>
.el-tag+.el-tag {
  margin-left: 5px;
}
.msg_tag, send_tag {
  margin-bottom:2px;
}
</style>