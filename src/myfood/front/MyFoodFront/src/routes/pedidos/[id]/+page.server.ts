import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {
  const id = params.id;

  const response = await fetch(`${ENDPOINT_URL}/pedidos/${id}`).then((res) =>
    res.json()
  );

  return { pedidoId: response };
}) satisfies PageServerLoad;
