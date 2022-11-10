package aaa.bivizul.a41project.presentation

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            fragmentManager.commit {
                add<MainFragment>(R.id.fragmentContainer, FRAGMENT_MAIN)
            }
        }

    }

    private companion object {

        private const val FRAGMENT_MAIN = "main"

    }
}