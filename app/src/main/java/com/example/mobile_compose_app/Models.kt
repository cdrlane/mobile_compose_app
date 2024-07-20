package com.example.mobile_compose_app

data class AssetInfo(
    val iconDrawable: Int,
    val name: String,
    val tickerName: String,
    val lastDayChange: List<Float>,
    val currentValue: Float,
    val total: Float,
    var riskValue: Int,
    var aum: Double
)



data class ClientInfo(
    val clientIcon: Int,
    val clientFirstname: String,
    val clientSurname: String,
    val clientTitle:String,
    val clientId: List<ClientId>?,
    val clientRiskAppetite: Int,
    val lastDayChange: List<Float>,
    val clientPortfolioPerformance: Float,
    val clientPortfolioValue: Float
)
data class ClientId(
    val idType:String,
    val idValue:String
)



