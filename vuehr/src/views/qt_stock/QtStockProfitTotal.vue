<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="个股总盈亏" name="profitholdmana"><profithold flag="qtstock"></profithold></el-tab-pane>
      <el-tab-pane label="每日个股盈亏" name="profitmana"><profit flag="qtstock"></profit></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import profit from "@/components/stock/profit";
import profithold from "@/components/stock/profithold";

export default {
  name: "QtStockProfitTotal",
  components:{
    profit,
    profithold,
  },
  data () {
    return {
      activeName: 'profitholdmana'
    }
  },
}
</script>

<style>
</style>