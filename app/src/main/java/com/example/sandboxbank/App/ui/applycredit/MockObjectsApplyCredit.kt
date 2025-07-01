package com.example.sandboxbank.App.ui.applycredit

import com.example.sandboxbank.App.ui.applycredit.entity.ApplyCreditResponse
import kotlin.random.Random

    //Запрос на бэк (POST /credit/create)

    //Если ответ успешный, то случайная открытая дебетовая карта пополниться на выбранную сумму

    //Если не удалось выполнить запрос на открытие из-за отсутствия интернета, сохранить запрос
    // в отложенные, показать пользователю заглушку. Как только запрос выполнится из отложенных
    // показать пользователю уведомление, что кредит успешно оформлен*

    //Можно оформить до 3 кредитов, при этом сумма их задолженности должна быть менее 5 000 000,
    // если пользователь уже оформил 3 кредита или превысил лимит в 5 000 000 сделать кнопку "оформить
    // кредит" неактивной, показать текст с описанием причины над кнопкой

    fun sendMockRequest(): ApplyCreditResponse {

        val resultArray = arrayListOf(
            ApplyCreditResponse.Success,
            ApplyCreditResponse.NoConnection,
            ApplyCreditResponse.CreditAmountLimit,
        )
        val numRows = resultArray.size

        return resultArray[Random.nextInt(numRows)]
    }



