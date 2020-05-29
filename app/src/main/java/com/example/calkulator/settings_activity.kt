package com.example.calkulator

import android.os.Bundle
import android.view.AbsSavedState
import com.kizitonwose.colorpreferencesample.PreferenceActivity

class settingsActivity : PreferenceActivity(){
    override fun onCreate(savedInState: Bundle?){
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(r.xml.settings)
    }
}