<script lang="ts">
  import SkeletonCard from "./cards/SkeletonCard.svelte";
  import { produtos } from "$lib/stores/produtoStore";
</script>

{#each $produtos as i}
  <SkeletonCard />
{/each}
