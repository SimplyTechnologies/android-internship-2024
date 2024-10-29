package com.simply.birthdayapp.main.shop.data.helper

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.simply.GetShopsQuery
import com.simply.birthdayapp.BuildConfig
import com.simply.birthdayapp.main.shop.data.model.ShopDataModel
import com.simply.type.ShopFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ShopHelper {
    val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNzMwMTg1MjgzLCJleHAiOjE3MzI3NzcyODN9.6stbQK-zhIkP2QtcDs2pXZ6MzvG_qkbVcQskQ61dx3k"
    private val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl(BuildConfig.API_URL)
        .addHttpHeader("Authorization", "Bearer $token")
        .build()
    private val _shopList: MutableStateFlow<List<ShopDataModel>> =
        MutableStateFlow(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _shopList.value = fetchShops(client = apolloClient)
            } catch (e: Exception) {
                Log.e("ShopHelper", "Error fetching shops: ${e.message}")
            }
        }
    }

    fun getShopList(): MutableStateFlow<List<ShopDataModel>> {
        return _shopList
    }

    private suspend fun fetchShops(
        client: ApolloClient,
        filter: ShopFilter? = null
    ): List<ShopDataModel> {
        val response = client.query(GetShopsQuery()).execute()

        return response.data?.shops?.map { shop ->
            ShopDataModel(
                id = shop.id,
                name = shop.name,
                avatarUrl = shop.image,
            )
        }!!
    }
}