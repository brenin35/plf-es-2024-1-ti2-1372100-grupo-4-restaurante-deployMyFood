<script lang="ts">
  import { onMount } from "svelte";
  import ModalCriar from "$lib/components/modal/ModalCriar.svelte";
  import ModalEdit from "$lib/components/modal/ModalEdit.svelte";

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

  onMount(async () => {
    const response = await fetch('http://localhost:3000/produtos');
    if (!response.ok) {
      console.error('Erro ao buscar produtos:', response.status);
      return;
    }
    pratos = await response.json();
  });
</script>

<div class="p-4 sm:ml-64">
  <div class="py-12">
    <div class="gap-0 py-1">
      <div class="items-center gap-0 pb-7">
        <h1 class="text-center text-4xl font-bold text-secondary">
          Cardapio restaurante
        </h1>
      </div>
    </div>
    <div class="flex justify-center gap-2">
      <ModalCriar />
    </div>
    {#if pratos.length == 0}
      <div class="flex justify-center items-center mt-40">
        <h1 class="text-xl text-center">
          Nenhum produto adicionado ao cardápio!
        </h1>
      </div>
    {/if}
    <div
      class="grid grid-cols-1 gap-3 sm:grid-cols-1 md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-3"
    >
      {#each pratos as item}
        <ModalEdit
          id={item.id}
          nome={item.nome}
          preco={item.preco}
          descricao={item.descricao}
          imagem={item.imagem}
          visibilidadeAvaliacao={item.visibilidadeAvaliacao}
          avaliacao={item.avaliacao}
        />
      {/each}
    </div>
  </div>
</div>
