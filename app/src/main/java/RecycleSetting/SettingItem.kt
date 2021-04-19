package SettingRecycle



// Définition de la Class qui sera dans notre RecyclerView
data class SettingItem(val name: String, val icon: Int, val onClick: (() -> Unit))
