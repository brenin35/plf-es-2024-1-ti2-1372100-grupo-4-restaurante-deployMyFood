<script lang="ts">
  import { toast } from "svelte-sonner";
  import { onMount } from "svelte";
  import { loadStripe } from "@stripe/stripe-js";
  import type { Pedido } from "$lib/types.ts";
  import ModalAvaliar from "$lib/components/modal/ModalAvaliar.svelte";
  import type { PageData } from "./$types";
  import { pedidoStore } from "$lib/stores/pedidoStore";
  import Button from "$lib/components/ui/button/button.svelte";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { ENDPOINT_URL } from "$lib/constants";

  export let data: PageData;

  let stripe;
  let clienteId = data.clienteId;
  let pedidos = data.pedidos;

  const filteredPedidos = pedidos.filter(
    (pedido: Pedido) => pedido.cliente.id === clienteId.id
  );

  let precoTotalPedido: number;
  pedidoStore.precoTotalPedido.subscribe((value) => {
    precoTotalPedido = value;
  });

  onMount(async () => {
    stripe = await loadStripe(
      "pk_test_51PNExaFK72ovkSt8je6WKkEbVPo3elT8UNSGhn9UDoPEhePTefYBOUHJc2PSJcxc42iiMEdB0ppNT4KNP0BMu7nN00kHfuRIlO"
    );
  });

  async function handlePayment(pedido) {
    const response = await fetch(
      `${ENDPOINT_URL}/api/payment/create-checkout-session`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ amount: pedido.precoTotalPedido * 100 }), // Amount in cents
      }
    );

    if (response.ok) {
      const { id } = await response.json();
      stripe.redirectToCheckout({ sessionId: id });
    } else {
      console.error("Failed to create checkout session");
    }
  }

  function notify() {
    toast.success("Vá até o caixa", {
      description: "Vá até o caixa para realizar o pagamento com o atendente!",
    });
  }
</script>

{#each filteredPedidos as pedido}
  <div class="p-4">
    <h1 class="text-2xl font-bold mb-4">Detalhes do Pedido #{pedido.id}</h1>
    <div class="mb-4">
      <p><strong>Cliente ID:</strong> {pedido.cliente.id}</p>
      <p><strong>Mesa ID:</strong> {pedido.mesa.id}</p>
      <p>
        <strong>Status de Preparo:</strong>
        {pedido.statusPreparo ? "Em Preparo" : "Pedido pronto"}
      </p>
      <p>
        <strong>Status de Pagamento:</strong>
        {pedido.statusPagamento ? "Pago" : "Não Pago"}
      </p>
      <p>
        <strong>Preço Total do Pedido:</strong>
        R${pedido.precoTotalPedido.toFixed(2)}
      </p>
    </div>

    <h2 class="text-xl font-semibold mb-3">Itens do Pedido</h2>
    <div
      class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4"
    >
      {#each pedido.itensPedido as itemPedido}
        <div
          class="flex flex-col items-center justify-center gap-4 border p-4 rounded"
        >
          <p><strong>Produto ID:</strong> {itemPedido.produto.id}</p>
          {#if itemPedido.produto.nome}
            <p><strong>Nome do Produto:</strong> {itemPedido.produto.nome}</p>
          {/if}
          <p><strong>Quantidade:</strong> {itemPedido.quantidade}</p>
          <p>
            <strong>Preço Item:</strong> R${itemPedido.precoItem.toFixed(2)}
          </p>
          <p>
            <strong>Preço Total:</strong> R${itemPedido.precoTotal.toFixed(2)}
          </p>
          <ModalAvaliar produtoId={itemPedido.produto.id} />
        </div>
      {/each}
    </div>
    <div class="mt-10">
      <Dialog.Root>
        <Dialog.Trigger>
          <Button variant="buttonAdd">Escolher forma de pagamento</Button>
        </Dialog.Trigger>
        <Dialog.Content class="sm:max-w-[600px]">
          <Dialog.Header>
            <Dialog.Title>Escolha a forma de pagamento</Dialog.Title>
            <Dialog.Description>Escolha!</Dialog.Description>
          </Dialog.Header>
          <div class="grid gap-4 py-4">
            <div class="grid grid-cols-4 items-center gap-4">
              <Button variant="buttonAdd" class="w-full" on:click={notify}>Dinheiro</Button>
              <Button variant="buttonAdd" class="w-full" on:click={() => handlePayment(pedido)}>Cartão</Button>
              <a href="/pagamentopix/{pedido.id}" class="w-full">
                <Button class="w-full" variant="buttonAdd">Pix</Button>
              </a>
            </div>
          </div>
          <!-- <Dialog.Footer>
            <Dialog.Close>
              <Button variant="buttonAdd" type="submit">Avaliar</Button>
            </Dialog.Close>
          </Dialog.Footer> -->
        </Dialog.Content>
      </Dialog.Root>
    </div>
  </div>
{/each}
