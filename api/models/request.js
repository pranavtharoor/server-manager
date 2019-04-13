module.exports = (sequelize, DataTypes) => {
  let Request = sequelize.define('request', {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    accepted: {
      type: DataTypes.BOOLEAN,
      allowNull: true,
      defaultValue: false
    },
    sshKey: {
      type: DataTypes.STRING(500),
      allowNull: true
    }
  });

  Request.associate = models => {
    models.request.belongsTo(models.server);
    models.request.belongsTo(models.user);
  };

  return Request;
};
