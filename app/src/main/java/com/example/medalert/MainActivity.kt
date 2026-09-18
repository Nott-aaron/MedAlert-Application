package com.example.medalert

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var medicineList: TextView
    private lateinit var addMedicineButton: Button

    // Store the actual medicine information
    private var medicineName = ""
    private var medicineDosage = ""
    private var medicineType = ""
    private var medicineFrequency = ""
    private var medicineReminder = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d("MedAlert", "MainActivity: onCreate()")

        medicineList = findViewById(R.id.tvMedicineList)
        addMedicineButton = findViewById(R.id.btnAddMedicine)

        // Make text clearly visible
        medicineList.setTextColor(Color.BLACK)
        medicineList.textSize = 17f
        medicineList.alpha = 1.0f

        // ---------------------------------------
        // ADD MEDICINE
        // ---------------------------------------

        addMedicineButton.setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    AddMedicineFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        // ---------------------------------------
        // RECEIVE MEDICINE FROM FRAGMENT
        // ---------------------------------------

        supportFragmentManager.setFragmentResultListener(
            "medicine_result",
            this
        ) { _, bundle ->

            medicineName =
                bundle.getString("name", "")

            medicineDosage =
                bundle.getString("dosage", "")

            medicineType =
                bundle.getString("type", "")

            medicineFrequency =
                bundle.getString("frequency", "")

            medicineReminder =
                bundle.getBoolean("reminder", false)

            // Display medicine
            medicineList.text =
                "💊  $medicineName\n\n" +
                        "Dosage: $medicineDosage\n" +
                        "Type: $medicineType\n" +
                        "Frequency: $medicineFrequency\n" +
                        "Reminder: " +
                        if (medicineReminder) {
                            "Enabled 🔔"
                        } else {
                            "Disabled"
                        }

            // Force black text
            medicineList.setTextColor(Color.BLACK)
            medicineList.textSize = 17f
            medicineList.alpha = 1.0f

            Log.d(
                "MedAlert",
                "Medicine received: $medicineName"
            )
        }

        // ---------------------------------------
        // OPEN MEDICINE DETAILS
        // ---------------------------------------

        medicineList.setOnClickListener {

            if (medicineName.isNotEmpty()) {

                val intent = Intent(
                    this,
                    MedicineDetailsActivity::class.java
                )

                // Send individual values
                intent.putExtra(
                    "name",
                    medicineName
                )

                intent.putExtra(
                    "dosage",
                    medicineDosage
                )

                intent.putExtra(
                    "type",
                    medicineType
                )

                intent.putExtra(
                    "frequency",
                    medicineFrequency
                )

                intent.putExtra(
                    "reminder",
                    medicineReminder
                )

                startActivity(intent)
            }
        }
    }

    // ---------------------------------------
    // ACTIVITY LIFECYCLE
    // ---------------------------------------

    override fun onStart() {
        super.onStart()

        Log.d(
            "MedAlert",
            "MainActivity: onStart()"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            "MedAlert",
            "MainActivity: onResume()"
        )
    }

    override fun onPause() {
        super.onPause()

        Log.d(
            "MedAlert",
            "MainActivity: onPause()"
        )
    }

    override fun onStop() {
        super.onStop()

        Log.d(
            "MedAlert",
            "MainActivity: onStop()"
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(
            "MedAlert",
            "MainActivity: onDestroy()"
        )
    }
}