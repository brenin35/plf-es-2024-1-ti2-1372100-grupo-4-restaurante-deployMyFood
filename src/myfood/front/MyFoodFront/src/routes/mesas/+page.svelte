<script lang="ts">
  import { onMount } from "svelte";
  // import type { PageData } from "./$types";

  // export let data: PageData;

  let endpoint = "http://localhost:8080";
  let cardapioMesa = "https://plf-es-2024-1-ti2-1372100-grupo-4-restaurante-deploy-my-food.vercel.app/cardapio";
  let mesas: Mesa[] = [];
  let qrLinks: string[] = [];
  let API_URL = "https://api.qrserver.com/v1/create-qr-code/?data=";
  let SIZE = "&size=[150]x[150]";

  type Mesa = {
    id: number;
    nome: string;
  };

  onMount(async () => {
    const response = await fetch(`${endpoint}/mesas`);
    if (!response.ok) {
      console.error("Erro ao buscar mesas:", response.status);
      return;
    }
    mesas = await response.json();
    qrLinks = mesas.map((mesa) => generateQRCode(mesa.id));
  });

  function generateQRCode(mesaId: number) {
    return `${API_URL}${cardapioMesa}/${mesaId}${SIZE}`;
  }
</script>

<div class="p-4 sm:ml-64">
  <h1>QR CODE GENERATOR</h1>

  {#each mesas as mesa, i}
    <div>
      <h2>{mesa.nome}</h2>
      <img src={qrLinks[i]} alt="QR Code" /><br />
      <a href={qrLinks[i]} download>Download</a>
    </div>
  {:else}
    <p>No mesas available! ☹️</p>
  {/each}
</div>
