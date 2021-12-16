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
import br.com.kingofservice.Cep
import br.com.kingofservice.Endereco
import br.com.kingofservice.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val CODE_IMAGE_Prestador = 100

class TelaCadastroPrestador : AppCompatActivity() {

    var imageBitmap: Bitmap? = null
    lateinit var trocarFotoPrestador: TextView
    lateinit var imgProfilePrestador: ImageView

    lateinit var etDataNascimentoPrestador: EditText
    lateinit var etCepPrestador: EditText
    lateinit var etEstadoPrestador: EditText
    lateinit var etCidadePrestador: EditText
    lateinit var etBairroPrestador: EditText
    lateinit var etRuaPrestador: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro_prestador)

        etDataNascimentoPrestador = findViewById(R.id.et_dataNascimentoCadastroPrestador)

        supportActionBar!!.title = "Novo Prestador"
        supportActionBar!!.subtitle = "cadastre os seus dados"
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar))

        //Criar um calendário:
        val calendario = Calendar.getInstance() //Instancioams o Calendar da classe Java.util
        val ano = calendario.get(Calendar.YEAR) //Pegamos dessa instância de Calendar o ano
        val mes = calendario.get(Calendar.MONTH) //Pagamos o mês desse calendar
        val dia = calendario.get(Calendar.DAY_OF_MONTH) //Pegamos o dia do mês

        val cepPrestador = findViewById<EditText>(R.id.et_cepCadastroPrestador)
        val estadoPrestador = findViewById<EditText>(R.id.et_estadoCadastroPrestador)
        val cidadePrestador = findViewById<EditText>(R.id.et_cidadeCadastroPrestador)
        val bairroPrestador = findViewById<EditText>(R.id.et_bairroCadastroPrestador)
        val ruaPrestador = findViewById<EditText>(R.id.et_ruaCadastroPrestador)

        etCepPrestador = findViewById(R.id.et_cepCadastroPrestador)
        etEstadoPrestador = findViewById(R.id.et_estadoCadastroPrestador)
        etCidadePrestador = findViewById(R.id.et_cidadeCadastroPrestador)
        etBairroPrestador = findViewById(R.id.et_bairroCadastroPrestador)
        etRuaPrestador = findViewById(R.id.et_ruaCadastroPrestador)

        val btnPrestador = findViewById<Button>(R.id.btn_Prestador)

        btnPrestador.setOnClickListener {

            val endereco = Endereco()
            endereco.zipcode = cepPrestador.text.toString()
            endereco.street = ruaPrestador.text.toString()
            endereco.state = estadoPrestador.text.toString()
            endereco.city = cidadePrestador.text.toString()
            endereco.neighborhood = bairroPrestador.text.toString()
        }

        cepPrestador.setOnFocusChangeListener { v, hasFocus ->

            val cep = cepPrestador.text

            if (!hasFocus && cep.length == 8) {
                searchByCEP()
                cepPrestador.error = null
            }

            if (!hasFocus && cep.length < 8) {
                cepPrestador.error = "CEP inválido"
            }
        }

        imgProfilePrestador = findViewById(R.id.iv_profile)

        trocarFotoPrestador = findViewById(R.id.tv_trocar_foto)

        imgProfilePrestador.setOnClickListener {
            abrirGaleria()
        }

        trocarFotoPrestador.setOnClickListener {
            abrirGaleria()
        }

        //Abrir o componente DatePicker
        etDataNascimentoPrestador.setOnClickListener {
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
                    etDataNascimentoPrestador.setText("$diaZero/$mesZero/$_ano")
                }, ano, mes, dia
            )
            dpd.show()
        }
    }

    // Parte de Menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_novo_usuario,menu);
        return true;
    }

    private fun trocarTela() {
        val intent = Intent(this, TelaListagem::class.java)
        startActivity(intent)
    }

    private fun cancelar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_save -> {
                trocarTela()
                return true
            }

            R.id.menu_cancel -> {
                cancelar()
                return true
            }

            R.id.menu_help -> Toast.makeText(this
                ,"Ajuda com sucesso!!",
                Toast.LENGTH_SHORT).show()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun searchByCEP() {

        val cep = etCepPrestador.text

        val remote = RetrofitFactory().retrofitService()
        val call: Call<Cep> = remote.getCEP(cep.toString())

        call.enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                val cep = response.body()
                if (cep != null) {
                    etRuaPrestador.setText(cep.logradouro)
                    etEstadoPrestador.setText(cep.uf)
                    etCidadePrestador.setText(cep.cidade)
                    etBairroPrestador.setText(cep.bairro)
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
            CODE_IMAGE_Prestador)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE_Prestador && resultCode == -1) {

            val stream = contentResolver.openInputStream(data!!.data!!)

            imageBitmap = BitmapFactory.decodeStream(stream)

            imgProfilePrestador.setImageBitmap(imageBitmap)
        }

    }

}
