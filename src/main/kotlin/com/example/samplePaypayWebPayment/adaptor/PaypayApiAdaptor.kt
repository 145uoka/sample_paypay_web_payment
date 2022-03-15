package com.example.samplePaypayWebPayment.adaptor

import jp.ne.paypay.ApiClient
import jp.ne.paypay.api.PaymentApi
import jp.ne.paypay.model.*
import org.springframework.stereotype.Component


@Component
class PaypayApiAdaptor(
    private val apiClient: ApiClient
) {

    fun createQrCode(
        merchantPaymentId: String,
        amount: Int,
        orderItems: List<MerchantOrderItem>,
        orderDescription: String?,
        isAuthorization: Boolean?,
        redirectUrl: String?,
        redirectType: QRCode.RedirectTypeEnum?,
        userAgent: String?
    ): QRCodeDetails {
        val qrCode = QRCode()
        qrCode.amount = MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY)
        qrCode.merchantPaymentId = merchantPaymentId
        qrCode.codeType = "ORDER_QR"
        qrCode.orderItems = orderItems
        orderDescription?.let { qrCode.orderDescription = it }
        isAuthorization?.let { qrCode.isAuthorization(isAuthorization) }
        redirectUrl?.let { qrCode.redirectUrl = redirectUrl }
        redirectType?.let { qrCode.redirectType = redirectType }
        userAgent?.let { qrCode.userAgent = userAgent }

        return PaymentApi(apiClient).createQRCode(qrCode)
    }

    fun getPaymentDetails(
        merchantPaymentId: String
    ): PaymentDetails {
        return PaymentApi(apiClient).getCodesPaymentDetails(merchantPaymentId)
    }
}