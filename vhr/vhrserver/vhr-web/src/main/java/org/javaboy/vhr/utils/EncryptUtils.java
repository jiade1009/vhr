package org.javaboy.vhr.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * @author : sam
 * @ClassName : EncryptUtils
 * @description : 签名验签
 * @datetime : 2022年 11月 04日 20:09
 * @version: : 1.0
 */
@Component
public class EncryptUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);
    @Resource
    private Environment environment;

    /**
     * 参数进行签名
     * 对参数Map增加tm、idx参数
     * 对Map进行md5签名
     * @param params
     * @return
     */
    public Map<String, Object> doEncrypt(Map<String, Object> params) {
        String idx = environment.getProperty("encrypt.idx");
        String key = environment.getProperty("encrypt.key");
        Date now = new Date();
        String now_str = DateUtils.formatDate(now, "yyyyMMddHHmmss");
        params.put("tm", now_str);
        params.put("idx", idx);
        String sign = getSign(params, key);
        params.put("sign", sign);
        return params;
    }

    /**
     * 采用md5签名
     * 将参数Map的数据转成json字符串，进行md5加密，将获得结果转成16进制并且转成大写字母
     * @param params
     * @param key 签名key
     * @return
     */
    public String getSign(Map<String, Object> params, String key) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json_str = mapper.writeValueAsString(params);
            json_str += "&key=" + key;
            LOGGER.debug(json_str);
            MessageDigest md = MessageDigest.getInstance("MD5");//申明使用MD5算法

            /**
             * 计算md5函数
             * digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
             * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
             * 转换成大写
             */
            md.update(json_str.getBytes(StandardCharsets.UTF_8));
            String sign = new BigInteger(1, md.digest()).toString(16).toUpperCase();
            LOGGER.debug(sign);
            return sign;
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验签
     * 1.确认参数Map是否含有必要的参数：sign、idx、tm
     * 2.时间戳校验，校验传递的参数信息是否是在60S之内的
     * 3.校验签名是否一致
     * @param params
     * @return -2 参数信息不完整；-3 时间戳校验不通过；-1 签名不一致；0 签名验签通过
     */
    public Integer checkSign(Map<String, Object> params) {
        // 1.确认是否含有必要的参数
        if (!(params.containsKey("sign") && params.containsKey("idx") && params.containsKey("tm"))) {
            LOGGER.error("签名验签，缺少必要的参数： sign, idx, tm");
            return -2;
        }
        String sign = (String) params.get("sign");
        String idx = (String) params.get("idx");
        String tm = (String) params.get("tm");
        LOGGER.info("请求的参数：{}", params.toString());
        // 2.时间戳的校验
        Date now = new Date();
        Date time = DateUtils.parseDate(tm, DateUtils.yyyyMMddHHmmss);
        time = DateUtils.addMinute(time, 1);
        if (time.before(now)) {
            LOGGER.error("请求时间已经超时，数据无效");
            return -3;
        }

        // 3.校验签名是否一致
        params.remove("sign");
        String key = environment.getProperty("encrypt.key");
        String check_sign = getSign(params, key);
        if (check_sign.equals(sign)) {
            return 0;
        } else {
            return -1;
        }
    }
}
