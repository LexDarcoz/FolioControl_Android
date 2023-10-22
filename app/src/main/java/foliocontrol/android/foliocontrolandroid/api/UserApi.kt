package foliocontrol.android.foliocontrolandroid.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import foliocontrol.android.foliocontrolandroid.context.LocalTokenState
import foliocontrol.android.foliocontrolandroid.context.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

data class UserTokenRequestData(
    val token: String,
)

data class UserLoginRequestData(val email: String, val password: String)
public interface UserApi {
    @Headers("Accept: application/json")
    @POST("api/user/login")
    fun login(@Body user: UserLoginRequestData): Call<UserTokenRequestData>
    @GET("api/user")
   fun getUser(@Header("Authorization") token: String): Call<User>
}

 fun UserLoginRequest(
    email: String, password: String, userState: MutableState<User>
) {

    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<UserTokenRequestData> = api.login(UserLoginRequestData(email, password))

    call.enqueue(object : Callback<UserTokenRequestData> {
        override fun onResponse(
            call: Call<UserTokenRequestData>,
            response: Response<UserTokenRequestData>
        ) {
            if (response.isSuccessful) {
                Log.d("Main", "Success! " + response.body().toString())

                //Token wegschrijven in storage
                userState.value.token = response.body()!!.token
                getUserData(response.body()!!.token, userState)

            }

        }

        override fun onFailure(call: Call<UserTokenRequestData>, t: Throwable) {
            println("Failed")
            Log.e("Main", "Failed" + t.message.toString())
        }
    })
}

fun getUserData(token: String, userState: MutableState<User>) {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<User> = api.getUser(token) // Include the token in the Authorization header

    call.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful) {
                Log.d("Main", "Success! " + response.body().toString())
                println("We got here")
                val userData = response.body()
                userState.value = userData!!

            }

        }


        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.e("Main", "Failed to get user data: " + t.message.toString())
        }
    })
}
