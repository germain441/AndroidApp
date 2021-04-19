package HistoricRecycle

import HistoricRecycle.HistoricItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

import com.example.androidapp.R

class HistoricItemAdapter(private val deviceList: ArrayList<HistoricItem>) : RecyclerView.Adapter<HistoricItemAdapter.ViewHolder>() {

    // Comment s'affiche ma vue
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(device: HistoricItem, onClick: ((selectedDevice: HistoricItem) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.title_item).text = device.name
            }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_historic, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position])
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

}