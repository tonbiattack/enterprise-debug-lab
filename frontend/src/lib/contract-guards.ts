import type { CustomerDetail } from '../api/contracts.js'

export function requireCustomerDisplayName(customer: CustomerDetail): string {
  if (customer.displayName.trim().length === 0) {
    throw new Error('Customer detail violates the required displayName contract')
  }
  return customer.displayName
}
