package com.wacmob.inker.utils

import com.wacmob.inker.preferences.PreferenceHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(val preferenceHandler: PreferenceHandler) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }


    private fun Request.signedRequest() = when (AuthorizationType.fromRequest(this)) {
        AuthorizationType.USER_TOKEN -> {



            Timber.e(preferenceHandler.userToken)

            newBuilder()
                .header("Authorization", "Bearer ${preferenceHandler.userToken}")
                .header("Content-Type", "application/json")
                .build()
        }

        AuthorizationType.REGISTER_INTERMEDIATE_TOKEN -> {
            Timber.e(preferenceHandler.userToken)

            newBuilder()
                .header("Authorization", preferenceHandler.userToken)
                .build()
        }

        AuthorizationType.DUMMY_TOKEN -> {
            newBuilder()
                .header("Authorization", "Bearer 61555e2da187ed794e537ec2|BYuZfTymnlGlH4Hqhp61w6MvUfsIIGFhm3EkaCkt")
                .build()
        }

        AuthorizationType.NONE -> this
    }


    enum class AuthorizationType {
        USER_TOKEN,
        REGISTER_INTERMEDIATE_TOKEN,
        DUMMY_TOKEN,
        NONE;

        companion object {
            fun fromRequest(request: Request): AuthorizationType =
                request.tag(AuthorizationType::class.java) ?: NONE
        }
    }


}