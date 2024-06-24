<script lang="ts">
  import type { PageData } from "./$types";
  export let data: PageData;

  let pedidos = data.pedidos;

  let allPedidos = data.allPedidos;

  let taxa = (pedidos.length / allPedidos.length) * 100;

  const filteredPreco = allPedidos.filter(
    (pedido) => pedido.precoTotalPedido > 50
  );
  let taxaPreco = (filteredPreco.length / allPedidos.length) * 100;
  console.log(taxaPreco);
</script>

<div class="flex justify-between mb-4 items-center">
  <h1 class="text-3xl font-bold text-center">Pedidos pagos</h1>
  <div>
    <div class="flex flex-col items-start">
      <p class="text-lg font-medium mb-2">Taxa de Pedidos Completados</p>
      <div class="w-full flex gap-3">
        <div class="w-72 h-5 bg-gray-200 rounded">
          <div
            class="h-5 bg-primary rounded animated-gradient"
            style="width: {taxa}%"
          ></div>
        </div>
        <span class="text-sm font-medium text-gray-500">{taxa.toFixed(1)}%</span>
      </div>
    </div>
    <div class="flex flex-col items-start">
      <p class="text-lg font-medium mb-2">Taxa de Pedidos acima de R$50</p>
      <div class="w-full flex gap-3">
        <div class="w-72 h-5 bg-gray-200 rounded">
          <div
            class="h-5 bg-primary rounded animated-gradient"
            style="width: {taxaPreco}%"
          ></div>
        </div>
        <span class="text-sm font-medium text-gray-500">{taxaPreco}%</span>
      </div>
    </div>
  </div>
</div>

{#if pedidos.length === 0}
  <p class="text-center">Nenhum pedido encontrado.</p>
{:else}
  <div>
    {#each pedidos as pedido (pedido.id)}
      <div
        class="bg-white border border-gray-200 rounded shadow p-4 mb-4 justify-between"
      >
        <div>
          <div class="flex justify-between">
            <h1 class="text-xl mb-2">
              Pedido <strong>#{pedido.id}</strong> -
              <span class="text-primary"
                >Mesa: <strong>{pedido.mesa.id}</strong></span
              >
            </h1>
            <p class="text-right text-xl">
              <strong>Total:</strong> R${pedido.precoTotalPedido}
            </p>
          </div>
          <div class="grid md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-2">
            {#each pedido.itensPedido as item}
              <div
                class="bg-white border border-gray-200 rounded shadow p-2 flex flex-col justify-between"
              >
                <h1 class="text-xl">
                  <strong>Prato: {item.produto.nome}</strong>
                </h1>
                <p>
                  Preco: <strong class="text-green-500"
                    >R${item.precoTotal}</strong
                  >
                </p>
                <p><strong>Quantidade: </strong>{item.quantidade}</p>
              </div>
            {/each}
          </div>
        </div>
        <div class=" flex flex-col justify-between mt-2"></div>
      </div>
    {/each}
  </div>
{/if}
