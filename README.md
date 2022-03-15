# sample_paypay_web_payment
PaypayのWebPaymentApiの利用sample

# 詳細
https://qiita.com/145uoka/items/4f9e7267e8dc44d8c3c7

# PayPayDocument
https://developer.paypay.ne.jp/products/docs/webpayment#demo-heading

# 動作確認をする場合
[application.yaml](https://github.com/145uoka/sample_paypay_web_payment/blob/main/src/main/resources/application.yaml)の以下を自分用に修正。

```
api-key: [YOUR_API_KEY]                 # 発行されたAPIキー
api-secret-key: [YOUR_SECRET_KEY]       # 発行されたシークレット
assume-merchant: [YOUR_ASSUME_MERCHANT] # 発行された店舗ID
```

bootrunした後、
[http://localhost:8080](http://localhost:8080)へアクセス
