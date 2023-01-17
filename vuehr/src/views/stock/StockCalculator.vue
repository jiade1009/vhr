<template>
  <div>
    <div>
      <el-form :model="bean" status-icon :rules="rules" ref="beanForm" label-width="140px">
        <div class="margin-tb-md"></div>
        <el-form-item label="买入价:" prop="buyPrice" style="width: 400px">
          <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.buyPrice"
                    placeholder="买入价" @blur="doCalculator">
            <el-button slot="append" @click="doCalculator">开始计算</el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="近期最高价:" style="width: 400px" prop="priceHigh">
          <el-input size="mini" prefix-icon="el-icon-edit" v-model="bean.priceHigh"
                    placeholder="近期最高价" @blur="doCalculator">
          </el-input>
        </el-form-item>
        <el-form-item label="持有数:">
          <div>{{bean.holdAmount}}</div>
        </el-form-item>
        <div class="margin-tb-md"></div>
        <el-divider content-position="left">止盈阶段股票交易</el-divider>
        <el-form-item label="当前所处止盈阶段:">
          <div>{{bean.sellStage}}</div>
        </el-form-item>
        <el-form-item label="P1阶段价格:" prop="p1Ratio">
          <div>{{bean.p1Price}}</div>
          <div class="sub-text-c">[P1阶段梯度价格计算公式：股票买入价*P1阶段比例，P1阶段比例：{{sellRule.p1Ratio}}]</div>
        </el-form-item>
        <el-form-item label="P2阶段价格:">
          <div>{{bean.p2Price}}</div>
          <div class="sub-text-c">[P2阶段梯度价格计算公式：P1阶段价格*P2阶段比例，P2阶段比例：{{ sellRule.p2Ratio }}]</div>
        </el-form-item>
        <el-form-item label="P3阶段价格:" prop="p3Ratio">
          <div>{{bean.p3Price}}</div>
          <div class="sub-text-c">[P3阶段梯度价格计算公式：P2阶段梯度价格*P3阶段比例，P3阶段比例：{{ sellRule.p3Ratio }}]</div>
        </el-form-item>
        <el-form-item label="P4阶段价格:" prop="p4Ratio">
          <div>{{bean.p4Price}}</div>
          <div class="sub-text-c">[P4阶段梯度价格计算公式：P3阶段梯度价格*P4阶段比例，P4阶段比例：{{ sellRule.p4Ratio }}]</div>
        </el-form-item>
        <el-form-item label="P5阶段价格:" prop="p5Ratio">
          <div>{{bean.p5Price}}</div>
          <div class="sub-text-c">[P5阶段梯度价格计算公式：P4阶段梯度价格*P5阶段比例，P5阶段比例：{{ sellRule.p5Ratio }}]</div>
        </el-form-item>

        <el-form-item label="当前卖出价格:">
          <div>{{bean.sellPrice}}</div>
          <div class="sub-text-c">[当前卖出价格计算公式：股票近期最高价*P{{bean.sellStage}}阶段卖出价比例，
            P{{bean.sellStage}}阶段卖出价比例：{{ bean.spRatio }}]</div>
        </el-form-item>


