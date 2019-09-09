package com.imastudio.kotlinlanjutanpertama.fragment


import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.multiplescreen.anko.adapters.AnimeAdapter
import com.example.multiplescreen.anko.adapters.OnAnimeClickListener
import com.imastudio.kotlinlanjutanpertama.DetailHalaman
import com.imastudio.kotlinlanjutanpertama.R
import com.imastudio.kotlinlanjutanpertama.data.getAnimes
import com.imastudio.kotlinlanjutanpertama.model.Anime
import kotlinx.android.synthetic.main.fragment_galery_anime.view.*
import org.jetbrains.anko.support.v4.progressDialog
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GaleryAnimeFragment : Fragment(), OnAnimeClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var v : View
        v =inflater.inflate(R.layout.fragment_galery_anime, container, false)
        v.recycleranime.adapter =AnimeAdapter(getAnimes(),this)
        v.recycleranime.layoutManager =GridLayoutManager(activity,2)
        return v
    }

    override fun onClick(anime: Anime) {
    val dialog = showLoadingDialog("please wait..","downloading . . ."+anime.name)
     loading{
         dialog.dismiss()
         pindahHalaman(anime)

     }

    }

    private fun pindahHalaman(anime: Anime) {
        startActivity<DetailHalaman>(
            "name" to anime.name,
            "desc" to anime.description,
            "link" to anime.linkweb,
             "gambar" to anime.image
            )

    }

    private fun loading(function: () -> Unit) {
     var pending =Handler()
        pending.postDelayed({function()},2000)
    }

    private fun showLoadingDialog(s: String, s1: String): ProgressDialog {
        val dialog = progressDialog(s,s1)
        return dialog
    }

}
