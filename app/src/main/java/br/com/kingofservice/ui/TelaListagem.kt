package br.com.kingofservice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class TelaListagem : AppCompatActivity() {

    lateinit var btnVerPerfil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_listagem)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        btnVerPerfil = findViewById(R.id.btn_ver_perfil)

        btnVerPerfil.setOnClickListener {
            val intent = Intent(this, TelaVerPerfilPrestador::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_opcoes_cliente,menu);
    return true;
    }

    private fun TelaCategorias() {
        val intent = Intent(this, TelaFeed::class.java)
        startActivity(intent)
    }

    private fun TelaBusca() {
        val intent = Intent(this, TelaListagem::class.java)
        startActivity(intent)
    }

    private fun TelaPerfilCliente() {
        val intent = Intent( this, TelaPerfilCliente::class.java)
        startActivity(intent)
    }

    private fun sairLogout() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.profile -> {
                TelaPerfilCliente()
                return true
            }

            R.id.busca_servicos -> {
                TelaBusca()
                return true
            }

            R.id.principais_categorias -> {
                TelaCategorias()
                return true
            }

            R.id.logout -> {
                sairLogout()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

}