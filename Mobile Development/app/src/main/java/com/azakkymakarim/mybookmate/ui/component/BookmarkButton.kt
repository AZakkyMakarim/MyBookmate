package com.azakkymakarim.mybookmate.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azakkymakarim.mybookmate.R
import com.azakkymakarim.mybookmate.ui.theme.MyBookmateTheme

@Composable
fun BookmarkButton(
    modifier: Modifier = Modifier,
    isBookmark: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Bookmark"
            }
    ) {
        if (isBookmark) {
            Icon(
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription = stringResource(R.string.bookmark),
            )
            Text(
                text = stringResource(R.string.bookmark),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.bookmark_border),
                contentDescription = stringResource(R.string.bookmark),
            )
            Text(
                text = stringResource(R.string.bookmark),
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonPreview() {
    MyBookmateTheme() {
        BookmarkButton(
            isBookmark = true,
            onClick = {}
        )
    }
}