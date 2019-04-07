const express = require('express');
const router = express.Router();

const validator = require('../utils/validator');
const schemas = require('../schemas');

const routes = require('./routes');

router.get('/servers', routes.showServers);

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
  '/requests/add/:id',
  validator(schemas.routes.addRequest),
  routes.addRequest
);

router.get(
  '/requests/accept/:id',
  validator(schemas.routes.idParam),
  routes.showRequest
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
