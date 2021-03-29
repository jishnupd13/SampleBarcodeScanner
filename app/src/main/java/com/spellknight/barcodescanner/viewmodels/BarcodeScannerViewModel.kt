package com.spellknight.barcodescanner.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.spellknight.barcodescanner.data.repository.ApplicationRepository
import com.spellknight.barcodescanner.utils.NetworkHelper

class BarcodeScannerViewModel  @ViewModelInject constructor(
    private val applicationRepository: ApplicationRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

}
