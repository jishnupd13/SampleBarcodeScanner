package com.spellknight.barcodescanner.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import com.spellknight.barcodescanner.R
import com.spellknight.barcodescanner.data.preference.PreferenceHandler
import com.spellknight.barcodescanner.databinding.ActivityBarcodeReadingBinding
import com.spellknight.barcodescanner.utils.StylishToastUtils
import com.spellknight.barcodescanner.utils.ToastUtils
import com.spellknight.barcodescanner.viewmodels.BarcodeReadingViewModel
import dagger.hilt.android.AndroidEntryPoint
import info.androidhive.barcode.BarcodeReader
import me.dm7.barcodescanner.zxing.ZXingScannerView
import javax.inject.Inject

@AndroidEntryPoint
class BarcodeReadingActivity : AppCompatActivity(),
    ZXingScannerView.ResultHandler {

    private val barcodeReadingViewModel: BarcodeReadingViewModel by viewModels()
    private lateinit var binding: ActivityBarcodeReadingBinding

    private val TAG = "BarcodeReadingActivity"

    private val MY_PERMISSIONS_REQUEST_CAMERA = 0


    var doubleBackToExitPressedOnce = false


    @Inject
    lateinit var logger: PreferenceHandler

    @Inject
    lateinit var toastUtils: ToastUtils

    @Inject
    lateinit var stylishToastUtils: StylishToastUtils

    private lateinit var barcodeReader: BarcodeReader

    private var mScannerView: ZXingScannerView? = null

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_barcode_reading)
        // barcodeReader= supportFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReader

        // mScannerView = ZXingScannerView(this)
        //mScannerView?.flash=true
        //  mScannerView?.setAutoFocus(true)
        //  setContentView(mScannerView)


        if (checkPermission()) {
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

            mScannerView = ZXingScannerView(this)
            setContentView(mScannerView)

        } else {
            requestPermission();
        }



        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(TAG, "TICK TICK")
            }

            override fun onFinish() {
                mScannerView?.stopCamera()
                showAlertOops(this@BarcodeReadingActivity)
            }
        }
        countDownTimer.start()
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }


    private fun showAlertOops(barcodeReadingActivity: BarcodeReadingActivity) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(barcodeReadingActivity)
        builder.setTitle("")
        builder.setCancelable(false)
        val customLayout: View =
            layoutInflater.inflate(R.layout.layout_oops_something_went_wrong, null)
        val btnCloseImage: ImageView = customLayout.findViewById(R.id.image_close)
        val buttonScanAgain: TextView = customLayout.findViewById(R.id.btn_scan_again)

        builder.setView(customLayout)
        val dialog: AlertDialog = builder.create()
        dialog.window?.attributes?.width =
            ConstraintLayout.LayoutParams.MATCH_PARENT
        dialog.show()

        btnCloseImage.setOnClickListener {
            dialog.dismiss()
            finish()
            overridePendingTransition(0, 0)
        }

        buttonScanAgain.setOnClickListener {
            try {
                mScannerView?.startCamera()
                countDownTimer.start()
                dialog.dismiss()
            } catch (e: NullPointerException) {
                Log.e(TAG, "null pointer exception")
            }
        }
    }


    private fun showScannedResult(
        barcodeReadingActivity: BarcodeReadingActivity,
        textScanResult: String
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setCancelable(false)
        val customLayout: View = layoutInflater.inflate(R.layout.layout_scan_result, null)
        val btnCloseImage: ImageView = customLayout.findViewById(R.id.image_close)
        val textScannedResult: TextView = customLayout.findViewById(R.id.text_scan_result_scan_test)
        val buttonScanAgain: TextView = customLayout.findViewById(R.id.btn_scan_again)
        builder.setView(customLayout)
        val dialog: AlertDialog = builder.create()
        dialog.window?.attributes?.width =
            ConstraintLayout.LayoutParams.MATCH_PARENT
        dialog.show()

        textScannedResult.text = textScanResult
        buttonScanAgain.setOnClickListener {
            dialog.dismiss()
            try {
                mScannerView?.resumeCameraPreview(this)
                countDownTimer.start()
            } catch (e: NullPointerException) {
                Log.e(TAG, "null pointer exception")
            }
        }

        btnCloseImage.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
        }
    }


    override fun handleResult(rawResult: Result?) {
        Log.v(TAG, rawResult?.text!!)
        Log.v(TAG, rawResult.barcodeFormat.toString())

        //TODO stop the timer
        countDownTimer.cancel()

        if (rawResult.text != null) {
            showScannedResult(this, rawResult.text)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            overridePendingTransition(0, 0)
        } else
            stylishToastUtils.showInfoToast("Please click BACK again to exit")
        this.doubleBackToExitPressedOnce = true
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA),
            MY_PERMISSIONS_REQUEST_CAMERA
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            MY_PERMISSIONS_REQUEST_CAMERA -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Permission Denied! you need to tick access to this! Else it does not work ",
                        Toast.LENGTH_SHORT
                    ).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED
                        ) {
                            requestPermission()
                        }
                    }
                }
            }
        }
    }
}

