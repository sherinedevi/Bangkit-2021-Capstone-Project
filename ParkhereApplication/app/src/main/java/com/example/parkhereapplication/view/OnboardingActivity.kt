package com.example.parkhereapplication.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.parkhereapplication.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class OnboardingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setImmersiveMode()

        addSlide(AppIntroFragment.newInstance(
                title = "Sistem Parkir Cerdas",
                description = "Mempermudah sistem parkir di indonesia dengan memanfaatkan sistem parkir yang cerdas, efektif dan efesien. ",
                titleColor = getColor(R.color.orange_primary),
                descriptionColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                titleTypefaceFontRes = R.font.montserrat_bold,
                descriptionTypefaceFontRes = R.font.montserrat_medium,
                imageDrawable = R.drawable.parkhere_illustration_1
        ))

        addSlide(AppIntroFragment.newInstance(
                title = "Akses dari Mana Saja",
                description = "Sistem parkir yang berguna untuk melihat jumlah kosong dan terisi dengan menggunakan CCTV yang dapat diakses dimana saja.",
                titleColor = getColor(R.color.orange_primary),
                descriptionColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                titleTypefaceFontRes = R.font.montserrat_bold,
                descriptionTypefaceFontRes = R.font.montserrat_medium,
                imageDrawable = R.drawable.parkhere_illustration_2
        ))

        addSlide(AppIntroFragment.newInstance(
                title = "Parkhere",
                description = "Yuk, mulai meningkatkan operasional perparkiran di Indonesia yang nyaman dan efisien dengan Parkhere.",
                titleColor = getColor(R.color.orange_primary),
                descriptionColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                titleTypefaceFontRes = R.font.montserrat_bold,
                imageDrawable = R.drawable.parkhere_illustration_3
        ))

        setIndicatorColor(
            selectedIndicatorColor = getColor(R.color.orange_primary),
            unselectedIndicatorColor = getColor(R.color.orange_light)
        )

        setDoneText("Mulai")
        setDoneTextTypeface(R.font.montserrat_bold)
        setNextArrowColor(
            Color.BLACK
        )
        setColorDoneText(
            Color.BLACK
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        val moveIntent = Intent(this@OnboardingActivity, MainActivity::class.java)
        moveIntent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(moveIntent)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        val moveIntent = Intent(this@OnboardingActivity, MainActivity::class.java)
        moveIntent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(moveIntent)
    }

}