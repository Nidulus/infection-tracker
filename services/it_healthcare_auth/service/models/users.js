const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

var userModel;

function init(settings) {
    mongoose.connect(settings.mongodb_url, { useNewUrlParser: true, useUnifiedTopology: true });

    let schema = mongoose.Schema({
        user: String,
        hashed_password: String
    });

    userModel = mongoose.model('healthcare_users', schema);
}

function getUser(user, password, callback) {
    userModel.findOne({user: user}, function (err, userObj) {
        if (!err && userObj) {
            callback(null, bcrypt.compareSync(password, userObj.hashed_password) ? userObj : null);
        } else {
            callback('No such user');
        }
    });
}

module.exports = {
    init: init,
    getUser: getUser,
};
