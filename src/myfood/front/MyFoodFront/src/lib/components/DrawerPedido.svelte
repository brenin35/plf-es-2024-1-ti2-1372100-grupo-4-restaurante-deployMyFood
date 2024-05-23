<script lang="ts">
  import * as Drawer from "$lib/components/ui/drawer";
  import Button from "./ui/button/button.svelte";
  import { postPedidos } from "$lib/fetchs/fetchPedidos";
  import { Check } from "lucide-svelte";
  import { onMount, createEventDispatcher } from "svelte";

  const dispatch = createEventDispatcher();
  let clienteId: number;

  onMount(async () => {
    localStorage.setItem("isAdmin", "false");

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

  async function postPedidosBtn() {
    if (!clienteId) {
      console.error("ClienteId not found.");
      return;
    }
    try {
      const response = await postPedidos(clienteId);

      dispatch("pedidoCreated", response);
    } catch (error) {
      console.error("Failed to post pedido:", error);
    }
  }
</script>

<Drawer.Root>
  <Drawer.Trigger
    ><Button variant="buttonAdd" type="submit" class="flex items-center mt-4">
      <p class="pr-2">Verificar pedido!</p>
      <Check />
    </Button></Drawer.Trigger
  >
  <Drawer.Content>
    <Drawer.Header>
      <Drawer.Title
        >Clique em finalizar pedido para realizar seu pedido!</Drawer.Title
      >
      <Drawer.Description
        >Seu pedido vai ser enviado para o restaurante.</Drawer.Description
      >
    </Drawer.Header>

    <Drawer.Footer>
      <Button on:click={postPedidosBtn}>Finalizar Pedido!</Button>
      <Drawer.Close>Cancelar</Drawer.Close>
    </Drawer.Footer>
  </Drawer.Content>
</Drawer.Root>
