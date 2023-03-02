package ca.qc.cstj.remotedatasource.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.core.loadFromResource
import ca.qc.cstj.remotedatasource.databinding.ItemPlanetBinding
import ca.qc.cstj.remotedatasource.domain.models.Planet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlanetRecyclerViewAdapter(var planets: List<Planet> = listOf())
    : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
            binding.imgImagePlanet.loadFromResource("planet${planet.image}")
            binding.txvTemperature.text = String.format("%.2f", planet.temperature)
        }
    }

}