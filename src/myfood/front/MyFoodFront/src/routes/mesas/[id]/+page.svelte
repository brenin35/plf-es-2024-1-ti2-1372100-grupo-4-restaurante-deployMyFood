<script lang="ts">
  import { onMount } from "svelte";
  let CadastroCliente;

  onMount(async () => {
    const module = await import("$lib/components/cards/CadastroCliente.svelte");
    CadastroCliente = module.default;
  });

  let endpoint = "http://localhost:8080";
</script>

<div class="p-4 sm:ml-64">
  <h1>Mesa</h1>
  <div class="flex justify-center items-center">
    {#if CadastroCliente}
      <CadastroCliente />
    {/if}
  </div>
</div>
