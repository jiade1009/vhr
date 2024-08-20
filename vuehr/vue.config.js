let proxyObj = {};
const CompressionPlugin = require("compression-webpack-plugin");
console.log("..............", process.env.NODE_ENV)
// 1.94.54.206
// 192.168.0.23:8081
local_host = "localhost"
if (process.env.NODE_ENV === 'production') {
    proxyObj['/ws'] = {
        ws: true,
        target: "ws://192.168.0.23:8081"
    };
    proxyObj['/'] = {
        ws: false,
        target: 'http://192.168.0.23:8081',
        changeOrigin: true,
        pathRewrite: {
            '^/': ''
        }
    }
} else {
    proxyObj['/ws'] = {
        ws: true,
        target: "ws://"+local_host+":8081"
        // target: 'ws://1.94.54.206:8081'
    };
    proxyObj['/'] = {
        ws: false,
        target: "http://"+local_host+":8081",
        // target: 'http://1.94.54.206:8081',
        changeOrigin: true,
        pathRewrite: {
            '^/': ''
        }
    }
}

module.exports = {
    devServer: {
        host: local_host,
        port: 8080,
        proxy: proxyObj
    },
    configureWebpack: config => {
        if (process.env.NODE_ENV === 'production') {
            return {
                plugins: [
                    new CompressionPlugin({
                        test: /\.js$|\.html$|\.css/,
                        threshold: 1024,
                        deleteOriginalAssets: false
                    })
                ]
            }
        }
    }
}