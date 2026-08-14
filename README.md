# Enterprise Debug Lab

`Enterprise Debug Lab` は、販売管理ポータルを題材にしたフルスタックのデバッグ練習教材です。目的は機能を追加することではなく、観測、仮説、切り分け、原因特定、最小修正、回帰テストを実務に近い経路で反復することです。

## 構成

ブラウザは Nuxt 3 を介して NestJS BFF に接続し、BFF は Spring Boot の業務APIのみを呼び出します。業務APIは PostgreSQL、Redis、WireMock を利用します。各サービスは `X-Request-Id` を用い、操作をまたいだログ相関を可能にします。

| コマンド | 用途 |
| --- | --- |
| `docker compose up --build` | フルスタックを起動する。 |
| `pnpm install && pnpm typecheck` | TypeScriptの型検査を実行する。 |
| `pnpm test` | Frontend・BFFの回帰テストを実行する。 |
| `mvn -f backend/pom.xml test` | Backendの回帰テストを実行する。 |
| `./scripts/verify.sh` | 文書、型検査、全テスト、差分をまとめて検証する。 |

## 学習の始め方

まず `docs/issues/` の1件を選び、解答資料を開かずに症状を再現します。BFFとBackendの構造化ログ、ブラウザのHTTP通信、PostgreSQLのSQLログ、Redisのキー・TTL、WireMockの受信リクエストを順に比較してください。バグ導入コミットでは不具合を再現し、自分の修正と回帰テストを作成した後に、対応する修正コミットと `solutions/` を参照します。

Issue・解答資料・Git履歴の対応は [SUMMARY.md](SUMMARY.md) と [coverage-matrix.md](coverage-matrix.md) にあります。
