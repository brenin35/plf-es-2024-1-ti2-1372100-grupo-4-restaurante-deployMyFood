import type { PageServerLoad } from "./$types";

let endpoint = "http://localhost:8080";

export const load = (async ({ params }) => {
  const id = params.id;
  const response = await fetch(`${endpoint}/mesas/${id}`).then((e) => e.json());
  console.log(response);
  return { id };
}) satisfies PageServerLoad;
