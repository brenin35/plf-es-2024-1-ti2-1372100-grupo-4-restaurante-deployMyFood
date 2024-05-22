<script>
  import NavbarCliente from "$lib/components/navbar/NavbarCliente.svelte";
  import "../app.pcss";
  import Navbar from "$lib/components/navbar/Navbar.svelte";
  import { onMount } from "svelte";

  let isAdmin = true;

  onMount(() => {
  if (typeof localStorage !== 'undefined') {
    isAdmin = localStorage.getItem('isAdmin') === 'true';
    console.log(isAdmin);
  }
});

</script>

<div class="app">
  <main>
    {#if isAdmin}
      <Navbar />
    {:else}
      <NavbarCliente />
    {/if}
    <div class="p-4 sm:ml-64">
      <slot></slot>
    </div>
  </main>
</div>
