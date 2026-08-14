import assert from 'node:assert/strict'
import test from 'node:test'
import { customerQueryKeys } from '../src/composables/queryKeys.js'

test('B12: inventory cache keys distinguish customers', () => {
  assert.notDeepEqual(customerQueryKeys.inventory('customer-a'), customerQueryKeys.inventory('customer-b'))
})
