package com.example.mobile_compose_app

data class AssetInfo(
    val iconDrawable: Int,
    val name: String,
    val tickerName: String,
    val lastDayChange: List<Float>,
    val currentValue: Float,
    val total: Float,
)

data class ClientInfo(
    val clientFirstname: String,
    val clientSurname: String,
    val clientTitle:String,
    val clientId: List<ClientId>?
)
data class ClientId(
    val idType:String,
    val idValue:String
)

data class instrumentDropDownItem(
     val insName:String,
     val insDescription:String?,
     val insManager:String?,
     val mgrDescription: String?,
     val insMinInvest:String?,
     val insRiskRating:String?

)

