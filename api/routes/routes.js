const { sequelize } = require('../models');
const { request, server, user } = require('../models');
const { exec } = require('child_process');
const to = require('../utils/to');

exports.showServers = async (req, res) => {
  server.findAll().then(res.send);
};

exports.showRequests = async (req, res) => {
  request.findAll({ where: { accepted: false } }).then(res.send);
};

exports.showAccess = async (req, res) => {
  request.findAll({ where: { accepted: true } }).then(res.send);
};

exports.addServer = async (req, res) => {
  server.create(req.body);
  res.send({ status: 1 });
};

exports.removeServer = async (req, res) => {
  server
    .destroy({ where: { id: req.params.id } })
    .then(data => res.send({ status: 1 }))
    .catch(err => res.send({ status: 0 }));
};

exports.acceptRequest = async (req, res) => {
  request.findOne({ where: { id: req.body.id } }).then(data => {
    exec(
      `ssh -t root@${data.ip} "echo '${
        data.sshKey
      }' >> ~/.ssh/authorized_keys && exit;bash -l"`,
      (err, stdout, stderr) => {
        if (err) return res.send({ status: 0 });
        request.updateAttributes(
          { accepted: true },
          { where: { id: req.body.id } }
        );
      }
    );
  });
};

exports.addRequest = async (req, res) => {
  server
    .findOne({ where: { id: req.body.id } })
    .then(data => {
      return request.create({
        server: data.id,
        user: req.session.id,
        sshKey: req.body.sshKey
      });
    })
    .then(data => {
      res.send({ status: 1 });
    })
    .catch(err => res.send({ status: 0 }));
};

exports.removeAccess = async (req, res) => {
  // ssh -t root@${IP} "sed -i -e 's#${SSH_KEY}##g' ~/.ssh/authorized_keys && exit;bash -l"
};

exports.hideServer = async (req, res) => {};
exports.hideRequest = async (req, res) => {};
exports.showRequest = async (req, res) => {};
exports.showServer = async (req, res) => {};
exports.init = async (req, res) => {};
