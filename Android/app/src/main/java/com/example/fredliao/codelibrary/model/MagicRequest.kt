package com.example.fredliao.codelibrary.model

import com.squareup.moshi.Json
import moe.banana.jsonapi2.JsonApi
import moe.banana.jsonapi2.Resource
import java.io.Serializable

@JsonApi(type = "magic-request")
data class MagicRequest(
    val email: String = "",
    @Json(name = "from-quick-login") var quickLogin: Boolean = true
) : Resource(), Serializable