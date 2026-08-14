import type { BffClient } from '../api/client.js'
import { customerQueryKeys } from './queryKeys.js'

export function customerSearchQuery(client: BffClient, keyword: string, requestId: string) {
  return {
    queryKey: customerQueryKeys.search(keyword),
    queryFn: () => client.searchCustomers(keyword, requestId)
  }
}
