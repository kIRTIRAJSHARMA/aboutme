package com.example.gdgmad

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gdgmad.ui.theme.GdgmadTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GdgmadTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.your_image),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Hi everyone", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "I'm Kirti Raj Sharma. I am an Android Developer with a passion for building apps. I enjoy learning new things every day.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialMediaIcon(
                iconResId = R.drawable.ic_github,
                url = "https://github.com/kIRTIRAJSHARMA"
            )
            SocialMediaIcon(
                iconResId = R.drawable.ic_linkedin,
                url = "https://www.linkedin.com/in/kirti-raj-sharma-a619572ab"
            )
            SocialMediaIcon(
                iconResId = R.drawable.ic_instagram,
                url = "https://www.instagram.com/k_raj_sharma/"
            )
            SocialMediaIcon(
                iconResId = R.drawable.ic_twitter,
                url = "https://twitter.com/your_twitter_handle"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "My Projects", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(projects) { project ->
                ProjectItem(project)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Add your action here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Celebrate")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SocialMediaIcon(iconResId: Int, url: String) {
    val context = LocalContext.current

    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = "Social Media Icon",
        modifier = Modifier
            .size(32.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
    )
}

data class Project(val name: String, val imageId: Int, val url: String)

val projects = listOf(
    Project("Project 1", R.drawable.project1_image, "https://github.com/kIRTIRAJSHARMA/ISL_Generator"),
    Project("Project 2", R.drawable.project1_image, "https://github.com/kIRTIRAJSHARMA/studyforge-1"),
    Project("Project 3", R.drawable.project1_image, "https://github.com/kIRTIRAJSHARMA/unit-converter-pp")
)

@Composable
fun ProjectItem(project: Project) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                if (project.url.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.url))
                    context.startActivity(intent)
                }
            }
    ) {
        Image(
            painter = painterResource(id = project.imageId),
            contentDescription = project.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    GdgmadTheme {
        MainContent()
    }
}
