package com.example.musify.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musify.R
import com.google.accompanist.insets.statusBarsPadding

/**
 * A composable that is used to display an image together with it's
 * [title] and [subtitle]. The image will be centered. The [title]
 * and [subtitle] will be placed after the image, in a vertical
 * manner.
 * @param title the title associated with the image.
 * @param imageUrl the url string that contains the url of the image
 * @param subtitle the subtitle associated with the image.
 * @param onBackButtonClicked the lambda that will be executed when the
 * back button is pressed.
 * @param isLoadingPlaceholderVisible used to indicate whether the loading
 * placeholder for the image is visible.
 * @param onImageLoading the lambda to execute when the image is
 * loading.
 * @param onImageLoaded the lambda to execute when the image has finished
 * loading. It is also provided with a nullable [Throwable] parameter that
 * can be used to determine whether the image was loaded successfully.
 * @param additionalMetadataContent the lambda the can be used to add additional
 * items that will appear after the [subtitle].
 */
@Composable
fun ImageHeaderWithMetadata(
    title: String,
    imageUrl: String,
    subtitle: String,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isLoadingPlaceholderVisible: Boolean = false,
    onImageLoading: () -> Unit = {},
    onImageLoaded: (Throwable?) -> Unit = {},
    additionalMetadataContent: @Composable () -> Unit,
) {
    Box(modifier) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .padding(8.dp),
            onClick = onBackButtonClicked
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_left_24),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImageWithPlaceholder(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(8.dp),
                model = imageUrl,
                contentDescription = null,
                isLoadingPlaceholderVisible = isLoadingPlaceholderVisible,
                onImageLoading = onImageLoading,
                onImageLoadingFinished = onImageLoaded,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = subtitle,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
            additionalMetadataContent()
        }
    }
}