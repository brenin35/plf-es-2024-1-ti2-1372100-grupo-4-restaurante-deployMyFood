const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'myfood'
});

connection.connect(error => {
  if (error) {
    console.error('Erro ao conectar ao banco de dados:', error);
    return;
  }

  console.log('Conectado ao banco de dados MySQL!');
});

module.exports = connection;
