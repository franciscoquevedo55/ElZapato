package udg.cuvalles.mx.elzapato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Perfil(
    val title: String,
    val subtitle: String,
    val primaryButtonText: String = "Button",
    val secondaryButtonText: String = "Button"
)

data class Bebidas(
    val id: Int,
    val title: String
)

data class Articulos(
    val id: Int,
    val title: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myProfile = Perfil(
            title = "Don Benny",
            subtitle = "Tacos y bajantes",
            primaryButtonText = "Ver productos",
            secondaryButtonText = "Mensaje"
        )

        val myCategories = listOf(
            Bebidas(1, "Agua de horchata"),
            Bebidas(2, "Agua de jamaica"),
            Bebidas(3, "Agua de cafe"),
            Bebidas(4, "Coca"),
            Bebidas(5, "Fuze tea"),
            Bebidas(6, "Agua natural"),
            Bebidas(7, "Fanta"),
            Bebidas(8, "Arizona")
        )

        val myArticles = listOf(
            Articulos(1, "Quesadilla de asada"),
            Articulos(2, "Quesadilla de adobada"),
            Articulos(3, "Lonche de asada"),
            Articulos(4, "Lonche de adobada"),
            Articulos(5, "Tacos de asada"),
            Articulos(6, "Tacos de adobada"),
            Articulos(7, "Tacos mixtos"),
            Articulos(8, "Vampiros"),
            Articulos(9, "Doraditas"),
            Articulos(10, "Mazapan")
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    DataDrivenScreen(
                        profile = myProfile,
                        categories = myCategories,
                        articles = myArticles
                    )
                }
            }
        }
    }
}

@Composable
fun DataDrivenScreen(
    profile: Perfil,
    categories: List<Bebidas>,
    articles: List<Articulos>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            MainCard(profile = profile)
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(categories, key = { it.id }) { category ->
                    CategoryBox(category = category)
                }
            }
        }

        item {
            Text(
                text = "Lista de Artículos ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(articles, key = { it.id }) { article ->
            ArticleCard(article = article)
        }
    }
}

@Composable
fun MainCard(profile: Perfil) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFECECEC)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD0D0D0))
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = profile.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = profile.subtitle,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(profile.secondaryButtonText)
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3D3D3),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.border(
                        width = 2.dp,
                        color = Color(0xFF2196F3),
                        shape = RoundedCornerShape(20.dp)
                    )
                ) {
                    Text(profile.primaryButtonText)
                }
            }
        }
    }
}

@Composable
fun CategoryBox(category: Bebidas) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFDCDCDC)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.title,
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ArticleCard(article: Articulos) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDCDCDC)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = article.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
        }
    }
}