package br.edu.ifsp.dmo.sitesfavoritos.view.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.sitesfavoritos.R
import br.edu.ifsp.dmo.sitesfavoritos.databinding.ItemViewBinding
import br.edu.ifsp.dmo.sitesfavoritos.view.ui.listeners.SiteItemClickListener
import br.edu.ifsp.dmo.sitesfavoritos.view.data.model.Site

class SiteAdapter(val context: Context, val dataset: List<Site>, val listener:
SiteItemClickListener
) :
    RecyclerView.Adapter<SiteAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Atributo do objeto
        val binding: ItemViewBinding = ItemViewBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Referencia do site a ser exibido
        val item = dataset[position]
        // Define dados do site na interface (item)
        holder.binding.textviewApelido.setText(item.apelido)
        holder.binding.textviewUrl.setText(item.url)
        if(item.favorito){

            holder.binding.imgHeart.setColorFilter(context.getColor(R.color.red))
        } else {

            holder.binding.imgHeart.setColorFilter(context.getColor(R.color.gray))
        }
        // Clique no icone de coração

        holder.binding.imgHeart.setOnClickListener{listener.clickHeartSiteItem(position
        )}
        // Clique no item da lista

        holder.binding.layoutItem.setOnClickListener{listener.clickSiteItem(position)}
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    fun removeItem(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }
}