# Git履歴の対応

受講者は、対象の「バグ導入」コミットへチェックアウトしてIssueだけを根拠に調査し、自身の修正とテストを作成できます。その後、対応する「修正」コミットと`solutions/`を比較してください。`main`はすべての修正を含み、全テストが成功する状態です。

| ID | バグ導入コミット | 修正コミット | 比較の主対象 |
| --- | --- | --- | --- |
| B01 | `65bd069` | `f10bf86` | BFFの下流エラー変換 |
| B02 | `15d7662` | `f87a939` | Redisへの保存条件 |
| B03 | `19210b9` | `9d252ec` | WireMock mappingの優先度 |
| B04 | `be378e0` | `cb8e69e` | `bodyFileName`と`__files` |
| B05 | `f82bf2c` | `0066b39` | XML namespaceの契約 |
| B06 | `ccca36d` | `d5ad183` | 在庫Entityの楽観ロック |
| B07 | `4053490` | `88706f7` | 一覧向けの一括クエリ |
| B08 | `25191e5` | `4be47f5` | 注文処理のTransaction境界 |
| B09 | `f443245` | `041f440` | 非nullableな表示名の契約 |
| B10 | `d1ac98f` | `80f9ca0` | 外部APIへのrequestId伝播 |
| B11 | `95e6f25` | `c82685b` | KubernetesのRedisサービス名 |
| B12 | `8f795a2` | `8a814b5` | 顧客単位のQuery cache key |

たとえばB02では、次のように教材状態を開きます。

```bash
git checkout 15d7662
# Issueを読み、再現・調査・修正・回帰テスト追加を行う
git diff 15d7662 f87a939 -- backend/src/main/java/com/example/sales/customers/CustomerCachePolicy.java
```

教材の終了後は、`main`へ戻します。

```bash
git checkout main
```
