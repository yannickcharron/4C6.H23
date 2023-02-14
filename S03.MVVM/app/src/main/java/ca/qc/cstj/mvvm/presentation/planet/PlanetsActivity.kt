package ca.qc.cstj.mvvm.presentation.planet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.qc.cstj.mvvm.R

class PlanetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets)
    }


    //La partie statique (static) de la classe
    companion object {
       fun newIntent(context: Context) : Intent {
           return Intent(context, PlanetsActivity::class.java)
       }
    }
}