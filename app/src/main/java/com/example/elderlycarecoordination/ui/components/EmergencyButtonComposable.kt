package com.example.elderlycarecoordination.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.elderlycarecoordination.model.FamilyMember

@Composable
fun EmergencyButton(
    context: Context,
    familyMembers: List<FamilyMember>
) {
    Button(onClick = {
        familyMembers.forEach { member ->
            // Example: send SMS
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:${member.phoneNumber}")
                putExtra("sms_body", "Emergency! Need help.")
            }
            context.startActivity(smsIntent)

            // Example: call (requires CALL_PHONE permission)
            val callIntent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:${member.phoneNumber}")
            }
            context.startActivity(callIntent)
        }
    }) {
        Text("Emergency")
    }
}
