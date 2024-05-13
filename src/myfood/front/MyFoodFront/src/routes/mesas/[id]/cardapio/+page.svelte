<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";

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

  let endpoint = "https://plf-es-2024-1-ti2-1372100-grupo-4.onrender.com";

  onMount(async () => {
    const response = await fetch(`${endpoint}/produtos`);
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await response.json();
  });
</script>

<div class="p-10 sm:ml-64">
  <div class="py-4">
    <div class=" items-center gap-0 pb-5">
      <h1 class="text-center text-4xl font-bold text-secondary">
        Cardapio restaurante
      </h1>
    </div>
    <Cardapio>
      {#each pratos as item}
        <ModalPedido
          nome={item.nome}
          preco={item.preco}
          descricao={item.descricao}
          img={item.imagem}
          visibilidadeAvaliacao={item.visibilidadeAvaliacao}
          avaliacao={item.avaliacao}
        />
      {/each}
    </Cardapio>
  </div>
</div>
