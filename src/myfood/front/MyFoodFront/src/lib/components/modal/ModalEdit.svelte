<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import * as Card from "$lib/components/ui/card";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import Switch from "../ui/switch/switch.svelte";
  import { Rating } from "flowbite-svelte";

  export let id: number;
  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let imagem: string;
  export let visibilidadeAvaliacao: boolean;
  export let avaliacao;

  const atualizarProduto = () => {
    const produto = { nome, descricao, preco, imagem, visibilidadeAvaliacao };

    fetch(`http://localhost:3000/produtos/edit/${id}`, {
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

  function excluirProduto() {
    const confirmDelete = window.confirm(
      "Tem certeza de que deseja excluir este produto?"
    );

    if (confirmDelete) {
      fetch(`http://localhost:3000/produtos/delete/${id}`, {
        method: "DELETE",
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Produto excluído com sucesso:", data);
          window.location.reload();
        })
        .catch((error) => {
          console.error("Erro:", error);
        });
    }
  }

  function converterImg(event: { target: { files: any[] } }) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      imagem = reader.result as string;
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  }
</script>

<main class="mt-5 flex items-center justify-center">
  <Dialog.Root>
    <Dialog.Trigger>
      <Card.Root class="w-auto">
        <div class="row flex">
          <div class="column w-7/12">
            <Card.Header>
              <Card.Title
                class="text-2xl font-bold tracking-tight text-gray-900 dark:text-white"
                >{nome}</Card.Title
              >
            </Card.Header>
            <Card.Content class="flex items-center">
              <div class="flex-grow">
                <p
                  class="mb-2 font-normal leading-tight text-gray-700 dark:text-gray-400"
                >
                  Descricao: {descricao}
                </p>

                {#if visibilidadeAvaliacao == true}
                  <div class=" flex justify-center">
                    <Rating count rating={4.95} id="example-4">
                      <span
                        class="w-1 h-1 mx-1.5 bg-gray-500 rounded-full dark:bg-gray-400"
                      />
                      <a
                        href="/"
                        class="text-sm font-medium text-gray-900 underline hover:no-underline dark:text-white"
                      >
                        73 reviews
                      </a>
                    </Rating>
                  </div>
                {/if}

                <p class="text-2xl font-bold text-gray-900 dark:text-white">
                  R${preco}
                </p>
              </div>
            </Card.Content>
          </div>
          <div class="column w-5/12 h-auto">
            <img
              src={imagem}
              alt=""
              class="ml-auto h-full w-60 rounded-r-lg object-cover"
            />
          </div>
        </div>
      </Card.Root>
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
          <Input
            id="imagem"
            type="file"
            accept="image/*"
            class="col-span-3"
            on:change={converterImg}
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label class="text-right">Visibilidade</Label>
          <Switch bind:checked={visibilidadeAvaliacao} />
          <p class="w-max text-sm text-right font-light text-primary">
            (Alternar visibilidade das avaliacoes)
          </p>
        </div>
      </div>
      <Dialog.Footer>
        <Dialog.Close>
          <Button variant="buttonAdd" on:click={excluirProduto}
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
