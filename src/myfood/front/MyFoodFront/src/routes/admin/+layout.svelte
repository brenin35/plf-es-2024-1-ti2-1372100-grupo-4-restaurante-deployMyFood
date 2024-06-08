<script>
    import "../app.pcss";
    import Navbar from "$lib/components/navbar/Navbar.svelte";
    import { Toaster } from "$lib/components/ui/sonner";
  
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
  