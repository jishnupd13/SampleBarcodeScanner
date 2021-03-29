package com.spellknight.barcodescanner.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.spellknight.barcodescanner.R
import com.spellknight.barcodescanner.data.preference.PreferenceHandler
import com.spellknight.barcodescanner.databinding.ActivityBarcodeScannerBinding
import com.spellknight.barcodescanner.utils.StylishToastUtils
import com.spellknight.barcodescanner.utils.ToastUtils
import com.spellknight.barcodescanner.viewmodels.BarcodeScannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BarcodeScannerActivity : AppCompatActivity(),View.OnClickListener {

    private val barcodeScannerViewModel: BarcodeScannerViewModel by viewModels()
    private lateinit var binding: ActivityBarcodeScannerBinding

    var doubleBackToExitPressedOnce = false

    @Inject
    lateinit var logger: PreferenceHandler

    @Inject
    lateinit var toastUtils: ToastUtils

    @Inject
    lateinit var stylishToastUtils: StylishToastUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_barcode_scanner)

        binding.imageBack.visibility=View.GONE

        binding.layoutSubmitButton.setOnClickListener(this)
        binding.imageBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.layoutSubmitButton->{
               // stylishToastUtils.showInfoToast("You Clicked in the barcode scanner button")
                val barcodeReadingIntent= Intent(this,BarcodeReadingActivity::class.java)
                startActivity(barcodeReadingIntent)
                overridePendingTransition(0,0)
            }

        }
    }

    //For double clicking exit code
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
          finishAffinity()
          overridePendingTransition(0,0)
        }else
            stylishToastUtils.showInfoToast("Please click BACK again to exit")
        this.doubleBackToExitPressedOnce = true
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}