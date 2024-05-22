<script lang="ts">
  import { onMount, createEventDispatcher } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";
  import { type Prato, type Pedido } from "$lib/types";
  import { getProdutos } from "$lib/components/fetchs/fetchProdutos";
  import { postPedidos } from "$lib/components/fetchs/fetchPedidos";

  let promise = fetch(`${ENDPOINT_URL}/produtos`);
  const dispatch = createEventDispatcher();

  let pratos: Prato[] = [];
  let clienteId: number;

  onMount(async () => {
    localStorage.setItem("isAdmin", "false");
    pratos = await getProdutos();

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

  async function postPedidosBtn() {
    if (!clienteId) {
      console.error("ClienteId not found.");
      return;
    }
    try {
      const response = await postPedidos(clienteId);

      dispatch("pedidoCreated", response);
    } catch (error) {
      console.error("Failed to post pedido:", error);
    }
  }
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
    <Cardapio>
      {#each pratos as item}
        <ModalPedido {...item} />
      {/each}
    </Cardapio>
    <Button
      on:click={postPedidosBtn}
      variant="buttonAdd"
      type="submit"
      class="flex items-center mt-4"
    >
      <p class="pr-2">Finalizar Pedido</p>
      <Check />
    </Button>
  {/await}
</div>
