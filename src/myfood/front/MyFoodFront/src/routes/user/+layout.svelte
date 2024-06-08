<script>
    import NavbarCliente from "$lib/components/navbar/NavbarCliente.svelte";
    import "../app.pcss";
    import { Toaster } from "$lib/components/ui/sonner";
  
  </script>
  
  <div class="app">
    <main>
      <NavbarCliente />
      <div class="p-4 sm:ml-64">
        <Toaster />
        <slot></slot>
      </div>
    </main>
  </div>
  