const { sequelize } = require('../models');
const { request, server, user } = require('../models');
const { exec } = require('child_process');
const to = require('../utils/to');

exports.showServers = async (req, res) => {
  server.findAll().then(data => res.send(data));
};

exports.showRequests = async (req, res) => {
  sequelize
    .query(
      'SELECT requests.id as id, users.name as name, users.email as email, servers.ip as ip, servers.domain as domain from requests join users on requests.userId = users.id join servers on servers.id = requests.serverId'
    )
    .then(data => res.send(data[0]));
};

exports.showAccess = async (req, res) => {
  request.findAll({ where: { accepted: true } }).then(data => res.send(data));
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
  request.findOne({ where: { id: req.params.id } }).then(data => {
    server.findOne({ where: { id: data.serverId } }).then(serverData => {
      exec(
        `ssh -t root@${serverData.ip} "echo '${
          data.sshKey
        }' >> ~/.ssh/authorized_keys && exit;bash -l"`,
        (err, stdout, stderr) => {
          if (err) {
            console.log(data, err);
            return res.send({ status: 0 });
          }
          request
            .update({ accepted: true }, { where: { id: req.body.id } })
            .then(data => res.send({ status: 1 }));
        }
      );
    });
  });
};

exports.addRequest = async (req, res) => {
  console.log(req.session.key);
  server
    .findOne({ where: { id: req.body.id } })
    .then(serverData => {
      return request.create({
        serverId: serverData.id,
        userId: req.session.key.id,
        sshKey: req.body.sshKey
      });
    })
    .then(data => {
      res.send({ status: 1 });
    })
    .catch(err => {
      console.log(err);
      res.send({ status: 0 });
    });
};

exports.rejectRequest = async (req, res) => {
  console.log(req.session.key);
  request
    .destroy({ where: { id: req.params.id } })
    .then(data => res.send({ status: 1 }))
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
