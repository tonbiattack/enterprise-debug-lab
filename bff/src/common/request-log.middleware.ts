import type { NextFunction, Request, Response } from 'express'
import { Injectable, NestMiddleware } from '@nestjs/common'
import { randomUUID } from 'node:crypto'

@Injectable()
export class RequestLogMiddleware implements NestMiddleware {
  use(req: Request, res: Response, next: NextFunction): void {
    const requestId = req.header('x-request-id') ?? randomUUID()
    const startedAt = Date.now()
    req.headers['x-request-id'] = requestId
    res.setHeader('X-Request-Id', requestId)
    res.on('finish', () => {
      console.info(JSON.stringify({ timestamp: new Date().toISOString(), requestId, service: 'bff', method: req.method, endpoint: req.originalUrl, status: res.statusCode, duration: Date.now() - startedAt }))
    })
    next()
  }
}
