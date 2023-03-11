package com.example.cloudcomputing_realtimeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cloudcomputing_realtimeactivity.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference

        binding.btnAdd.setOnClickListener {
            val name = binding.txtName.text.toString()
            val phone = binding.txtPhone.text.toString()
            val city = binding.txtCity.text.toString()

            val user = hashMapOf(
                "name" to name,
                "phone" to phone,
                "city" to city
            )
            myRef.child("user").child("$count").setValue(user)
            count++
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
        }
        binding.btnGetUser.setOnClickListener {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value
                    binding.tvUserData.text = value.toString()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}