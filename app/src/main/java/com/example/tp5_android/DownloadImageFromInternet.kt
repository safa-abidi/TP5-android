package com.example.tp5_android

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView

@Suppress("DEPRECATION")
class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageURL = urls[0]
        var image: Bitmap? = null
        try {
            image = BitmapFactory.decodeStream(java.net.URL(imageURL).openStream())
        }
        catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return image
    }
    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}