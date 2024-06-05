<script lang="ts">
  import ModalCriar from "$lib/components/modal/ModalCriar.svelte";
  import ModalEdit from "$lib/components/modal/ModalEdit.svelte";
  import { onMount } from "svelte";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { getProdutos } from "$lib/fetchs/fetchProdutos";
  import { type Avaliacao } from "$lib/types";

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

  onMount(async () => {
    pratos = await getProdutos();
  });
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
  <Cardapio>
    {#each pratos as item}
      <ModalEdit {...item} />
    {/each}
  </Cardapio>
</div>
