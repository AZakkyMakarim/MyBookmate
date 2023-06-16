package com.azakkymakarim.mybookmate.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azakkymakarim.mybookmate.R
import com.azakkymakarim.mybookmate.ui.theme.MyBookmateTheme

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = Color.Gray,
            disabledPlaceholderColor = Color.Transparent
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.search_book))
        },
        maxLines = 1,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 16.dp)
            .clip(RoundedCornerShape(16.dp))

    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MyBookmateTheme() {
        SearchBar(query = "Test", onQueryChange = {})
    }
}