function formatDate(date_str) {
    if (!!date_str) {
        let dt = new Date(date_str)
        return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
    } else {
        return "";
    }
}

//将两个json对象合并
function twoJsonMerge(json1,json2){
    let length1 = 0,length2 = 0,jsonStr,str;
    for(let ever in json1) length1++;
    for(let ever in json2) length2++;
    if(length1 && length2) str = ',';
    else str = '';
    jsonStr = ((JSON.stringify(json1)).replace(/,}/,'}') + (JSON.stringify(json2)).replace(/,}/,'}')).replace(/}{/,str);
    return JSON.parse(jsonStr);
}

/**
 * 获取对应股票代码在东方财富的访问链接
 *   沪市主板股票代码：600、601、603、605开头
     深市主板股票代码：000开头
     深市中小板股票代码：002开头
     创业板股票代码：300开头
     科创板股票代码：688开头
 * @param code
 */
function getEastMoneyUrl(code) {
    // http://quote.eastmoney.com/sh600327.html
    // http://quote.eastmoney.com/kcb/688167.html
    // http://quote.eastmoney.com/sz300912.html
    // http://quote.eastmoney.com/bj/830964.html
    let website = "http://quote.eastmoney.com/";
    if (code.indexOf("600")==0 || code.indexOf("601")==0 || code.indexOf("603")==0 || code.indexOf("605")==0) {
        return website + "sh" + code + ".html";
    } else if (code.indexOf("000")==0 || code.indexOf("001")==0 || code.indexOf("002")==0
        || code.indexOf("003")==0 ||code.indexOf("300")==0 ||code.indexOf("301")==0) {
        return website + "sz" + code + ".html";
    } else if (code.indexOf("688")==0) {
        return website + "kcb/" + code + ".html";
    } else if (code.indexOf("43")==0 || code.indexOf("83")==0 || code.indexOf("87")==0){
        return website + "bj/" + code + ".html";
    } else {
        return code;
    }
}
export default {
    formatDate,
    twoJsonMerge,
    getEastMoneyUrl
}