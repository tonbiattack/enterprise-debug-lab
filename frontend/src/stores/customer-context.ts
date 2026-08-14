export interface CustomerContextState {
  selectedCustomerId: string | null
}

export function selectCustomer(state: CustomerContextState, customerId: string): CustomerContextState {
  return { ...state, selectedCustomerId: customerId }
}
