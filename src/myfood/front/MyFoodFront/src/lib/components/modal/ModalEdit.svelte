<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import * as Card from "$lib/components/ui/card";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";

  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let imagem: string;

  function atualizarProduto() {
    const novoNomeElement = document.getElementById("name");
    const novaDescricaoElement = document.getElementById("category");
    const novoPrecoElement = document.getElementById("price");

    if (novoNomeElement && novaDescricaoElement && novoPrecoElement) {
      const novoNome = (novoNomeElement as HTMLInputElement).value;
      const novaDescricao = (novaDescricaoElement as HTMLInputElement).value;
      const novoPreco = (novoPrecoElement as HTMLInputElement).value;

      let produtos = JSON.parse(localStorage.getItem("produtos") || "[]");

      const index = produtos.findIndex((produto: any) => produto.nome === nome);

      if (index !== -1) {
        produtos[index].nome = novoNome;
        produtos[index].descricao = novaDescricao;
        produtos[index].preco = novoPreco;
        produtos[index].imagem = imagem;

        localStorage.setItem("produtos", JSON.stringify(produtos));
      } else {
        console.error("Produto não encontrado no localStorage.");
      }
    } else {
      console.error("Elementos de entrada não encontrados.");
    }
  }

  function excluirProduto() {
    const confirmDelete = window.confirm(
      "Tem certeza de que deseja excluir este produto?"
    );

    if (confirmDelete) {
      let produtos = JSON.parse(localStorage.getItem("produtos") || "[]");
      const index = produtos.findIndex((produto: any) => produto.nome === nome);

      if (index !== -1) {
        produtos.splice(index, 1);
        localStorage.setItem("produtos", JSON.stringify(produtos));
      } else {
        console.error("Produto não encontrado no localStorage.");
      }
    } else {
    }
  }

  function converterImg(event) {
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
          <div class="column">
            <Card.Header>
              <Card.Title
                class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white"
                >{nome}</Card.Title
              >
            </Card.Header>
            <Card.Content class="flex items-center">
              <div class="flex-grow">
                <p
                  class="mb-3 font-normal leading-tight text-gray-700 dark:text-gray-400"
                >
                  Descricao: {descricao}
                </p>
                <p class="text-2xl font-bold text-gray-900 dark:text-white">
                  R${preco}
                </p>
              </div>
            </Card.Content>
          </div>
          <div class="column">
            <img
              src={imagem}
              alt=""
              class="ml-auto h-52 w-48 rounded-r-xl object-cover"
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
          <Input id="name" value={nome} class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="category" class="text-right">Descricao</Label>
          <Textarea
            placeholder="Escreva aqui a descricao do prato..."
            id="description"
            value={descricao}
            class="col-span-3 resize-none h-24"
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="price" class="text-right">Preco</Label>
          <Input id="price" value={preco} class="col-span-3" />
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
