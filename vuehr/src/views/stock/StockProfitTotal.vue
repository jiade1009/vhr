<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="每日总盈亏" name="profittotalmana"><profittotal flag="stock"></profittotal></el-tab-pane>
      <el-tab-pane label="每月总盈亏" name="profitmonthmana"><profitmonth flag="stock"></profitmonth></el-tab-pane>
      <el-tab-pane label="每年总盈亏" name="profityearmana"><profityear flag="stock"></profityear></el-tab-pane>
      <el-tab-pane label="每日个股盈亏" name="profitmana"><profit flag="stock"></profit></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import profit from "@/components/stock/profit";
import profittotal from "@/components/stock/profittotal";
import profitmonth from "@/components/stock/profitmonth";
import profityear from "@/components/stock/profityear";

export default {
  name: "StockProfitTotal",
  components:{
    profit,
    profittotal,
    profitmonth,
    profityear
  },
  data () {
    return {
      activeName: 'profittotalmana'
    }
  },
}
</script>

<style>
</style>