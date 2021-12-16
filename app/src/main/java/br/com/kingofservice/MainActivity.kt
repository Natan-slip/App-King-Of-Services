package br.com.kingofservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.kingofservice.ui.R
import br.com.kingofservice.ui.TelaLogin
import br.com.kingofservice.ui.TelaTipoUsuario

class MainActivity : AppCompatActivity() {

    lateinit var editUser: EditText
    lateinit var editPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnCadastro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btt_login)
        btnCadastro = findViewById(R.id.btt_cadastro)

        //Remover a appBar
        supportActionBar!!.hide()

        btnLogin.setOnClickListener {
            IrParaTelaLogin()
        }

        btnCadastro.setOnClickListener {
            IrParaTelaTipoUsuario()
        }
    }

    private fun IrParaTelaLogin(){

        val telaLogin = Intent(this, TelaLogin::class.java)
        startActivity(telaLogin)
    }


// Funcionalidade de quando clicar em Cadastre-se ir para os tipos de categorias


    private fun IrParaTelaTipoUsuario(){

        val telaTipoUsuario = Intent(this, TelaTipoUsuario::class.java)
        startActivity(telaTipoUsuario)
    }
}