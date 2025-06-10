package com.example.sandboxbank

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sandboxbank.util.virtual_server.ResponseRegAuth
import com.example.sandboxbank.util.virtual_server.ServerDataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Иницируем сервер
        val vws = ServerDataSource()
        //Отправляем запрос на регистрацию
        val response = vws.registration("test@test.com", "wdasfasf")
        //Если получили ответ выводим токены
        if (response is ResponseRegAuth) {
            Log.i("TEST", "Registration code is ${response.httpCode}! \n We got user ${response.userId} with tokens ${response.refreshToken} and ${response.accessToken}")
            // Если ошибка, получаем только код
        } else {
            Log.i("TEST", "We lost answer ${response.httpCode}")
        }

        val auth = vws.auth("ata@asd.sd", "sadafgq")
        if (auth is ResponseRegAuth) {
            Log.i("TEST", "Auth code is ${response.httpCode}! \n We got user ${auth.userId} with tokens ${auth.refreshToken} and ${auth.accessToken}")
            // Если ошибка, получаем только код
        } else {
            Log.i("TEST", "We lost answer ${response.httpCode}")
        }

    }
}