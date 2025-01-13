package br.edu.ifsp.dmo.sitesfavoritos.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.sitesfavoritos.view.data.dao.SiteDao
import br.edu.ifsp.dmo.sitesfavoritos.view.data.model.Site

class MainViewModel : ViewModel() {

    private val dao = SiteDao

    private val _sites = MutableLiveData<List<Site>>()
    val sites: LiveData<List<Site>>
        get() {
            return _sites
        }

    private val _insertSite = MutableLiveData<Boolean>()
    val insertSite: LiveData<Boolean> = _insertSite

    private val _updateSite = MutableLiveData<Boolean>()
    val updateSite: LiveData<Boolean>
        get() = _updateSite

    private val _deleteSite = MutableLiveData<Boolean>()
    val deleteSite: LiveData<Boolean> = _deleteSite

    fun insertSite(Apelido: String, url: String) {
        val site = Site(Apelido, url)
        dao.add(site)
        _insertSite.value = true
        load()
    }

    fun clickHeartSiteItem(position: Int) {
        val sitesList = dao.getAll()
        if (position in sitesList.indices) {
            sitesList[position].favorito = !sitesList[position].favorito
            _updateSite.value = true
            load()  // Atualiza o LiveData
        }
    }

    fun updateSite(position: Int) {
        val site = dao.getAll()[position]
        site.favorito = !site.favorito
        _updateSite.value = true
        load()
    }

    fun clickDeleteSiteItem(position: Int) {
        if (position in 0 until dao.getAll().size) {
            val site = dao.getAll()[position]
            dao.remove(site)
            _deleteSite.value = true
            load()
        }

    }

    private fun load() {
        _sites.value = dao.getAll().toList()
    }
}