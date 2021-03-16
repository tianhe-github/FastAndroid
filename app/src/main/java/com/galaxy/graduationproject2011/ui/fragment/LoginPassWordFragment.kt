package com.galaxy.graduationproject2011.ui.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.galaxy.common.base.BaseFragment
import com.galaxy.common.extension.isInternetOn
import com.galaxy.common.extension.showShortToast
import com.galaxy.common.extension.singleClick
import com.galaxy.common.extension.start
import com.galaxy.common.utils.PreferenceUtils
import com.galaxy.graduationproject2011.MyApplication
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.Constant
import com.galaxy.graduationproject2011.room.AppDatabase
import com.galaxy.graduationproject2011.room.User
import com.galaxy.graduationproject2011.ui.activity.LoginActivity
import com.galaxy.graduationproject2011.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login_mobile_number.btnVerify
import kotlinx.android.synthetic.main.fragment_login_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.*


/**
 * Created by Liam.Zheng on 2020/10/20
 *
 * Des:
 */
class LoginPassWordFragment : BaseFragment<LoginActivity>() {

    override fun getlayoutId(): Int {
        return R.layout.fragment_login_password
    }

    override fun initView() {
        tvTitle.text = getString(R.string.log_in)

        ivBack.singleClick {
            findNavController().navigateUp()
        }

        tvGoToRegister.singleClick {
            findNavController().navigate(R.id.action_loginPassWordFragment_to_loginSignUpFragment)
        }
        btnVerify.singleClick {
            if (!requireActivity().isInternetOn()) {
                showShortToast(getString(R.string.the_network_not_connected))
                return@singleClick
            }
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
                lifecycleScope.launch {
                    val result = withContext(Dispatchers.IO) {
                        var resultFlag = false
                        AppDatabase.getInstance(requireContext()).userDao().getAll().forEach {
                            if (username == it.userName && password == it.userPassword) {
                                resultFlag = true
                                return@forEach
                            }
                        }
                        resultFlag
                    }

                    if (result) {
                        delay(1500)
                        requireActivity().start<MainActivity>()
                    } else {
                        showShortToast(getString(R.string.the_password_is_incorrect))
                    }
                }

        }
        etUsername.doAfterTextChanged {
            cheackButtonState()
        }
        etPassword.doAfterTextChanged {
            cheackButtonState()
        }
    }

    override fun initData() {

    }


    private fun cheackButtonState() {
        btnVerify.isEnabled = etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty()
    }

    companion object {
        private const val TAG = "LoginPassWordFragment"

        @JvmStatic
        fun newInstance() = LoginPassWordFragment()
    }


}