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

app.get('/produtos', (req, res) => {
  const sql = 'SELECT * FROM produtos';

  db.query(sql, (error, results) => {
    if (error) {
      console.error('Erro ao buscar produtos:', error);
      res.status(500).send('Erro ao buscar produtos');
      return;
    }

    res.json(results);
  });
});

app.post('/produtos', (req, res) => {
  console.log(req.body);
  const { nome, descricao, preco, imagem, avaliacao, visibilidadeAvaliacao } = req.body;

  const sql = 'INSERT INTO produtos (nome, descricao, preco, imagem, avaliacao, visibilidadeAvaliacao) VALUES (?, ?, ?, ?, ?, ?)';
  const values = [nome, descricao, preco, imagem, avaliacao, visibilidadeAvaliacao];

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
  const { nome, descricao, preco, imagem, avaliacao, visibilidadeAvaliacao} = req.body;

  const sql = 'UPDATE produtos SET nome = ?, descricao = ?, preco = ?, imagem = ?, avaliacao = ?, visibilidadeAvaliacao = ? WHERE id = ?';
  const values = [nome, descricao, preco, imagem, avaliacao, visibilidadeAvaliacao, idProduto];

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

app.delete('/produtos/delete/:id', (req, res) => {
  const idProduto = req.params.id;

  const sql = 'DELETE FROM produtos WHERE id = ?';
  const values = [idProduto];

  db.query(sql, values, (error, results) => {
    if (error) {
      console.error('Erro ao excluir produto:', error);
      res.status(500).send('Erro ao excluir produto');
      return;
    }

    if (results.affectedRows === 0) {
      res.status(404).send('Produto não encontrado');
      return;
    }

    res.json({ message: 'Produto excluído com sucesso!' });
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando em http://localhost:${port}`);
});