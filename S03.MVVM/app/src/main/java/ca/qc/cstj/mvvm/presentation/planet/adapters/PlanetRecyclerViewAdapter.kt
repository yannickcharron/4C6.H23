package ca.qc.cstj.mvvm.presentation.planet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.mvvm.R
import ca.qc.cstj.mvvm.databinding.ItemPlanetBinding
import ca.qc.cstj.mvvm.domain.models.Planet

class PlanetRecyclerViewAdapter(var planets: List<Planet>)
    : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Créer un ViewHolder pour presque chaque planète
        //Charger en mémoire un item planète pour créer un ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = planets.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPlanetBinding.bind(view)

        fun bind(planet: Planet) {
            binding.txvPlanetName.text = planet.name
            binding.txvPlanetTemperature.text = String.format("%.2f", planet.temperature)

            //TODO: Image
        }
    }
}