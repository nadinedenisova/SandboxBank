package com.example.sandboxbank

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sandboxbank.util.virtual_server.HttpMethods
import com.example.sandboxbank.util.virtual_server.ServerDataSource
import com.example.sandboxbank.util.virtual_server.ServerRequest
import com.example.sandboxbank.util.virtual_server.ServerResponse

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
        var reg = vws.registration(ServerRequest.Registration(
            HttpMethods.POST,
            "test@test.com",
            "wdasfasf"))

        if (reg is ServerResponse.Registration){
            Log.i("TEST", "Registration code is ${reg.httpCode}! \n We got user ${reg.userId} with tokens ${reg.refreshToken} and ${reg.accessToken}")
        } else {
            Log.i("TEST", "We lost answer to reg ${reg.httpCode}")
        }

        reg = vws.auth(ServerRequest.Auth(
            HttpMethods.POST,
            "ata@asd.sd",
            "sadafgq7"))

        if (reg is ServerResponse.Auth){
            Log.i("TEST", "Auth code is ${reg.httpCode}! \n We got user ${reg.userId} with tokens ${reg.refreshToken} and ${reg.accessToken}")
        } else {
            Log.i("TEST", "We lost answer to reg ${reg.httpCode}")
        }

        reg = vws.refresh(ServerRequest.Refresh(
            HttpMethods.POST,
            1,
            "k77V6bTYlAiXNRejTbEkyKfvPeQoyL75ifOpfb5vG4AHSA8Yl"))

        if (reg is ServerResponse.Refresh){
            Log.i("TEST", "Refresh code is ${reg.httpCode}! \n We got user ${reg.refreshToken} with tokens ${reg.accessToken}")
        } else {
            Log.i("TEST", "We lost answer to reg ${reg.httpCode}")
        }

    }
}