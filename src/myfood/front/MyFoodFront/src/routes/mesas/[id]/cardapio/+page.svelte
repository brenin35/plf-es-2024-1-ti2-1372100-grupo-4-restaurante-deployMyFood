<script lang="ts">
  import { onMount } from "svelte";
  import ModalPedido from "$lib/components/modal/ModalPedido.svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import Cardapio from "$lib/components/Cardapio.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { Check } from "lucide-svelte";
  import { cart } from "$lib/cartStore";
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

  type itemPedido = {
    id: number;
    nome: string;
    quantidade: number;
    precoTotal: number;
  };

  type Pedido = {
    clienteIdPedido: any;
    mesaIdPedido: any;
    status: string;
    valorPago: number;
    produtoId: number;
    quantidade: number;
  };

  let itemPedidos: itemPedido;
  
  let pratos: Prato[] = [];

  onMount(async () => {
    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await response.json();
  });

  async function finalizePedido() {
    console.log("clienteId", $session.clienteId);
    console.log("mesaId", $session.mesaId);

    for (let item of $cart) {
      console.log("preco", item.precoTotal);
      console.log("itemId", item.id);
      console.log("quantidade", item.quantidade);
      const Pedido = {
        clienteIdPedido: $session.clienteId,
        mesaIdPedido: $session.mesaId,
        status: "PENDENTE",
        valorPago: item.precoTotal,
        produtoId: item.id,
        quantidade: item.quantidade,
      };

      const response = await fetch(`${ENDPOINT_URL}/pedidos`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(Pedido),
      });

      if (!response.ok) {
        console.error("Failed to finalize order:", response.status);
      }
    }

    cart.set([]);
  }
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
      on:click={finalizePedido}
    >
      <p class="pr-2">Finalizar Pedido</p>
      <Check />
    </Button>
  </div>
</div>
