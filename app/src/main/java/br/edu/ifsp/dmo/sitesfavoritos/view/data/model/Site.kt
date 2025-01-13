package br.edu.ifsp.dmo.sitesfavoritos.view.data.model

class Site(var apelido: String, var url: String) {
    var favorito: Boolean = false

    private companion object {
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init {
        id = lastId
        lastId += 1L
    }
}
