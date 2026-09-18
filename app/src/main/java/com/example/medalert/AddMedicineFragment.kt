package com.example.medalert

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment

class AddMedicineFragment : Fragment() {

    private val CHANNEL_ID = "medalert_channel"

    // Used when notification permission is requested
    private var pendingMedicineName = ""
    private var pendingDosage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onCreate()"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_add_medicine,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onViewCreated()"
        )

        val medicineName =
            view.findViewById<EditText>(
                R.id.etMedicineName
            )

        val dosage =
            view.findViewById<EditText>(
                R.id.etDosage
            )

        val typeGroup =
            view.findViewById<RadioGroup>(
                R.id.radioType
            )

        val frequencyGroup =
            view.findViewById<RadioGroup>(
                R.id.radioFrequency
            )

        val reminder =
            view.findViewById<CheckBox>(
                R.id.checkboxReminder
            )

        val saveButton =
            view.findViewById<Button>(
                R.id.btnSaveMedicine
            )

        // ---------------------------------------
        // SAVE MEDICINE
        // ---------------------------------------

        saveButton.setOnClickListener {

            val name =
                medicineName.text
                    .toString()
                    .trim()

            val dose =
                dosage.text
                    .toString()
                    .trim()

            // Validate name
            if (name.isEmpty()) {

                medicineName.error =
                    "Enter medicine name"

                return@setOnClickListener
            }

            // Validate dosage
            if (dose.isEmpty()) {

                dosage.error =
                    "Enter dosage"

                return@setOnClickListener
            }

            // Validate medicine type
            val selectedTypeId =
                typeGroup.checkedRadioButtonId

            if (selectedTypeId == -1) {

                Toast.makeText(
                    requireContext(),
                    "Select medicine type",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Validate frequency
            val selectedFrequencyId =
                frequencyGroup.checkedRadioButtonId

            if (selectedFrequencyId == -1) {

                Toast.makeText(
                    requireContext(),
                    "Select frequency",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val selectedType =
                view.findViewById<RadioButton>(
                    selectedTypeId
                ).text.toString()

            val selectedFrequency =
                view.findViewById<RadioButton>(
                    selectedFrequencyId
                ).text.toString()

            // ---------------------------------------
            // SEND DATA TO MAIN ACTIVITY
            // ---------------------------------------

            val bundle = Bundle()

            bundle.putString(
                "name",
                name
            )

            bundle.putString(
                "dosage",
                dose
            )

            bundle.putString(
                "type",
                selectedType
            )

            bundle.putString(
                "frequency",
                selectedFrequency
            )

            bundle.putBoolean(
                "reminder",
                reminder.isChecked
            )

            parentFragmentManager.setFragmentResult(
                "medicine_result",
                bundle
            )

            // ---------------------------------------
            // NOTIFICATION
            // ---------------------------------------

            if (reminder.isChecked) {

                pendingMedicineName = name
                pendingDosage = dose

                createNotificationChannel()

                sendNotificationOrRequestPermission()
            }

            Toast.makeText(
                requireContext(),
                "Medicine saved successfully!",
                Toast.LENGTH_SHORT
            ).show()

            parentFragmentManager.popBackStack()
        }
    }

    // ---------------------------------------
    // CREATE NOTIFICATION CHANNEL
    // ---------------------------------------

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Medicine Reminders",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description =
                "Notifications for MedAlert medicine reminders"

            channel.enableVibration(true)

            val notificationManager =
                requireContext().getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            notificationManager.createNotificationChannel(
                channel
            )
        }
    }

    // ---------------------------------------
    // SEND OR REQUEST PERMISSION
    // ---------------------------------------

    private fun sendNotificationOrRequestPermission() {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU
        ) {

            if (
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                requestPermissions(
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS
                    ),
                    100
                )

                return
            }
        }

        showNotification(
            pendingMedicineName,
            pendingDosage
        )
    }

    // ---------------------------------------
    // NOTIFICATION
    // ---------------------------------------

    private fun showNotification(
        medicineName: String,
        dosage: String
    ) {

        val notification =
            NotificationCompat.Builder(
                requireContext(),
                CHANNEL_ID
            )
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
                )
                .setContentTitle(
                    "💊 Medicine Reminder Created"
                )
                .setContentText(
                    "$medicineName - $dosage reminder has been created."
                )
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "Medicine: $medicineName\n" +
                                    "Dosage: $dosage\n\n" +
                                    "Your medicine reminder has been created successfully."
                        )
                )
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )
                .setCategory(
                    NotificationCompat.CATEGORY_REMINDER
                )
                .setAutoCancel(true)
                .setDefaults(
                    NotificationCompat.DEFAULT_ALL
                )
                .build()

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }

        NotificationManagerCompat
            .from(requireContext())
            .notify(
                System.currentTimeMillis().toInt(),
                notification
            )

        Log.d(
            "MedAlert",
            "Notification sent for $medicineName"
        )
    }

    // ---------------------------------------
    // PERMISSION RESULT
    // ---------------------------------------

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (requestCode == 100) {

            if (
                grantResults.isNotEmpty() &&
                grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {

                // Permission granted → NOW send notification
                showNotification(
                    pendingMedicineName,
                    pendingDosage
                )

                Log.d(
                    "MedAlert",
                    "Notification permission granted"
                )

            } else {

                Toast.makeText(
                    requireContext(),
                    "Notification permission denied",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d(
                    "MedAlert",
                    "Notification permission denied"
                )
            }
        }
    }

    // ---------------------------------------
    // FRAGMENT LIFECYCLE
    // ---------------------------------------

    override fun onStart() {
        super.onStart()

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onStart()"
        )
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onResume()"
        )
    }

    override fun onPause() {
        super.onPause()

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onPause()"
        )
    }

    override fun onStop() {
        super.onStop()

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onStop()"
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d(
            "MedAlert",
            "AddMedicineFragment: onDestroyView()"
        )
    }
}