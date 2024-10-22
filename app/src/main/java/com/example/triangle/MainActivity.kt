package com.example.triangle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.triangle.ui.theme.TriangleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var firstTriangle by remember {
                mutableStateOf("")
            }
            var secondTriangle by remember {
                mutableStateOf("")
            }
            var thirdTriangle by remember {
                mutableStateOf("")
            }
            var alertText by remember {
                mutableStateOf("")
            }
            TriangleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Spacer(modifier = Modifier.size(30.dp))

                        Text(
                            text = "Введите длины сторон треугольника",
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.size(10.dp))

                        OutlinedTextField(
                            value = firstTriangle,
                            onValueChange = { value ->
                                firstTriangle = value
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Превая строна")
                            })

                        OutlinedTextField(
                            value = secondTriangle,
                            onValueChange = { value ->
                                secondTriangle = value
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Вторая строна")
                            })

                        OutlinedTextField(
                            value = thirdTriangle,
                            onValueChange = { value ->
                                thirdTriangle = value
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Третья строна")
                            })

                        Button(

                            onClick = {
                                alertText =
                                    checkTriangleType(firstTriangle, secondTriangle, thirdTriangle)
                            }, modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(text = "Проверить")
                        }
                    }
                }

                if (alertText.isNotEmpty()) {
                    Dialog(onDismissRequest = { alertText = "" }) {
                        Column(
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    Color.White, shape = RoundedCornerShape(10.dp)
                                ),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = alertText,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                textAlign = TextAlign.Center,
                            )
                            Button(
                                onClick = { alertText = "" },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .height(48.dp),
                            ) {
                                Text(text = "Ок")
                            }
                        }
                    }
                }
            }
        }
    }

    fun checkTriangleType(a: String, b: String, c: String): String {
        val first = a.toIntOrNull()
        val second = b.toIntOrNull()
        val third = c.toIntOrNull()

        return when {
            a.isBlank() || b.isBlank() || c.isBlank() -> "Поля должны быть заполнены"
            first == null || second == null || third == null -> "Ошибка валидации полей"
            first <= 0 || second <= 0 || third <= 0 -> "Стороны должны быть положительными числами"
            first + second <= third || first + third <= second || second + third <= first -> "Треугольник не существует"
            first == second && second == third -> "Равносторонний треугольник"
            first == second || second == third || first == third -> "Равнобедренный треугольник"
            else -> "Разносторонний треугольник"
        }
    }

}
