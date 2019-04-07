const moment = require('moment');
module.exports = (req, res, next) => {
  console.log({
    time: moment().format('LLLL'),
    path: req.path,
    ...(!!Object.keys(req.body).length && { body: JSON.stringify(req.body) }),
    ...(!!Object.keys(req.query).length && { query: req.query }),
    ...(!!Object.keys(req.params).length && { params: req.params })
  });
  res.sendError = (err, msg = 'Internal server error', status = 500) => {
    err && console.log('[ERROR] ', err);
    console.log('response:\n', { msg });
    res.status(status).send({ success: false, msg });
  };
  res.sendSuccess = (data, msg, status = 200) => {
    console.log({ msg });
    res.status(status).send({ success: true, msg, ...(data && { data }) });
  };
  next();
};
