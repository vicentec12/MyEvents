package br.com.vicentec12.eventtest.ui.event_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import br.com.vicentec12.eventtest.EventsApp
import br.com.vicentec12.eventtest.R
import br.com.vicentec12.eventtest.databinding.ActivityEventDetailsBinding
import br.com.vicentec12.eventtest.extensions.Extensions.loadUrl
import br.com.vicentec12.eventtest.extensions.Extensions.setCollapsingToolbarExpandable
import javax.inject.Inject


class EventDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mBinding: ActivityEventDetailsBinding

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: EventDetailsViewModel by viewModels { mFactory }

    private var mEventId: Int = 0
    private lateinit var mImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as EventsApp).mAppComponent
            .eventDetailsComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        mBinding = ActivityEventDetailsBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(lytToolbar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            viewModel = mViewModel
            lifecycleOwner = this@EventDetailsActivity
        }
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        mEventId = intent.getIntExtra(EXTRA_EVENT_ID, 0)
        mImageUrl = intent.getStringExtra(EXTRA_IMAGE_URL) ?: ""
        mViewModel.getEventDetails(mEventId)
        setupCollapsedToolbar()
        mBinding.lytErrorMessage.btnErrorMessage.setOnClickListener(this)
    }

    private fun setupCollapsedToolbar() {
        ViewCompat.setTransitionName(mBinding.lytToolbar.ivwToolbar, "event")
        mBinding.lytToolbar.ivwToolbar.loadUrl(mImageUrl)
        mViewModel.isToolbarExpanded.observe(this) { isCollapsingExpanded ->
            mBinding.nsvEventDetails.isNestedScrollingEnabled = isCollapsingExpanded
            mBinding.lytToolbar.ablToolbar.setCollapsingToolbarExpandable(
                isCollapsingExpanded
            )
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.btn_error_message -> mViewModel.getEventDetails(mEventId)
            }
        }
    }

    companion object {

        private const val EXTRA_EVENT_ID = "event_id"
        private const val EXTRA_IMAGE_URL = "image_url"

        fun newIntentInstance(mContext: Context, mEventId: Int?, mImageUrl: String?): Intent {
            val mIntent = Intent(mContext, EventDetailsActivity::class.java)
            mIntent.putExtra(EXTRA_EVENT_ID, mEventId)
            mIntent.putExtra(EXTRA_IMAGE_URL, mImageUrl)
            return mIntent
        }

    }

}