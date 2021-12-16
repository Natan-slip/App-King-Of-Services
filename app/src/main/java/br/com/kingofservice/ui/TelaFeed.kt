package br.com.kingofservice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class TelaFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_feed)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opcoes_cliente,menu);
        return true;
    }

    private fun TelaListagem() {
        val intent = Intent(this, TelaFeed::class.java)
        startActivity(intent)
    }

    private fun TelaCategorias() {
        val intent = Intent(this, TelaListagem::class.java)
        startActivity(intent)
    }

    private fun TelaPerfilPrestador() {
        val intent = Intent( this, TelaVerPerfilPrestador::class.java)
        startActivity(intent)
    }

    private fun sairLogout() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.profile -> {
                TelaPerfilPrestador()
                return true
            }

            R.id.principais_categorias -> {
                TelaListagem()
                return true
            }

            R.id.busca_servicos -> {
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