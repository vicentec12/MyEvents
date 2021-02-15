package br.com.vicentec12.eventtest.extensions

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.vicentec12.eventtest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.Behavior.DragCallback
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


object Extensions {

    fun Double.toCurrency() = NumberFormat
        .getCurrencyInstance(Locale.getDefault()).format(this)

    fun Long.toDate(): String {
        val pattern = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(Date(this))
    }

    fun AppCompatImageView.loadUrl(mUrl: String?) {
        Glide.with(this.context).load(mUrl ?: "")
            .apply(RequestOptions().dontTransform())
            .error(R.drawable.ic_broken_image)
            .fallback(R.drawable.ic_broken_image)
            .onlyRetrieveFromCache(true)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(createProgressDrawable(this.context))
            .into(this)
    }

    fun AppBarLayout.setCollapsingToolbarExpandable(mExpandedToolbar: Boolean) {
        val params = this.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior?.setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return mExpandedToolbar
            }
        })
        this.setExpanded(mExpandedToolbar, true)
    }

    fun TextInputLayout.isEmptyValue(): Boolean {
        if (editText?.text.toString().trim().isEmpty()) {
            addError(String.format(context.getString(R.string.message_error_empty_value), hint))
            return false
        }
        return true
    }

    private fun TextInputLayout.addError(message: String?) {
        error = message
        parent.requestChildFocus(this, this)
        if (endIconMode != TextInputLayout.END_ICON_NONE)
            errorIconDrawable = null
    }

    fun TextInputLayout.removeError() {
        if (error != null) {
            error = null
            isErrorEnabled = false
        }
    }

    private fun createProgressDrawable(context: Context): CircularProgressDrawable {
        val mProgressDrawable = CircularProgressDrawable(context)
        mProgressDrawable.setStyle(CircularProgressDrawable.LARGE)
        val mColor = ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            mProgressDrawable.colorFilter = BlendModeColorFilter(mColor, BlendMode.SRC_ATOP);
        else
            mProgressDrawable.setColorFilter(mColor, PorterDuff.Mode.SRC_ATOP);
        mProgressDrawable.start()
        return mProgressDrawable
    }

}