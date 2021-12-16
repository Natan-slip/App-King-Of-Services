package br.com.kingofservice.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TelaLogin : AppCompatActivity() {

    lateinit var editUser: EditText
    lateinit var editPassword: EditText
    lateinit var btnFazerLogin: Button
    lateinit var tvMensagemErro: TextView
    lateinit var tvCrieSuaConta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_login)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        val lembrar = dados.getBoolean("lembrar", false)

        if (lembrar == true){
        }

        editUser = findViewById(R.id.et_email)
        editPassword = findViewById(R.id.et_senha)
        tvMensagemErro = findViewById(R.id.tv_mensagem_erro)
        tvCrieSuaConta = findViewById(R.id.tv_crie_sua_conta)
        btnFazerLogin = findViewById(R.id.btt_fazerLogin)

        btnFazerLogin.setOnClickListener {
            val intent = Intent(this, TelaListagem::class.java)
            startActivity(intent)
        }


        tvCrieSuaConta.setOnClickListener {
            val intent = Intent(this, TelaTipoUsuario::class.java)
            startActivity(intent)
        }
    }
}