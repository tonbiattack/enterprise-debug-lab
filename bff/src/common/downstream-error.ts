export class DownstreamError extends Error {
  constructor(readonly status: number, readonly body: string) {
    super(`Backend request failed with ${status}`)
  }
}

export async function propagateDownstream<T>(response: Response): Promise<T> {
  if (!response.ok) throw new DownstreamError(response.status, await response.text())
  return response.json() as Promise<T>
}
