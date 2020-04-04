const mongoose = require('mongoose');
const Double = require('@mongoosejs/double');

var timepointModel;

function init(settings) {
    mongoose.connect(settings.mongodb_url, { useNewUrlParser: true, useUnifiedTopology: true });

    let schema = mongoose.Schema({
        citizen_id: String,
        lat: Double,
        long: Double,
        timestamp: Date
    });

    timepointModel = mongoose.model('timepoint', schema);
}

function listAllTimepoints(logger, callback) {
    timepointModel.find({}, function (err, timepoints) {
        if(err || !timepoints) {
            logger.error(err);
            callback({"status": 500, "message": []});
        } else {
            callback({"status": 200, "message": timepoints});
        }
    });
}

module.exports = {
    init: init,
    listAllTimepoints: listAllTimepoints
};