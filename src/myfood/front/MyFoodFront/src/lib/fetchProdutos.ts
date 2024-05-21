import { ENDPOINT_URL } from "$lib/constants";
import { type Avaliacao } from "$lib/fetchAvaliacao";

export type Prato = {
    visibilidadeAvaliacao: boolean;
    id: number;
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
    avaliacao: Avaliacao[];
    pedidoId: number;
  };

export async function getProdutos() {
    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      throw new Error("Falha produtos");
    }
    return await response.json();
  }