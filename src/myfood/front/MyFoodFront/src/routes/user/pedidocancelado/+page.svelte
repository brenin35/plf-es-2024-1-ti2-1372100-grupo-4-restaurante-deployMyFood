<script lang="ts">
  import { onMount } from "svelte";

  let clienteId: number;

  onMount(() => {
    const storedClienteId = localStorage.getItem("clienteId");

    if (storedClienteId) {
      clienteId = parseInt(storedClienteId, 10);
      if (!isNaN(clienteId)) {
        alertUser();
      } else {
        console.error("Cliente ID is not a valid number");
      }
    } else {
      console.error("Cliente não registrado");
    }
  });

  function alertUser() {
    const message =
      "Seu pagamento foi cancelado. Clique OK para retornar à pagina de pagamento!";
    if (window.confirm(message)) {
      window.location.href = `/user/pedidoscliente/${clienteId}`;
    }
  }
</script>