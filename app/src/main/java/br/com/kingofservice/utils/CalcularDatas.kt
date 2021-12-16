package br.com.kingofservice.utils

import android.util.Log
import java.text.DateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun obterDiferencaEntreDatasEmAnos(dataInicio: String) : String {
    var hoje: LocalDate = LocalDate.now()

    var dataIni = LocalDate.parse(
        dataInicio,
        DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    var idade = Period.between(dataIni, hoje)

    return  idade.years.toString()
}

