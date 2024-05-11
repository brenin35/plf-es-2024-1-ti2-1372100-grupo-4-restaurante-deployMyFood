<script context="module">
  export async function load({ params }) {
    const { id } = params;

    return {
      props: {
        id,
      },
    };
  }
</script>

<script>
  export let id;
</script>

<p>Mesa {id}</p>
