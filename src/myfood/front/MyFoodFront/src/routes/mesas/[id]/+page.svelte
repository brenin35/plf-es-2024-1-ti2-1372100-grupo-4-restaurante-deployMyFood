<script lang="ts">
  import type { PageData } from "./$types";
  import { Diamonds } from "svelte-loading-spinners";
  import { Button } from "$lib/components/ui/button/index.js";
  import * as Card from "$lib/components/ui/card/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";

  export let data: PageData;

  let mesa = data.mesas;
  let cliente = data.clientes;
  let endpoint = "http://localhost:8080";
  let promise = fetch(`${endpoint}/mesas`);

  cliente.mesaId = mesa.id;

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

    const response = await fetch(`${endpoint}/clientes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(clienteData),
    });

    if (response.ok) {
      const data = await response.json();
      console.log("Cliente adicionado com sucesso:", data);
      window.location.href =`/mesas/${mesa.id}/cardapio`;
    } else {
      const errorMessage = await response.text();
      console.error("Falha ao adicionar cliente:", errorMessage);
    }
  };
</script>

<div class="p-4 sm:ml-64">
  <h1 class="text-center text-xl mb-4">
    Cadastro em <strong>{mesa.nomeMesa}</strong>
  </h1>
  <div class="flex justify-center items-center">
    {#await promise}
      <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
    {:then}
      <Card.Root class="w-[400px] shadow-lg">
        <Card.Header>
          <Card.Title>Cadastre em mesa {mesa.id}</Card.Title>
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
                  required
                />
              </div>
              <div class="flex flex-col space-y-1.5">
                <Label for="telefone">Telefone</Label>
                <Input
                  bind:value={cliente.contatoCliente}
                  id="phone"
                  placeholder="(31) 98765-4321"
                  required
                />
              </div>
            </div>
          </form>
        </Card.Content>
        <Card.Footer class="flex justify-end">
          {#if cliente.nomeCliente && cliente.contatoCliente}
              <Button type="submit" variant="buttonAdd" on:click={criarCliente}
                >Cadastrar!</Button
              >
          {:else}
            <Button type="submit" variant="buttonAdd" disabled
              >Cadastrar!</Button
            >
          {/if}
        </Card.Footer>
      </Card.Root>
    {/await}
  </div>
</div>
