package br.edu.ifsp.dmo.sitesfavoritos.view.data.model

import androidx.room.Entity

@Entity(tableName = "sites")
class Site(var apelido: String, var url: String) {
    var favorito: Boolean = false
}
