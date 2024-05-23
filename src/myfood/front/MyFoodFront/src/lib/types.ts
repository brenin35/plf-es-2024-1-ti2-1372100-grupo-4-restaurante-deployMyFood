export type ItemPedido = {
  id?: number;
  quantidade: number;
  precoItem: number;
  precoTotal: number;
  produto: {
    id: number;
    nome?: string;
  };
};

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
export type Avaliacao = {
  id: number;
  produtoId: number;
  estrelas: number;
  comentario: string;
};

export type Pedido = {
  id?: number;
  cliente: {
    id: number;
  };
  mesa: {
    id: number;
  };
  statusPreparo: boolean;
  statusPagamento: boolean;
  precoTotalPedido: number;
  itensPedido: ItemPedido[];
};
