import type { CustomerDetail, InventoryItem, SearchResponse } from './contracts.js'

export class BffClient {
  constructor(private readonly baseUrl: string) {}

  async searchCustomers(keyword: string, requestId: string): Promise<SearchResponse> {
    return this.request<SearchResponse>(`/api/customers?keyword=${encodeURIComponent(keyword)}`, requestId)
  }

  async getCustomer(id: string, requestId: string): Promise<CustomerDetail> {
    return this.request<CustomerDetail>(`/api/customers/${encodeURIComponent(id)}`, requestId)
  }

  async getInventory(customerId: string, requestId: string): Promise<InventoryItem[]> {
    return this.request<InventoryItem[]>(`/api/products?customerId=${encodeURIComponent(customerId)}`, requestId)
  }

  private async request<T>(path: string, requestId: string): Promise<T> {
    const response = await fetch(`${this.baseUrl}${path}`, { headers: { 'X-Request-Id': requestId } })
    if (!response.ok) throw new Error(`BFF request failed with ${response.status}`)
    return response.json() as Promise<T>
  }
}
