# 観測ランブック

各HTTPリクエストでは `timestamp`、`requestId`、`service`、`method`、`endpoint`、`status`、`duration` を確認します。最初にブラウザで `X-Request-Id` を控え、BFF、Backend、外部スタブの順で同じ値を検索してください。結果不正時には、HTTPステータスを成功・失敗だけで判断せず、応答本文とBFFの変換前後を比較します。

キャッシュ疑いではRedisのキー、値、TTLを確認して、外部レスポンスが変更された後も同じ値が返るかを比べます。DB疑いではSQLログと更新件数、トランザクション境界、並行実行時のversionを観測します。WireMock疑いではmappingの優先度、条件、`__files`、受信履歴を確認します。
