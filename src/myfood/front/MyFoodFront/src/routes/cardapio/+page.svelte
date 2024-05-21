<script lang="ts">
  import ModalCriar from "$lib/components/modal/ModalCriar.svelte";
  import ModalEdit from "$lib/components/modal/ModalEdit.svelte";
  import { onMount } from "svelte";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";

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
    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await response.json();
  });
</script>

  <div class="py-4">
    <div class="gap-0 py-1">
      <div class="mb-5 mt-5 flex flex-col sm:flex-row justify-between items-center">
        <h1 class="text-center text-4xl font-bold text-secondary">
          Cardapio restaurante
        </h1>
        <ModalCriar />
      </div>
    </div>
    <Cardapio>
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
    </Cardapio>
  </div>
