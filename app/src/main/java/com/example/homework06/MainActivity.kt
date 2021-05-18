package com.example.homework06

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeInfo()
    }

    private fun changeInfo() {
        binding.btnChangeInfo.setOnClickListener {
            if (isFilled()) {

                val gender = if (binding.rbMale.isChecked) "Male" else "Female"

                val person = Person(
                    binding.edFirstName.text.toString(),
                    binding.edLastName.text.toString(),
                    binding.edEmail.text.toString(),
                    binding.edBirthYear.text.toString().toInt(),
                    gender
                )

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("person", person)
                startActivityForResult(intent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFilled(): Boolean {
        return binding.edFirstName.text.isNotEmpty() &&
                binding.edLastName.text.isNotEmpty() &&
                binding.edEmail.text.isNotEmpty() &&
                binding.edBirthYear.text.isNotEmpty()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedPerson = data?.getParcelableExtra<Person>("updatedPerson")
            binding.edFirstName.setText(updatedPerson?.firstName)
            binding.edLastName.setText(updatedPerson?.lastName)
            binding.edEmail.setText(updatedPerson?.email)
            binding.edBirthYear.setText(updatedPerson!!.birthYear.toString())

            if (updatedPerson.gender == "Male") {
                binding.rbMale.isChecked = true
            } else {
                binding.rbFemale.isChecked = true
            }
        }
    }
}