package com.example.nasaexplorer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import coil.compose.rememberAsyncImagePainter

@Composable
fun LoadImageFromUrl(url: String) {
    // RememberImagePainter will handle the image loading and caching
    val painter = rememberAsyncImagePainter(url)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewImage() {
    LoadImageFromUrl("https://example.com/image.jpg")
}