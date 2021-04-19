package SettingRecycle

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.R
import com.example.androidapp.databinding.ActivitySettingRecycleBinding

class SettingRecycle : AppCompatActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingRecycle::class.java)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private lateinit var binding: ActivitySettingRecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle(R.string.titreApp)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        binding.recycleSetting.layoutManager = LinearLayoutManager(this)

        //Lien vers les paramètres
        val locationIntent = Intent().apply {
            action = android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
        }

        //Lien vers les paramètres de l'application
        val paramIntent = Intent().apply {
            action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        }
        paramIntent.setData(Uri.parse("package:" + packageName));

        //lien vers l'email
        val mailto = "mailto:germain.adam@reseau.eseo.fr";
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)

        // Array contenant les différents Item des setting qui seront présent dans notre activité
        val tab = arrayOf(
            SettingItem("Paramètre de l'application", R.drawable.settings) {
                startActivity(paramIntent)
            },
            SettingItem("Paramètre de localisation", R.drawable.localisation) {
                startActivity(locationIntent)
            },
            SettingItem("Application map", R.drawable.map) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.4937187,-0.5504861")));
            },
            SettingItem("Site de l'ESEO", R.drawable.school) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr/")));
            },
            SettingItem("Email", R.drawable.email) {
                startActivity(emailIntent)
            }
        )

        binding.recycleSetting.adapter = SettingItemAdapter(tab)
    }


}