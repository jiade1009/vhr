<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title" @click="home">
                  纯阳系统V2.0
                </div>
                <div>
                    <el-button icon="el-icon-bell" type="text" style="margin-right: 8px;color: #000000;" size="normal" @click="goChat"></el-button>
                    <el-dropdown class="userInfo" @command="commandHandler">
  <span class="el-dropdown-link">
    {{user.name}}<i><img :src="user.userface?user.userface:userface" alt=""></i>
  </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                            <el-dropdown-item command="setting">设置</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu router unique-opened :default-active="$route.path">
                        <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i style="color: #409eff;margin-right: 5px" :class="item.iconCls"></i>
                                <span>{{item.title}}</span>
                            </template>
                            <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">
                                {{child.title}}
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
                        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$store.state.routeMap[this.$router.currentRoute.name].title}}</el-breadcrumb-item>
                    </el-breadcrumb>

                    <div v-if="this.$router.currentRoute.path=='/home'">
                      <div>
                        <p>统计日期：{{ bean.profitDay.dateResearch }}</p>
                      </div>
                      <div class="demo-block">
                        <el-row :gutter="10">
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="." :value="fundUsable" title="可用资金">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="." :value="bean.profitDay.total" title="总市值">
                                <template slot="prefix">
                                  <i class="el-icon-s-flag" style="color: red"></i>
                                </template>
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value-style="{ color: s_color_totalProfit }" :value="bean.profitDay.totalProfit" title="总浮动盈亏">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value-style="{ color: s_color_profit }" :value="bean.profitDay.profit" title="当日盈亏">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="4">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value-style="{ color: s_color_profit }" :value="bean.profitDay.profitRate*100" title="当日盈亏率">
                                <template slot="suffix"> % </template>
                              </el-statistic>
                            </div>
                          </el-col>
                      </el-row>
                      </div>
                      <!--月统计结果-->
                      <div class="demo-block">
                        <div>
                          <p>当月盈亏</p>
                        </div>
                        <el-row :gutter="10">
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="." :value="bean.profitMonth.totalBegin" title="月初始市值">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitMonth.totalEnd" title="当前市值">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitMonth.profit" title="当月盈亏">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitMonth.profitRate*100" title="当月盈亏率">
                                <template slot="suffix"> % </template>
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="4">
                            <div>
                              <el-statistic title="盈亏交易数比">
                                <template slot="formatter"> {{ bean.profitMonth.profitAmount }} / {{bean.profitMonth.lossAmount}} </template>
                              </el-statistic>
                            </div>
                          </el-col>
                        </el-row>
                      </div>
                      <!--年统计结果-->
                      <div class="demo-block">
                        <div>
                          <p>本年盈亏</p>
                        </div>
                        <el-row :gutter="10">
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="." :value="bean.profitYear.totalBegin" title="年初始市值">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitYear.totalEnd" title="当前市值">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitYear.profit" title="本年盈亏">
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="5">
                            <div>
                              <el-statistic group-separator="," :precision="2" decimal-separator="."
                                            :value="bean.profitYear.profitRate*100" title="本年盈亏率">
                                <template slot="suffix"> % </template>
                              </el-statistic>
                            </div>
                          </el-col>
                          <el-col :span="4">
                            <div>
                              <el-statistic title="盈亏交易数比">
                                <template slot="formatter"> {{ bean.profitYear.profitAmount }} / {{bean.profitYear.lossAmount}} </template>
                              </el-statistic>
                            </div>
                          </el-col>
                        </el-row>
                      </div>
                    </div>
                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    export default {
        name: "Home",
        data() {
            return {
              userface: this.$ELEMENT.userface,
                // user: JSON.parse(window.sessionStorage.getItem("user"))
              bean: {
                profitDay: {
                  dateResearch: '20200101',
                  profit: 0,
                  profitRate: 0,
                  total: 0,
                  totalProfit: 0
                },
                profitMonth: {
                  profit: 0,
                  profitRate: 0,
                  totalBegin: 0,
                  totalEnd: 0,
                  profitAmount: 0,
                  lossAmount: 0
                },
                profitYear: {
                  profit: 0,
                  profitRate: 0,
                  totalBegin: 0,
                  totalEnd: 0,
                  profitAmount: 0,
                  lossAmount: 0
                }
              },
              fundUsable: 0,
              baseCode: 'stk_fund_usable',
              s_color_totalProfit: 'red',
              s_color_profit: 'red',
              s_color_profitRate: 'red',
              size: 0,
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            },
            user() {
                return this.$store.state.currentHr;
            }
        },
        beforeMount() {
        },
        mounted() {
          this.initData();
          this.updateFundUsable();
        },
        updated() {
          // console.log("update:"+this.size);
          // this.size += 1;
        },
      methods: {
            initData() {
              this.getRequest('/stock/profittotal/latest').then(resp =>{
                if (resp) {
                  let o = resp.obj
                  if (o) { // 存在正在运行的卖出策略
                    // console.log(o)
                    // o.total = o.total.toFixed(2);
                    // o.totalProfit = o.totalProfit.toFixed(2);
                    // o.profit = o.profit.toFixed(2);
                    // o.profitRate = o.profitRate.toFixed(4);
                    if (o.profitDay) {
                      if (o.profitDay.totalProfit<0) this.s_color_totalProfit='green';
                      if (o.profitDay.profit<0) this.s_color_profit='green';
                      if (o.profitDay.profitRate<0) this.s_color_profitRate='green';
                    }
                    if (!o.profitMonth) {
                      o.profitMonth = this.bean.profitMonth
                    }
                    if (!o.profitYear) {
                      o.profitYear = this.bean.profitYear
                    }
                    this.bean = o;
                  }
                }
              });
            },
            updateFundUsable() {
              // 由于可用资金在ghk_stock系统中会更改，因此不能通过读取缓存的方式获取
              this.getRequest("/system/databasetype/getFundUsable").then(resp =>{
                if (resp) {
                  if (resp.obj) {
                    let o = resp.obj.value;
                    this.fundUsable = parseFloat(o);
                  }
                }
              });
            },
            home() {
              this.$router.push("/home");
              this.updateFundUsable();
            },
            goChat() {
                this.$router.push("/chat");
            },
            commandHandler(cmd) {
                if (cmd == 'logout') {
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user")
                        this.$store.commit('initRoutes', []);
                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作'
                        });
                    });
                }else if (cmd == 'userinfo') {
                    this.$router.push('/hrinfo');
                }
            }
        }
    }
</script>

<style>
    .homeRouterView {
        margin-top: 10px;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #409eff;
        padding-top: 50px;
    }

    .homeHeader {
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff;
        cursor: pointer;
    }

    .homeHeader .userInfo {
        cursor: pointer;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
    }
    .demo-block {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 1px solid #ebebeb;
      border-radius: 3px;
      transition: .2s;
      padding: 24px;
    }
</style>