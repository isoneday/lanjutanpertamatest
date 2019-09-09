package com.imastudio.kotlinlanjutanpertama

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail_halaman.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailHalaman : AppCompatActivity() {

    lateinit var linkWeb: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_halaman)
        checkPermission()
        tangkapData(intent)
        aksiListener()

    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CALL_PHONE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    42
                )
            }
        } else {
            // Permission has already been granted
//            callPhone()
        }
    }

    private fun callPhone() {
        makeCall(getString(R.string.nohp))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
//                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    private fun aksiListener() {
        love_it.onClick {
            val options = listOf(
                getString(R.string.watched_id_in_a_day),
                getString(R.string.the_best),
                getString(R.string.tell_me_more_by_email)
            )


            showSeletor(
                getString(R.string.tell_me_more),
                options
            ) { dialointerface: DialogInterface, selectedIndex: Int ->
                when (selectedIndex) {
                    0 -> {

                        toastShort("me too")
                    }
                    1 -> {
                        toastLong("me too")
                    }
                    2 -> {
                        sendAnimeToEmail(
                            getString(R.string.my_email),
                            "love it", getString(R.string.template_email_body, txttitle.text.toString())
                        )
                    }


                }
            }
        }
        hate_it.onClick {
            val options = listOf(
                getString(R.string.it_bored_to_death),
                getString(R.string.my_grandma_thinks_is_cool),
                getString(R.string.tell_me_more_by_email)
            )

            showSeletor(
                getString(R.string.tell_me_more),
                options
            ) { dialointerface: DialogInterface, selectedIndex: Int ->
                when (selectedIndex) {
                    0 -> {

                        toastShort(getString(R.string.im_not_agree))
                    }
                    1 -> {
                        showAlert(R.string.seriously, {
                            callPhone()
                        }, { browse("http://www.google.com") })
                    }
                    2 -> {
                        sendAnimeToEmail(
                            getString(R.string.my_email),
                            "love it", getString(R.string.template_email_body, txttitle.text.toString())
                        )
                    }
                }
            }
        }
    }

    private fun showAlert(seriously: Int, function: () -> Unit, function1: () -> Unit) {
        alert(seriously) {

            yesButton {
                function()
            }
            noButton {
                function1()
            }

        }.show()
    }

    private fun sendAnimeToEmail(nameemail: String, subject: String, body: String) {
        email(nameemail, subject, body)
    }


    private fun showSeletor(string: String, options: List<String>, function: (DialogInterface, Int) -> Unit) {
        selector(string, options, function)
    }

    private fun toastLong(unit: String) {
        longToast(unit)
    }

    private fun toastShort(unit: String) {
        toast(unit)
    }


    private fun tangkapData(intent: Intent?) {
        with(intent) {
            txttitle.text = this?.extras?.get("name").toString()
            description.text = this?.extras?.get("desc").toString()
            anime_image.setImageResource(this?.extras?.get("gambar") as Int)
            linkWeb = extras?.get("link").toString()

        }

    }

    //untuk menampilkan menu di kanan atas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.share ->{
               share("${txttitle.text} \n $linkWeb","give a subject ${txttitle.text} ")
                true
            }
            R.id.open_imdb ->{
                browse("$linkWeb")
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

}
