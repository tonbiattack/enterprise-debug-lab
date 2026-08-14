import { Injectable } from '@nestjs/common'
import { propagateDownstream } from '../common/downstream-error.js'

@Injectable()
export class BackendClient {
  private readonly baseUrl = process.env.BACKEND_API_URL ?? 'http://localhost:8080'

  async get<T>(path: string, requestId: string): Promise<T> {
    const response = await fetch(`${this.baseUrl}${path}`, { headers: { 'X-Request-Id': requestId } })
    return propagateDownstream<T>(response)
  }

  async post<T>(path: string, body: unknown, requestId: string): Promise<T> {
    const response = await fetch(`${this.baseUrl}${path}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json', 'X-Request-Id': requestId },
      body: JSON.stringify(body)
    })
    return propagateDownstream<T>(response)
  }
}
