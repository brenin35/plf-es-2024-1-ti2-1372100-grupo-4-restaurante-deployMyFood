<script lang="ts">
  import Reviews from "$lib/components/Reviews.svelte";
  import { onMount } from "svelte";
  import { Rating, AdvancedRating } from "flowbite-svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import { ENDPOINT_URL } from "$lib/constants";
  import { getProdutos } from "$lib/fetchs/fetchProdutos";
  import {
    fetchAvaliacao,
    calculaMediaAvaliacao,
    contadorPercentAvaliacao,
    calculaMediaAvaliacaoTotal,
  } from "$lib/fetchs/fetchAvaliacao";
  import type { Avaliacao, Prato } from "$lib/types";

  let pratos: Prato[] = [];
  let avaliacoes: Avaliacao[] = [];
  let starRatings: number[] = [];
  let mediaAvaliacaoTotal = 0;

  let promise = fetch(`${ENDPOINT_URL}/produtos`);

  onMount(async () => {
    avaliacoes = await fetchAvaliacao();
    starRatings = contadorPercentAvaliacao(avaliacoes);
    mediaAvaliacaoTotal = calculaMediaAvaliacaoTotal(avaliacoes);

    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await getProdutos();
  });
</script>

<div class=" ">
  <div class="py-12">
    <div class="gap-0 py-1">
      <div class="items-center gap-0 pb-7">
        <h1 class="text-center text-4xl font-bold text-secondary">
          Avaliações por prato
        </h1>
      </div>
    </div>
    {#await promise}
      <div class="flex items-center justify-center mt-20">
        <Diamonds size="60" color="#FF3E00" unit="px" duration="1s" />
      </div>
    {:then}
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {#each pratos as prato}
          <div class="bg-white rounded-lg shadow-md p-4 text-center border">
            <p class="text-xl font-semibold mb-4">
              Avaliações de {prato.nome}
            </p>
            {#if avaliacoes.filter((av) => av.produto.id === prato.id).length === 0}
              <p class="text-primary text-xl mt-4">
                {prato.nome} não possui nenhuma avaliação
              </p>
            {:else}
              {#each avaliacoes.filter((av) => av.produto.id === prato.id) as review}
                <hr />
                <div class="flex justify-center mt-4">
                  <Rating total={5} rating={review.estrelas}>
                    <p
                      slot="text"
                      class="ms-2 text-sm font-medium text-gray-500 "
                    >
                      {review.estrelas} de 5
                    </p>
                  </Rating>
                </div>
                <p class="mb-4">Comentário: {review.comentario}</p>
              {/each}
            {/if}
          </div>
        {/each}
      </div>
    {/await}
    <div class="mt-10">
      <h1 class="text-xl mb-3">Avaliações totais!</h1>
      <div>
        <Reviews
          five={starRatings[4]}
          four={starRatings[3]}
          three={starRatings[2]}
          two={starRatings[1]}
          one={starRatings[0]}
          from={mediaAvaliacaoTotal.toFixed(1)}
          total={avaliacoes.length}
        />
      </div>
    </div>
  </div>
</div>
