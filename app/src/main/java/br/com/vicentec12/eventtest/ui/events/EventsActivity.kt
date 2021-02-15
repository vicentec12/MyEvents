package br.com.vicentec12.eventtest.ui.events

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.vicentec12.eventtest.EventsApp
import br.com.vicentec12.eventtest.R
import br.com.vicentec12.eventtest.data.model.Event
import br.com.vicentec12.eventtest.databinding.ActivityEventsBinding
import br.com.vicentec12.eventtest.interfaces.OnItemClickListener
import br.com.vicentec12.eventtest.ui.event_details.EventDetailsActivity
import javax.inject.Inject

class EventsActivity : AppCompatActivity(), OnItemClickListener, View.OnClickListener {

    lateinit var mBinding: ActivityEventsBinding

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: EventsViewModel by viewModels { mFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as EventsApp).mAppComponent
            .eventsComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        mBinding = ActivityEventsBinding.inflate(layoutInflater).apply {
            setContentView(root)
            viewModel = mViewModel
            lifecycleOwner = this@EventsActivity
            setSupportActionBar(lytToobar.toolbar)
        }
        init()
    }

    private fun init() {
        setupRecycler()
        mViewModel.listEvents()
        mBinding.lytErrorMessage.btnErrorMessage.setOnClickListener(this)
    }

    private fun setupRecycler() {
        mBinding.rvwEvents.apply {
            val mAdapter = EventsAdapter()
            mAdapter.mOnItemClickListener = this@EventsActivity
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.btn_error_message -> mViewModel.listEvents()
            }
        }
    }

    override fun onItemClick(mView: View, mItem: Any?, mPosition: Int) {
        mItem?.let {
            val mEvent = it as Event
            startActivity(EventDetailsActivity.newIntentInstance(this, mEvent.id, mEvent.image))
        }
    }

}