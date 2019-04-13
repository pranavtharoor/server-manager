const Joi = require('joi');

const addServer = Joi.object({
  body: Joi.object({
    domain: Joi.string()
      .allow('')
      .allow(null)
      .max(100),
    ip: Joi.string()
      .max(100)
      .required()
  }).required()
});

const addRequest = Joi.object({
  body: Joi.object({
    sshKey: Joi.string().max(500),
    id: Joi.number().required()
  }).required()
});

const idParam = Joi.object({
  params: Joi.object({
    id: Joi.number().required()
  }).required()
});

module.exports = { addServer, idParam };
