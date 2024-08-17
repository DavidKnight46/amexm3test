package org.futurlab.amexexcerise.controllers

import org.futurlab.amexexcerise.models.GiftCardModelDBResponse
import org.futurlab.amexexcerise.models.GiftCardModelRequest
import org.futurlab.amexexcerise.models.GiftCardModelResponse
import org.futurlab.amexexcerise.service.AMEXServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("giftcardcontroller/")
class AMEXController(amexServiceImpl: AMEXServiceImpl) {

    val amexServiceI : AMEXServiceImpl = amexServiceImpl;

    @PostMapping("createAnGiftCard")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAnGiftCard(@RequestBody request: GiftCardModelRequest):GiftCardModelDBResponse{
        return amexServiceI.createAGiftCard(request)
    }

    @GetMapping("getAnGiftCard/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getAnGiftCard(@PathVariable id: String): GiftCardModelResponse{
        return amexServiceI.getGiftCard(id = id)
    }

    @DeleteMapping("deleteAnGiftCard/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteAnGiftCard(@PathVariable id: String){
        amexServiceI.deleteAnGiftCard(id = id)
    }

    @GetMapping("getAllGiftCards")
    @ResponseStatus(HttpStatus.OK)
    fun getAllGiftCards(): List<GiftCardModelResponse>{
        return amexServiceI.getAllGiftCards()
    }
}