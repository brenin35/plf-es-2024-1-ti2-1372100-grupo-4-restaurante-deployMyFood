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
  <div class="">
    <div class="gap-0 py-1">
      <div class="items-center gap-0 pb-7">
        <h1 class="text-center text-4xl font-bold">Cardapio</h1>
      </div>
    </div>
    <div class="flex justify-center gap-2">
      <ModalCriar />
    </div>
    <div
      class="grid grid-cols-1 gap-3 sm:grid-cols-1 md:grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-3"
    >
      {#each pratos as item}
        <ModalEdit
          nome={item.nome}
          preco={item.preco}
          descricao={item.descricao}
          img={item.imagem}
        />
      {/each}
    </div>
  </div>
</div>
