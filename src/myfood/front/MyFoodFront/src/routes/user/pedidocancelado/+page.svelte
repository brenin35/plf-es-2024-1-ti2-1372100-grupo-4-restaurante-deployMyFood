<script lang="ts">
  import { onMount } from "svelte";

  let clienteId: number;

  onMount(async () => {
    const storedClienteId = localStorage.getItem("clienteId");

    if (storedClienteId) {
      clienteId = parseInt(storedClienteId, 10);
      if (!isNaN(clienteId)) {
        console.log("Cliente ID:", clienteId);
      } else {
        console.error("Cliente ID is not a valid number");
      }
    } else {
      console.error("Cliente não registrado");
    }
  });
</script>

<h1 class="text-center text-xl">
  Seu pagamento foi cancelado, clique <a class="text-primary no-underline hover:underline" href="/user/pedidoscliente/{clienteId}"
    >aqui</a
  > para retornar à pagina de pagamento!
</h1>
