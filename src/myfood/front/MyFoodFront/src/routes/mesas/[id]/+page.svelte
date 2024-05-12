<script lang="ts">
  import { onMount } from "svelte";
  import type { PageData } from "./$types";

  export let data: PageData;

  let mesa = data;
  let CadastroCliente;

  onMount(async () => {
    const module = await import("$lib/components/cards/CadastroCliente.svelte");
    CadastroCliente = module.default;
  });
</script>

<div class="p-4 sm:ml-64">
  <h1 class="text-center text-xl mb-4">Cadastro em {mesa.nomeMesa}</h1>
  <div class="flex justify-center items-center">
    {#if CadastroCliente}
      <CadastroCliente
        cliente={{ nomeCliente: "", contatoCliente: "", mesaId: "" }}
        id={mesa.id}
      />
    {/if}
  </div>
</div>
