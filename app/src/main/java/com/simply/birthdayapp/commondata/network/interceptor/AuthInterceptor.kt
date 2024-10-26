package com.simply.birthdayapp.commondata.network.interceptor

import com.apollographql.apollo.api.ApolloRequest
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class AuthInterceptor(private val dataStoreProvider: DataStoreProvider) : ApolloInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        return flow {
            val token = dataStoreProvider.getToken()

            val requestWithAuth = request.newBuilder()
                .addHttpHeader("Authorization", "Bearer $token")
                .build()

            emitAll(chain.proceed(requestWithAuth))
        }
    }
}