module.exports = (sequelize, DataTypes) => {
  let Request = sequelize.define('request', {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    domain: {
      type: DataTypes.STRING,
      allowNull: true
    },
    ip: {
      type: DataTypes.STRING,
      allowNull: true
    },
    accepted: {
      type: DataTypes.BOOLEAN,
      allowNull: true,
      defaultValue: false
    }
  });

  Request.associate = models => {
    models.request.belongsTo(models.server);
    models.request.belongsTo(models.user);
  };

  return Request;
};
