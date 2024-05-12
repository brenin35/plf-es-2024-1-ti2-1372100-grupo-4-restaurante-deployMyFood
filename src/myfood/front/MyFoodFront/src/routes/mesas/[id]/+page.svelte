<script lang="ts">
  import { onMount } from "svelte";
  import type { PageData } from "./$types";

  export let data: PageData;

  let mesa = data;
  let CadastroCliente;
  let endpoint = "http://localhost:8080";

  onMount(async () => {
    const module = await import("$lib/components/cards/CadastroCliente.svelte");
    CadastroCliente = module.default;
  });
</script>

<div class="p-4 sm:ml-64">
  <h1>Mesa {mesa.id}</h1>
  <div class="flex justify-center items-center">
    {#if CadastroCliente}
      <CadastroCliente />
    {/if}
  </div>
</div>
