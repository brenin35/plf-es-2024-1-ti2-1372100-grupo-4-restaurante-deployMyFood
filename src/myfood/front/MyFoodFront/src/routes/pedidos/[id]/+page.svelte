<script lang="ts">
  import ModalAvaliar from "$lib/components/modal/ModalAvaliar.svelte";
  import type { Pedido } from "$lib/types.ts";
  import type { PageData } from "./$types";

  export let data: PageData;

  let pedido: Pedido = data.pedidoId;
</script>

<div class="p-4">
  <h1 class="text-2xl font-bold mb-4">Detalhes do Pedido #{pedido.id}</h1>
  <div class="mb-4">
    <p><strong>Cliente ID:</strong> {pedido.cliente.id}</p>
    <p><strong>Mesa ID:</strong> {pedido.mesa.id}</p>
    <p>
      <strong>Status de Preparo:</strong>
      {pedido.statusPreparo ? "Em Preparo" : "Pedido pronto"}
    </p>
    <p>
      <strong>Status de Pagamento:</strong>
      {pedido.statusPagamento ? "Pago" : "Não Pago"}
    </p>
    <p>
      <strong>Preço Total do Pedido:</strong>
      R${pedido.precoTotalPedido.toFixed(2)}
    </p>
  </div>

  <h2 class="text-xl font-semibold mb-3">Itens do Pedido</h2>
  <div
    class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4"
  >
    {#each pedido.itensPedido as itemPedido}
      <div
        class="flex flex-col items-center justify-center gap-4 border p-4 rounded"
      >
        <p><strong>Produto ID:</strong> {itemPedido.produto.id}</p>
        {#if itemPedido.produto.nome}
          <p><strong>Nome do Produto:</strong> {itemPedido.produto.nome}</p>
        {/if}
        <p><strong>Quantidade:</strong> {itemPedido.quantidade}</p>
        <p><strong>Preço Item:</strong> R${itemPedido.precoItem.toFixed(2)}</p>
        <p>
          <strong>Preço Total:</strong> R${itemPedido.precoTotal.toFixed(2)}
        </p>
        <ModalAvaliar />
      </div>
    {/each}
  </div>
</div>
