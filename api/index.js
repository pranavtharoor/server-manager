require('dotenv').config();
const express = require('express');
const bodyParser = require('body-parser');
const logger = require('morgan');

const models = require('./models');
const routes = require('./routes');
const responseFormat = require('./utils/responseFormat');

const app = express();

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(responseFormat);

app.use('/api', routes);

const port = process.env.PORT || 3000;

models.sequelize.sync().then(
  () => {
    app.listen(port, () => {
      console.log('[SUCCESS] Listening on port ' + port);
    });
  },
  err => {
    console.log('[ERROR] Could not sync models: ', err);
  }
);
