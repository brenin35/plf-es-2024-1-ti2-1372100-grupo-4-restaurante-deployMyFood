const express = require('express');
const db = require('./database');
const app = express();
const port = 3000;

const cors = require('cors');
const bodyParser = require('body-parser');

app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));

app.use(express.json());
app.use(cors());

app.get('/', (req, res) => {
  db.query('SELECT * FROM produtos', (error, results) => {
    if (error) {
      console.error('Erro ao buscar produtos:', error);
      res.status(500).send('Erro ao buscar produtos');
      return;
    }

    res.send(results);
  });
});

app.post('/produtos', (req, res) => {
  console.log(req.body);
  const { nome, descricao, preco, imagem } = req.body;

  const sql = 'INSERT INTO produtos (nome, descricao, preco, imagem) VALUES (?, ?, ?, ?)';
  const values = [nome, descricao, preco, imagem];

  db.query(sql, values, (error, results) => {
    if (error) {
      console.error('Erro ao adicionar produto:', error);
      res.status(500).send('Erro ao adicionar produto');
      return;
    }

    res.json({ message: 'Produto adicionado com sucesso!' });
  });
});

app.put('/produtos/edit/:id', (req, res) => {
  const idProduto = req.params.id;
  const { nome, descricao, preco, imagem } = req.body;

  const sql = 'UPDATE produtos SET nome = ?, descricao = ?, preco = ?, imagem = ? WHERE id = ?';
  const values = [nome, descricao, preco, imagem, idProduto];

  db.query(sql, values, (error, results) => {
    if (error) {
      console.error('Erro ao atualizar produto:', error);
      res.status(500).send('Erro ao atualizar produto');
      return;
    }

    if (results.affectedRows === 0) {
      res.status(404).send('Produto não encontrado');
      return;
    }

    res.json({ message: 'Produto atualizado com sucesso!' });
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando em http://localhost:${port}`);
});