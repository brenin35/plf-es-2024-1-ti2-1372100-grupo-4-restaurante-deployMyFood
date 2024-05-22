<script lang="ts">
  import Reviews from "$lib/components/Reviews.svelte";
  import { onMount } from "svelte";
  import { Rating, AdvancedRating } from "flowbite-svelte";
  import { Diamonds } from "svelte-loading-spinners";
  import { ENDPOINT_URL } from "$lib/constants";
  import { getProdutos } from "$lib/components/fetchs/fetchProdutos";
  import {
    fetchAvaliacao,
    calculaMediaAvaliacao,
    contadorPercentAvaliacao,
    calculaMediaAvaliacaoTotal,
  } from "$lib/components/fetchs/fetchAvaliacao";
  import { type Avaliacao, type Prato } from "$lib/types";

  let id: number;
  let avaliacao: Avaliacao[] = [];

  let pratos: Prato[] = [];

  let promise = fetch(`${ENDPOINT_URL}/produtos`);

  let mediaAvaliacao = 0;
  let starRatings: number[] = [];
  let mediaAvaliacaoTotal = 0;

  onMount(async () => {
    avaliacao = await fetchAvaliacao();
    starRatings = contadorPercentAvaliacao(avaliacao);
    mediaAvaliacao = calculaMediaAvaliacao(avaliacao, id);
    mediaAvaliacaoTotal = calculaMediaAvaliacaoTotal(avaliacao);

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
          Avaliacões por prato
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
          {#if avaliacao.filter((av) => av.produtoId === prato.id).length === 0}
            <div class="bg-white rounded-lg shadow-md p-4 text-center border">
              <p class="text-xl font-semibold">Avaliações de {prato.nome}</p>
              <p class="text-primary text-xl mt-4">
                {prato.nome} não possui nenhuma avaliação
              </p>
            </div>
          {:else}
            <div class="bg-white rounded-lg shadow-md p-4 text-center border">
              <p class="text-xl font-semibold mb-4">
                Avaliações de {prato.nome}
              </p>
              {#each avaliacao.filter((av) => av.produtoId === prato.id) as review}
                <hr />
                <div class="flex justify-center mt-4">
                  <p>Avaliação:</p>
                  <Rating id="" total={5} rating={review.estrelas}>
                    <p
                      slot="text"
                      class="ms-2 text-sm font-medium text-gray-500 dark:text-gray-400"
                    >
                      {review.estrelas} de 5
                    </p>
                  </Rating>
                </div>
                <p class="mb-4">Comentário: {review.comentario}</p>
              {/each}
            </div>
          {/if}
        {/each}
      </div>
    {/await}
    <div class="mt-10">
      <h1 class="text-xl mb-3">Avaliacões totais!</h1>
      <div>
        {#key starRatings}
          <AdvancedRating
            ratings={[
              { label: "5 Estrelas", rating: starRatings[4] },
              { label: "4 Estrelas", rating: starRatings[3] },
              { label: "3 Estrelas", rating: starRatings[2] },
              { label: "2 Estrelas", rating: starRatings[1] },
              { label: "1 Estrelas", rating: starRatings[0] },
            ]}
          >
            <span slot="rating">
              {#key mediaAvaliacaoTotal}
                <Rating total={5} rating={mediaAvaliacaoTotal} id="example-8">
                  <p
                    slot="text"
                    class="text-sm font-medium text-gray-500 dark:text-gray-400 ms-2"
                  >
                    {mediaAvaliacaoTotal} de 5
                  </p>
                </Rating>
              {/key}
            </span>
            <p
              slot="globalText"
              class="mt-2 text-sm font-medium text-gray-500 dark:text-gray-400"
            >
              {avaliacao.length} avaliacões no total!
            </p>
          </AdvancedRating>
        {/key}
      </div>
    </div>
  </div>
</div>
