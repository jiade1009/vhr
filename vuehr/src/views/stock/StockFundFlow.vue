<template>
  <div>
    <fundflow flag="stock"></fundflow>
  </div>
</template>

<script>
import fundflow from '../../components/stock/fundflow';
export default {
  name: "StockFundFlow",
  components:{
    fundflow,
  },
  data () {
    return{
      test:"ttt"
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