import { writable } from "svelte/store";
import type { Prato } from "$lib/types";

export const produtos = writable<Prato[]>([]);
