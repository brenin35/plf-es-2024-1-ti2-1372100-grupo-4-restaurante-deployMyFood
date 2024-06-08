<script>
  export let five;
  export let four;
  export let three;
  export let two;
  export let one;

  export let total;

  export let from;

  let stars = [false, false, false, false, false];

  $: {
    for (let i = 0; i < 5; i++) {
      stars[i] = i < from;
    }
  }
</script>

<div class="flex items-center mb-2">
  {#each stars as filled, index}
    <svg
      class="w-4 h-4 {filled ? 'text-primary' : 'text-gray-300'} me-1"
      aria-hidden="true"
      xmlns="http://www.w3.org/2000/svg"
      fill="currentColor"
      viewBox="0 0 22 20"
    >
      <path
        d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"
      />
    </svg>
  {/each}
  <p class="ms-1 text-sm font-medium text-gray-500">{from}</p>
  <p class="ms-1 text-sm font-medium text-gray-500">de</p>
  <p class="ms-1 text-sm font-medium text-gray-500">5</p>
</div>

<p class="text-sm font-medium text-gray-500">
  {total} avaliações no total!
</p>

<div class="flex items-center mt-4">
  <p class="text-sm font-medium">5 estrelas</p>
  <div class="w-2/4 h-5 mx-4 bg-gray-200 rounded">
    <div class="h-5 animated-gradient rounded" style="width: {five}%"></div>
  </div>
  <span class="text-sm font-medium text-gray-500">{five}%</span>
</div>
<div class="flex items-center mt-4">
  <p class="text-sm font-medium">4 estrelas</p>
  <div class="w-2/4 h-5 mx-4 bg-gray-200 rounded">
    <div class="h-5 animated-gradient rounded" style="width: {four}%"></div>
  </div>
  <span class="text-sm font-medium text-gray-500">{four}%</span>
</div>
<div class="flex items-center mt-4">
  <p class="text-sm font-medium">3 estrelas</p>
  <div class="w-2/4 h-5 mx-4 bg-gray-200 rounded">
    <div class="h-5 animated-gradient rounded" style="width: {three}%"></div>
  </div>
  <span class="text-sm font-medium text-gray-500">{three}%</span>
</div>
<div class="flex items-center mt-4">
  <p class="text-sm font-medium">2 estrelas</p>
  <div class="w-2/4 h-5 mx-4 bg-gray-200 rounded">
    <div class="h-5 animated-gradient rounded" style="width: {two}%"></div>
  </div>
  <span class="text-sm font-medium text-gray-500">{two}%</span>
</div>
<div class="flex items-center mt-4">
  <p class="text-sm font-medium">1 estrela &nbsp;</p>
  <div class="w-2/4 h-5 mx-4 bg-gray-200 rounded">
    <div class="h-5 animated-gradient rounded" style="width: {one}%"></div>
  </div>
  <span class="text-sm font-medium text-gray-500">{one}%</span>
</div>

<div class="flex flex-col items-start mt-10">
  <p class="text-lg font-medium mb-2">Percentual de Avaliações Positivas (avaliacoes com 4 e 5 estrelas)</p>
  <div class="w-full flex gap-3"> 
    <div class="w-2/4 h-5 bg-gray-200 rounded">
      <div class="h-5 animated-gradient rounded" style="width: {five + four}%"></div>
    </div>
    <span class="text-sm font-medium text-gray-500">{five + four}%</span>
  </div>
</div>

