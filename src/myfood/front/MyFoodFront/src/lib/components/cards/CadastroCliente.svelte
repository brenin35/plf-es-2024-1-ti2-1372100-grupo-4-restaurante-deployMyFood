<script lang="ts">
  import { Button } from "$lib/components/ui/button/index.js";
  import * as Card from "$lib/components/ui/card/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
  import { ENDPOINT_URL } from "$lib/constants";
  import {createCliente} from "../../../../../../back_novo/src/main/java/com/myfood/myfoodback/services/clientesService"

  export let id: number;
  export let cliente: {
    nomeCliente: string;
    contatoCliente: string;
    mesaId: number;
  };
  cliente.mesaId = id;

  const criarCliente = async () => {
    const clienteData = {
      nomeCliente: cliente.nomeCliente,
      contatoCliente: cliente.contatoCliente,
      mesaId: cliente.mesaId,
    };
    if (!clienteData.nomeCliente || !clienteData.contatoCliente) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    try {
      const data = await createCliente(clienteData); 
      console.log("Cliente adicionado com sucesso:", data);
      window.location.reload();
    } catch (error) {
      console.error("Falha ao adicionar cliente:", error);
    }
  };
</script>

<Card.Root class="w-[400px] shadow-lg">
  <Card.Header>
    <Card.Title>Cadastre em mesa {id}</Card.Title>
    <Card.Description
      >Coloque seu nome e telefone para acessar o Cardapio!</Card.Description
    >
  </Card.Header>
  <Card.Content>
    <form>
      <div class="grid w-full items-center gap-4">
        <div class="flex flex-col space-y-1.5">
          <Label for="name">Nome</Label>
          <Input
            bind:value={cliente.nomeCliente}
            id="name"
            placeholder="Seu nome"
          />
        </div>
        <div class="flex flex-col space-y-1.5">
          <Label for="telefone">Telefone</Label>
          <Input
            bind:value={cliente.contatoCliente}
            id="phone"
            placeholder="(31) 98765-4321"
          />
        </div>
      </div>
    </form>
  </Card.Content>
  <Card.Footer class="flex justify-end">
    <Button type="submit" variant="buttonAdd" on:click={criarCliente}
      >Cadastrar!</Button
    >
  </Card.Footer>
</Card.Root>
