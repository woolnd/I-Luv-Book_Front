package com.example.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.main.databinding.ActivityLoadBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LoadActivity: AppCompatActivity() {

    lateinit var binding: ActivityLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoadBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(0, TimeUnit.SECONDS) // 연결 제한 시간 설정 (0초로 설정하여 시간 제한 없음)
            .readTimeout(0, TimeUnit.SECONDS)    // 읽기 제한 시간 설정 (0초로 설정하여 시간 제한 없음)
            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
            .build()


        var retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.128.159:8080")//서버 주소를 적을 것
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(Service::class.java)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        binding.loadBackIv.setOnClickListener {
            val intent = Intent(this, InitActivity::class.java)
            startActivity(intent)
            finish()
        }
        val model = "ft:gpt-3.5-turbo-0613:personal::8nlFXV6P"
        val textlist = intent.getStringArrayListExtra("textList")
        val on_off = intent.getStringExtra("on_off")

        Log.d("on_off", on_off.toString())

//        val request = TaleCreate(model, textlist)
//        Service.makeFairytale("infant", request).enqueue(object : Callback<TaleResponse> {
//            var dialog = AlertDialog.Builder(this@LoadActivity)
//            override fun onResponse(
//                call: Call<TaleResponse>,
//                response: Response<TaleResponse>
//            ) { //서버에서 받은 코드값을 duplic_code 객체에 넣음
//                var result = response.body() //서버에서 받은 코드값을 duplic_code 객체에 넣음
//                if (result != null) {
//
//                    val intent = Intent(this@LoadActivity, BookActivity::class.java)
//                    intent.putExtra("title", result.title)
//                    intent.putExtra("contents", result.engTale)
//                    intent.putExtra("keywords", result.keywords)
//                    startActivity(intent)
//
//                } else {
//                    dialog.setTitle("생성 실패")
//                    dialog.setMessage("생성에 실패하였습니다.")
//                    dialog.show()
//
//                }
//
//            }
//            override fun onFailure(call: Call<TaleResponse>, t: Throwable) {
//                dialog.setTitle("통신 실패")
//                dialog.setMessage(t.toString())
//                dialog.show()
//            }
//        })
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@LoadActivity, TalkActivity::class.java)
            // 여기에 필요한 intent.putExtra() 호출을 추가할 수 있습니다.
            startActivity(intent)
            finish() // LoadActivity 종료
        }, 10000)
    }
}
