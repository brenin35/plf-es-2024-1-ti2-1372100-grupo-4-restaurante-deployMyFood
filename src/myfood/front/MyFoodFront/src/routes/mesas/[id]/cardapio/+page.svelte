<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import type { Avaliacao } from "$lib/types";
  import type { PageData } from "./$types";
  import DrawerPedido from "$lib/components/DrawerPedido.svelte";
  import Button from "$lib/components/ui/button/button.svelte";

  export let data: PageData;

  type Prato = {
    visibilidadeAvaliacao: boolean;
    id: number;
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
    avaliacoes: Avaliacao[];
  };

  let pratos: Prato[] = [];
  pratos = data.produtos;
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
  <div>
    {#if pratos.length === 0}
      <div class="flex justify-center items-center mt-40">
        <h1 class="text-xl text-center">
          Nenhum produto adicionado ao cardápio!
        </h1>
      </div>
    {:else}
      <div
        class="grid grid-cols-1 gap-5 sm:grid-cols-1 md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-3"
      >
        {#each pratos as item}
          <ModalPedido {...item} />
        {/each}
      </div>
    {/if}

    <div class="fixed bottom-0 right-0 flex items-end mr-10 mb-10 gap-4">
      <a href="/pedidoscliente/{clienteId}">
        <Button variant="buttonAdd">Avaliar pedido e realizar pagamento!</Button
        >
      </a>
      <DrawerPedido />
    </div>
  </div>
</div>
