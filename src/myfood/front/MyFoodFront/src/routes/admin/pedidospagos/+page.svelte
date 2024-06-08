<script lang="ts">
  import type { PageData } from "./$types";
  export let data: PageData;

  let pedidos = data.pedidos;
</script>

<h1 class="text-3xl font-bold mb-4 text-center">Pedidos pagos</h1>
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
            <div
              class="grid md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-2"
            >
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
