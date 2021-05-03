package com.reavature.beefcake.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.R
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.network.RetrofitAPIService
import com.reavature.beefcake.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding: FragmentLoginBinding
        get() = _binding!!


    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        this.activity?.actionBar?.setDisplayShowTitleEnabled(true)

        binding.loginButton.setOnClickListener {

            if (validateInput()) {
                loginRequest()
            }

        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    private fun validateInput(): Boolean {
        var isValid: Boolean = true

        if (et_login_username.text.toString().isNullOrBlank()) {
            et_login_username.error = "Please Enter Correct Username"
            isValid = false
        }

        if (et_login_password.text.toString().isNullOrBlank()) {
            et_login_password.error = "Please Enter Correct Password"
            isValid = false
        }

        return isValid
    }

    @SuppressLint("LogNotTimber")
    private fun loginRequest() {

        val jsonObject = JSONObject()

        jsonObject.put("username", binding.etLoginUsername.text.toString())
        jsonObject.put("password", binding.etLoginPassword.text.toString())

//             Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

//            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = RetrofitAPIService.instance2.loginUser(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e(
                        "RETROFIT REQUEST",
                        "${response.code()} + ${response.body()?.token}"
                    )
                    if (response.body()?.token == null) {
                        et_login_username.error = "Please Enter Correct Username"
                        et_login_password.error = "Please Enter Correct Password"
                        Log.e("RETROFIT_ERROR", response.code().toString())
                    } else if (response.body()?.token != null) {


                        Log.e(
                            "username",
                            binding.etLoginUsername.text.toString()
                        )


                        MemberDatabase.addMemberToken(
                            et_login_username.text.toString(),
                            response.body()?.token.toString()
                        )
                        Log.e(
                            "Member List",
                            "${MemberDatabase.memberList}"
                        )

                        MemberDatabase.currentMember =
                            MemberDatabase.memberList[et_login_username.text.toString()]

                        MemberDatabase.currentMember?.username = et_login_username.text.toString()
                        MemberDatabase.currentMember?.token = response.body()?.token.toString()

                        Log.e(
                            "Current Member:",
                            "${MemberDatabase.currentMember}"
                        )
                        val token = MemberDatabase.currentMember?.token.toString()
                        Log.e("token", token)

                        findNavController().navigate(R.id.action_loginFragment_to_mainMenu3)

                    }
                }
            }

        }
    }
}
