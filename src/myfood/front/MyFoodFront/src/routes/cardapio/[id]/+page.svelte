<script lang="ts">
  import CadastroCliente from "$lib/components/cards/CadastroCliente.svelte";

  let endpoint = "http://localhost:8080";
</script>

<div class="p-4 sm:ml-64">
  <h1>Mesa</h1>
  <div class="flex justify-center items-center">
    <CadastroCliente />
  </div>
</div>