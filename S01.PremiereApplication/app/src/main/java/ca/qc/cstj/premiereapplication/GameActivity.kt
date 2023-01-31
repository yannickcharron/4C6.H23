package ca.qc.cstj.premiereapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import kotlin.reflect.typeOf

class GameActivity : AppCompatActivity() {

    private var nbTries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val txvMessage = findViewById<TextView>(R.id.txvMessage)
        val nprNumber = findViewById<NumberPicker>(R.id.nprNumber)
        val btnValidate = findViewById<Button>(R.id.btnValidate)
        val txvTries = findViewById<TextView>(R.id.txvTries)

        txvMessage.text = getString(R.string.msgGame, intent.getStringExtra(PLAYER_NAME_EXTRA))

        nprNumber.minValue = 0
        nprNumber.maxValue = 100

        //Xavier le code que tu as manqué
        val theGoodNumber = (0 .. 100).random()
        //Log.i("GameActivity", theGoodNumber.toString())

        btnValidate.setOnClickListener {
            nbTries++
            val choice = nprNumber.value

//            if(choice == theGoodNumber) {
//                Snackbar.make(btnValidate,  "Bravo", Snackbar.LENGTH_LONG).show()
//            } else if (choice > theGoodNumber) {
//                Snackbar.make(btnValidate,  "Le nombre recherché est plus petit.", Snackbar.LENGTH_LONG).show()
//            } else {
//                Snackbar.make(btnValidate,  "Le nombre recherché est plus grand.", Snackbar.LENGTH_LONG).show()
//            }

            when {
                choice == theGoodNumber -> {
                    Snackbar.make(btnValidate,  "Bravo", Snackbar.LENGTH_INDEFINITE)
                    finish()
                }
                choice > theGoodNumber -> {
                    Snackbar.make(btnValidate,  "Le nombre recherché est plus petit.", Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    Snackbar.make(btnValidate,  "Le nombre recherché est plus grand.", Snackbar.LENGTH_LONG).show()
                }
            }

            txvTries.text = applicationContext.resources.getQuantityString(R.plurals.nbTries, nbTries, nbTries)



        }

    }

    //La partie statique de notre classe
    companion object {
        //    public static Intent newIntent() { }
        const val PLAYER_NAME_EXTRA = "PLAYER_NAME"
        fun newIntent(context: Context, playerName: String): Intent {
            val intent = Intent(context, GameActivity::class.java)
            //En c# Extra serait de type Dictionnary<String, Object>
            intent.putExtra(PLAYER_NAME_EXTRA, playerName)
            return intent
        }
    }

}





