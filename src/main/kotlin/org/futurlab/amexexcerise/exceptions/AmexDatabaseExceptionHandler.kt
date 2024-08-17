package org.futurlab.amexexcerise.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class AmexDatabaseExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(GiftCardIDNotFoundException::class)
    fun handleIDNotPresentException(e: GiftCardIDNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        return handleExceptionInternal(e,
            e.message,
            HttpHeaders.EMPTY,
            HttpStatus.NOT_FOUND,
            request)
    }
}