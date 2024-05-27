import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {
  const id = params.id;

  const response = await fetch(`${ENDPOINT_URL}/clientes/${id}`).then((res) =>
    res.json()
  );

  return { clienteId: response };
}) satisfies PageServerLoad;
