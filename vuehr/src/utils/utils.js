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
export default {
    formatDate,
    twoJsonMerge
}