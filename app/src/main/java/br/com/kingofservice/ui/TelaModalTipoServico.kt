package br.com.kingofservice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

class TelaModalTipoServico : AppCompatActivity() {

    lateinit var etServicoProcura: EditText
    lateinit var etLocalOndeSeraFeito: EditText
    lateinit var etConhecimnetoNecessario: EditText
    lateinit var etDescricaoDoQuePrecisa: EditText
    lateinit var btnEnviarModal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_modal_tipo_servico)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.logo)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        etServicoProcura = findViewById(R.id.et_servico_procura)
        etLocalOndeSeraFeito = findViewById(R.id.et_local_onde_sera_feito)
        etConhecimnetoNecessario = findViewById(R.id.et_conhecimneto_necessario)
        etDescricaoDoQuePrecisa = findViewById(R.id.et_descricao_do_que_precisa)
        btnEnviarModal = findViewById(R.id.btn_enviarModal)

        btnEnviarModal.setOnClickListener {
            val intent = Intent(this, TelaListagem::class.java)
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
