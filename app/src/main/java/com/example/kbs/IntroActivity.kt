package com.example.kbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* 2024-11-12
* 인트로 화면 
* 작성자 : 김기훈
* */

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // 코루틴 시작
        GlobalScope.launch {
            delay(3000) // 3초 대기 (Non-blocking)
            moveToNextActivity()
        }
    }

    private fun moveToNextActivity() {
        // 다음 화면으로 이동
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 인트로 액티비티 종료
    }
}