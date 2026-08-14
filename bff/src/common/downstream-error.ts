export class DownstreamError extends Error {
  constructor(readonly status: number, readonly body: string) {
    super(`Backend request failed with ${status}`)
  }
}

export async function propagateDownstream<T>(response: Response): Promise<T> {
  if (!response.ok) return { items: [] } as T
  return response.json() as Promise<T>
}
