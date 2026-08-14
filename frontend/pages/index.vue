<script setup lang="ts">
import { ref } from 'vue'
import { useQuery } from '@tanstack/vue-query'
import { BffClient } from '../src/api/client'
import { customerSearchQuery } from '../src/composables/useCustomerSearch'
import { createRequestId } from '../src/lib/request-id'

const config = useRuntimeConfig()
const keyword = ref('acme')
const client = new BffClient(config.public.bffBaseUrl)
const requestId = createRequestId()
const query = useQuery(customerSearchQuery(client, keyword.value, requestId))
</script>

<template>
  <main>
    <h1>販売管理ポータル</h1>
    <label>顧客検索 <input v-model="keyword" /></label>
    <p v-if="query.isPending">検索中です。</p>
    <p v-else-if="query.isError">顧客検索に失敗しました。</p>
    <ul v-else>
      <li v-for="customer in query.data.items" :key="customer.id">{{ customer.name }}（{{ customer.tier }}）</li>
    </ul>
  </main>
</template>
