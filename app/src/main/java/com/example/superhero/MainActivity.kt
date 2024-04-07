package com.example.superhero


import SuperheroesTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superhero.Data.SuperHeroData.heroes
import com.example.superhero.Model.Hero


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeroApp(){

    Scaffold(
        modifier=Modifier.fillMaxSize(),
        topBar = {
          TopBar()
        }
    ){ it->
        HeroList(contentPadding = it)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier=Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name) ,
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier=modifier
    )

}


@Composable
fun HeroList(
    modifier: Modifier=Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)){
    LazyColumn(
        modifier=Modifier.padding(
            start = 16.dp,
            end = 16.dp),
        contentPadding = contentPadding
    ) {
        items(heroes){hero->
            HeroScreen(hero = hero,modifier=Modifier.padding(bottom = 8.dp))
        }
    }
}


@Composable
fun HeroScreen(
    hero:Hero,
    modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                HeroInformation(
                    name = hero.nameRes,
                    description = hero.descriptionRes
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            HeroImage(
                image = hero.imageRes,
                imageDes = hero.nameRes
            )

        }
    }


}

@Composable
fun HeroImage(modifier: Modifier= Modifier,image: Int,imageDes:Int){
   Box (modifier= Modifier
       .size(72.dp)
       .clip(RoundedCornerShape(8.dp))){
       Image(
           painter = painterResource(id = image),
           contentDescription = stringResource(id = imageDes),
           contentScale = ContentScale.FillWidth,
           alignment = Alignment.TopCenter
           )
   }

}


@Composable
fun HeroInformation(name: Int,description:Int,modifier: Modifier = Modifier) {

    Text(
        text = stringResource(id = name),
        style = MaterialTheme.typography.displaySmall
    )
    Text(
        text = stringResource(id = description),
        style = MaterialTheme.typography.bodyLarge
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroesTheme {
      HeroApp()
          }
}