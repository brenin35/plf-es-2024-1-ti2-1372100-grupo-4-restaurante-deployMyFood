<script lang="ts" context="module">
  // import type { PageData } from "./$types";

  // export let data: PageData;

  let inputText = "";
  let textPresent = false;
  let API_URL = "https://api.qrserver.com/v1/create-qr-code/?data=";
  let size = "&size=[150]x[150]";
  let full_link = "";

  function dataSubmit(e) {
    if (inputText !== "") {
      textPresent = true;
      full_link = API_URL + inputText + size;
    }
    console.log(full_link);
  }
</script>

<div class="p-4 sm:ml-64">
  <h1>QR CODE GENERATOR</h1>
  <form on:submit|preventDefault={dataSubmit}>
    <input
      bind:value={inputText}
      class="textInput"
      type="text"
      placeholder="Enter any text or url..."
    />
    <input class="btn" type="submit" value="Submit" />
  </form> 
  {#if textPresent}
    <img src={full_link} alt="" /><br />
    <a href={full_link} download>Download</a>
  {:else}
    <p>No QR code yet! ☹️</p>
  {/if}
</div>
