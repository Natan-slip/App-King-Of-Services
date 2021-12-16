package br.com.kingofservice.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import br.com.kingofservice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val CODE_IMAGE = 100

class TelaCadastroCliente : AppCompatActivity() {

    var imageBitmap: Bitmap? = null
    lateinit var trocarFotoCliente: TextView
    lateinit var imgProfileCliente: ImageView

    lateinit var etCepCliente: EditText
    lateinit var etEstadoCliente: EditText
    lateinit var etCidadeCliente: EditText
    lateinit var etBairroCliente: EditText
    lateinit var etRuaCliente: EditText

    lateinit var etDataNascimentoCliente: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro_cliente)

        val cepCliente = findViewById<EditText>(R.id.et_cepCadastroCliente)
        val estadoCliente = findViewById<EditText>(R.id.et_estadoCadastroCliente)
        val cidadeCliente = findViewById<EditText>(R.id.et_cidadeCadastroCliente)
        val bairroCliente = findViewById<EditText>(R.id.et_bairroCadastroCliente)
        val ruaCliente = findViewById<EditText>(R.id.et_ruaCadastroCliente)

        etCepCliente = findViewById(R.id.et_cepCadastroCliente)
        etRuaCliente = findViewById(R.id.et_ruaCadastroCliente)
        etEstadoCliente = findViewById(R.id.et_estadoCadastroCliente)
        etCidadeCliente = findViewById(R.id.et_cidadeCadastroCliente)
        etBairroCliente = findViewById(R.id.et_bairroCadastroCliente)
        etDataNascimentoCliente = findViewById(R.id.et_dataNascimentoCadastroCliente)

        val btnCliente = findViewById<Button>(R.id.btn_Cliente)

        btnCliente.setOnClickListener {

            val endereco = Endereco()
            endereco.zipcode = cepCliente.text.toString()
            endereco.street = ruaCliente.text.toString()
            endereco.state = estadoCliente.text.toString()
            endereco.city = cidadeCliente.text.toString()
            endereco.neighborhood = bairroCliente.text.toString()
        }

            cepCliente.setOnFocusChangeListener { v, hasFocus ->

                val cep = cepCliente.text

                if (!hasFocus && cep.length == 8) {
                    searchByCEP()
                    cepCliente.error = null
                }

                if (!hasFocus && cep.length < 8) {
                    cepCliente.error = "CEP inválido"
                }
            }

        imgProfileCliente = findViewById(R.id.iv_profile)

        imgProfileCliente.setOnClickListener {
            abrirGaleria()
        }

        trocarFotoCliente = findViewById(R.id.tv_trocar_foto)

        trocarFotoCliente.setOnClickListener {
            abrirGaleria()
        }

            supportActionBar!!.title = "Novo Cliente"
            supportActionBar!!.subtitle = "cadastre os seus dados"
            supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

            //Criar um calendário:
            val calendario = Calendar.getInstance() //Instancioams o Calendar da classe Java.util
            val ano = calendario.get(Calendar.YEAR) //Pegamos dessa instância de Calendar o ano
            val mes = calendario.get(Calendar.MONTH) //Pagamos o mês desse calendar
            val dia = calendario.get(Calendar.DAY_OF_MONTH) //Pegamos o dia do mês

            //Abrir o componente DatePicker
        etDataNascimentoCliente.setOnClickListener {
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                        var diaZero = _dia.toString()
                        var mesZero = _mes.toString()
                        if (_dia < 10) {
                            diaZero = "0$_dia"
                        }

                        if (_mes < 10) {
                            mesZero = "${_mes + 1}"
                        }
                        etDataNascimentoCliente.setText("$diaZero/$mesZero/$_ano")
                    }, ano, mes, dia
                )
                dpd.show()
            }
        }

// Parte de Menu

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_novo_usuario, menu);
            return true;
        }

        private fun trocaTela() {
            val intent = Intent(this, TelaListagem::class.java)
            startActivity(intent)
        }

        private fun cancelarTela() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.menu_save -> {
                    trocaTela()
                    return true
                }

                R.id.menu_cancel -> {
                    cancelarTela()
                    return true
                }

                R.id.menu_help -> Toast.makeText(
                    this,
                    "Ajuda com sucesso!!",
                    Toast.LENGTH_SHORT
                ).show()

            }

            return super.onOptionsItemSelected(item)
        }

    private fun searchByCEP() {

        val cep = etCepCliente.text

        val remote = RetrofitFactory().retrofitService()
        val call: Call<Cep> = remote.getCEP(cep.toString())

        call.enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                val cep = response.body()
                if (cep != null) {
                    etRuaCliente.setText(cep.logradouro)
                    etEstadoCliente.setText(cep.uf)
                    etCidadeCliente.setText(cep.cidade)
                    etBairroCliente.setText(cep.bairro)
                }
            }

            override fun onFailure(call: Call<Cep>, t: Throwable) {
                Log.i("cep", t.message.toString())
            }

        })
    }

    private fun abrirGaleria() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"

        startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha uma foto"),
            CODE_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1) {

            val stream = contentResolver.openInputStream(data!!.data!!)

            imageBitmap = BitmapFactory.decodeStream(stream)

            imgProfileCliente.setImageBitmap(imageBitmap)
        }

    }

}

