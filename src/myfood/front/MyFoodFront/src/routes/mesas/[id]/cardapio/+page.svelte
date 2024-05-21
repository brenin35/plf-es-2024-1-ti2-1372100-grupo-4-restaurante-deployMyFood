<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";
  import { getProdutos } from "$lib/fetchProdutos";
  import { type Avaliacao } from "$lib/types";
  import { type Prato } from "$lib/types";

  let promise = fetch(`${ENDPOINT_URL}/produtos`);

  let pratos: Prato[] = [];
  let clienteId;

  onMount(async () => {
    pratos = await getProdutos();

    clienteId = localStorage.getItem("clienteId");

    if (clienteId) {
      console.log("Cliente ID:", clienteId);
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
    <Cardapio>
      {#each pratos as item}
        <ModalPedido {...item} />
      {/each}
    </Cardapio>
    <Button variant="buttonAdd" type="submit" class="flex items-center mt-4">
      <p class="pr-2">Finalizar Pedido</p>
      <Check />
    </Button>
  {/await}
</div>
