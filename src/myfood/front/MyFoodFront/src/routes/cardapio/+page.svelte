<script lang="ts">
  import { onMount } from "svelte";
  import ModalCriar from "$lib/components/modal/ModalCriar.svelte";
  import ModalEdit from "$lib/components/modal/ModalEdit.svelte";

  type Prato = {
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
  };

  let pratos: Prato[] = [];

  onMount(() => {
    pratos = JSON.parse(localStorage.getItem("produtos") ?? "[]");
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
          nome={item.nome}
          preco={item.preco}
          descricao={item.descricao}
          imagem={item.imagem}
        />
      {/each}
    </div>
  </div>
</div>
