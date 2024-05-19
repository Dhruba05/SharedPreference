package com.example.shared_preference

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shared_preference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences("myPref" , Context.MODE_PRIVATE)
        val editor =sharedPref.edit()
        val editName= binding.etName
        val editAge=binding.etAge
        val editAdult=binding.checkbox

        binding.btnSave.setOnClickListener {
            val name =editName.text.toString()
            val age = editAge.text.toString().toInt()
            val isAdult=editAdult.isChecked

            editor.apply {
                putString("name" , name)
                putInt("age" , age)
                putBoolean("isAdult" , isAdult)
                apply()
                //commit() can be used but it puts data syncronously and will block main thread

            }
            Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()

        }
        binding.btnLoad.setOnClickListener {
            val name =sharedPref.getString("name" , null)
            val age=sharedPref.getInt("age" , 0)
            val isAdult=sharedPref.getBoolean("isAdult" , false)


            editName.setText(name)
            editAge.setText(age.toString())
            editAdult.isChecked = isAdult
            Toast.makeText(applicationContext, "Data loaded", Toast.LENGTH_SHORT).show()
        }


        }
    }
