package org.futurlab.amexexcerise.service

import org.futurlab.amexexcerise.exceptions.GiftCardIDNotFoundException
import org.futurlab.amexexcerise.models.GiftCardModelDBResponse
import org.futurlab.amexexcerise.models.GiftCardModelRequest
import org.futurlab.amexexcerise.models.GiftCardModelResponse
import kotlin.jvm.Throws

interface AMEXServiceI {
    fun createAGiftCard(giftCardModelRequest: GiftCardModelRequest) : GiftCardModelDBResponse;

    @Throws(GiftCardIDNotFoundException::class)
    fun getGiftCard(id: String): GiftCardModelResponse;

    fun getGiftCards(id: Array<Int>): List<GiftCardModelResponse>

    fun getAllGiftCards(): List<GiftCardModelResponse>

    @Throws(GiftCardIDNotFoundException::class)
    fun deleteAnGiftCard(id: String);

    //fun useGiftCards(id: Array<Int>);

    //fun upDateGiftCard(giftCardModelRequest: GiftCardModelRequest, id: String)
}