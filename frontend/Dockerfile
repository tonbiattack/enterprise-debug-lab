FROM node:22-alpine
WORKDIR /app
COPY package.json ./
RUN corepack enable && pnpm install --prod
COPY . .
EXPOSE 3000
CMD ["pnpm", "dev", "--host", "0.0.0.0"]
