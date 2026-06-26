package com.utsav.nexusnotes.presentation.editor.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteEditor(

    modifier: Modifier = Modifier,

    title: String,

    content: String,

    onTitleChange: (String) -> Unit,

    onContentChange: (String) -> Unit

) {

    val focusManager = LocalFocusManager.current

    val titleFocusRequester = remember {
        FocusRequester()
    }

    val contentFocusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
    }

    Column(

        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()

    ) {

        HorizontalDivider()

        BasicTextField(

            value = title,

            onValueChange = onTitleChange,

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
                .focusRequester(titleFocusRequester),

            singleLine = true,

            textStyle = TextStyle(

                fontSize = 30.sp,

                color = MaterialTheme.colorScheme.onBackground

            ),

            keyboardOptions = KeyboardOptions(

                capitalization = KeyboardCapitalization.Sentences,

                imeAction = ImeAction.Next

            ),

            keyboardActions = KeyboardActions(

                onNext = {

                    contentFocusRequester.requestFocus()

                }

            ),

            cursorBrush = SolidColor(
                MaterialTheme.colorScheme.primary
            ),

            decorationBox = { innerTextField ->

                if (title.isBlank()) {

                    Text(

                        text = "Title",

                        fontSize = 30.sp,

                        color = MaterialTheme.colorScheme.outline

                    )

                }

                innerTextField()

            }

        )

        BasicTextField(

            value = content,

            onValueChange = onContentChange,

            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
                .focusRequester(contentFocusRequester)
                .verticalScroll(
                    rememberScrollState()
                ),

            textStyle = TextStyle(

                fontSize = 18.sp,

                lineHeight = 30.sp,

                color = MaterialTheme.colorScheme.onBackground

            ),

            keyboardOptions = KeyboardOptions(

                capitalization = KeyboardCapitalization.Sentences,

                imeAction = ImeAction.Default

            ),

            keyboardActions = KeyboardActions(

                onDone = {

                    focusManager.clearFocus()

                }

            ),

            cursorBrush = SolidColor(
                MaterialTheme.colorScheme.primary
            ),

            decorationBox = { innerTextField ->

                if (content.isBlank()) {

                    Text(

                        text = "Start writing your note...",

                        fontSize = 18.sp,

                        color = MaterialTheme.colorScheme.outline

                    )

                }

                innerTextField()

            }

        )

    }

}