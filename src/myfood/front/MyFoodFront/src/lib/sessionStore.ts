import { writable } from 'svelte/store';

function persist(key: string, value: any) {
  if (typeof window !== 'undefined') {
    const storedValue = window.localStorage.getItem(key);
    if (storedValue) {
      value = JSON.parse(storedValue);
    }

    const store = writable(value);

    store.subscribe(($value) => {
      window.localStorage.setItem(key, JSON.stringify($value));
    });

    return store;
  } else {
    return writable(value);
  }
}

export const session = persist('session', { clienteId: null, mesaId: null });