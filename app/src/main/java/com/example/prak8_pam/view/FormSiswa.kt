@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.prak8_pam.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField // Diimpor yang benar
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.prak8_pam.R


@OptIn(ExperimentalMaterial3Api::class) // Anotasi dipindahkan ke fungsi yang relevan
@Composable
fun FormSiswa(
    pilihanJK: List<String>,
    OnSubmitButtonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
) {


    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtAlamat by remember { mutableStateOf("") }
    var txtGender by remember { mutableStateOf("") }
    var listData: MutableList<String> = mutableListOf(txtNama, txtAlamat, txtGender)

    Scaffold(
        topBar = { // 1. TopAppBar harus berada di dalam parameter topBar
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.home),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    // 2. Tentukan nama parameter 'containerColor'
                    containerColor = colorResource(id = R.color.teal_700)
                )
            )
        }
    ) { isiRuang ->
        Column(
            modifier = Modifier.padding(isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            // 3. Menggunakan OutlinedTextField dari Material3
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 20.dp),
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it

                },
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(20.dp)
                    .width(250.dp),
                thickness = 1.dp, // 5. 'thickness' harus memiliki nilai (misal: 1.dp)
                color = Color.Red
            )

            Row {
                pilihanJK.forEach { item ->
                    Row(modifier = Modifier.selectable(
                        selected = txtGender == item,
                        onClick = {
                            txtGender = item
                        }
                    ),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        )
                        Text(item)
                    }



                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(20.dp)
                    .width(250.dp), // 8. Tambahkan koma di sini
                thickness = 1.dp,
                color = Color.Red
            )

            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(250.dp),
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = {
                    txtAlamat = it
                },
            )
            Spacer(modifier = Modifier.height(20.dp)) // 10. Menggunakan 'Spacer' dari Compose
            Button(
                modifier = Modifier.fillMaxWidth(1f),
                enabled = txtAlamat.isNotEmpty(),
                onClick = { OnSubmitButtonClicked(listData) }
            ) {
                // 11. Lengkapi ID resource string untuk teks tombol
                Text(stringResource(id = R.string.submit))
            }
        }
    }
}
