const auth_cli = require('./lib/auth');

function onMessage(headers, params, query, body, logger, requester, reply) {
    auth_cli.isAuthorized(headers, function(authorized, token) {
        if(!authorized || !token) {
            reply.send(401, { 'Content-type': 'application/json' }, { error: 'Unauthorized' });
        } else {
            // Write code here
            reply.send(200, { 'Content-type': 'application/json' }, { data: 'Hello World!' });
        }
    });
};

module.exports = {
    onMessage: onMessage
};