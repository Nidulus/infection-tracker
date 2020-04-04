const jwt = require('jwt-simple');
const settings = require('../config/settings');

const auth_client = {
    isAuthorized: function(headers, callback) {
        let token = headers['Authentication'] || headers['authentication'];
        try {
            let decoded_token = jwt.decode(token, settings.server_secret);

            if (decoded_token.exp > Date.now()) {
                callback(true, decoded_token);
            } else {
                callback(false, null);
            }
        } catch (err) {
            callback(false, null);
        }
    }
}

module.exports = auth_client;