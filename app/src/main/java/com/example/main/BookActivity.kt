package com.example.main

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.main.databinding.ActivityBookBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit

class BookActivity: AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var binding: ActivityBookBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tts = TextToSpeech(this, this)


        var title = intent.getStringExtra("title")
        //var eng_text = intent.getStringExtra("contents").toString()
        var keywords = intent.getStringArrayListExtra("keywords")


        val httpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
            .build()


        Log.d("stringhey1", "hello")

        var retrofit = Retrofit.Builder()
            .baseUrl("http://3.37.128.159:8080")//서버 주소를 적을 것
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(Service::class.java)





        //val request = TransLate("en", "ko", eng_text)

//        Service.translateFairytale(request).enqueue(object : Callback<ResponseBody> {
//            var dialog = AlertDialog.Builder(this@BookActivity)
//            override fun onResponse(
//                call: Call<ResponseBody>,
//                response: Response<ResponseBody>
//            ) { //서버에서 받은 코드값을 duplic_code 객체에 넣음
//                if (response.isSuccessful) {
//                    if(response != null){
//                        val result = response.body()?.string()
//                        val kor_text = result.toString()
//
//                        Log.d("stringstring", kor_text)
//                    }
//                }
//            }
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                dialog.setTitle("통신 실패")
//                dialog.setMessage(t.toString())
//                dialog.show()
//            }
//        })





        var kor_text = """옛날, 무성한 숲 속 깊은 곳에 자리 잡은 평화로운 마을에 엘라라는 이름의 어린 소녀가 살고 있었습니다. 엘라는 항상 따뜻한 "안녕하세요"로 모든 사람에게 인사를 하는, 그녀의 친절한 마음과 친절한 성격으로 알려져 있었습니다. 하지만 그녀는 그녀의 쾌활한 인사가 그녀를 놀라운 모험으로 이끌 것이라는 것을 몰랐습니다."""
        val eng_text = "But Lee Jin stood his ground and bravely held up the pot of potion. As the dragon approached, he threw the potion towards it, creating a cloud of strong-smelling smoke. The dragon was taken aback by the smell and started coughing uncontrollably. It tried to breathe fire, but the smoke from the potion choked it, making it weaker.Taking advantage of the dragon's state, Lee Jin grabbed a nearby rope and tied it around the dragon's neck. With all his strength, he pulled the dragon towards the village. The villagers, seeing their brave farmer, came out of their houses and joined Lee Jin in pulling the dragon towards a deep pit they had prepared.With a final heave, they managed to push the dragon into the pit. The dragon roared and thrashed about, but it was trapped. The villagers quickly covered the pit, making sure the dragon could never escape.From that day on, Lee Jin became a hero in the village. The crops flourished, and the villagers never had to worry about the dragon again. Lee Jin's hard work and determination had saved the day, and the village remained peaceful and prosperous for years to come."
        val eng_remove = eng_text.replace("\\n", "").replace("\\n\\n", "").replace("\\", "").replace("\n", "") ?: ""
        val eng_split = eng_remove.split("(?<=\\.)".toRegex())
        val kor_remove = kor_text.replace("\\n", "").replace("\\n\\n", "").replace("\\", "").replace("\n", "") ?: ""
        val kor_split = kor_remove.split("(?<=\\.)".toRegex())


        val itemList_eng = ArrayList<String>()
        val itemList_eng_kor = ArrayList<String>()

        for(i in 0 until eng_split.size){
            itemList_eng.add(eng_split[i])
            itemList_eng_kor.add(eng_split[i])
            itemList_eng_kor.add(kor_split[i])
        }

        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putStringArrayList("eng", itemList_eng)
        bundle.putStringArrayList("kor", itemList_eng_kor)
        bundle.putStringArrayList("keywords", keywords)
        val fragment_book  = BookFragment()
        fragment_book.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.book_main_fl, fragment_book).commit()



        binding.bookTtsIv.setOnClickListener {
            val currentImg = binding.bookTtsIv.drawable
            val ttsOn = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_tts_on)
            val ttsOff = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_tts_off)
            if(currentImg != null && ttsOn != null && ttsOff != null){
                if(areDrawablesEqual(currentImg, ttsOff)){
                    binding.bookTtsIv.setImageResource(R.drawable.book_tts_on)
                    tts.speak(itemList_eng.toString(), TextToSpeech.QUEUE_FLUSH, null, "")

                }
                else if(areDrawablesEqual(currentImg, ttsOn)){
                    binding.bookTtsIv.setImageResource(R.drawable.book_tts_off)
                    stopTTS()
                }
            }
        }

        binding.bookTranslateIv.setOnClickListener {
            val currentImg = binding.bookTranslateIv.drawable
            val translateOn = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_translate_on)
            val translateOff = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_translate_off)
            if(currentImg != null && translateOn != null && translateOff != null){
                if(areDrawablesEqual(currentImg, translateOff)){
                    binding.bookTranslateIv.setImageResource(R.drawable.book_translate_on)

                    val fragment = supportFragmentManager.findFragmentById(R.id.book_main_fl)
                    if (fragment is BookFragment) {
                        fragment.onPrimaryButtonOn()
                    }
                }
                else if(areDrawablesEqual(currentImg, translateOn)){
                    binding.bookTranslateIv.setImageResource(R.drawable.book_translate_off)

                    val fragment = supportFragmentManager.findFragmentById(R.id.book_main_fl)
                    if (fragment is BookFragment) {
                        fragment.onPrimaryButtonOff()
                    }
                }
            }
        }

        binding.bookHeartIv.setOnClickListener {
            val currentImg = binding.bookHeartIv.drawable
            val heartOn = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_heart_on)
            val heartOff = ContextCompat.getDrawable(this@BookActivity, R.drawable.book_heart_off)
            if(currentImg != null && heartOn != null && heartOff != null){
                if(areDrawablesEqual(currentImg, heartOff)){
                    binding.bookHeartIv.setImageResource(R.drawable.book_heart_on)
                }
                else if(areDrawablesEqual(currentImg, heartOn)){
                    binding.bookHeartIv.setImageResource(R.drawable.book_heart_off)
                }
            }
        }
    }

    fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    fun areDrawablesEqual(drawable1: Drawable, drawable2: Drawable): Boolean {
        val bitmap1 = drawableToBitmap(drawable1)
        val bitmap2 = drawableToBitmap(drawable2)
        return bitmap1.sameAs(bitmap2)
    }


    // Drawable을 Bitmap으로 변환하는 함수
    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // 언어 설정 (한국어로 설정 예시)
            val result = tts.setLanguage(Locale.ENGLISH)
            tts.setSpeechRate(0.5f)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // 언어 데이터가 없거나 지원되지 않을 경우
                // 해당 언어를 사용할 수 없음
            }
        } else {
            // TTS 초기화 실패
        }
    }

    private fun stopTTS() {
        if (::tts.isInitialized) {
            // TTS를 중지하고 자원을 해제
            tts.stop()
        }
    }
    override fun onDestroy() {
        // 액티비티가 종료될 때 TTS 자원 해제
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
