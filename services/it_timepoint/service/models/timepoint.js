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

function store(citizen_id, lat, long, timestamp, logger, callback) {
    timepointExists(citizen_id, lat, long, timestamp, logger, function(exists) {
        if(exists) {
            callback({"status": 405, "message": "Timepoint already exists"});
        } else {
            let newTimepoint = new timepointModel({
                citizen_id: citizen_id,
                lat: lat,
                long: long,
                timestamp: timestamp
            });
            newTimepoint.save(function(err, _timepoint) {
                if (err) {
                    logger.error(err);
                    callback({"status": 500, "message": err});
                } else {
                    callback({"status": 200, "message": "Timepoint successfully stored"});
                }
            });
        }
    });
}

function timepointExists(citizen_id, lat, long, timestamp, logger, callback) {
    timepointModel.findOne({
        citizen_id: citizen_id,
        lat: lat,
        long: long,
        timestamp: timestamp
    }, function (err, timepoint) {
        if (!err && timepoint) {
            callback(true);
        } else {
            logger.error(err);
            callback(false);
        }
    });
}

module.exports = {
    init: init,
    store: store
};