package com.wkds.basicscodelabcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wkds.basicscodelabcompose.ui.theme.BasicsCodelabComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabComposeTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onClickContinueBtn = { shouldShowOnboarding = !shouldShowOnboarding })
    } else {
        HomeScreen()
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onClickContinueBtn: () -> Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClickContinueBtn
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun MyAppPreview() {
    BasicsCodelabComposeTheme {
        MyApp()
    }
}


@Composable
fun HomeScreen() {

    val people = listOf<String>("Michael", "John", "Purity")
    // A surface container using the 'background' color from the theme
    Surface(
        color = Color.White, //MaterialTheme.colorScheme.background
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {

        Column() {
            for (person in people) {
                CustomCard(person = person)
            }
        }
    }
}


@Composable
fun CustomCard(person: String) {

    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color(0xFF003030))


    ) {
        Row(
            modifier = Modifier.padding(all = 24.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = extraPadding)
                    .weight(1f)
            ) {
                Text("Hi,", color = Color.White)
                Text("$person", color = Color.White)
            }
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsCodelabComposeTheme {

    }
}