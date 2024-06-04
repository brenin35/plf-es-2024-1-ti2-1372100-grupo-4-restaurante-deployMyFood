<script>
	import { ENDPOINT_URL } from '$lib/constants';
	import { onMount } from 'svelte';

	let quantity = 1;
	let unitPrice = 10;
	let amount = unitPrice * quantity;

	let payerFirstName = '';
	let payerLastName = '';
	let payerEmail = '';
	let identificationType = 'CPF';
	let identificationNumber = '';

	let showCart = true;
	let showPaymentForm = false;
	let showResult = false;

	let loading = false;
	let error = false;
	let success = false;
	let errorMessage = '';
	let paymentDetails = {
		id: '',
		status: '',
		detail: '',
		qrCodeBase64: '',  // Add field to store QR code
		qrCode: ''         // Add field to store QR code URL
	};

	function updatePrice() {
		amount = unitPrice * quantity;
	}

	function sanitizeIdentificationNumber(number) {
		return number.replace(/\D/g, ''); // Remove all non-numeric characters
	}

	function sendPayment(event) {
		event.preventDefault();

		const sanitizedNumber = sanitizeIdentificationNumber(identificationNumber);

		const paymentData = {
			transactionAmount: Number(amount),
			description: 'Some book',
			payer: {
				firstName: payerFirstName,
				lastName: payerLastName,
				email: payerEmail,
				identification: {
					type: identificationType,
					number: sanitizedNumber,
				}
			},
		};

		console.log('Sending payment data:', paymentData);

		loading = true;
		fetch(`${ENDPOINT_URL}/process_payment`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(paymentData),
		})
			.then(response => response.json())
			.then(result => {
				loading = false;
				if (result.status !== 400) {
					paymentDetails.id = result.id;
					paymentDetails.status = result.status;
					paymentDetails.detail = result.detail;
					paymentDetails.qrCodeBase64 = result.qrCodeBase64;  // Set QR code base64 data
					paymentDetails.qrCode = result.qrCode;              // Set QR code URL
					success = true;
				} else {
					errorMessage = result.message;
					error = true;
				}
				showPaymentForm = false;
				showResult = true;
			})
			.catch(error => {
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

<main class="min-h-screen bg-gray-100">
	<!-- Shopping Cart -->
	{#if showCart}
	<section class="shopping-cart">
		<div class="container mx-auto p-6">
			<div class="block-heading text-center mb-8">
				<h2 class="text-2xl font-bold">Shopping Cart</h2>
				<p>This is an example of a Mercado Pago integration</p>
			</div>
			<div class="content bg-white shadow rounded-lg p-6">
				<div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
					<div>
						<div class="items">
							<div class="product">
								<div class="info flex">
									<img class="w-24 h-24 object-cover mr-4" src="/img/product.png" alt="Product Image">
									<div class="product-details">
										<h5 class="text-lg font-semibold">Product</h5>
										<p><b>Description: </b><span>Some book</span><br>
											<b>Author: </b>Dale Carnegie<br>
											<b>Number of pages: </b>336<br>
											<b>Price:</b> $ <span>{unitPrice}</span></p>
										<div>
											<label for="quantity" class="block text-sm font-medium">Quantity</label>
											<input type="number" bind:value={quantity} min="1" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" on:change={updatePrice}>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="summary bg-gray-50 p-6 rounded-lg shadow-md">
						<h3 class="text-lg font-semibold">Cart</h3>
						<div class="summary-item flex justify-between mt-4">
							<span class="text-sm">Subtotal</span><span class="text-sm font-semibold">$ {amount}</span>
						</div>
						<button class="btn mt-4 w-full bg-indigo-600 text-white py-2 rounded-md hover:bg-indigo-700" on:click={handleCheckout}>Checkout</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	{/if}
	<!-- Payment -->
	{#if showPaymentForm}
	<section class="payment-form">
		<div class="container mx-auto p-6">
			<div class="block-heading text-center mb-8">
				<h2 class="text-2xl font-bold">PIX Payment</h2>
				<p>This is an example of a Mercado Pago integration</p>
			</div>
			<div class="form-payment bg-white shadow rounded-lg p-6">
				<div class="products mb-6">
					<h2 class="title text-lg font-semibold">Summary</h2>
					<div class="item flex justify-between">
						<span class="price">$ {unitPrice}</span>
						<p class="item-name">Book x <span>{quantity}</span></p>
					</div>
					<div class="total flex justify-between mt-4">Total<span class="price font-semibold">$ {amount}</span></div>
				</div>
				<div class="payment-details">
					<form on:submit={sendPayment}>
						<h3 class="title text-lg font-semibold mb-4">Buyer Details</h3>
						<div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
							<div class="form-group">
								<input bind:value={payerFirstName} type="text" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="First name"/>
							</div>
							<div class="form-group">
								<input bind:value={payerLastName} type="text" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="Last name"/>
							</div>
						</div>
						<div class="form-group mt-4">
							<input bind:value={payerEmail} type="email" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="E-mail"/>
						</div>
						<div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-4">
							<div class="form-group">
								<select bind:value={identificationType} class="form-select mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
									<option value="CPF">CPF</option>
									<option value="CNPJ">CNPJ</option>
								</select>
							</div>
							<div class="form-group">
								<input bind:value={identificationNumber} type="text" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" placeholder="Identification number" />
							</div>
						</div>
						<div class="form-group mt-6">
							<button type="submit" class="btn w-full bg-indigo-600 text-white py-2 rounded-md hover:bg-indigo-700">Pay</button>
						</div>
						{#if loading}
							<p class="text-center mt-4">Loading, please wait...</p>
						{/if}
						<div class="form-group mt-4">
							<a href="#" class="text-indigo-600 hover:underline" on:click={goBack}>Go back to Shopping Cart</a>
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
				<h2 class="text-2xl font-bold">Payment Result</h2>
				<p>This is an example of a Mercado Pago integration</p>
			</div>
			<div class="content bg-white shadow rounded-lg p-6">
				<div class="product info product-details">
					<div class="flex justify-center">
						<div class="product-info text-center">
							{#if error}
								<img src="/img/fail.png" width="350px" class="mx-auto my-4">
								<p class="text-lg font-semibold text-red-600">Something went wrong</p>
								<p>{errorMessage}</p>
							{:else}
								<p><b>ID: </b><span>{paymentDetails.id}</span></p>
								<p><b>Status: </b><span>{paymentDetails.status}</span></p>
								<p><b>Detail: </b><span>{paymentDetails.detail}</span></p>
								{#if paymentDetails.qrCodeBase64}
									<p><b>QR Code:</b></p>
									<img src={`data:image/png;base64,${paymentDetails.qrCodeBase64}`} alt="QR Code" class="mx-auto my-4"/>
								{/if}
								{#if paymentDetails.qrCode}
									<p><b>QR Code URL:</b> <a href="{paymentDetails.qrCode}" class="text-indigo-600 hover:underline">{paymentDetails.qrCode}</a></p>
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
	.btn {
		margin-top: 20px;
	}
</style>
