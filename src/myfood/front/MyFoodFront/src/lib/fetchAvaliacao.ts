import { ENDPOINT_URL } from "$lib/constants";

export type Avaliacao = {
  produtoId: number;
  estrelas: number;
  comentario: string;
};

export async function fetchAvaliacao(): Promise<Avaliacao[]> {
  try {
    const response = await fetch(`${ENDPOINT_URL}/avaliacao`);
    if (!response.ok) {
      throw new Error("Failed to fetch evaluations");
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    return [];
  }
}

export function calculaMediaAvaliacao(
  avaliacoes: Avaliacao[],
  produtoId: number
): number {
  const avaliacoesProduto = avaliacoes.filter(
    (avaliacao) => avaliacao.produtoId === produtoId
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

  return ratings.map((rating) => (rating / totalRatings) * 100);
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
