package br.com.kingofservice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class TelaTipoUsuario : AppCompatActivity() {

    lateinit var btnPrestador: Button
    lateinit var btnCliente: Button
    lateinit var ivCliente: ImageView
    lateinit var ivPrestador: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_tipo_usuario)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        btnPrestador = findViewById(R.id.btt_prestador)
        ivPrestador = findViewById(R.id.iv_prestador)
        btnCliente = findViewById(R.id.btt_cliente)
        ivCliente = findViewById(R.id.iv_cliente)

        btnPrestador.setOnClickListener {
            TelaCadastroPrestador()
        }

        ivPrestador.setOnClickListener {
            TelaCadastroPrestador()
        }

        btnCliente.setOnClickListener {
            TelaCadastroCliente()
        }

        ivCliente.setOnClickListener {
            TelaCadastroCliente()
        }
    }

    private fun TelaCadastroCliente(){

        val telaCliente = Intent(this,TelaCadastroCliente::class.java)
        startActivity(telaCliente)
    }

    private fun TelaCadastroPrestador(){

        val telaCadastroPrestador = Intent(this,TelaCadastroPrestador::class.java)
        startActivity(telaCadastroPrestador)
    }
}