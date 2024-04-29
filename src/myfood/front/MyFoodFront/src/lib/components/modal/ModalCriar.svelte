<script lang="ts">
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";

  export let nome: string;
  export let preco: number;
  export let descricao: string;
  export let img: string;

  let pratos = [
    {
      nome: "",
      descricao: "",
      preco: 20,
      img: "",
    },
    {
      nome: "",
      descricao: "",
      preco: 20,
      img: "",
    },
  ];

  function addPrato() {
    const novoPrato = {
      nome: nome,
      preco: preco,
      descricao: descricao,
      img: img,
    };
    pratos.push(novoPrato);

    console.log(novoPrato);
    
  }
</script>

<main class="mt-5 flex items-center justify-center">
  <Dialog.Root>
    <Dialog.Trigger>
      <Button>Adicionar</Button>
    </Dialog.Trigger>
    <Dialog.Content class="sm:max-w-[600px]">
      <Dialog.Header>
        <Dialog.Title>Adicionar um produto</Dialog.Title>
        <Dialog.Description>
          Adicione um produto ao cardapio!
        </Dialog.Description>
      </Dialog.Header>
      <div class="grid gap-4 py-4">
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="name" class="text-right">Nome</Label>
          <Input id="name" value={nome} class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="category" class="text-right">Categoria</Label>
          <Input id="category" value={descricao} class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="price" class="text-right">Preco</Label>
          <Input id="price" value={preco} class="col-span-3" />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="imagem" class="text-right">Imagem</Label>
          <Input id="imagem" type="file" accept="image/*" class="col-span-3" />
        </div>
      </div>
      <Dialog.Footer>
        <Button type="submit" on:click={addPrato}>Adicionar produto</Button>
      </Dialog.Footer>
    </Dialog.Content>
  </Dialog.Root>
</main>
