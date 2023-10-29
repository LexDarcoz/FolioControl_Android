package foliocontrol.android.foliocontrolandroid.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import foliocontrol.android.foliocontrolandroid.context.LoginState
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

data class UserTokenRequestData(
    val token: String
)

interface UserApi {
    @Headers("Accept: application/json")
    @POST("api/user/login")
    suspend fun login(@Body userState: JsonObject): JsonObject

    @GET("api/user")
    suspend fun getUser(@Header("Authorization") token: String): JsonObject
}

suspend fun UserLoginRequest(
    loginCredentials: LoginState,
    updateTokenState: (String) -> Unit,
): Boolean {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    val api = retrofit.create(UserApi::class.java)
    var mapper: Map<String, JsonElement> = emptyMap()
    mapper = mapper.plus(Pair("email", JsonPrimitive(loginCredentials.email)))
    mapper = mapper.plus(Pair("password", JsonPrimitive(loginCredentials.password)))

    var obj: JsonObject = JsonObject(content = mapper)
    Log.i("UserLoginRequest", "obj: $obj")
    val call: JsonObject = api.login(obj)
    Log.i("UserLoginRequest", "call: $call")
    var token = call["token"].toString()
    updateTokenState(token);

    return token.isNotEmpty()
}

// fun getUserData(token: String, userState: MutableState<User>) {
//    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
//        .addConverterFactory(GsonConverterFactory.create()).build()
//
//    val api = retrofit.create(UserApi::class.java)
//     var mapper: Map<String, JsonElement> = emptyMap()
//     mapper = mapper.plus(Pair("email", JsonPrimitive(loginCredentials.email)))
//     mapper = mapper.plus(Pair("password", JsonPrimitive(loginCredentials.password)))
//
//    call.enqueue(object : Callback<User> {
//        override fun onResponse(call: Call<User>, response: Response<User>) {
//            if (response.isSuccessful) {
//                Log.d("Main", "Success! " + response.body().toString())
//                println("We got here")
//                val userData = response.body()
//                userState.value = userData!!
//            }
//        }
//
//        override fun onFailure(call: Call<User>, t: Throwable) {
//            Log.e("Main", "Failed to get user data: " + t.message.toString())
//        }
//    })
// }
