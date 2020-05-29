package com.kizitonwose.colorpreferencesample

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.view.View
import com.example.calkulator.R



open class PreferenceActivity : BaseActivity() {
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFragmentManager().beginTransaction().replace(
            R.id.content_frame,
            MyPreferenceFragment()
        ).commit()
    }

    class MyPreferenceFragment : PreferenceFragment() {
        private val CUSTOM_PICKER_PREF_KEY = "color_pref_lobster"
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_main)
            findPreference(CUSTOM_PICKER_PREF_KEY).onPreferenceClickListener =
                Preference.OnPreferenceClickListener { preference ->
                    showColorDialog(preference)
                    true
                }
        }

        private fun showColorDialog(preference: Preference) {
            val inflater = activity.layoutInflater
            val colorView: View = inflater.inflate(R.layout.dialog_color, null)
            val color = PreferenceManager.getDefaultSharedPreferences(activity)
                .getInt(CUSTOM_PICKER_PREF_KEY, Color.YELLOW)
            val lobsterPicker: LobsterPicker =
                colorView.findViewById<View>(R.id.lobsterPicker) as LobsterPicker
            val shadeSlider: LobsterShadeSlider =
                colorView.findViewById<View>(R.id.shadeSlider) as LobsterShadeSlider
            lobsterPicker.addDecorator(shadeSlider)
            lobsterPicker.setColorHistoryEnabled(true)
            lobsterPicker.setHistory(color)
            lobsterPicker.setColor(color)
            AlertDialog.Builder(activity)
                .setView(colorView)
                .setTitle("Choose Color")
                .setPositiveButton("SAVE"
                ) { dialogInterface, i -> (preference as ColorPreference).setValue(lobsterPicker.getColor()) }
                .setNegativeButton("CLOSE", null)
                .show()
        }
    }
}