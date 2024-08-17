package org.futurlab.amexexcerise.exceptions

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Slf4j
@RestControllerAdvice
class AmexDatabaseExceptionHandler : ResponseEntityExceptionHandler() {

    val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(GiftCardIDNotFoundException::class)
    fun handleIDNotPresentException(e: GiftCardIDNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        logger.error(e.message)

        return handleExceptionInternal(e,
            e.message,
            HttpHeaders.EMPTY,
            HttpStatus.NOT_FOUND,
            request)
    }
}