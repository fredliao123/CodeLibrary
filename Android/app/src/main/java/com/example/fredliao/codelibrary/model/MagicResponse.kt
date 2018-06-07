package com.example.fredliao.codelibrary.model

import com.squareup.moshi.Json
import moe.banana.jsonapi2.JsonApi
import moe.banana.jsonapi2.Resource
import java.io.Serializable

@JsonApi(type = "magic-tokens")
data class MagicResponse(
    val email: String = "",
    @Json(name = "magic-token") var token: String? = ""
) : Resource(), Serializable
