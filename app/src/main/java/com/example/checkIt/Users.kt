package com.example.checkIt

import java.io.Serializable

data class Users (
    val name: String?,
    val birthday: String?,
    val phone: String?,
    val city: String?,
    val firstDeposit: Double?
) : Serializable
