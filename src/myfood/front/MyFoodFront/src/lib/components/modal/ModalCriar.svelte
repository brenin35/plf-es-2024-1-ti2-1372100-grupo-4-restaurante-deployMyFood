<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { ENDPOINT_URL } from "$lib/constants";

  let nome = "";
  let descricao = "";
  let imagemURL = "";
  let preco = 0;
  export let imagemFile: File | null = null;
  let visibilidadeAvaliacao = true;

  function handleFileChange(event: any) {
    imagemFile = event.target.files[0];
  }

  async function uploadImagem(file: any) {
  const formData = new FormData();
  formData.append("file", file);

  const response = await fetch(`${ENDPOINT_URL}/file/upload`, {
    method: "POST",
    body: formData,
  });

  if (!response.ok) {
    throw new Error("File upload failed");
  }

  const data = await response.text();
  return data; 
}

async function adicionarProduto() {
  if (!nome || !descricao || !preco) {
    alert("Por favor, preencha todos os campos obrigatórios.");
    return;
  }

  try {
    let imagem = "";
    if (imagemFile) {
      imagem = await uploadImagem(imagemFile);
      // Update the state variable with the URL
      imagemURL = imagem;
    }

    const produto = {
      nome,
      descricao,
      preco,
      imagem,
      visibilidadeAvaliacao,
    };

    const response = await fetch(`${ENDPOINT_URL}/produtos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto),
    });

    if (!response.ok) {
      throw new Error("Failed to add product");
    }

    const data = await response.json();
    console.log("Produto adicionado com sucesso:", data);
    window.location.reload();
  } catch (error) {
    console.error("Erro:", error);
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
            on:change={handleFileChange}
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
