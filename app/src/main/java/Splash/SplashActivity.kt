package Splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.androidapp.MainActivity
import com.example.androidapp.R


class SplashActivity : AppCompatActivity() {
    //splash activity de 2 seconde à l'ouverture de l'appli
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartIntent(this));
            finish()
        }, 2000)
    }
}