<!--        <el-form-item label="P1阶段卖出价:">-->
<!--          <div>{{bean.p1SellPrice}}</div>-->
<!--          <div class="sub-text-c">[P1阶段卖出价格计算公式：股票近期最高价*P1阶段卖出价比例，P1阶段卖出价比例：{{ sellRule.sp1Ratio }}]</div>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="P2阶段卖出价:">-->
<!--          <div>{{bean.p2SellPrice}}</div>-->
<!--          <div class="sub-text-c">[P2阶段卖出价格计算公式：股票近期最高价*P2阶段卖出价比例，P2阶段卖出价比例：{{ sellRule.sp2Ratio }}]</div>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="P3阶段卖出价:">-->
<!--          <div>{{bean.p3SellPrice}}</div>-->
<!--          <div class="sub-text-c">[P3阶段卖出价格计算公式：股票近期最高价*P3阶段卖出价比例，P3阶段卖出价比例：{{ sellRule.sp3Ratio }}]</div>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="P4阶段卖出价:">-->
<!--          <div>{{bean.p4SellPrice}}</div>-->
<!--          <div class="sub-text-c">[P4阶段卖出价格计算公式：股票近期最高价*P4阶段卖出价比例，P4阶段卖出价比例：{{ sellRule.sp4Ratio }}]</div>-->
<!--        </el-form-item>-->

        <div class="margin-tb-md"></div>
        <el-divider content-position="left">止损阶段股票交易</el-divider>
        <el-form-item label="止损价格:">
          <div>{{bean.closeEma18}}</div>
          <div>止损价格计算公式：price_lowest &lt; close_ema_18 </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "StockCalculator",
  data () {
    let validNumberPass1 = (rule, value, callback) => {//包含小数的数字
      // let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
      let reg = /^(0|([1-9]\d*))(\.\d+)?$/g;
      if (value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入数字'));
      } else {
        callback();
      }
    };
    let validNumberPass2 = (rule, value, callback) => {//正整数
      // let reg = /^[+]{0,1}(\d+)$/g;
      let reg = /^(?:[0-9]\d*)$/g;
      if (value === '') {
        callback(new Error('请输入内容'));
      } else if (!reg.test(value)) {
        callback(new Error('请输入0及0以上的整数'));
      } else {
        callback();
      }
    };
    return {
      //新增参数
      bean: {
        "id": null,
        "buyPrice": null,
        "holdAmount": 100,
        "sellStage": 0,
        "priceClose": null,
        "priceHigh": null,
        "priceLow": null,
        "priceSellHigh": null,
        "priceSellLow": null,
        "closeEma18": null,
        "p1Price": null,
        "p1SellPrice": null,
        "p2Price": null,
        "p2SellPrice": null,
        "p3Price": null,
        "p3SellPrice": null,
        "p4Price": null,
        "p4SellPrice": null,
        "p5Price": null,
      },
      rules: {
        buyPrice: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
        priceHigh: [{validator:validNumberPass1, trigger: 'blur', required: true, message: '必须为数字值'}],
      },
      sellRule: {
        sellRatio: 0.5,
        p1Ratio: 1.15,
        p2Ratio: 1.1,
        p3Ratio: 1.1,
        p4Ratio: 1.1,
        p5Ratio: 1.1,
        sp1Ratio: 0.85,
        sp2Ratio: 0.87,
        sp3Ratio: 0.89,
        sp4Ratio: 0.91,
      }
    }
  },
  mounted() {
    this.getSellRule();
  },
  methods: {
    getSellRule() {
      this.getRequest("/stock/sellrule/getRunning").then(resp =>{
        if (resp) {
          if (resp.obj) { // 存在正在运行的卖出策略
            this.sellRule = resp.obj
          }
        }
      });
    },
    emptyBean() {
      this.bean = {
        "id": null,
        "buyPrice": null,
        "holdAmount": 100,
        "sellStage": 0,
        "priceClose": null,
        "priceHigh": null,
        "priceLow": null,
        "priceSellHigh": null,
        "priceSellLow": null,
        "closeEma18": null,
        "p1Price": null,
        "p1SellPrice": null,
        "p2Price": null,
        "p2SellPrice": null,
        "p3Price": null,
        "p3SellPrice": null,
        "p4Price": null,
        "p4SellPrice": null,
        "p5Price": null,
      };
    },
    doCalculator() {
      this.$refs['beanForm'].validate(valid => {
        if (valid) {
          let bean = this.bean;
          let sellRule = this.sellRule;
          let p1Price = (bean.buyPrice * sellRule.p1Ratio).toFixed(2);
          let p2Price = (p1Price * sellRule.p2Ratio).toFixed(2);
          let p3Price = (p2Price * sellRule.p3Ratio).toFixed(2);
          let p4Price = (p3Price * sellRule.p4Ratio).toFixed(2);
          let p5Price = (p4Price * sellRule.p5Ratio).toFixed(2);

          let buyPrice = this.bean.buyPrice;
          let priceHigh = this.bean.priceHigh;
          let stage = 0;
          let spRatio = 0;
          if (parseFloat(priceHigh)<parseFloat(buyPrice)) stage = 0, spRatio = '无';
          else if (priceHigh>p1Price && priceHigh<p2Price) stage = 1, spRatio = sellRule.sp1Ratio;
          else if (priceHigh>p2Price && priceHigh<p3Price) stage = 2, spRatio = sellRule.sp2Ratio;
          else if (priceHigh>p3Price && priceHigh<p4Price) stage = 3, spRatio = sellRule.sp3Ratio;
          else if (priceHigh>p4Price && priceHigh<p5Price) stage = 4, spRatio = sellRule.sp4Ratio;
          else if (priceHigh>p5Price) stage = 5, spRatio = 1, priceHigh = p5Price;
          let sellPrice = spRatio == '无'? '暂无': (priceHigh*spRatio).toFixed(2);
          let result = this.$utils.twoJsonMerge(this.bean, {
            p1Price: p1Price,
            p2Price: p2Price,
            p3Price: p3Price,
            p4Price: p4Price,
            p5Price: p5Price,
            sellStage: stage,
            spRatio: spRatio,
            sellPrice: sellPrice,
          });
          this.bean = result;
        }
      });

    }
  }
}
</script>

<style scoped>

</style>