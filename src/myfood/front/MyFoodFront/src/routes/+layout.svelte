<script>
  import "../app.pcss";
  import Navbar from "$lib/components/navbar/Navbar.svelte";
</script>

<div class="app">
  <main>
    <Navbar />
    <div class="p-4 sm:ml-64">
      <slot></slot>
    </div>
  </main>
</div>
