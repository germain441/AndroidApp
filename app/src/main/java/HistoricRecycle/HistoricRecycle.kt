package HistoricRecycle

import HistoricRecycle.HistoricItemAdapter
import SettingRecycle.SettingRecycle
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.R
import com.example.androidapp.databinding.ActivityHistoricRecycleBinding

class HistoricRecycle : AppCompatActivity() {


    companion object {
        public var historique = ArrayList<HistoricItem>();
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HistoricRecycle::class.java)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private lateinit var binding: ActivityHistoricRecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle(R.string.titreApp)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        binding.recycleHistoric.layoutManager = LinearLayoutManager(this)


        //affiche les items de l'historique
        binding.recycleHistoric.adapter = HistoricItemAdapter(historique)


    }


}