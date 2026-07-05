package com.sildeag.sound2text.featurepdf.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.featurepdf.viewmodel.FormDiscoveryViewModel
import com.sildeag.sound2text.core.pdf.PdfForm

@Composable
fun FormListScreen(viewModel: FormDiscoveryViewModel) {
    val forms by viewModel.forms.collectAsState()

    Column {
        forms.forEach { (groupId, parts) ->
            Text(groupId)
            parts.forEach { form ->
                Text("• ${form.name}")
            }
        }
    }
}

