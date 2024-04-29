<script>
  import "../app.pcss";
  import Navbar from "$lib/components/navbar/Navbar.svelte";
</script>

<div class="app">
  <main>
    <Navbar />
    <slot></slot>
  </main>
</div>
