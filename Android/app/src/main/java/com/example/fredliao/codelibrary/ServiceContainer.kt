package com.example.fredliao.codelibrary

import android.content.Context
import com.example.fredliao.codelibrary.network.CodeLibApiManager
import com.example.fredliao.codelibrary.network.data.CodeLibRepository

class ServiceContainer(context: Context) {
    val codeLibRepository = CodeLibRepository(CodeLibApiManager(context))
}