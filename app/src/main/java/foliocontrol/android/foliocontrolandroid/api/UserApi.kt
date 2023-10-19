package foliocontrol.android.foliocontrolandroid.api

import android.util.Log
import androidx.compose.runtime.MutableState
import foliocontrol.android.foliocontrolandroid.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class UserLoginRequestData(val email: String, val password: String)
public interface UserApi {
    @Headers("Accept: application/json")
    @POST("api/user/login")
    fun login(@Body user: UserLoginRequestData): Call<UserModel>
}

fun UserLoginRequest(
    email: String,
    password: String,
    token: MutableState<String>
) {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<UserModel> = api.login(UserLoginRequestData(email, password))

    call.enqueue(object : Callback<UserModel> {
        override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
            if (response.isSuccessful) {
                Log.d("Main", "Success! " + response.body().toString())
            }
            token.value = response.body()!!.token
        }

        override fun onFailure(call: Call<UserModel>, t: Throwable) {
            println("Failed")
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}
