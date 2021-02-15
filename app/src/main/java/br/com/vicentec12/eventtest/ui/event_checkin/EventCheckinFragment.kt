package br.com.vicentec12.eventtest.ui.event_checkin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import br.com.vicentec12.eventtest.EventsApp
import br.com.vicentec12.eventtest.R
import br.com.vicentec12.eventtest.databinding.FragmentEventCheckinBinding
import br.com.vicentec12.eventtest.extensions.Extensions.isEmptyValue
import br.com.vicentec12.eventtest.extensions.Extensions.removeError
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class EventCheckinFragment(
    private val mEventId: Int
) : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var mBinding: FragmentEventCheckinBinding

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: EventCheckinViewModel by viewModels { mFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as EventsApp).mAppComponent
            .eventCheckinComponent().create().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEventCheckinBinding.inflate(inflater, container, false).apply {
            viewModel = mViewModel
            lifecycleOwner = this@EventCheckinFragment
        }
        init()
        return mBinding.root
    }

    private fun init() {
        setupResultCode()
        mBinding.btnEffectCheckin.setOnClickListener(this)
    }

    private fun setupResultCode() {
        mViewModel.resultCode.observe(this) { code ->
            if (code != 0) {
                val builder =
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.title_alert_dialog_notice)
                        .setMessage(mViewModel.message.value!!)
                        .setPositiveButton(R.string.label_ok, null);
                if (code == 200)
                    builder.setOnDismissListener { this.dismiss() }
                builder.show()
            }
        }
    }

    fun validateCheckin(): Boolean {
        mBinding.tilCheckinName.removeError()
        mBinding.tilCheckinEmail.removeError()
        if (!mBinding.tilCheckinName.isEmptyValue())
            return false
        if (!mBinding.tilCheckinEmail.isEmptyValue())
            return false
        return true
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.btn_effect_checkin -> {
                    if (validateCheckin())
                        mViewModel.effectCheckin(mEventId)
                }
            }
        }
    }

    companion object {

        fun newInstance(mEventId: Int) = EventCheckinFragment(mEventId)

    }

}