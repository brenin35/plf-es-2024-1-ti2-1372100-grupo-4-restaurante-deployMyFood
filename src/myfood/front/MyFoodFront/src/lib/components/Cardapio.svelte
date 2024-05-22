<script lang="ts">
  import { onMount } from "svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import { ENDPOINT_URL } from "$lib/constants";
  import { getProdutos } from "$lib/components/fetchs/fetchProdutos";

  type Prato = {
    visibilidadeAvaliacao: boolean;
    id: number;
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
    avaliacao: Array<{ estrelas: number; comentario: string }>;
  };

  let pratos: Prato[] = [];

  let promise = fetch(`${ENDPOINT_URL}/produtos`);

  onMount(async () => {
    pratos = await getProdutos();
  });
</script>

{#await promise}
  <div class="flex items-center justify-center mt-20">
    <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
  </div>
{:then}
  {#if pratos.length == 0}
    <div class="flex justify-center items-center mt-40">
      <h1 class="text-xl text-center">
        Nenhum produto adicionado ao cardápio!
      </h1>
    </div>
  {/if}
  <div
    class="grid grid-cols-1 gap-5 sm:grid-cols-1 md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-3"
  >
    <slot />
  </div>
{/await}
