export default defineNuxtConfig({
  modules: ['@pinia/nuxt'],
  runtimeConfig: {
    public: {
      bffBaseUrl: process.env.NUXT_PUBLIC_BFF_BASE_URL ?? 'http://localhost:3001'
    }
  },
  devtools: { enabled: false }
})
