package br.com.kingofservice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.bottomnavigationfragment.ConcluidoFragment
import com.example.bottomnavigationfragment.PendenteFragment
import com.example.bottomnavigationfragment.HomeFragment

class TelaProcesso : AppCompatActivity() {

    //Botões
    lateinit var btnHome: Button
    lateinit var btnCliente: Button
    lateinit var btnBusca: Button

    lateinit var homeFragment: HomeFragment
    lateinit var pendenteFragment: PendenteFragment
    lateinit var concluidoFragment: ConcluidoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_processo)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        btnBusca = findViewById(R.id.btn_busca)
        btnCliente = findViewById(R.id.btn_cliente)
        btnHome = findViewById(R.id.btn_home)

        concluidoFragment = ConcluidoFragment()
        pendenteFragment = PendenteFragment()
        homeFragment = HomeFragment()

        btnHome.setOnClickListener {
            setFragment(homeFragment)
        }

        btnCliente.setOnClickListener {
            setFragment(pendenteFragment)
        }

        btnBusca.setOnClickListener {
            setFragment(concluidoFragment)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opcoes_cliente,menu);
        return true;
    }

    private fun TelaCategorias() {
        val intent = Intent(this, TelaFeed::class.java)
        startActivity(intent)
    }

    private fun TelaPerfilCliente() {
        val intent = Intent( this, TelaPerfilCliente::class.java)
        startActivity(intent)
    }

    private fun TelaBusca() {
        val intent = Intent(this, TelaListagem::class.java)
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

