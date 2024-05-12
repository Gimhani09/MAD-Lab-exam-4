package com.example.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admincrud.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val id =binding.uploadId.text.toString()
            val name =binding.uploadName.text.toString()
            val age =binding.uploadAge.text.toString()
            val gender =binding.uploadGender.text.toString()
            val contact =binding.uploadPhone.text.toString()
            val diagnosis =binding.uploadDiagnosis.text.toString()
            val history =binding.uploadHistory.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("Patient Information")
            val PatientData=PatientData(id,name,age,gender,contact,diagnosis,history)
            databaseReference.child(id).setValue(PatientData).addOnSuccessListener {
                binding.uploadId.text.clear()
                binding.uploadName.text.clear()
                binding.uploadAge.text.clear()
                binding.uploadGender.text.clear()
                binding.uploadPhone.text.clear()
                binding.uploadDiagnosis.text.clear()
                binding.uploadHistory.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

    }
}