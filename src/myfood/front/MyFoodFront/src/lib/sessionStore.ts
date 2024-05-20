import { writable } from 'svelte/store';

export const session = writable({
  clienteId: null,
  mesaId: null
});

