package com.printoverit.machinetestapp

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import android.widget.Toast

import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import coil.load
import kotlinx.android.synthetic.main.activity_meme_details.*
import kotlinx.coroutines.launch
import java.io.File
import com.karumi.dexter.PermissionToken

import com.karumi.dexter.MultiplePermissionsReport

import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest

import java.lang.Error

class MemeDetailsActivity : AppCompatActivity() {
    private val args by navArgs<MemeDetailsActivityArgs>()
    private var url:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_details)
         url=args.data.url
        meme_name.text=args.data.name
        val layoutParams = LinearLayout.LayoutParams(args.data.width, args.data.height)
        meme_image_preview.setLayoutParams(layoutParams)
        meme_image_preview.load(args.data.url.toString()){
            crossfade(600)
        }
        meme_download.setOnClickListener {
            lifecycleScope.launch {
                checkPermisssion()
                Toast.makeText(applicationContext,"Downloaded",Toast.LENGTH_SHORT).show()
        } }
    }

    private fun checkPermisssion() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()){
                        downloadImage()
                    }else{
                        Toast.makeText(applicationContext,"Busdik Permission de",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) { /* ... */
                }
            }).check()
    }

    private fun downloadImage() {
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(url)
        val directory = File(Environment.DIRECTORY_PICTURES)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url?.substring(url?.lastIndexOf("/")!! + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url?.substring(url?.lastIndexOf("/")!! + 1)
                )
        }
        downloadManager.enqueue(request)
    }
}