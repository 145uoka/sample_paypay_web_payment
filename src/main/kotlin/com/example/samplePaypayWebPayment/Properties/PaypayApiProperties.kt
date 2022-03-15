package com.example.samplePaypayWebPayment.Properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "paypay")
data class PaypayApiProperties(
    val productionMode: Boolean,
    val apiKey: String,
    val apiSecretKey: String,
    val assumeMerchant: String
)
