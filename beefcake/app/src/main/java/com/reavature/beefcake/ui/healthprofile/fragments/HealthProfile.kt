package com.reavature.beefcake.ui.healthprofile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.R
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.network.RetrofitAPIService
import com.reavature.beefcake.databinding.FragmentHealthProfileBinding
import com.reavature.beefcake.healthprofile_model.HealthProfileResponse
import com.reavature.beefcake.ui.auth.checkEachField
import com.reavature.beefcake.ui.healthprofile.viewmodel.HealthProfileViewModel
import kotlinx.android.synthetic.main.fragment_health_profile.*
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject



/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

class HealthProfile() : Fragment() {

    private var _binding: FragmentHealthProfileBinding? = null

    private val binding: FragmentHealthProfileBinding
        get() = _binding!!

    private val viewModel: HealthProfileViewModel by lazy {
        ViewModelProvider(this).get(HealthProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHealthProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.btnUpdate.setOnClickListener {
            CheckforValidData()?.let {
                updateProfileRequest()
            }
        }
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
    private fun CheckforValidData(): HealthProfileResponse? {

        val invalidFields: MutableList<EditText> = arrayListOf()
        checkEachField(requireView()).forEach { view ->
            if (view is EditText && view.text.toString().isBlank())
                invalidFields.add(view)
        }
        //Phone number field only takes 9 characters (index of 8)
        if (et_phone.text!!.length < 8)
            invalidFields.add(et_phone)

        if (!Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches())
            invalidFields.add(et_email)

        invalidFields.distinct().forEach { it.error = "Please enter valid ${it.hint}" }

        if (invalidFields.isNotEmpty()) {
            return null
        }

        return HealthProfileResponse(
            et_username.text.toString(),
            et_password.text.toString(),
            et_email.text.toString(),
            et_phone.text.toString(),
            et_address.text.toString(),
            et_height.text.toString(),
            et_weight.text.toString(),
            et_age.text.toString(),
            et_bodyType.text.toString(),
            et_sex.text.toString()
        )
    }


    private @SuppressLint("LogNotTimber")
    fun updateProfileRequest() {

        val jsonObject = JSONObject()

        jsonObject.put("username", binding.etUsername.editableText.toString())
        jsonObject.put("password", binding.etPassword.editableText.toString())
        jsonObject.put("email", binding.etEmail.editableText.toString())
        jsonObject.put("address", binding.etAddress.editableText.toString())
        jsonObject.put("phone", binding.etPhone.editableText.toString())
        jsonObject.put("height", binding.etHeight.editableText.toString())
        jsonObject.put("weight", binding.etWeight.editableText.toString())
        jsonObject.put("age", binding.etAge.editableText.toString())
        jsonObject.put("body", binding.etBodyType.editableText.toString())
        jsonObject.put("sex", binding.etSex.editableText.toString())

//             Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
//            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response

//            MemberDatabase.addMemberToken(
//                et_username.text.toString(),
//                MemberDatabase.currentMember?.token.toString()
//            )
//            MemberDatabase.currentMember = MemberDatabase.memberList[et_username.text.toString()]
//
//            Log.e("Member List", "${MemberDatabase.memberList}")
//            Log.e("Current Member", "${MemberDatabase.memberList[et_username.text.toString()]}")

            val response = RetrofitAPIService.instance2.updateProfile(
                requestBody,
                "Bearer ${MemberDatabase.currentMember?.token.toString()}"
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body()?.username == null) {

                        Toast.makeText(context, "Sorry, something went wrong, please try again", Toast.LENGTH_LONG).show()
                        Log.e("RETROFIT_ERROR", response.code().toString())
                    } else if (response.body() != null) {

                        MemberDatabase.addMemberToken(
                            et_username.text.toString(),
                            MemberDatabase.currentMember?.token.toString()
                        )
                        MemberDatabase.currentMember = MemberDatabase.memberList[et_username.text.toString()]

                        Log.e("Member List", "${MemberDatabase.memberList}")
                        Log.e("Current Member", "${MemberDatabase.memberList[et_username.text.toString()]}")
                        Log.e(
                            "RETROFIT SUCCESSFUL",
                            "${response.code()} + ${response.body()?.username}"
                        )
                        findNavController().navigate(R.id.action_healthProfile_to_mainMenu)
                    }
                }
            }
        }
    }
}