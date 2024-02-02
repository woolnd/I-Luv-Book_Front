package com.example.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.main.databinding.ActivityMakeBinding


class MakeActivity : AppCompatActivity() {

    private lateinit var speechRecognizer: SpeechRecognizer

    lateinit var binding: ActivityMakeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission()

        //RecognizerIntent 생성 (마이크)
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName) //여분키
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")

        window.statusBarColor = android.graphics.Color.parseColor("#F5F5F5")
        window.navigationBarColor = Color.TRANSPARENT

        binding.makeMikeIv.setOnClickListener {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this@MakeActivity)
            speechRecognizer.setRecognitionListener(recognitionListener) //리스너 설정
            speechRecognizer.startListening(intent) //듣기 시작

        }


        // ToKen list and print text
        val list = listOf(binding.token1Cl, binding.token2Cl, binding.token3Cl, binding.token4Cl,binding.token5Cl)
        val list2 = listOf(binding.token1,binding.token2,binding.token3,binding.token4,binding.token5)


        binding.makeKeywordBtnIv.setOnClickListener {
            val text = binding.makeKeywordEt.text.toString()
            var text_count = 0
            for (i in text) text_count += 1

            if(text_count < 1 ) Toast.makeText(application,"다시 입력해주세요",Toast.LENGTH_SHORT).show()
            if(text_count > 15) Toast.makeText(application,"다시 입력해주세요",Toast.LENGTH_SHORT).show()

            else {
                binding.makeKeywordEt.setText(null)
                var check = 0

                for (i in 0..4) {
                    if (list[i].visibility == View.GONE) break
                    else check += 1
                }

                if (check < 5) {
                    list[check].visibility = View.VISIBLE
                    list2[check].text = text
                    binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
                    binding.mainCreateBtn.setOnClickListener {
                        val textList = ArrayList<String>()
                        for (textView in list2) {
                            val text = textView.text.toString()
                            if(text != "")
                                textList.add(text)
                        }

                        val intent = Intent(this, LoadActivity::class.java)
                        intent.putExtra("textList", textList)

                        val currentImg = binding.makeChoiceIv.drawable
                        val on = ContextCompat.getDrawable(this, R.drawable.make_choice_on)
                        val off = ContextCompat.getDrawable(this, R.drawable.make_choice_off)
                        if(currentImg != null && on != null && off != null){
                            if(areDrawablesEqual(currentImg, on)){
                                intent.putExtra("on_off", "on")
                            }
                            else if(areDrawablesEqual(currentImg, off)){
                                intent.putExtra("on_off", "off")

                            }
                        }
                        startActivity(intent)
                    }
                }
            }

        }

        // if click close button, token close
        binding.close1.setOnClickListener {
            binding.token1Cl.visibility = View.GONE
            var check_button = 0
            for (i in 0..4) {
                if (list[i].visibility != View.GONE) check_button = 1
            }
            if (check_button == 0) binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
        }
        binding.close2.setOnClickListener {
            binding.token2Cl.visibility = View.GONE
            var check_button = 0
            for (i in 0..4) {
                if (list[i].visibility != View.GONE) check_button = 1
            }
            if (check_button == 0) binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
        }
        binding.close3.setOnClickListener {
            binding.token3Cl.visibility = View.GONE
            var check_button = 0
            for (i in 0..4) {
                if (list[i].visibility != View.GONE) check_button = 1
            }
            if (check_button == 0) binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
        }
        binding.close4.setOnClickListener {
            binding.token4Cl.visibility = View.GONE
            var check_button = 0
            for (i in 0..4) {
                if (list[i].visibility != View.GONE) check_button = 1
            }
            if (check_button == 0) binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
        }
        binding.close5.setOnClickListener {
            binding.token5Cl.visibility = View.GONE
            var check_button = 0
            for (i in 0..4) {
                if (list[i].visibility != View.GONE) check_button = 1
            }
            if (check_button == 0) binding.mainCreateBtn.setImageResource(R.drawable.make_book_btn)
        }


        binding.makeKeywordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var text = binding.makeKeywordEt.text.toString()
                if(verifyId(text)){
                    binding.makeKeywordBtnIv.setImageResource(R.drawable.make_keyword_on_btn)
                }
                else{
                    binding.makeKeywordBtnIv.setImageResource(R.drawable.make_keyword_btn)
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.makeBackIv.setOnClickListener {
            finish()
        }

        binding.makeChoiceIv.setOnClickListener {
            val currentImg = binding.makeChoiceIv.drawable
            val on = ContextCompat.getDrawable(this, R.drawable.make_choice_on)
            val off = ContextCompat.getDrawable(this, R.drawable.make_choice_off)
            if(currentImg != null && on != null && off != null){
                if(areDrawablesEqual(currentImg, on)){
                    binding.makeChoiceIv.setImageResource(R.drawable.make_choice_off)
                }
                else if(areDrawablesEqual(currentImg, off)){
                    binding.makeChoiceIv.setImageResource(R.drawable.make_choice_on)
                }
            }
        }


    }

    //권한설정
    private fun requestPermission() {
        // 버전 체크, 권한 허용했는지 체크
        if (Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(this@MakeActivity, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MakeActivity,
                arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }
    }
    // 리스너 설정
    private val recognitionListener: RecognitionListener = object : RecognitionListener {
        // 말하기 시작할 준비가되면 호출
        override fun onReadyForSpeech(params: Bundle) {
            Toast.makeText(applicationContext, "음성인식 시작", Toast.LENGTH_SHORT).show()
        }
        // 말하기 시작했을 때 호출
        override fun onBeginningOfSpeech() {}
        // 입력받는 소리의 크기를 알려줌
        override fun onRmsChanged(rmsdB: Float) {}
        // 말을 시작하고 인식이 된 단어를 buffer에 담음
        override fun onBufferReceived(buffer: ByteArray) {}
        // 말하기를 중지하면 호출
        override fun onEndOfSpeech() {
        }
        // 오류 발생했을 때 호출
        override fun onError(error: Int) {
            val message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER 가 바쁨"
                SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                else -> "알 수 없는 오류임"
            }
        }
        // 인식 결과가 준비되면 호출
        override fun onResults(results: Bundle) {
//            val list = listOf(binding.token1Cl, binding.token2Cl, binding.token3Cl, binding.token4Cl,binding.token5Cl)
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줌
            val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (matches != null) {
                val editable = Editable.Factory.getInstance().newEditable("")
                for (i in matches.indices) {
                    editable.append(matches[i])
                }
                binding.makeKeywordEt.text = editable
            }
        }
        // 부분 인식 결과를 사용할 수 있을 때 호출
        override fun onPartialResults(partialResults: Bundle) {}
        // 향후 이벤트를 추가하기 위해 예약
        override fun onEvent(eventType: Int, params: Bundle) {}
    }

    fun verifyId(id: String) : Boolean {
        val regexId = """^[a-z]{1,15}$""".toRegex()
        return regexId.matches(id)
    }

    // Drawable 객체를 비교하는 함수
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
}