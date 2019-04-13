const express = require('express');
const router = express.Router();

const validator = require('../utils/validator');
const schemas = require('../schemas');
const bcrypt = require('bcryptjs');
const { user } = require('../models');

const routes = require('./routes');

router.get('/servers', routes.showServers);

router.post('/register', async (req, res) => {
  let err, myUser, salt, hash, newUser;
  [err, salt] = await to(bcrypt.genSalt(10));
  if (err) return res.sendError(err);
  [err, hash] = await to(bcrypt.hash(req.body.password, salt));
  if (err) return res.sendError(err);
  [err, newUser] = await to(
    user.create({
      ...req.body,
      password: hash
    })
  );
  if (err) return res.send({ status: 0 });
  return res.send({ status: 1 });
});

router.post('/login', async (req, res) => {
  let err, userData, result;
  [err, userData] = await to(
    user.findOne({
      where: {
        email: req.body.email
      }
    })
  );
  if (err) return res.send({ status: 0 });
  if (!userData) return res.send({ status: 0 });
  [err, result] = await to(
    bcrypt.compare(req.body.password, userData.password)
  );
  if (err) return res.send({ status: 0 });
  if (!result) return res.send({ status: 0 });
  req.session.key = userData;
  req.session.save(() => {
    res.send({ status: 1 });
  });
});

router.post(
  '/servers/add',
  validator(schemas.routes.addServer),
  routes.addServer
);

router.get(
  '/servers/show/:id',
  validator(schemas.routes.idParam),
  routes.showServer
);

router.get(
  '/servers/hide/:id',
  validator(schemas.routes.idParam),
  routes.hideServer
);

router.get(
  '/servers/remove/:id',
  validator(schemas.routes.idParam),
  routes.removeServer
);

router.get('/requests', routes.showRequests);

router.post(
  '/requests/add',
  // validator(schemas.routes.addRequest),
  routes.addRequest
);

router.get(
  '/requests/accept/:id',
  validator(schemas.routes.idParam),
  routes.acceptRequest
);

router.get(
  '/requests/reject/:id',
  validator(schemas.routes.idParam),
  routes.hideRequest
);

router.get('/accesses', routes.showAccess);

router.get(
  '/accesses/remove/:id',
  validator(schemas.routes.idParam),
  routes.removeAccess
);

module.exports = router;
