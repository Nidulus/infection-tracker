const settings = require('./config/settings');
const auth_cli = require('./lib/auth');
const timepointDb = require('./models/timepoint');

timepointDb.init(settings);

function onMessage(headers, params, query, body, logger, requester, reply) {
    auth_cli.isAuthorized(headers, function(authorized, token) {
        if(!authorized || !token) {
            reply.send(401, { 'Content-type': 'application/json' }, { error: 'Unauthorized' });
        } else {
            timepointDb.store(body.citizen_id, body.lat, body.long, body.timestamp, logger, function(ret) {
                reply.send(ret.status, { 'Content-type': 'application/json' }, ret);
            });
        }
    });
};

module.exports = {
    onMessage: onMessage
};