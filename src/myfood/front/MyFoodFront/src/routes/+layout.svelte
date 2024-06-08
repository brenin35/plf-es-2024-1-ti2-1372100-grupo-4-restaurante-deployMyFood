<script>
  import "../app.pcss";
  import { Toaster } from "$lib/components/ui/sonner";

</script>

<div class="app">
  <main>
      <Toaster />
      <slot></slot>
  </main>
</div>
