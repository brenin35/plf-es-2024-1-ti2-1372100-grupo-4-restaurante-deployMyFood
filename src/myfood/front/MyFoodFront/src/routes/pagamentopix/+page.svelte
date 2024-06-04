<script lang="ts">
  import { MP_KEY, ENDPOINT_URL } from "$lib/constants";
  import { onMount } from "svelte";

  onMount(() => {
    const mp = new MercadoPago(`${MP_KEY}`, {
      locale: "pt-BR",
    });

    const bricksBuilder = mp.bricks();

    const renderWalletBrick = async () => {
      const settings = {
        initialization: {
          redirectMode: "modal",
          preferenceId: null, // This will be set later
        },
        customization: {
          texts: {
            action: "pay",
            valueProp: "security_details",
          },
          visual: {
            hideValueProp: true,
            buttonBackground: "black", // default, black, blue, white
            valuePropColor: "grey", // grey, white
            buttonHeight: "48px", // min 48px - max free
            borderRadius: "6px",
            verticalPadding: "16px", // min 16px - max free
            horizontalPadding: "0px", // min 0px - max free
          },
          checkout: {
            theme: {
              elementsColor: "#ee1105", // color hex code
              headerColor: "#ee1105", // color hex code
            },
          },
        },
        callbacks: {
          onReady: () => {
            // Callback called when Brick is ready.
          },
          onSubmit: async (formData) => {
            try {
              const response = await fetch(`${ENDPOINT_URL}/api/create_payment`, {
                method: "POST",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify({
                  amount: formData.transaction_amount,
                  token: formData.token,
                  description: "Product description",
                  paymentMethodId: "visa", // Adjust this as necessary
                  email: formData.email, // Make sure to get the email from formData or user data
                }),
              });

              const data = await response.json();
              settings.initialization.preferenceId = data.id; // Set the preference ID
              await bricksBuilder.create("wallet", "wallet_container", settings);
            } catch (error) {
              console.error(error);
            }
          },
        },
      };

      await bricksBuilder.create("wallet", "wallet_container", settings);
    };

    renderWalletBrick();
  });
</script>

<svelte:head>
  <script src="https://sdk.mercadopago.com/js/v2"></script>
</svelte:head>

<div id="wallet_container"></div>
