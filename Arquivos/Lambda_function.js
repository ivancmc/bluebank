const https = require('https')
exports.handler = async (event) => {
    return httprequest().then((data) => {
        const response = {
            statusCode: 200,
            body: JSON.stringify(data),
        };
    return response;
    });
};
function httprequest() {
     return new Promise((resolve, reject) => {
        const options = {
            host: 'y627nu63c3.execute-api.us-east-1.amazonaws.com',
            path: '/dezktop/agendamento/efetivar',
            method: 'GET'
        };
        const req = https.request(options, (res) => {
            if (res.statusCode < 200 || res.statusCode >= 300) {
                if(res.statusCode == 404){
                    console.log('NÃO HÁ AGENDAMENTOS.');
                }
                return reject(new Error('statusCode=' + res.statusCode));
            }
            var body = [];
            res.on('data', function(chunk) {
                body.push(chunk);
            });
            res.on('end', function() {
                try {
                    body = JSON.parse(Buffer.concat(body).toString());
                    console.log('AGENDAMENTOS REALIZADOS:',body);
                } catch(e) {
                    reject(e);
                }
                resolve(body);
            });
        });
        req.on('error', (e) => {
          reject(e.message);
        });
        // send the request
       req.end();
    });
}
