<script lang="ts">
  import { Input } from "$lib/components/ui/input/index.js";
  import Button from "$lib/components/ui/button/button.svelte";
  import { ENDPOINT_URL } from "$lib/constants";
  import { onMount } from "svelte";
  import type { PageData } from "./$types";
  import type { Pedido } from "$lib/types.ts";

  export let data: PageData;

  let pedido = data.pedido;

  let amount = pedido.precoTotalPedido;

  let payerFirstName = pedido.cliente.nomeCliente;
  let telefone = pedido.cliente.telefone
  let payerLastName = "";
  let payerEmail = "";
  let identificationType = "CPF";
  let identificationNumber = "";

  let showCart = true;
  let showPaymentForm = false;
  let showResult = false;

  let loading = false;
  let error = false;
  let success = false;
  let errorMessage = "";
  let paymentDetails = {
    id: "",
    status: "",
    detail: "",
    qrCodeBase64: "", // Add field to store QR code
    qrCode: "", // Add field to store QR code URL
  };

  function updatePrice() {
    amount = pedido.precoTotalPedido;
  }

  function sanitizeIdentificationNumber(number) {
    return number.replace(/\D/g, ""); // Remove all non-numeric characters
  }

  function sendPayment(event) {
    event.preventDefault();

    const sanitizedNumber = sanitizeIdentificationNumber(identificationNumber);

    const paymentData = {
      transactionAmount: Number(amount),
      description: telefone,
      payer: {
        firstName: payerFirstName,
        lastName: payerLastName,
        email: payerEmail,
        identification: {
          type: identificationType,
          number: sanitizedNumber,
        },
      },
    };

    console.log("Sending payment data:", paymentData);

    loading = true;
    fetch(`${ENDPOINT_URL}/process_payment`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(paymentData),
    })
      .then((response) => response.json())
      .then((result) => {
        loading = false;
        if (result.status !== 400) {
          paymentDetails.id = result.id;
          paymentDetails.status = result.status;
          paymentDetails.detail = result.detail;
          paymentDetails.qrCodeBase64 = result.qrCodeBase64; // Set QR code base64 data
          paymentDetails.qrCode = result.qrCode; // Set QR code URL
          success = true;
        } else {
          errorMessage = result.message;
          error = true;
        }
        showPaymentForm = false;
        showResult = true;
      })
      .catch((error) => {
        loading = false;
        alert("Unexpected error\n" + JSON.stringify(error));
      });
  }

  function handleCheckout() {
    showCart = false;
    showPaymentForm = true;
  }

  function goBack() {
    showPaymentForm = false;
    showCart = true;
  }

  onMount(updatePrice);
</script>

<main class="bg-gray-200 rounded-lg">
  {#if showCart}
    <section class="shopping-cart">
      <div class="container mx-auto p-6">
        <div class="block-heading text-center mb-8">
          <h2 class="text-2xl font-bold">Seu pedido</h2>
          <p>Realize o pagamento do seu pedido!</p>
        </div>
        <div class="content bg-white shadow rounded-lg p-6">
          <div class="mb-4">
            <p><strong>Cliente:</strong> {pedido.cliente.nomeCliente}</p>
            <p><strong>Mesa ID:</strong> {pedido.mesa.id}</p>
            <p>
              <strong>Status de Preparo:</strong>
              {pedido.statusPreparo ? "Em Preparo" : "Pedido pronto"}
            </p>
            <p>
              <strong>Status de Pagamento:</strong>
              {pedido.statusPagamento ? "Pago" : "Não Pago"}
            </p>
            <p>
              <strong>Preço Total do Pedido:</strong>
              R${pedido.precoTotalPedido.toFixed(2)}
            </p>
          </div>
          <Button
            variant="buttonAdd"
            class="mt-4 w-full"
            on:click={handleCheckout}>Realizar o pagamento!</Button
          >
        </div>
      </div>
    </section>
  {/if}
  <!-- Payment -->
  {#if showPaymentForm}
    <section class="payment-form">
      <div class="container mx-auto p-6">
        <div class="block-heading text-center mb-8">
          <h2 class="text-2xl font-bold">Pagamento PIX</h2>
        </div>
        <div class="form-payment bg-white shadow rounded-lg p-6">
          <div class="payment-details">
            <form on:submit={sendPayment}>
              <h3 class="title text-lg font-semibold mb-4">
                Informe os detalhes
              </h3>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div class="form-group">
                  <Input
                    bind:value={payerFirstName}
                    type="text"
                    class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    placeholder="Primeiro nome"
                  />
                </div>
                <div class="form-group">
                  <Input
                    bind:value={payerLastName}
                    type="text"
                    class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    placeholder="Ultimo nome"
                  />
                </div>
              </div>
              <div class="form-group mt-4">
                <Input
                  bind:value={payerEmail}
                  type="email"
                  class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  placeholder="E-mail"
                />
              </div>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-4">
                <div class="form-group">
                  <select
                    bind:value={identificationType}
                    class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  >
                    <option value="CPF">CPF</option>
                  </select>
                </div>
                <div class="form-group">
                  <Input
                    bind:value={identificationNumber}
                    type="text"
                    class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    placeholder="Digite seu CPF"
                  />
                </div>
              </div>
              <div class="form-group mt-6">
                <Button type="submit" class="btn w-full" variant="buttonAdd">Ver QRCode</Button>
              </div>
              {#if loading}
                <p class="text-center mt-4">Gerando QRCode, aguarde...</p>
              {/if}
              <div class="form-group mt-4">
                <a
                  href="#"
                  class="text-primary hover:underline"
                  on:click={goBack}>Voltar ao detalhes do pedido</a
                >
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  {/if}
  <!-- Result -->
  {#if showResult}
    <section class="shopping-cart">
      <div class="container mx-auto p-6">
        <div class="block-heading text-center mb-8">
          <h2 class="text-2xl font-bold">Pagamento com PIX</h2>
        </div>
        <div class="content bg-white shadow rounded-lg p-6">
          <div class="product info product-details">
            <div class="flex justify-center">
              <div class="product-info text-center">
                {#if error}
                  <p class="text-lg font-semibold text-red-600">
                    Algo deu errado
                  </p>
                  <p>{errorMessage}</p>
                {:else}
                  <p><b>ID: </b><span>{paymentDetails.id}</span></p>
                  <p><b>Status: </b><span>{paymentDetails.status}</span></p>
                  <p><b>Detalhes: </b><span>{paymentDetails.detail}</span></p>
                  {#if paymentDetails.qrCodeBase64}
                    <p><b>QR Code:</b></p>
                    <img
                      src={`data:image/png;base64,${paymentDetails.qrCodeBase64}`}
                      alt="QR Code"
                      class="mx-auto my-4 w-64"
                    />
                  {/if}
                  {#if paymentDetails.qrCode}
                    <p>
                      <b>Link do QR Code</b>
                      <a
                        href={paymentDetails.qrCode}
                        class="text-primary hover:underline"
                        >{paymentDetails.qrCode}</a
                      >
                    </p>
                  {/if}
                {/if}
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  {/if}
</main>

<style>
  .container {
    margin-top: 50px;
  }
  .block-heading {
    text-align: center;
    margin-bottom: 20px;
  }
</style>
