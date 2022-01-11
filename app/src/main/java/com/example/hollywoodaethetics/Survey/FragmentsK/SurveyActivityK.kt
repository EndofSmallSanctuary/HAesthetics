package com.example.hollywoodaethetics.Survey.FragmentsK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hollywoodaethetics.R
import com.example.hollywoodaethetics.Survey.FragmentsK.HeightPickerFragmentK

class SurveyActivityK : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        supportFragmentManager.beginTransaction().replace(R.id.survey_items,
            HeightPickerFragmentK()
        ).commit();



    }
}
