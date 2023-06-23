<template>
  <div>
    <holdtrade flag="stock"></holdtrade>
  </div>
</template>

<script>
import holdtrade from "@/components/stock/holdtrade";
export default {
  name: "StockHoldTrade",
  components:{
    holdtrade
  },
  data () {
    return {
    }
  },
}
</script>

<style>
</style>