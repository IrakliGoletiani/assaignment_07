package com.example.homework06

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework06.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    private lateinit var personData: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personData = intent.getParcelableExtra("person")!!

        setTextsToViews()
        changedInfo()
    }

    private fun setTextsToViews() {
        binding.edFirstName.setText(personData.firstName)
        binding.edLastName.setText(personData.lastName)
        binding.edEmail.setText(personData.email)
        binding.edBirthYear.setText(personData.birthYear.toString())

        if (personData.gender == "Male") binding.rbMale.isChecked = true else binding.rbFemale.isChecked = true
    }

    private fun changedInfo() {
        binding.btnSave.setOnClickListener {
            if (isFilled()) {
                if (binding.edFirstName.text.toString() != personData.firstName ||
                    binding.edLastName.text.toString() != personData.lastName ||
                    binding.edEmail.text.toString() != personData.email ||
                    binding.edBirthYear.text.toString() != personData.birthYear.toString()

                ) {
                    val gender = if (binding.rbMale.isChecked) "Male" else "Female"
                    val updatedPerson = Person(
                        binding.edFirstName.text.toString(),
                        binding.edLastName.text.toString(),
                        binding.edEmail.text.toString(),
                        binding.edBirthYear.text.toString().toInt(),
                        gender
                    )
                    intent.putExtra("updatedPerson", updatedPerson)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Profile not updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFilled(): Boolean {
        return binding.edFirstName.text.isNotEmpty() &&
                binding.edLastName.text.isNotEmpty() &&
                binding.edEmail.text.isNotEmpty() &&
                binding.edBirthYear.text.isNotEmpty()
    }

}