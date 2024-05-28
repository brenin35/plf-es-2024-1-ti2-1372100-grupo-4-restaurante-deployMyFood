<script lang="ts">
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import * as Card from "$lib/components/ui/card";
  import { Button } from "$lib/components/ui/button";
  import { Plus, Minus } from "lucide-svelte";
  import { Rating } from "flowbite-svelte";
  import { onMount } from "svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import {
    fetchAvaliacao,
    calculaMediaAvaliacao,
  } from "$lib/fetchs/fetchAvaliacao";
  import { type Avaliacao, type ItemPedido, type Prato } from "$lib/types";
  import { pedidoStore } from "../../stores/pedidoStore";
  import { toast } from "svelte-sonner";

  export let id: number;
  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let imagem: string;
  export let visibilidadeAvaliacao: boolean;
  export let avaliacao: Avaliacao[] = [];

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
    avaliacao = await fetchAvaliacao();
    mediaAvaliacao = calculaMediaAvaliacao(avaliacao, id);
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

<main class="mt-5 flex items-center justify-center">
  <Dialog.Root>
    <Dialog.Trigger>
      <Card.Root class="w-auto">
        <div class="row flex">
          <div class="column w-7/12 max-w-xl h-60">
            <Card.Header>
              <Card.Title
                class="mb-2 text-2xl font-bold tracking-tight text-gray-900 "
              >
                {nome}
              </Card.Title>
            </Card.Header>
            <Card.Content class="flex items-center">
              <div class="flex-grow">
                <p class="mb-3 font-normal leading-tight text-gray-700">
                  Descricão: {descricao}
                </p>
                {#if visibilidadeAvaliacao == true}
                  <div class="flex justify-center">
                    <Rating
                      count
                      rating={parseFloat(mediaAvaliacao.toFixed(1))}
                      id="example-4"
                    >
                      <span class="w-1 h-1 mx-1.5 bg-gray-500 rounded-full" />
                      <p class="text-sm font-medium text-gray-900">
                        {#if avaliacao.filter((av) => av.produto.id === id).length == 1}
                          {avaliacao.filter((av) => av.produto.id === id)
                            .length}
                          avaliação
                        {:else}
                          {avaliacao.filter((av) => av.produto.id === id)
                            .length}
                          avaliações
                        {/if}
                      </p>
                    </Rating>
                  </div>
                {/if}
                <p class="text-2xl font-bold text-gray-900">
                  R${preco}
                </p>
              </div>
            </Card.Content>
          </div>
          <div class="column w-5/12 max-h-60">
            <img
              src={imagem}
              alt=""
              class="ml-auto h-full w-60 rounded-r-lg object-cover"
            />
          </div>
        </div>
      </Card.Root>
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
