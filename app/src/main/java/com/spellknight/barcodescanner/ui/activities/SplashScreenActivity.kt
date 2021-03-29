package com.spellknight.barcodescanner.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.spellknight.barcodescanner.R
import com.spellknight.barcodescanner.data.preference.PreferenceHandler
import com.spellknight.barcodescanner.databinding.ActivitySplashScreenBinding
import com.spellknight.barcodescanner.utils.StylishToastUtils
import com.spellknight.barcodescanner.utils.ToastUtils
import com.spellknight.barcodescanner.viewmodels.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModels()
    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var logger: PreferenceHandler

    @Inject
    lateinit var toastUtils: ToastUtils

    @Inject
    lateinit var stylishToastUtils: StylishToastUtils

    private val TAG="SplashScreenActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        //Splash screen counter
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(TAG,"$millisUntilFinished")
            }
            override fun onFinish() {
                val barcodeIntent= Intent(applicationContext,BarcodeScannerActivity::class.java)
                startActivity(barcodeIntent)
                overridePendingTransition(0,0)
            }
        }.start()

    }
}