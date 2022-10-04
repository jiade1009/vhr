<template>
    <div>
        <div>
          <div style="margin-top: 10px;display: flex;justify-content: space-between">
            <div>
              <el-input v-model="keywords" placeholder="通过用户名搜索用户..." prefix-icon="el-icon-search"
                        style="width: 400px;margin-right: 10px" @keydown.enter.native="doSearch"></el-input>
              <el-button icon="el-icon-search" type="primary" @click="doSearch">搜索</el-button>
            </div>
            <div>
              <el-button type="primary" icon="el-icon-plus" @click="showAddHrView()">
                添加用户
              </el-button>
            </div>
          </div>
        </div>
        <el-dialog
          :title="title"
          :visible.sync="dialogVisible"
          width="80%">
          <div>
            <el-form :model="hr" :rules="rules" ref="hrForm" label-position="right">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="账号:" prop="username">
                    <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="hr.username"
                              placeholder="请输入员工账号"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="姓名:" prop="name">
                    <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="hr.name"
                              placeholder="请输入员工姓名"></el-input>
                  </el-form-item>
                </el-col>

              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="手机:" prop="phone">
                    <el-input size="mini" style="width: 200px" prefix-icon="el-icon-phone"
                              v-model="hr.phone" placeholder="手机"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="地址:" prop="address">
                    <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit"
                              v-model="hr.address" placeholder="请输入联系地址"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="用户状态:" prop="enabled">
                    <el-switch
                        style="padding-top: 5px"
                        v-model="hr.enabled"
                        active-text="启用"
                        @change="enabledChangeInfo(hr)"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        inactive-text="禁用">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="备注:" prop="remark">
                    <el-input type="textarea" :row="2" size="mini" v-model="hr.remark"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="doAddHr">确 定</el-button>
          </span>
        </el-dialog>
        <div class="hr-container">
            <el-card class="hr-card" v-for="(hr,index) in hrs" :key="index">
                <div slot="header" class="clearfix">
                    <span>{{hr.name}}</span>
                    <el-button style="float: right; padding: 3px 3px;color: #e30007;" type="text"
                               icon="el-icon-delete" @click="deleteHr(hr)">删除</el-button>
                    <el-button style="float: right; padding: 3px 3px ;" type="primary"
                               icon="el-icon-edit" @click="showEditHrView(hr)">编辑</el-button>
                    <el-button style="float: right; padding: 3px 3px ;" type="info"
                             icon="" @click="resetPassword(hr)">重置密码</el-button>
                </div>
                <div>
                    <div class="img-container">
                        <img :src="hr.userface?hr.userface:userface" :alt="hr.name" :title="hr.name" class="userface-img">
                    </div>
                    <div class="userinfo-container">
                        <div>用户名：{{hr.name}}</div>
                        <div>手机号码：{{hr.phone}}</div>
                        <div>地址：{{hr.address}}</div>
                        <div>用户状态：
                            <el-switch
                                    v-model="hr.enabled"
                                    active-text="启用"
                                    @change="enabledChange(hr)"
                                    active-color="#13ce66"
                                    inactive-color="#ff4949"
                                    inactive-text="禁用">
                            </el-switch>
                        </div>
                        <div>用户角色：
                            <el-tag type="success" style="margin-right: 4px" v-for="(role,indexj) in hr.roles"
                                    :key="indexj">{{role.nameZh}}
                            </el-tag>
                            <el-popover
                                    placement="right"
                                    title="角色列表"
                                    @show="showPop(hr)"
                                    @hide="hidePop(hr)"
                                    width="200"
                                    trigger="click">
                                <el-select v-model="selectedRoles" multiple placeholder="请选择">
                                    <el-option
                                            v-for="(r,indexk) in allroles"
                                            :key="indexk"
                                            :label="r.nameZh"
                                            :value="r.id">
                                    </el-option>
                                </el-select>
                                <el-button slot="reference" icon="el-icon-more" type="text"></el-button>
                            </el-popover>
                        </div>
                        <div>备注：{{hr.remark}}</div>
                    </div>
                </div>
            </el-card>

        </div>
    </div>
</template>

