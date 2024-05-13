import type { PageServerLoad } from "./$types";

let endpoint = "http://localhost:8080";

export const load = (async ({ params }) => {
  const id = params.id;

  const response = await fetch(`${endpoint}/mesas/${id}`).then((res) => res.json());

  const responseClientes = await fetch(`${endpoint}/clientes`).then((res) => res.json());

  console.log(responseClientes);
  console.log(response);

  return { mesas: response, clientes: responseClientes };
}) satisfies PageServerLoad;
