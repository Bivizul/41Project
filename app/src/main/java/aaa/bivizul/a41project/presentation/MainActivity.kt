package aaa.bivizul.a41project.presentation

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import aaa.bivizul.a41project.databinding.ActivityMainBinding
import aaa.bivizul.a41project.domain.util.checkFunsponet
import aaa.bivizul.a41project.domain.util.getFunspoScrOrnt
import aaa.bivizul.a41project.domain.util.getFunspodlg
import aaa.bivizul.a41project.presentation.dofunspo.DofunspoFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        if (!checkFunsponet(this)) {
            getFunspodlg(this, this)
        } else {
            setContentView(R.layout.activity_main)
            val image = getFunspoScrOrnt(resources)
            binding.backMainActivity.load(image)

//            val fragmentManager = supportFragmentManager
//            if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
//                fragmentManager.commit {
//                    add<DofunspoFragment>(R.id.fragmentContainer, FRAGMENT_MAIN)
//                }
//            }
        }
    }

    private companion object {

        private const val FRAGMENT_MAIN = "main"

    }
}