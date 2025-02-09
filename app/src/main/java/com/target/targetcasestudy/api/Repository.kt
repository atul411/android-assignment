package com.target.targetcasestudy.api

import javax.inject.Inject


class Repository(private val dealApiKtx: DealApiKtx) {
    suspend fun retrieveDeals(): DealResponse {
        return dealApiKtx.retrieveDeals()
    }

    suspend fun retrieveDeals(id: String): Deal {
        return dealApiKtx.retrieveDeal(id)
    }
}