const jwt = require('jwt-simple');
const settings = require('../config/settings');
const usersdb = require('../models/users');

usersdb.init(settings);

const auth = {
    login: function(username, password, callback) {
        if (!username || !password) {
            callback({
                "status": 401,
                "message": "Invalid credentials"
            });
        } else {
	        // Fire a query to the DB and check if the credentials are valid
	        auth.validate(username, password, function(err, user) {
	            if (err || !user) {
	                callback({
	                    "status": 401,
	                    "message": "Invalid credentials"
	                });
	            } else {
	                // Authentication succeeded, we will generate a token
	                // and dispatch it to the client
	                callback({
	                    "status": 200,
	                    "message": gen_token(user.user)
	                });
	            }
	            return;
	        });
	    }
    },

    refresh_token: function(headers, callback) {
        let token = headers['Authentication'] || headers['authentication'];
        try {
            let decoded_token = jwt.decode(token, settings.server_secret);

            if (isValidForRefresh(decoded_token)) {
                callback({
                    "status": 200,
                    "message": gen_token(decoded_token.username)
                });
            } else {
                callback({
                    "status": 401,
                    "message": 'Token expired'
                });
            }
        } catch (err) {
            callback({
                "status": 500,
                "message": err
            });
        }
    },

    validate: function(username, password, callback) {
        usersdb.getUser(username, password, function(err, user) {
            if (err || !user) return callback(err);
            callback(null, user);
        });
    }
}

// private methods

function gen_token(username) {
    let expires = expiresIn(settings.token_lifetime);
    let token = jwt.encode({
        username: username,
        exp: expires
    }, settings.server_secret);

    return {
        token: token,
        expires: expires
    };
}

function expiresIn(numMinutes) {
    let dateObj = new Date();
    return dateObj.setTime(dateObj.getTime() + numMinutes * 60000);
}

function isValidForRefresh(decoded_token) {
    if( decoded_token.exp > (Date.now() + (settings.refresh_token_lifetime * 60000)) ) {
        return false;
    } else {
        return true;
    }
}

module.exports = auth;