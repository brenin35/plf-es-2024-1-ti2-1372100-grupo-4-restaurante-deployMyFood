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
  
  <div class="p-4 sm:ml-64">
    <p>Mesa {id}</p>
  </div>
  