<script lang="ts">
  import { Skeleton } from "$lib/components/ui/skeleton";
  import * as Card from "$lib/components/ui/card";
  import { Rating } from "flowbite-svelte";
</script>

<Card.Root class="w-auto border shadow-lg">
  <div class="row flex">
    <div class="column w-7/12 max-w-xl h-60">
      <Card.Header>
        <Skeleton class="mb-2 h-8 flex-grow"/>
      </Card.Header>
      <Card.Content class="flex items-center">
        <div class="flex-grow">
            <Skeleton class="mb-2 h-4 flex-grow"/>
            <Skeleton class="mb-4 h-4 flex-grow"/>
            <Skeleton class="mb-3 h-6 flex-grow"/>
        </div>
      </Card.Content>
    </div>
    <div class="column w-5/12 max-h-60">
      <Skeleton class="ml-auto h-full rounded-r-lg object-cover"/>
    </div>
  </div>
</Card.Root>

