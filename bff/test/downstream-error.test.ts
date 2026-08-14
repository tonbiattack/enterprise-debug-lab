import assert from 'node:assert/strict'
import test from 'node:test'
import { DownstreamError, propagateDownstream } from '../src/common/downstream-error.js'

test('B01: downstream 502 is propagated instead of becoming an empty success payload', async () => {
  const response = new Response('upstream unavailable', { status: 502 })
  await assert.rejects(() => propagateDownstream(response), (error: unknown) => error instanceof DownstreamError && error.status === 502)
})
