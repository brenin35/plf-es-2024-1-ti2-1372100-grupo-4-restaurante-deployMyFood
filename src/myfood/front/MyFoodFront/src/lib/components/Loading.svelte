<script lang="ts">
  import SkeletonCard from "./cards/SkeletonCard.svelte";

  let items = Array.from({ length: 9 });
</script>

{#each items as _, index}
  <SkeletonCard />
{/each}
