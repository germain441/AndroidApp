package Localisation

import HistoricRecycle.HistoricItem
import HistoricRecycle.HistoricRecycle.Companion.historique
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidapp.databinding.ActivityLocalisationBinding
import java.util.*


class LocalisationActivity : AppCompatActivity() {


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LocalisationActivity::class.java)
        }
    }

    val PERMISSION_REQUEST_LOCATION = 9999

    private lateinit var binding: ActivityLocalisationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.meLocaliser.setOnClickListener{
            this.requestPermission()
        }
    }

// fonction de vérification de permission
    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
//fonction de demande de permission, si oui on appelle la fonction de calcul de distance
    private fun requestPermission() {
        if (!hasPermission()) {

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_LOCATION
            )
        } else {
            getDistanceEseo()
        }
    }


    // Cette fonction va renvoyer la localisation si la permission à été accordée
    @SuppressLint("MissingPermission")
    private fun getDistanceEseo() {


        if (hasPermission()) {

            val locationManager =
                    applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager?
            locationManager?.run {

                locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)?.run {
                   calculDistance(this) //Nous appelons la fonction de calcul de la distance avec en paramètre la localisation actuelle
                }
            }
        }
    }
    //Cette fonction va calculer la distance entre la localisation du téléphone et la position fixe de l'eseo
    private fun calculDistance(localisation: Location){
        val arrondi = Math.pow(10.0, 1.0) //variable pour arrondir
        val eseo = Location("localisation eseo")
        eseo.latitude = 47.4937187
        eseo.longitude = -0.5504861

        val distanceEseo = localisation.distanceTo(eseo).toDouble()/1000
        //calcul visant à arrondir la distance
        val distanceEseoArrodi = Math.floor((distanceEseo) * arrondi)/arrondi;

        // Modification de notre texte présent dans le layout avec la distance en km de l'eseo
        binding.distanceText.setText(distanceEseoArrodi.toString() + " km");


        //Récupère l'heure actuel pour avoir le moment de la localisation
        val calendar: Calendar = Calendar.getInstance()
        val heure = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        val secondes = calendar.get(Calendar.SECOND)

        // Nous ajoutons ici à notre historique la position de l'utilisateur et l'heure actuelle
        historique.add(HistoricItem(" latitue : " + localisation.latitude.toString() + " longitude : " + localisation.longitude.toString() + " localisé à " + heure + "h" + minutes + "min"+ secondes +"s"));
    }
}

