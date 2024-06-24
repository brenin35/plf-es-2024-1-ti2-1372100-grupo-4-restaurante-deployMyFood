<script context="module">
  export const load = async () => {
    const responseAvaliacao = await fetch(`${ENDPOINT_URL}/avaliacao`).then(
      (res) => res.json()
    );
    return { props: { avaliacoes: responseAvaliacao } };
  };
</script>

<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import * as Card from "$lib/components/ui/card";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import Switch from "../ui/switch/switch.svelte";
  import { Rating } from "flowbite-svelte";
  import { onMount } from "svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { calculaMediaAvaliacao } from "$lib/fetchs/fetchAvaliacao";
  import { type Avaliacao } from "$lib/types";
  import { toast } from "svelte-sonner";
  import CardCardapio from "../cards/CardCardapio.svelte";
  import { produtos } from "$lib/stores/produtoStore";

  export let avaliacoes: Avaliacao[];
  export let id: number;
  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let imagem: string;
  export let visibilidadeAvaliacao: boolean;

  let mediaAvaliacao = 0;

  onMount(async () => {
    console.log(avaliacoes); 
    mediaAvaliacao = calculaMediaAvaliacao(avaliacoes, id);
  });

  const atualizarProduto = () => {
    const produto = { nome, descricao, preco, imagem, visibilidadeAvaliacao };

    fetch(`${ENDPOINT_URL}/produtos/edit/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Produto atualizado com sucesso:", data);
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
    console.log(visibilidadeAvaliacao);
  };

  async function excluirProduto(id: number) {
    try {
      const response = await fetch(`${ENDPOINT_URL}/produtos/delete/${id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (response.ok) {
        console.log("Produto deletado com sucesso");
        toast.success("Produto deletado com sucesso!", {
          description: "Seu produto foi deletado com sucesso.",
        });

        produtos.update((current) =>
          current.filter((produto) => produto.id !== id)
        );
      } else if (response.status === 404) {
        console.error("Produto não encontrado");
      } else {
        console.error("Falha ao deletar");
      }
    } catch (error) {
      console.error("Error deletar produto:", error);
    }
  }

  async function handleDelete() {
    if (confirm("Tem certeza que quer deletar esse produto?")) {
      await excluirProduto(id);
    }
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

    <Dialog.Content class="sm:max-w-[600px]">
      <Dialog.Header>
        <Dialog.Title>Editar {nome}</Dialog.Title>
        <Dialog.Description>
          Faca as alteracões em {nome} e depois clique em salvar!
        </Dialog.Description>
      </Dialog.Header>
      <div class="grid gap-4 py-4">
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="name" class="text-right">Nome</Label>
          <Input
            bind:value={nome}
            id="name"
            type="text"
            maxlength={17}
            class="col-span-3"
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="category" class="text-right">Descricao</Label>
          <Textarea
            bind:value={descricao}
            placeholder="Escreva aqui a descricao do prato..."
            id="description"
            maxlength={54}
            class="col-span-3 resize-none h-24"
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="price" class="text-right">Preco</Label>
          <Input bind:value={preco} id="price" class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="imagem" class="text-right">Imagem</Label>
          <Input id="imagem" type="file" accept="image/*" class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label class="text-right">Visibilidade</Label>
          <Switch bind:checked={visibilidadeAvaliacao} />
          <p class="w-max text-sm text-right font-light text-primary">
            (Alternar visibilidade das avaliações)
          </p>
        </div>
      </div>
      <Dialog.Footer>
        <Dialog.Close>
          <Button variant="buttonAdd" on:click={handleDelete}
            >Excluir prato</Button
          >
        </Dialog.Close>

        <Dialog.Close>
          <Button variant="buttonAdd" type="submit" on:click={atualizarProduto}
            >Salvar alteracões</Button
          >
        </Dialog.Close>
      </Dialog.Footer>
    </Dialog.Content>
  </Dialog.Root>
</main>
