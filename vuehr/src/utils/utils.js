function formatDate(date_str) {
    if (!!date_str) {
        let dt = new Date(date_str)
        return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
    } else {
        return "";
    }
}
export default {
    formatDate
}