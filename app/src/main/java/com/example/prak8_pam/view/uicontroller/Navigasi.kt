package com.example.prak8_pam.view.uicontroller




import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prak8_pam.model.DataJK.JenisK
import com.example.prak8_pam.view.FormSiswa
import com.example.prak8_pam.view.TampilSiswa
import com.example.prak8_pam.viewmodel.SiswaViewModel

enum class Navigasi {
    FormSiswa,
    TampilSiswa
}

@Composable
fun SiswaApp(
    // edit 1 : parameter viewModel
    modifier: Modifier,
    viewModel : SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
){
    Scaffold { isiRuang->
        // edit 2 : tambahkan variabel uiState
        val uistate = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.FormSiswa.name,

            modifier = Modifier.padding( isiRuang)) {
            composable(route = Navigasi.FormSiswa.name){
                // edit 3 : tambahkan variabel konteks
                val konteks = LocalContext.current
                FormSiswa (
                    // edit 4 : parameter pilihanJK dan onSubmitButtonClicked
                    pilihanJK = JenisK.map {id -> konteks.resources.getString(id) },
                    OnSubmitButtonClicked ={
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.TampilSiswa.name)
                    }
                )
            }
            composable(route = Navigasi.TampilSiswa.name) {
                TampilSiswa(
                    // edit 5 : parameter statusUiSiswa
                    statusUiSiswa = uistate.value,
                    onBackBtnClick = {
                        cancelAndBackToFormSiswa(navController) }
                )
            }
        }
    }
}
private fun cancelAndBackToFormSiswa(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.FormSiswa.name,
        inclusive = false)
}