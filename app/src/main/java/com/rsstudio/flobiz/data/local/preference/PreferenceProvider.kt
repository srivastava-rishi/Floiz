package com.rsstudio.flobiz.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceProvider
@Inject
constructor(
    @ApplicationContext private val context: Context
){

    companion object {

        const val REMOVE_ADS = "remove_ads"
        // default value
        const val DFT_ADS_VALUE : Int = 0
    }

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun clearPreferences() {
        preference.edit().clear().apply()
    }

    fun saveRemoveAdsValue(remove_add_value: Int) {
        preference.edit().putInt(
            REMOVE_ADS,
            remove_add_value
        ).apply()
    }

    fun getRemoveAdsValue(): Int {
        return preference.getInt(REMOVE_ADS, DFT_ADS_VALUE)
    }

}