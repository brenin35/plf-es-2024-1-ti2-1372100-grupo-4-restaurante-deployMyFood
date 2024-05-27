<script lang="ts">
    import type { PageData } from './$types';
    
    export let data: PageData;

    let cliente = data.clienteId;
</script>

<pre>
    {JSON.stringify(cliente,null,2)}
</pre>