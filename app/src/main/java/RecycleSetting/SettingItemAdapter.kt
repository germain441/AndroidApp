package SettingRecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidapp.R

class SettingItemAdapter(private val deviceList: Array<SettingItem>, private val onClick: ((selectedDevice: SettingItem) -> Unit)? = null) : RecyclerView.Adapter<SettingItemAdapter.ViewHolder>() {

    // Comment s'affiche ma vue
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(device: SettingItem, onClick: ((selectedDevice: SettingItem) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.title_item).text = device.name
            itemView.findViewById<ImageView>(R.id.icon_item).setImageResource(device.icon)


            itemView.findViewById<ConstraintLayout>(R.id.layout_setting).setOnClickListener {
                device.onClick.invoke()
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_setting, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position], onClick)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

}