package com.example.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import com.example.settings.ui.theme.SettingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SettingsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsContainer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsContainer(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SettingsHeader()
        SettingsImage()
        SettingsCheckbox()
        SettingsSwitch()
        SettingsSlider()
        SettingsRadioButtons()
        SettingsAlertDialog()
    }
}

@Composable
fun SettingsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 10.dp)
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.settings_icon_description)
        )
    }
}

@Composable
fun SettingsImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_profile_image),
            fontSize = 18.sp
        )
        Image(
            painter = painterResource(id = R.drawable.sunflower),
            contentDescription = stringResource(id = R.string.settings_profile_image),
            modifier = Modifier
                .padding(10.dp)
                .height(34.dp)
                .clickable {
                    // Acción al hacer clic en la imagen
                }
        )
    }
}

@Composable
fun SettingsCheckbox() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_consent),
            fontSize = 18.sp
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Composable
fun SettingsSwitch() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.settings_mobile_data),
            fontSize = 18.sp
        )
        Switch(
            modifier = Modifier.padding(end = 10.dp),
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Composable
fun SettingsSlider() {
    var sliderValue by remember { mutableStateOf(0f) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = stringResource(id = R.string.settings_text_size),
            fontSize = 18.sp
        )
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            steps = 2
        )
    }
}

@Composable
fun SettingsRadioButtons() {
    var selectedPaymentMethod by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.payment_method),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        listOf("PayPal", "Credit Card", "Bank Transfer").forEach { paymentMethod ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (selectedPaymentMethod == paymentMethod),
                    onClick = { selectedPaymentMethod = paymentMethod },
                    colors = RadioButtonDefaults.colors()
                )
                Text(
                    text = paymentMethod,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SettingsAlertDialog() {
    var showDialog by remember { mutableStateOf(false) }

    Button(onClick = { showDialog = true }) {
        Text(text = stringResource(id = R.string.sign_out))
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = stringResource(id = R.string.alert_title))
            },
            text = {
                Text(text = stringResource(id = R.string.alert_message))
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }
}

@Composable
fun BoxDisplay() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "RED",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "GREEN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "BLUE",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun SurfaceExample() {
    Surface(
        modifier = Modifier
            .padding(40.dp)
            .width(300.dp),
        color = Color.LightGray,
        contentColor = Color.Yellow,
        shape = RoundedCornerShape(22.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        border = BorderStroke(2.dp, SolidColor(Color.Blue))
    ) {
        Text(
            text = "Hello, Surface!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 22.sp
        )
    }
}

@Preview
@Composable
fun SettingsContainerPreview() {
    SettingsContainer()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SettingsTheme {
        Text("Vista previa")
    }
}
