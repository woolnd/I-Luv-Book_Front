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
import com.example.main.databinding.ActivityTalkBinding
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

class TalkActivity: AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var binding: ActivityTalkBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTalkBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tts = TextToSpeech(this, this)


        val eng_text = """What a beautiful day, says Mom. Wake up Nicholas. Hello sun, says Nicholas.Good morning birds. It’s a lovely day,” says Dad. Let’s have a picnic by the river. Can my friend Jacob come? asks Nicholas. Don’t forget me. I love picnics! says Donkey. And me. I want to come too! says Dog. Follow us, say the birds. I’ll race you to that tree, Nicholas says. I won, I won,” says Donkey. Not fair,” says Nicholas. You’ve got four legs. I won, I won, says Donkey. Not fair, says Nicholas."""

        val eng_split = eng_text.split("(?<=\\.)".toRegex())

        val itemList_eng = ArrayList<String>()


        for(i in 0 until eng_split.size){
            itemList_eng.add(eng_split[i])
        }

        val keywords = arrayListOf("beatiful", "day")

        val bundle = Bundle()
        bundle.putString("title", "A Beautiful Day")
        bundle.putStringArrayList("eng", itemList_eng)
        bundle.putStringArrayList("keywords", keywords)
        val fragment  = TalkFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.book_talk_fl, fragment).commit()



        binding.bookTtsIv.setOnClickListener {
            val currentImg = binding.bookTtsIv.drawable
            val ttsOn = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_tts_on)
            val ttsOff = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_tts_off)
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
            val translateOn = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_translate_on)
            val translateOff = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_translate_off)
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
            val heartOn = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_heart_on)
            val heartOff = ContextCompat.getDrawable(this@TalkActivity, R.drawable.book_heart_off)
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
