<script lang="ts">
  import { Textarea } from "$lib/components/ui/textarea/index.js";
  import * as Dialog from "$lib/components/ui/dialog/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { Button, buttonVariants } from "$lib/components/ui/button";
  import { ENDPOINT_URL } from "$lib/constants";
  import type { Avaliacao } from "$lib/types.ts";

  let estrelas: number;
  let comentario: string;
  export let produtoId: number;

  async function submitAvaliacao() {
    const newAvaliacao = {
      produto: { id: produtoId },
      estrelas: Number(estrelas),
      comentario,
    };

    try {
      const response = await fetch(`${ENDPOINT_URL}/avaliacao`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newAvaliacao),
      });

      const responseData = await response.json();
      console.log("Success:", responseData);
    } catch (error) {
      console.error("Fetch Error:", error);
    }
  }
</script>

<main class="mt-5 flex items-center justify-center">
  <Dialog.Root>
    <Dialog.Trigger>
      <Button variant="buttonAdd">Avaliar prato</Button>
    </Dialog.Trigger>
    <Dialog.Content class="sm:max-w-[600px]">
      <Dialog.Header>
        <Dialog.Title>Avaliar prato!</Dialog.Title>
        <Dialog.Description>Avalie o prato</Dialog.Description>
      </Dialog.Header>
      <div class="grid gap-4 py-4">
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="estrelas" class="text-right">Quantas estrelas?</Label>
          <Input
            placeholder="Digite o numero de estrelas"
            id="estrelas"
            type="number"
            min="1"
            max="5"
            bind:value={estrelas}
            class="col-span-3"
            required
          />
        </div>
        <div class="grid grid-cols-4 items-center gap-4">
          <Label for="comentario" class="text-right">Comentario</Label>
          <Textarea
            placeholder="Escreva um comentario para o prato..."
            id="comentario"
            maxlength={255}
            bind:value={comentario}
            class="col-span-3 resize-none h-24"
            required
          />
        </div>
      </div>
      <Dialog.Footer>
        <Dialog.Close>
          <Button variant="buttonAdd" type="submit" on:click={submitAvaliacao}
            >Avaliar</Button
          >
        </Dialog.Close>
      </Dialog.Footer>
    </Dialog.Content>
  </Dialog.Root>
</main>
