<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="每日总盈亏" name="profittotalmana"><profittotal flag="stock"></profittotal></el-tab-pane>
      <el-tab-pane label="每日各股盈亏" name="profitmana"><profit flag="stock"></profit></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import profit from "@/components/stock/profit";
import profittotal from "@/components/stock/profittotal";

export default {
  name: "StockProfitTotal",
  components:{
    profit,
    profittotal
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