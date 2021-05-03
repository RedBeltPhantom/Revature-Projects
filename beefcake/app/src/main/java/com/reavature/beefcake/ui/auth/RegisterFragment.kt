package com.reavature.beefcake.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.R
import com.reavature.beefcake.databinding.FragmentRegisterBinding
import com.reavature.beefcake.auth_model.User
import com.reavature.beefcake.network.RetrofitAPIService
import kotlinx.android.synthetic.main.fragment_register.*
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


class RegisterFragment() : Fragment() {


    /** This is the view binding that is made to reference the views of the Fragment Home Page */
    private var _binding: FragmentRegisterBinding? = null

    /** This is the binding that retrieves the binding to reference the views */
    private val binding: FragmentRegisterBinding
        get() = _binding!!


    @SuppressLint("LogNotTimber")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.bodytype,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                binding.spBodyType.adapter = adapter
            }
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.gender,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                binding.spSex.adapter = adapter
            }

        binding.btnRegister.setOnClickListener {

//            Log.e(
//                "username",
//                "$registrationUsername, $registrationPassword, $registrationEmail, $registrationAddress, $registrationPhone, $registrationHeight, $registrationWeight, $registrationAge, $registrationBodyType, $registrationGender"
//            )

            registerUser()?.let {
                registrationRequest()
//                Log.e(
//                    "username",
//                    "$registrationUsername, $registrationPassword, $registrationEmail, $registrationAddress, $registrationPhone, $registrationHeight, $registrationWeight, $registrationAge, $registrationBodyType, $registrationGender"
//                )
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }


    private fun registerUser(): User? {

        val invalidFields: MutableList<EditText> = arrayListOf()

        checkEachField(requireView()).forEach { view ->
            if (view is EditText && view.text.toString().isBlank())
                invalidFields.add(view)
        }

        //Phone number field only takes 9 characters (index of 8)
        if (et_registration_phone.text!!.length < 8)
            invalidFields.add(et_registration_phone)

        if (!EMAIL_ADDRESS.matcher(et_registration_email.text.toString()).matches())
            invalidFields.add(et_registration_email)

        invalidFields.distinct().forEach { it.error = "Please enter valid ${it.hint}" }


        if (invalidFields.isNotEmpty()) {
            return null

        }


        /**
         * Make sure to query data base to see if username already exist
         */

        return User(
            et_registration_username.text.toString(),
            et_registration_password.text.toString(),
            et_registration_email.text.toString(),
            et_registration_phone.text.toString(),
            "address",
            et_registration_height.text.toString(),
            et_registration_weight.text.toString(),
            et_registration_age.text.toString(),
            sp_bodyType.selectedItem.toString(),
            sp_sex.selectedItem.toString()
        )
    }


    @SuppressLint("LogNotTimber")
    fun registrationRequest() {

        val jsonObject = JSONObject()
        jsonObject.put("username", binding.etRegistrationUsername.editableText.toString())
        jsonObject.put("password", binding.etRegistrationPassword.editableText.toString())
        jsonObject.put("email", binding.etRegistrationEmail.editableText.toString())
        jsonObject.put("address",binding.etRegistrationAddress.editableText.toString() )
        jsonObject.put("phone", binding.etRegistrationPhone.editableText.toString())
        jsonObject.put("height", binding.etRegistrationHeight.editableText.toString())
        jsonObject.put("weight", binding.etRegistrationWeight.editableText.toString())
        jsonObject.put("age", binding.etRegistrationAge.editableText.toString())
        jsonObject.put("body", binding.spBodyType.selectedItem.toString())
        jsonObject.put("sex", binding.spSex.selectedItem.toString())

//             Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

//            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody =
            jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = RetrofitAPIService.instance2.createUser(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e(
                        "RETROFIT SUCCESSFUL",
                        "${response.code()} + ${response.body()?.message}"
                    )

                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}

fun checkEachField(view: View): List<View> {

    if (view !is ViewGroup)
        return arrayListOf(view)

    return arrayListOf<View>(view).apply { view.forEach { addAll(checkEachField(it)) } }
}

