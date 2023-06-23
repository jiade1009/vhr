<template>
  <div>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="代码管理" name="stockmana"><basicinfo flag="ustock"></basicinfo></el-tab-pane>
      <el-tab-pane label="交易日查看" name="datemana"><tradedate flag="ustock"></tradedate></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import basicinfo from '../../components/stock/basicinfo';
import tradedate from '../../components/stock/tradedate';
export default {
  name: "UStockBasicinfo",
  components:{
    basicinfo,
    tradedate
  },
  data () {
    return{
      activeName: 'stockmana'
    }
  },
  mounted() {
  },
  methods: {
  }
}
</script>

<style scoped>

</style>