package com.wkds.basicscodelabcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onClickContinueBtn = { shouldShowOnboarding = !shouldShowOnboarding })
    } else {
        HomeScreen()
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onClickContinueBtn: () -> Unit) {

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

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview
@Composable
fun HomeScreen() {

    val people: List<String> = List(1000) { "Person $it" }
    // A surface container using the 'background' color from the theme
    Surface(
        color = Color.White, //MaterialTheme.colorScheme.background
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {

        LazyColumn() {
            items(items = people) { name ->
                CustomCard(person = name)
            }
        }
    }
}


@Composable
fun CustomCard(person: String) {

    val expanded = rememberSaveable { mutableStateOf(false) }

//    val extraPadding by animateDpAsState(
//        if (expanded.value) 48.dp else 0.dp,
//
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )

    val cardColor by animateColorAsState(
        if (expanded.value) Color(0xFF121212) else Color(0xFF003030),
        animationSpec = tween(
            durationMillis = 2000,
            delayMillis = 40,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = cardColor)
            .animateContentSize (
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )


    ) {
        Column() {
            Row(
                modifier = Modifier.padding(24.dp),
            ) {
                Column(
                    modifier = Modifier
                        //.padding(bottom = if (extraPadding > 0.dp) extraPadding else 0.dp)
                        .weight(1f)
                ) {
                    Text(
                        "Hello,",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text("$person", color = Color.White)
                }
//            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
//                Text(text = if (expanded.value) "Show less" else "Show more")
//            }

                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        if (!expanded.value) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
                        "side icon",
                        tint = Color.White
                    )
                }
            }

                AnimatedVisibility(visible = expanded.value) {
                    Row(
                        modifier = Modifier.padding(all = 24.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.card_paragraph_greetings),
                            color = Color.White
                        )
                    }
                }

        }
    }
}
