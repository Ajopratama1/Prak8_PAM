package com.example.prak8_pam.view.uicontroller



import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prak8_pam.view.FormSiswa
import com.example.prak8_pam.view.TampilSiswa


enum class Navigasi {
    Formulirku,
    Detail
}


@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    Scaffold { isiRuang ->
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding(isiRuang)
        ) {
            composable(route = Navigasi.Formulirku.name) {
                FormSiswa(
                    OnSubmitBtnClick = {
                        navController.navigate(Navigasi.Detail.name)
                    }

                )
            }
            composable(route = Navigasi.Detail.name) {
                TampilSiswa(
                    onBackBtnClick = {
                        cancelAndBackToFormulirku(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulirku(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulirku.name,
        inclusive = false)
}

