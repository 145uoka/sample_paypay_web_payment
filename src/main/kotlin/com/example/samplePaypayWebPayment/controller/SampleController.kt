package com.example.samplePaypayWebPayment.controller

import com.example.samplePaypayWebPayment.adaptor.PaypayApiAdaptor
import jp.ne.paypay.model.MerchantOrderItem
import jp.ne.paypay.model.MoneyAmount
import jp.ne.paypay.model.QRCode
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.time.Instant

@Controller
class SampleController(
    private val paypayApiAdaptor: PaypayApiAdaptor
) {
    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("selectProduct")
    }

    @PostMapping("/order")
    fun order(
        @RequestParam("quantity") quantity: Int
    ): ModelAndView {
        val merchantPaymentId = Instant.now().toEpochMilli().toString()

        val orderItems = listOf(
            MerchantOrderItem()
                .name("バウムクーヘン")
                .category("ケーキ")
                .productId("XXX001")
                .quantity(quantity)
                .unitPrice(MoneyAmount().amount(500).currency(MoneyAmount.CurrencyEnum.JPY))
        )

        val response = paypayApiAdaptor.createQrCode(
            merchantPaymentId = merchantPaymentId,
            amount = orderItems.sumOf { it.unitPrice.amount * it.quantity },
            orderItems = orderItems,
            orderDescription = "注文説明",
            isAuthorization = null,
            redirectUrl = "http://localhost:8080/paymentDetails/%s".format(merchantPaymentId),
            redirectType = QRCode.RedirectTypeEnum.WEB_LINK,
            userAgent = null
        )
        println(response)

        return ModelAndView("redirect:" + response.data.url)
    }

    @GetMapping("/paymentDetails/{merchantPaymentId}")
    fun getPaymentDetails(
        @PathVariable("merchantPaymentId") merchantPaymentId: String
    ): ModelAndView {
        val response = paypayApiAdaptor.getPaymentDetails(merchantPaymentId = merchantPaymentId)
        print(response)
        return ModelAndView("paymentDetails").addObject("result", response.data)
    }
}