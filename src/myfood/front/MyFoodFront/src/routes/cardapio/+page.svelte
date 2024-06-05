<script lang="ts">
  import ModalCriar from "$lib/components/modal/ModalCriar.svelte";
  import ModalEdit from "$lib/components/modal/ModalEdit.svelte";
  import type { Avaliacao } from "$lib/types";
  import type { PageData } from "./$types";

  export let data: PageData;

  let produtos = data.produtos;

  type Prato = {
    visibilidadeAvaliacao: boolean;
    id: number;
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
    avaliacoes: Avaliacao[];
  };

  let pratos: Prato[] = produtos;
</script>

<div class="py-4">
  <div class="gap-0 py-1">
    <div
      class="mb-5 mt-5 flex flex-col sm:flex-row justify-between items-center"
    >
      <h1 class="text-center text-4xl font-bold text-secondary">
        Cardapio restaurante
      </h1>
      <ModalCriar />
    </div>
  </div>

  {#if produtos.length === 0}
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
        <ModalEdit {...item} />
      {/each}
    </div>
  {/if}
</div>
