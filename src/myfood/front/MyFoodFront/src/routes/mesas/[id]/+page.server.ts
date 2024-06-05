import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {
  const id = params.id;

  const response = await fetch(`${ENDPOINT_URL}/mesas/${id}`).then((res) =>
    res.json()
  );

  const responseClientes = await fetch(`${ENDPOINT_URL}/clientes`).then((res) =>
    res.json()
  );

  return { mesas: response, clientes: responseClientes };
}) satisfies PageServerLoad;
