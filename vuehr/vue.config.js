let proxyObj = {};
const CompressionPlugin = require("compression-webpack-plugin");
console.log("..............", process.env.NODE_ENV)
// 121.37.203.125
// 192.168.0.23:8081
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
        target: "ws://localhost:8081"
        // target: 'ws://121.37.203.125:8081'
    };
    proxyObj['/'] = {
        ws: false,
        target: 'http://localhost:8081',
        // target: 'http://121.37.203.125:8081',
        changeOrigin: true,
        pathRewrite: {
            '^/': ''
        }
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
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