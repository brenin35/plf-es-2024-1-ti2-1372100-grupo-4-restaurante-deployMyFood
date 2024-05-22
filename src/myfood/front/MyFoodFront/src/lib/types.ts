export type ItemPedido = {
  quantidade: number;
  precoItem: number;
  precoTotal: number;
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
  id: number;
  cliente_id: number;
  mesa_id: number;
  statusPreparo: boolean;
  statusPagamento: boolean;
  precoTotalPedido: number;
  itensPedido: ItemPedido[];
};
