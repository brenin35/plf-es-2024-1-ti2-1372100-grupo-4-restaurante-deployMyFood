<script>
  import NavbarCliente from "$lib/components/navbar/NavbarCliente.svelte";
  import "../app.pcss";
  import Navbar from "$lib/components/navbar/Navbar.svelte";
  import { onMount } from "svelte";
  import { Toaster } from "$lib/components/ui/sonner";

  let isAdmin = true;

  onMount(() => {
    if (typeof localStorage !== "undefined") {
      isAdmin = localStorage.getItem("isAdmin") === "true";
      console.log(isAdmin);
    }
  });
</script>

<div class="app">
  <main>
    <Navbar />
    <div class="p-4 sm:ml-64">
      <Toaster />
      <slot></slot>
    </div>
  </main>
</div>
