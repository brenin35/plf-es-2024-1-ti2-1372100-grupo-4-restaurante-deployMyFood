<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { createEventDispatcher } from "svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { createProduto } from "../../../../../../back_novo/src/main/java/com/myfood/myfoodback/services/produtosService";

  const dispatch = createEventDispatcher();

  let nome = "";
  let descricao = "";
  let preco = 0;
  let imagem = "";
  let visibilidadeAvaliacao = true;

  async function adicionarProduto() {
    if (!nome || !descricao || !preco) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }
    const produto = {
      nome,
      descricao,
      preco,
      imagem,
      visibilidadeAvaliacao,
    };

    try {
      const data = await createProduto(produto);
      console.log("Produto adicionado com sucesso:", data);
      window.location.reload();
    } catch (error) {
      console.error("Erro:", error);
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
      <Button variant="buttonAdd">Adicionar prato ao cardapio!</Button>
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
          <Input
            placeholder="Digite o nome do prato..."
            id="name"
            type="text"
            maxlength={17}
            bind:value={nome}
            class="col-span-3"
            required
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="category" class="text-right">Descricao</Label>
          <Textarea
            placeholder="Escreva aqui a descricao do prato..."
            id="description"
            maxlength={54}
            bind:value={descricao}
            class="col-span-3 resize-none h-24"
            required
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="price" class="text-right">Preco</Label>
          <Input id="price" bind:value={preco} class="col-span-3" required />
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
      <Dialog.Footer
        ><Dialog.Close>
          <Button variant="buttonAdd" type="submit" on:click={adicionarProduto}
            >Adicionar produto</Button
          >
        </Dialog.Close>
      </Dialog.Footer>
    </Dialog.Content>
  </Dialog.Root>
</main>
