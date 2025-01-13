package br.edu.ifsp.dmo.sitesfavoritos.view.data.dao

import br.edu.ifsp.dmo.sitesfavoritos.view.data.model.Site

object SiteDao {

    private var sites: MutableList<Site> = mutableListOf()

    fun add(site: Site) {
        sites.add(site)
    }
    fun remove(site: Site) {
        sites.remove(site)
    }
    fun getAll() = sites

    fun get(id: Long): Site? {
        return sites.stream()
            .filter{s -> s.id == id}
            .findFirst()
            .orElse(null)
    }

}