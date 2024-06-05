<script lang="ts">
  import Button from "$lib/components/ui/button/button.svelte";
  import { onMount } from "svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import {
    ENDPOINT_URL,
    DEPLOY_FRONT_URL,
    API_URL,
    SIZE,
  } from "$lib/constants";
  import { criarMesa, deleteMesa } from "$lib/fetchs/fetchMesas";
  import type { PageData } from "./$types";

  export let data: PageData;
  let cardapioMesa = `${DEPLOY_FRONT_URL}/mesas`;
  let mesas: Mesa[] = data.mesas;
  let qrLinks: string[] = [];
  let promise = fetch(`${ENDPOINT_URL}/mesas`);

  type Mesa = {
    id: number;
  };

  onMount(async () => {
    await mesas;
    qrLinks = mesas.map((mesa) => gerarQRCode(mesa.id));
  });

  function gerarQRCode(mesaId: number) {
    return `${API_URL}${cardapioMesa}/${mesaId}${SIZE}`;
  }

  async function handleDeleteMesa(id: number) {
    try {
      await deleteMesa(id);
      await mesas;
      window.location.reload();
    } catch (error) {
      console.error("Erro deletando mesa:", error);
    }
  }

  async function handleCreateMesa() {
    try {
      await criarMesa();
      await mesas;
    } catch (error) {
      console.error("Erro criando mesa:", error);
    }
  }
</script>

<div class="py-4">
  <div class="mb-5 mt-5 flex flex-col sm:flex-row justify-between">
    <h1 class="text-center text-4xl font-bold text-secondary">
      Mesas restaurante
    </h1>
    <Button on:click={handleCreateMesa} variant="buttonAdd"
      >Cadastrar mesa</Button
    >
  </div>
  {#await promise}
    <div class="flex items-center justify-center mt-20">
      <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
    </div>
  {:then}
    {#if mesas.length == 0}
      <div class="flex justify-center items-center mt-40">
        <h1 class="text-xl text-center">Nenhuma mesa registrada!</h1>
      </div>
    {:else}
      <div
        class="grid grid-cols-1 gap-5 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-4 justify-center items-center"
      >
        {#each mesas as mesa, index (mesa.id)}
          <div
            class="w-full border shadow-lg p-5 flex flex-col gap-2 rounded-lg"
          >
            <h2 class="text-center text-xl">Mesa {mesa.id}</h2>
            {#await qrLinks}
              <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
            {:then}
              <img src={qrLinks[index]} alt="QR Code" /><br />
            {/await}
            <div class="flex gap-2 flex-col xl:flex-row">
              <Button
                variant="buttonDD"
                on:click={() => handleDeleteMesa(mesa.id)}>Deletar mesa</Button
              >
              <Button variant="buttonDD">
                <a
                  class="flex justify-end"
                  target="_blank"
                  href={qrLinks[index]}
                  download="qrcode">Download</a
                >
              </Button>
            </div>
          </div>
        {/each}
      </div>
    {/if}
  {/await}
</div>
