import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {

  const response = await fetch(`${ENDPOINT_URL}/produtos`).then((res) =>
    res.json()
  );

  return { produtos: response };
}) satisfies PageServerLoad;
