<script lang="ts">
  import { onMount } from "svelte";
  import { get } from 'svelte/store';
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";
  import { session } from "$lib/sessionStore";

  type Prato = {
    visibilidadeAvaliacao: boolean;
    id: number;
    nome: string;
    preco: number;
    descricao: string;
    imagem: string;
    avaliacao: Array<{ estrelas: number; comentario: string }>;
  };

  let pedidoId: number;
  let pratos: Prato[] = [];

  async function createPedido(clienteId: number, mesaId: number) {
    try {
      const response = await fetch(`${ENDPOINT_URL}/pedidos`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          clienteIdPedido: clienteId,
          mesaIdPedido: mesaId,
          statusPreparo: false,
          statusPagamento: false,
          precoTotalPedido: 0
        })
      });
      console.log(response);
      
      if (!response.ok) {
        throw new Error('Failed to create Pedido');
      }

      const data = await response.json();
      return data.id;
    } catch (error) {
      console.error('Erro ao criar pedido:', error);
      return null;
    }
  }

  onMount(async () => {
    const { clienteId, mesaId } = get(session);

    if (clienteId === null || mesaId === null) {
      console.error('Cliente ID ou Mesa ID não definidos na sessão');
      return;
    }

    pedidoId = await createPedido(clienteId, mesaId);

    if (pedidoId === null) {
      console.error('Erro ao criar o pedido');
      return;
    }

    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await response.json();
  });
</script>

<div class="py-4">
  <div class="items-center gap-0 pb-5">
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
        imagem={item.imagem}
        id={item.id}
        visibilidadeAvaliacao={item.visibilidadeAvaliacao}
        avaliacao={item.avaliacao}
        pedidoId={pedidoId}
      />
    {/each}
  </Cardapio>
  <Button variant="buttonAdd" type="submit" class="flex items-center mt-4">
    <p class="pr-2">Finalizar Pedido</p>
    <Check />
  </Button>
</div>
