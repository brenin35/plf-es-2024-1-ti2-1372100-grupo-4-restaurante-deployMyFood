<script context="module">
  export const load = async () => {
    const responseAvaliacao = await fetch(`${ENDPOINT_URL}/avaliacao`).then(
      (res) => res.json()
    );
    return { props: { avaliacoes: responseAvaliacao } };
  };
</script>

<script lang="ts">
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import * as Card from "$lib/components/ui/card";
  import { Button } from "$lib/components/ui/button";
  import { Plus, Minus } from "lucide-svelte";
  import { Rating } from "flowbite-svelte";
  import { onMount } from "svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { calculaMediaAvaliacao } from "$lib/fetchs/fetchAvaliacao";
  import { type Avaliacao, type ItemPedido, type Prato } from "$lib/types";
  import { pedidoStore } from "../../stores/pedidoStore";
  import { toast } from "svelte-sonner";
  import CardCardapio from "../cards/CardCardapio.svelte";

  export let avaliacoes: Avaliacao[];
  export let id: number;
  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let imagem: string;
  export let visibilidadeAvaliacao: boolean;

  let quantidade = 1;
  let itemPreco = preco;
  let mediaAvaliacao = 0;

  function increase() {
    quantidade += 1;
    itemPreco += preco;
  }

  function decrease() {
    if (quantidade > 1) {
      quantidade -= 1;
      itemPreco -= preco;
    }
  }

  onMount(async () => {
    mediaAvaliacao = calculaMediaAvaliacao(avaliacoes, id);
  });

  function adicionarProdutoAoPedido() {
    const itemPedido: ItemPedido = {
      quantidade: quantidade,
      precoItem: preco,
      precoTotal: itemPreco,
      produto: { id: id, nome: nome },
    };
    console.log(itemPedido);

    toast.success("Item adicionado ao pedido!", {
      description: "O item foi adicionado ao pedido com sucesso!",
    });

    pedidoStore.addItem(itemPedido);
  }
</script>

<main class="flex items-center justify-center">
  <Dialog.Root>
    <Dialog.Trigger>
      <CardCardapio
        {id}
        {nome}
        {preco}
        {descricao}
        {imagem}
        {visibilidadeAvaliacao}
        {mediaAvaliacao}
        {avaliacoes}
      />
    </Dialog.Trigger>
    <Dialog.Content class="max-w-[600px]">
      <Dialog.Header>
        <Dialog.Title>
          Adicionar <span class="font-bold text-primary">{nome}</span> ao seu pedido
        </Dialog.Title>
        <Dialog.Description>
          Selecione a quantidade e clique em adicionar!
        </Dialog.Description>
      </Dialog.Header>
      <div class="grid gap-4 py-4">
        <div class="flex flex-col items-center justify-center gap-4">
          <img
            src={imagem}
            alt="imagem {nome}"
            class="rounded-lg object-cover h-72 w-full"
          />
        </div>
        <div class="flex items-center justify-center">
          <h1 class="mr-3 items-center text-lg">Quantidade:</h1>
          <div class="flex items-center gap-3 text-center">
            <Button variant="buttonAdd" on:click={decrease}><Minus /></Button>
            <p class="text-xl font-bold">{quantidade}</p>
            <Button variant="buttonAdd" on:click={increase}><Plus /></Button>
          </div>
        </div>
        <p class="mt-3 text-right text-xl">
          Valor total: <span class="font-bold text-primary">R${itemPreco}</span>
        </p>
      </div>
      <Dialog.Footer>
        <Dialog.Close>
          <Button
            on:click={adicionarProdutoAoPedido}
            variant="buttonAdd"
            type="submit"
            class="flex items-center"
          >
            <p class="pr-2">Adicionar produto ao pedido</p>
            <Plus />
          </Button>
        </Dialog.Close>
      </Dialog.Footer>
    </Dialog.Content>
  </Dialog.Root>
</main>
