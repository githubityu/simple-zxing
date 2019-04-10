package com.blovesoft.zxingdemo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.zxing.Result
import com.library.zxing.ScanListener
import com.library.zxing.ScanManager
import com.library.zxing.decode.DecodeThread
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_scan.*

class MainActivity : AppCompatActivity(), ScanListener {
    lateinit var scanManager: ScanManager
     var handler: Handler = Handler()
    override fun scanResult(rawResult: Result?, bundle: Bundle?) {
        var data = rawResult?.text
        Log.e("MainActivity", data ?: "")
        handler.dely(500){
            scanManager.reScan()
        }
    }

    override fun scanError(e: Exception?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        scanManager =
            ScanManager(
                this,
                capture_preview,
                capture_container,
                capture_crop_view,
                capture_scan_line,
                DecodeThread.QRCODE_MODE,
                this
            )
        btn_photo.setOnClickListener {
            selectPic(this, PictureConfig.CHOOSE_REQUEST, false)
        }
        RxPermissions(this).request(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .subscribe { aBoolean ->
                if (aBoolean!!) {

                } else {

                }
            }
    }

    override fun onResume() {
        super.onResume()
        scanManager.onResume()
        scanManager.reScan()
    }

    override fun onPause() {
        super.onPause()
        scanManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        scanManager.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    if (selectList.size > 0) {
                        val compressPath = selectList[0].compressPath
                        scanManager.scanningImage(compressPath)
                    }
                }
                else -> {
                }
            }
        }

    }
}