<script>
    import {Message} from "element-ui";

    export default {
        name: "SysHr",
        data() {
            let validatePass = (rule, value, callback) => {
              if (value === '') {
                callback(new Error('请输入密码'));
              } else {
                if (this.hrForm.checkPass !== '') {
                  this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
              }
            };
            let validatePass2 = (rule, value, callback) => {
              if (value === '') {
                callback(new Error('请再次输入密码'));
              } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
              } else {
                callback();
              }
            };
            return {
                userface: this.$ELEMENT.userface,
                keywords: '',
                hrs: [],
                selectedRoles: [],
                allroles: [],
                dialogVisible: false,
                title:'',
                hr: {
                  id: null,
                  username: '',
                  name: '',
                  phone: '',
                  address: '',
                  enabled: true,
                  remark:''
                },
                rules: {
                  username: [{required: true, message: '请输入用户账号', trigger: 'blur'}],
                  name: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                  phone: [{required: true, message: '请输入手机', trigger: 'blur'}],
                  address: [{required: false, message: '请输入输入地址', trigger: 'blur'}],
                  remark: [{required: false, message: '请输入备注信息', trigger: 'blur'}],
                }
            }
        },
        mounted() {
            this.initHrs();
        },
        methods: {
            deleteHr(hr) {
                this.$confirm('此操作将永久删除【'+hr.name+'】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/hr/"+hr.id).then(resp=>{
                        if (resp) {
                            this.initHrs();
                        }
                    })
                }).catch(() => {
                    Message.info({message: '已取消删除'});
                });
            },
            resetPassword(hr) {
              this.$confirm('重置【'+hr.name+'】的密码为：ghk, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.putRequest("/system/hr/reset/"+hr.id).then(resp=>{
                  if (resp) {
                    this.initHrs();
                  }
                })
              }).catch(() => {
                Message.info({message: '已取消重置密码', duration: 500});
              });
            },
            editHr(hr) {

            },
            showEditHrView(data) {
              this.emptyHr();
              this.title = '编辑操作员信息';
              this.hr = data;
              this.dialogVisible = true;
            },
            showAddHrView() {
              this.emptyHr();
              this.title = '添加操作员';
              this.dialogVisible = true;
            },
            emptyHr() {
                this.hr = {
                  id: null,
                  username: '',
                  name: '',
                  phone: '',
                  address: '',
                  enabled: true,
                  remark:''
                }
            },
            doAddHr() {
              if (this.hr.id) { //update
                this.$refs['hrForm'].validate(valid => {
                  if (valid) {
                    this.putRequest("/system/hr/", this.hr).then(resp => {
                      if (resp) {
                        this.dialogVisible = false;
                        this.initHrs();
                      }
                    })
                  }
                });
              } else { //insert
                this.$refs['hrForm'].validate(valid => {
                  if (valid) {
                    this.postRequest("/system/hr/", this.hr).then(resp => {
                      if (resp) {
                        this.dialogVisible = false;
                        this.initHrs();
                      }
                    })
                  }
                });
              }
            },
            doSearch() {
                this.initHrs();
            },
            hidePop(hr) {
                let roles = [];
                Object.assign(roles, hr.roles);
                let flag = false;
                if (roles.length != this.selectedRoles.length) {
                    flag = true;
                } else {
                    for (let i = 0; i < roles.length; i++) {
                        let role = roles[i];
                        for (let j = 0; j < this.selectedRoles.length; j++) {
                            let sr = this.selectedRoles[j];
                            if (role.id == sr) {
                                roles.splice(i, 1);
                                i--;
                                break;
                            }
                        }
                    }
                    if (roles.length != 0) {
                        flag = true;
                    }
                }
                if (flag) {
                    let url = '/system/hr/role?hrid=' + hr.id;
                    this.selectedRoles.forEach(sr => {
                        url += '&rids=' + sr;
                    });
                    this.putRequest(url).then(resp => {
                        if (resp) {
                            this.initHrs();
                        }
                    });
                }
            },
            showPop(hr) {
                this.initAllRoles();
                let roles = hr.roles;
                this.selectedRoles = [];
                roles.forEach(r => {
                    this.selectedRoles.push(r.id);
                })
            },
            //编辑用户资料时候，更改用户状态
            enabledChangeInfo(hr) {
            },
            enabledChange(hr) {
                delete hr.roles;
                this.putRequest("/system/hr/", hr).then(resp => {
                    if (resp) {
                        this.initHrs();
                    }
                })
            },
            initAllRoles() {
                this.getRequest("/system/hr/roles").then(resp => {
                    if (resp) {
                        this.allroles = resp;
                    }
                })
            },
            initHrs() {
                this.getRequest("/system/hr/?keywords="+this.keywords).then(resp => {
                    if (resp) {
                        this.hrs = resp;
                    }
                })
            }
        }
    }
</script>

<style>
    .userinfo-container div {
        font-size: 12px;
        color: #409eff;
    }

    .userinfo-container {
        margin-top: 20px;
    }

    .img-container {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .userface-img {
        width: 72px;
        height: 72px;
        border-radius: 72px;
    }

    .hr-container {
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .hr-card {
        width: 350px;
        margin-bottom: 20px;
    }
</style>