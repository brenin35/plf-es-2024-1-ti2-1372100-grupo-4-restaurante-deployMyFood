<script>
  import Button from "$lib/components/ui/button/button.svelte";
  import { ShieldCheck, UserCheck, ListOrdered } from "lucide-svelte";
</script>

<svelte:head>
  <title>MyFood</title>
  <meta name="description" content="TI-2 MyFood project" />
</svelte:head>

<div class="p-4 sm:ml-64">
  <div class="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:px-8">
    <div class="text-center">
      <h1 class="text-4xl font-bold text-secondary mb-4">
        Bem vindo ao <span class="text-primary">MyFood</span>!
      </h1>
      <p class="text-lg mb-8">
        Sua plataforma definitiva para gerenciar o cardápio e pedidos do seu
        restaurante.
      </p>
      <div class="mb-12">
        <Button href={"/cardapio"} variant="buttonAdd"
          >Comece agora adicionando produtos</Button
        >
      </div>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
      <div class="bg-white p-6 rounded-lg shadow">
        <div
          class="flex justify-between mb-4 text-center items-center content-center"
        >
          <h2 class="text-xl font-semibold text-gray-800">
            Gerenciamento de menus
          </h2>
          <ShieldCheck />
        </div>

        <p class="text-gray-600 text-center">
          Atualize, adicione ou remova itens de seu menu sem esforço com nossa
          interface intuitiva.
        </p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow">
        <div
          class="flex justify-between mb-4 text-center items-center content-center"
        >
          <h2 class="text-xl font-semibold text-gray-800">
            Engajamento do cliente
          </h2>
          <UserCheck />
        </div>
        <p class="text-gray-600 text-center">
          Mantenha-se conectado com seus clientes por meio de nosso sistema de
          feedback e promoções.
        </p>
      </div>
      <div class="bg-white p-6 rounded-lg shadow">
        <div
          class="flex justify-between mb-4 text-center items-center content-center"
        >
          <h2 class="text-xl font-semibold text-gray-800">
            Gerencie pedidos das mesas
          </h2>
          <ListOrdered />
        </div>
        <p class="text-gray-600 text-center">
          Visualize pedidos feito pelos clientes e facilite esse processo.
        </p>
      </div>
    </div>
  </div>
</div>
