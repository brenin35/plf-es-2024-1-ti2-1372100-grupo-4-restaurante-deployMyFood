<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";


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
          id={item.id}
          visibilidadeAvaliacao={item.visibilidadeAvaliacao}
          avaliacao={item.avaliacao}
        />
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
  </div>
