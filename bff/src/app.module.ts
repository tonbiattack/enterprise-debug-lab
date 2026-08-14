import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common'
import { CustomerController } from './customers/customer.controller.js'
import { BackendClient } from './customers/backend.client.js'
import { RequestLogMiddleware } from './common/request-log.middleware.js'

@Module({ controllers: [CustomerController], providers: [BackendClient] })
export class AppModule implements NestModule {
  configure(consumer: MiddlewareConsumer): void {
    consumer.apply(RequestLogMiddleware).forRoutes('*')
  }
}
