package br.edu.ifsp.dmo.sitesfavoritos.view.ui.maIn

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.sitesfavoritos.R
import br.edu.ifsp.dmo.sitesfavoritos.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.sitesfavoritos.databinding.SitesDialogBinding
import br.edu.ifsp.dmo.sitesfavoritos.view.ui.adapters.SiteAdapter
import br.edu.ifsp.dmo.sitesfavoritos.view.ui.listeners.SiteItemClickListener
import br.edu.ifsp.dmo.sitesfavoritos.view.data.model.Site

class MainActivity : AppCompatActivity(), SiteItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private var datasource = ArrayList<Site>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configListeners()
        configRecyclerView()
    }

    // Método da interface SiteItemClickListener que é
    // acionado no adapter do RecyclerView.
    // Método utiliza uma Intent implicita para abrir
    // o navegador com o endereço passado como argumento
    override fun clickSiteItem(position: Int) {
        val site = datasource[position]
        val mIntent = Intent(Intent.ACTION_VIEW)
        mIntent.setData(Uri.parse("http://" + site.url))
        startActivity(mIntent)
    }

    // Método da interface SiteItemClickListener que é
    // acionado no adapter do RecyclerView quando o
    // usuário clicar sobre a imagem de coração.
    override fun clickHeartSiteItem(position: Int) {
        val site = datasource[position]
        site.favorito = !site.favorito
        notifyAdapter()
    }

    // Configuração do listener do floatActionButton.
    private fun configListeners() {
        binding.buttonAdd.setOnClickListener { handleAddSite() }
    }

    // Configuração do RecyclerView.
    private fun configRecyclerView() {
        // Define o adapter passando como argumento a fonte de dados
        // e o objeto que implementa a interface SiteItemClickListener
        // no caso a própria MainActivity.
        val adapter = SiteAdapter(this, datasource, this)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        binding.recyclerviewSites.layoutManager = layoutManager
        binding.recyclerviewSites.adapter = adapter
    }

    // Método é responsável por notificar o adapter informando
    // que houve alteração em algum dos dados da fonte.
    private fun notifyAdapter() {
        val adapter = binding.recyclerviewSites.adapter
        adapter?.notifyDataSetChanged()
    }

    private fun handleAddSite() {

        val tela = layoutInflater.inflate(R.layout.sites_dialog, null)
        val bindingDialog: SitesDialogBinding = SitesDialogBinding.bind(tela)
        // Configuração do AlertDialog
        val builder = AlertDialog.Builder(this)
            .setView(tela)
            .setTitle(R.string.novo_site)
            .setPositiveButton(R.string.salvar,
                DialogInterface.OnClickListener { dialog, which ->
                    // Salvar um site é incluir um objeto na lista,
                    // e notificar o adapter que existe atualização.
                    datasource.add(
                        Site(
                            bindingDialog.edittextApelido.text.toString(),
                            bindingDialog.edittextUrl.text.toString()
                        )
                    )
                    notifyAdapter()
                    dialog.dismiss()
                })
            .setNegativeButton(R.string.cancelar,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        val dialog = builder.create()
        dialog.show()
    }
}