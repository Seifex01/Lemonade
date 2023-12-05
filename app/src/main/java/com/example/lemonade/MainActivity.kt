package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    limonadaApp()
                }
            }
        }
    }
}

@Composable
fun limonadaApp(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center))
{
    //Variable del indice que van a utilizar tanto las imagenes como los diferentes textos
    var indice by remember { mutableStateOf(1) }
    //Variable de las pulsaciones que vamos a tener que dar en la pantalla
    var pulsaciones by remember { mutableStateOf(0) }
    //Variable del numero aleatorio que se generará para cambiar los elementos de la interfaz
    var random = (1..4).random()

    //Las siguientes variables van a depender de la variable índice:

    //Las imagenes que vamos a utilizar e iran cambiando
    val imageResource = when (indice){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    //Los textos descriptivos de la imagen que vamos a utilizar e iran cambiando
    val textoContent = when (indice){
        1 -> R.string.limonero
        2 -> R.string.limon
        4 -> R.string.vaso_limonada
        else -> R.string.vaso_vacio
    }
    //Los textos que irán cambiando debajo de la imagen
    val textoImagen = when(indice){
        1 -> R.string.toca_limonero
        2 -> R.string.toca_limon
        3 -> R.string.toca_limonada
        else -> R.string.toca_vaso
    }


    //Panel de titulo
    Column(
        modifier = Modifier
            .background(Color(android.graphics.Color.parseColor("#F7DC6F")))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(24.dp)
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Imagen Clickable
        Image(
            painter = painterResource(id = imageResource),
            //Texto descriptivo de la imagen
            contentDescription = stringResource(id = textoContent),
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF82E0AA),
                    shape = RoundedCornerShape(20.dp))
                .size(240.dp)
                .clickable {
                    // Al hacer clic, incrementa el contador y cambia la imagen
                    pulsaciones++

                    if(pulsaciones == random){
                        indice = (indice % 4) + 1
                        /*
                         * Al dividir el indice entre 4 nos aseguramos que siempre sea menor que 4
                         * y despues le sumamos uno para que avance.
                         */

                        //ahora reiniciamos tanto la variable pulsaciones como random
                        pulsaciones = 0
                        random = (0..4).random()

                    }
                }
        )

        //Texto que se situa debajo de la imagen e irá cambiando junto a esta
        Text(
            text = stringResource(id = textoImagen),
            modifier = Modifier
                .padding(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        limonadaApp()
    }
}