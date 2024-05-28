<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { type Prato, type Pedido } from "$lib/types";
  import type { PageData } from "./$types";
  import DrawerPedido from "$lib/components/DrawerPedido.svelte";
  import Button from "$lib/components/ui/button/button.svelte";

  export let data: PageData;

  let pratos: Prato[] = [];
  pratos = data.produtos;
  let promise = pratos;
  let clienteId: number;

  onMount(async () => {
    const storedClienteId = localStorage.getItem("clienteId");

    if (storedClienteId) {
      clienteId = parseInt(storedClienteId, 10);
      if (!isNaN(clienteId)) {
        console.log("Cliente ID:", clienteId);
      } else {
        console.error("Cliente ID is not a valid number");
      }
    } else {
      console.error("Cliente não registrado");
    }
  });
</script>

<div class="py-4">
  <div class=" items-center gap-0 pb-5">
    <h1 class="text-center text-4xl font-bold text-secondary">
      Cardapio restaurante
    </h1>
  </div>
  {#await promise}
    <div class="flex items-center justify-center mt-20">
      <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
    </div>
  {:then}
    <div>
      <Cardapio>
        {#each pratos as item}
          <ModalPedido {...item} />
        {/each}
      </Cardapio>
      <div class="fixed bottom-0 right-0 flex items-end mr-10 mb-10 gap-4">
        <a href="/pedidoscliente/{clienteId}">
          <Button variant="buttonAdd">Avaliar pedido!</Button>
        </a>
        <DrawerPedido />
      </div>
    </div>
  {/await}
</div>
