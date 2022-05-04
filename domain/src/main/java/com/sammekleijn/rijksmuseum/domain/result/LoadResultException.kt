package com.sammekleijn.rijksmuseum.domain.result

class LoadResultException(val errorType: ErrorType) : Throwable("Error while fetching data: $errorType")

fun ErrorType.toLoadResultException() = LoadResultException(this)
