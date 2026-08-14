export const customerQueryKeys = {
  search: (keyword: string) => ['customers', 'search', keyword] as const,
  detail: (customerId: string) => ['customers', 'detail', customerId] as const,
  inventory: (customerId: string) => ['inventory', customerId] as const
}
