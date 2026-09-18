package com.example.medalert

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MedicineDetailsActivity : AppCompatActivity() {

    private lateinit var medicineName: TextView
    private lateinit var medicineDosage: TextView
    private lateinit var medicineType: TextView
    private lateinit var medicineFrequency: TextView
    private lateinit var medicineReminder: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_medicine_details)

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onCreate()"
        )

        // Find views
        medicineName =
            findViewById(R.id.tvMedicineName)

        medicineDosage =
            findViewById(R.id.tvDosage)

        medicineType =
            findViewById(R.id.tvMedicineType)

        medicineFrequency =
            findViewById(R.id.tvFrequency)

        medicineReminder =
            findViewById(R.id.tvReminder)

        backButton =
            findViewById(R.id.btnBackDashboard)

        // ---------------------------------------
        // RECEIVE DATA FROM MAIN ACTIVITY
        // ---------------------------------------

        val name =
            intent.getStringExtra("name") ?: "Unknown"

        val dosage =
            intent.getStringExtra("dosage") ?: "-"

        val type =
            intent.getStringExtra("type") ?: "-"

        val frequency =
            intent.getStringExtra("frequency") ?: "-"

        val reminder =
            intent.getBooleanExtra(
                "reminder",
                false
            )

        // ---------------------------------------
        // DISPLAY DATA
        // ---------------------------------------

        medicineName.text = name

        medicineDosage.text =
            "Dosage: $dosage"

        medicineType.text =
            "Medicine Type: $type"

        medicineFrequency.text =
            "Frequency: $frequency"

        medicineReminder.text =
            if (reminder) {
                "🔔 Reminder: Enabled"
            } else {
                "🔕 Reminder: Disabled"
            }

        // Make all text black
        medicineName.setTextColor(Color.BLACK)
        medicineDosage.setTextColor(Color.BLACK)
        medicineType.setTextColor(Color.BLACK)
        medicineFrequency.setTextColor(Color.BLACK)
        medicineReminder.setTextColor(Color.BLACK)

        // ---------------------------------------
        // BACK TO DASHBOARD
        // ---------------------------------------

        backButton.setOnClickListener {

            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onStart()"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onResume()"
        )
    }

    override fun onPause() {
        super.onPause()

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onPause()"
        )
    }

    override fun onStop() {
        super.onStop()

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onStop()"
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(
            "MedAlert",
            "MedicineDetailsActivity: onDestroy()"
        )
    }
}