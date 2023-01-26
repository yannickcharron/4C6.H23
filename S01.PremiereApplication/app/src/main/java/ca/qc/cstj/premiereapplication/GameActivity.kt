package ca.qc.cstj.premiereapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.reflect.typeOf

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

//    public static Intent newIntent()
//    { }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, GameActivity::class.java)
            return intent
        }
    }

}

