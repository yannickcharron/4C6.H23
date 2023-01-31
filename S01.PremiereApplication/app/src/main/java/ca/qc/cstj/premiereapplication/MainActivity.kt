package ca.qc.cstj.premiereapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPhone = findViewById<Button>(R.id.btnPhone)
        val btnSMS: Button = findViewById(R.id.btnSMS)
        val btnGame = findViewById<Button>(R.id.btnGame)
        val edtPlayerName = findViewById<EditText>(R.id.edtPlayerName)

        btnGame.setOnClickListener {
            startActivity(GameActivity.newIntent(this, edtPlayerName.text.toString()))
        }

        btnPhone.setOnClickListener {
            //Toast.makeText(this, R.string.msgHello, Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:4508529614"))
            startActivity(intent)
        }

        btnSMS.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:4504368520"))
            intent.putExtra("sms_body", "Message de mon code d'amour")
            startActivity(intent)
        }
    }
}