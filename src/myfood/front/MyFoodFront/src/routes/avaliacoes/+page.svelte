<script lang="ts">
  //import type { PageData } from "./$types";
  import Reviews from "$lib/components/Reviews.svelte";
  import { onMount } from "svelte";
  import { Rating, AdvancedRating } from "flowbite-svelte";

  //export let data: PageData;

  let id: number;
  let avaliacao: {
    produto_id: number;
    estrelas: number;
    comentario: string;
  }[] = [];

  type Prato = {
    id: number;
    nome: string;
  };
  let pratos: Prato[] = [];

  async function fetchAvaliacao() {
    try {
      const response = await fetch("http://localhost:3000/avaliacao");
      if (!response.ok) {
        throw new Error("Falha fetch nas avaliacoes");
      }
      avaliacao = await response.json();
    } catch (error) {
      console.error(error);
    }
  }

  function calculaMediaAvaliacao(
    avaliacoes: { produto_id: number; estrelas: number }[],
    produto_id: number
  ) {
    const avaliacoesProduto = avaliacoes.filter(
      (avaliacao) => avaliacao.produto_id === produto_id
    );
    if (avaliacoesProduto.length === 0) {
      return 0;
    }
    const totalStars = avaliacoesProduto.reduce(
      (acc, avaliacao) => acc + avaliacao.estrelas,
      0
    );
    return totalStars / avaliacoesProduto.length;
  }
  let mediaAvaliacao = 0;

  function contadorPercentAvaliacao(
    avaliacoes: { produto_id: number; estrelas: number }[]
  ) {
    let ratings = [0, 0, 0, 0, 0];

    avaliacoes.forEach((avaliacao) => {
      ratings[avaliacao.estrelas - 1]++;
    });
    const totalRatings = avaliacoes.length;

    const ratingPercentages = ratings.map(
      (rating) => (rating / totalRatings) * 100
    );
    return ratingPercentages;
  }

  function calculaMediaAvaliacaoTotal(
    avaliacao: { produto_id: number; estrelas: number }[]
  ) {
    if (avaliacao.length === 0) {
      return 0;
    }
    const sum = avaliacao.reduce(
      (total, current) => total + current.estrelas,
      0
    );
    const average = sum / avaliacao.length;

    return average;
  }

  let starRatings = contadorPercentAvaliacao(avaliacao);
  let mediaAvaliacaoTotal = 0;

  onMount(async () => {
    await fetchAvaliacao();
    starRatings = contadorPercentAvaliacao(avaliacao);
    mediaAvaliacao = calculaMediaAvaliacao(avaliacao, id);
    mediaAvaliacaoTotal = calculaMediaAvaliacaoTotal(avaliacao);

    const response = await fetch("http://localhost:3000/produtos");
    if (!response.ok) {
      console.error("Erro ao buscar produtos:", response.status);
      return;
    }
    pratos = await response.json();
  });
  console.log(mediaAvaliacaoTotal);
</script>

<div class="p-4 sm:ml-64">
  <div class="py-12">
    <div class="gap-0 py-1">
      <div class="items-center gap-0 pb-7">
        <h1 class="text-center text-4xl font-bold text-secondary">
          Avaliacões por prato
        </h1>
      </div>
    </div>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      {#each pratos as prato}
        {#if avaliacao.filter((av) => av.produto_id === prato.id).length === 0}
          <div class="bg-white rounded-lg shadow-md p-4">
            <p class="text-xl font-semibold">Avaliações de {prato.nome}</p>
            <p class="text-primary text-xl mt-4">
              {prato.nome} não possui nenhuma avaliação
            </p>
          </div>
        {:else}
          <div class="bg-white rounded-lg shadow-md p-4">
            <p class="text-xl font-semibold">Avaliações de {prato.nome}</p>
            {#each avaliacao.filter((av) => av.produto_id === prato.id) as review}
              <div class="mt-4 flex">
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
              <p>Comentário: {review.comentario}</p>
            {/each}
          </div>
        {/if}
      {/each}
    </div>
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
