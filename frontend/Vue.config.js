const fs = require('fs')

module.exports = {
    devServer: {
        proxy: {
            '/ws-connect/': {
                target: 'https://localhost:8081',
                changeOrigin: true,
                ws: true
            }
        },
        https: {
            key: fs.readFileSync('certs/example.com+5-key.pem'),
            cert: fs.readFileSync('certs/example.com+5.pem'),
        },
        public: 'https://secret-santa.smn-router.keenetic.pro/'
    }
}