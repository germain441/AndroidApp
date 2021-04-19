package com.example.androidapp

import Localisation.LocalisationActivity
import SettingRecycle.SettingRecycle
import HistoricRecycle.HistoricRecycle
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Chaque bouton clickable de notre interface va ici démarrer une activité ou pour le dernier bouton, vider l'historique

        //activité de localisation
        binding.localiser.setOnClickListener{
            startActivity(LocalisationActivity.getStartIntent(this));
        }

        // activité des réglages
        binding.boutonSetting.setOnClickListener {
            startActivity(SettingRecycle.getStartIntent(this));
        }
        //Activité de l'historique
        binding.historique.setOnClickListener {
            startActivity(HistoricRecycle.getStartIntent(this));
        }

        //Lien vers youtube
        binding.youtube.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCVk1c5qatvxmV0UUM-p7nwA")));
        }
        //Vider l'historique
        binding.boutonVider.setOnClickListener {
            HistoricRecycle.historique.clear();
        }
    }
}