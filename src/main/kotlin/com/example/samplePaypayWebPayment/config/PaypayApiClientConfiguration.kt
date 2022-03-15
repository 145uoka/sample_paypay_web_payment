package com.example.samplePaypayWebPayment.config

import com.example.samplePaypayWebPayment.Properties.PaypayApiProperties
import jp.ne.paypay.ApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaypayApiClientConfiguration(
    private val paypayApiProperties: PaypayApiProperties
) {

    @Bean
    fun apiClient(): ApiClient {
        val apiClient = jp.ne.paypay.Configuration().defaultApiClient
        apiClient.setProductionMode(paypayApiProperties.productionMode) // true:本番環境, false:テスト環境
        apiClient.setApiKey(paypayApiProperties.apiKey)                 // 発行されたAPIキー
        apiClient.setApiSecretKey(paypayApiProperties.apiSecretKey)     // 発行されたシークレット
        apiClient.setAssumeMerchant(paypayApiProperties.assumeMerchant) // 発行された店舗ID
        return apiClient
    }
}