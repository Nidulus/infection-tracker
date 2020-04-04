const auth = require('./lib/auth');

function onMessage(headers, params, query, body, logger, requester, reply) {
    if(headers.url.endsWith('/login') && headers.method === 'POST') {
        auth.login(body.user, body.password, function(ret) {
            reply.send(ret.status, { 'Content-type': 'application/json' }, ret);
        });
    } else if(headers.url.endsWith('/refresh_token') && headers.method === 'GET') {
        auth.refresh_token(headers, function(ret) {
            reply.send(ret.status, { 'Content-type': 'application/json' }, ret);
        });
    } else {
        reply.send(404, { 'Content-type': 'application/json' }, {"status": 404, "message": "Page not found"});
    }
};

module.exports = {
    onMessage: onMessage
};
