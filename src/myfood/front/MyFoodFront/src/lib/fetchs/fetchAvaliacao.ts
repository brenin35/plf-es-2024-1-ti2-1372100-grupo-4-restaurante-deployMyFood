import { type Avaliacao } from "../types";

export function calculaMediaAvaliacao(
  avaliacoes: Avaliacao[],
  produtoId: number
): number {
  const avaliacoesProduto = avaliacoes.filter(
    (avaliacao) => avaliacao.produto.id === produtoId
  );
  if (avaliacoesProduto.length === 0) {
    return 0;
  }
  const totalStars = avaliacoesProduto.reduce(
    (acc, avaliacao) => acc + avaliacao.estrelas,
    0
  );
  return totalStars / avaliacoesProduto.length;
}

export function contadorPercentAvaliacao(avaliacoes: Avaliacao[]): number[] {
  let ratings = [0, 0, 0, 0, 0];

  avaliacoes.forEach((avaliacao) => {
    ratings[avaliacao.estrelas - 1]++;
  });
  const totalRatings = avaliacoes.length;

  return ratings.map((rating) =>
    parseFloat(((rating / totalRatings) * 100).toFixed(1))
  );
}

export function calculaMediaAvaliacaoTotal(avaliacoes: Avaliacao[]): number {
  if (avaliacoes.length === 0) {
    return 0;
  }
  const sum = avaliacoes.reduce(
    (total, current) => total + current.estrelas,
    0
  );
  return sum / avaliacoes.length;
}
