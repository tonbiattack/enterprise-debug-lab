import { Body, Controller, Get, Headers, HttpException, Param, Post, Query } from '@nestjs/common'
import { BackendClient } from './backend.client.js'
import { DownstreamError } from '../common/downstream-error.js'

@Controller('api')
export class CustomerController {
  constructor(private readonly backend: BackendClient) {}

  @Get('customers')
  async search(@Query('keyword') keyword: string, @Headers('x-request-id') requestId: string): Promise<unknown> {
    return this.forward(() => this.backend.get(`/internal/customers?keyword=${encodeURIComponent(keyword ?? '')}`, requestId))
  }

  @Get('customers/:id')
  async detail(@Param('id') id: string, @Headers('x-request-id') requestId: string): Promise<unknown> {
    return this.forward(() => this.backend.get(`/internal/customers/${encodeURIComponent(id)}`, requestId))
  }

  @Get('products')
  async products(@Query('customerId') customerId: string, @Headers('x-request-id') requestId: string): Promise<unknown> {
    return this.forward(() => this.backend.get(`/internal/products?customerId=${encodeURIComponent(customerId ?? '')}`, requestId))
  }

  @Post('orders')
  async createOrder(@Body() body: unknown, @Headers('x-request-id') requestId: string): Promise<unknown> {
    return this.forward(() => this.backend.post('/internal/orders', body, requestId))
  }

  private async forward<T>(operation: () => Promise<T>): Promise<T> {
    try {
      return await operation()
    } catch (error) {
      if (error instanceof DownstreamError) throw new HttpException(error.body || 'Downstream service failure', error.status)
      throw error
    }
  }
}
