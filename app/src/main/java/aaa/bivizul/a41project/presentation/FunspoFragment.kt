@file:Suppress("DEPRECATION")

package aaa.bivizul.a41project.presentation

import aaa.bivizul.a41project.R
import aaa.bivizul.a41project.appComponent
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class FunspoFragment : Fragment() {

    private lateinit var funspowv: WebView

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_funspo, container, false)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        funspowv = root.findViewById(R.id.funspowv)

        funspowv.webViewClient = WebViewClient()

        funspowv.webChromeClient = MyChromeClient()
        funspowv.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        funspowv.isScrollbarFadingEnabled = false

        setSettings()

        val url = arguments?.getString(FUNSPOKOR) ?: FUNSPODOR

        if (savedInstanceState == null) {
            funspowv.post {
                kotlin.run { funspowv.loadUrl(url) }
            }
        }

        funspowv.canGoBack()
        funspowv.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                funspowv.canGoBack()
            ) {
                funspowv.goBack()
                return@OnKeyListener true
            }
            false
        })

        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val funspows = funspowv.settings
        funspows.javaScriptEnabled = true
        funspows.loadWithOverviewMode = true
        funspows.allowFileAccess = true
        funspows.domStorageEnabled = true
        funspows.builtInZoomControls = true
        funspows.displayZoomControls = false
        funspows.useWideViewPort = true
        funspows.setSupportZoom(true)
        funspows.userAgentString = funspows.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        funspowv.saveState(outState)
    }

    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }


        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (mCustomView == null) {
                null
            } else BitmapFactory.decodeResource(activity!!.applicationContext.resources, 2130837573)
        }

        override fun onHideCustomView() {
            (activity!!.window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            activity!!.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            activity?.requestedOrientation = mOriginalOrientation
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?
        ) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            mCustomView = paramView
            mOriginalSystemUiVisibility = activity!!.window.decorView.systemUiVisibility
            mOriginalOrientation = activity?.requestedOrientation!!
            mCustomViewCallback = paramCustomViewCallback
            (activity!!.window.decorView as FrameLayout).addView(
                mCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            activity!!.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REQUEST_CODE) {
            filePathCallback!!.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    intent
                )
            )
            filePathCallback = null
        }
    }

    companion object {
        const val FUNSPOKOR = "funspokor"
        const val FUNSPODOR = "https://www.google.com/"
    }

}