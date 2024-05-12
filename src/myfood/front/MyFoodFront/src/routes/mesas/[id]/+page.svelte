<script lang="ts">
  import { onMount } from "svelte";
  import type { PageData } from "./$types";
  import CadastroCliente from "$lib/components/cards/CadastroCliente.svelte";

  export let data: PageData;

  let mesa = data;
  let CadastroClienteComponent: typeof CadastroCliente;

  onMount(async () => {
    const module = await import("$lib/components/cards/CadastroCliente.svelte");
    CadastroClienteComponent = module.default;
  });
</script>

<div class="p-4 sm:ml-64">
  <h1 class="text-center text-xl mb-4">Cadastro em <strong>{mesa.nomeMesa}</strong></h1>
  <div class="flex justify-center items-center">
    {#if CadastroClienteComponent}
    <CadastroClienteComponent cliente={{ nomeCliente: "", contatoCliente: "", mesaId: 0 }} id={parseInt(mesa.id)} />
    {/if}
  </div>
</div>
