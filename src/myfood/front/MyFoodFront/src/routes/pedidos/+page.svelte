<script lang="ts">
  import type { PageData } from "./$types";
  import Button from "$lib/components/ui/button/button.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import type { Pedido } from "$lib/types";

  export let data: PageData;

  let pedidos: Pedido[] = data.pedidos;
</script>

<main class="p-4">
  <h1 class="text-3xl font-bold mb-4 text-center">Pedidos</h1>
  {#if pedidos.length === 0}
    <p class="text-center">Nenhum pedido encontrado.</p>
  {:else}
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <div>
        <h2 class="text-xl font-semibold mb-2 text-center">
          Pedidos pendentes
        </h2>
        {#each pedidos as pedido (pedido.id)}
          <div
            class="bg-white border border-gray-200 rounded shadow p-4 mb-4 justify-between"
          >
            <div>
              <div class="flex justify-between">
                <h1 class="text-xl mb-2">
                  Pedido <strong>#{pedido.id}</strong> - <span class="text-primary">Mesa: <strong>{pedido.mesa.id}</strong></span>
                </h1>
                <p class="text-right text-xl"><strong>Valor total:</strong> R${pedido.precoTotalPedido}</p>
              </div>
              <div class="grid md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-2">
                {#each pedido.itensPedido as item}
                  <div
                    class="bg-white border border-gray-200 rounded shadow p-4 flex flex-col justify-between"
                  >
                    <h1 class="text-xl">
                      <strong>Prato: {item.produto.id}</strong>
                    </h1>
                    <p><strong>Quantidade: </strong>{item.quantidade}</p>
                  </div>
                {/each}
              </div>
            </div>
            <div class=" flex flex-col justify-between mt-2">
              <Button variant="buttonAdd">Pedido finalizado!</Button>
            </div>
          </div>
        {/each}
      </div>
    </div>
  {/if}
</main>
