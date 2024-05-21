<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";
  import { session, type ItemPedido, cart } from "$lib/sessionStore";
  import { getProdutos } from "$lib/fetchProdutos";
  import { type Avaliacao } from "$lib/fetchAvaliacao";
  import { type Prato } from "$lib/fetchProdutos";

  let promise = fetch(`${ENDPOINT_URL}/produtos`);

  let pratos: Prato[] = [];

  type Pedido = {
    clienteIdPedido: any;
    mesaIdPedido: any;
    status: string;
    valorPedido: number;
    produtoId: number;
  };

  onMount(async () => {
    pratos = await getProdutos();
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
    <Button
      variant="buttonAdd"
      type="submit"
      class="flex items-center mt-4"
    >
      <p class="pr-2">Finalizar Pedido</p>
      <Check />
    </Button>
  {/await}
</div>
