export interface CustomerSummary {
  id: string
  name: string
  tier: 'STANDARD' | 'GOLD'
}

export interface CustomerDetail {
  id: string
  displayName: string
  recentOrderCount: number
}

export interface InventoryItem {
  productId: string
  productName: string
  availableQuantity: number
}

export interface SearchResponse {
  items: CustomerSummary[]
}
