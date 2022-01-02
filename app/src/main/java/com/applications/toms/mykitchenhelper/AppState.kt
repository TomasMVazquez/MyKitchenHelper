package com.applications.toms.mykitchenhelper

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.HalfExpanded),
    scaffoldState: ScaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open)),
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState = remember(navController, modalBottomSheetState, focusManager, keyboardController) {
    AppState(navController, modalBottomSheetState, scaffoldState, coroutineScope, focusManager, keyboardController)
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class AppState constructor (
    val navController: NavHostController,
    val modalBottomSheetState: ModalBottomSheetState,
    val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope,
    private val focusManager: FocusManager,
    private val keyboardController: SoftwareKeyboardController?
) {
    fun hideKeyboard() {
        keyboardController?.hide()
    }
    fun hideModalSheet() {
        coroutineScope.launch { modalBottomSheetState.hide() }
    }
    fun showModalSheet() {
        coroutineScope.launch { modalBottomSheetState.show() }
    }
    fun moveFocus(focusDirection: FocusDirection) {
        coroutineScope.launch { focusManager.moveFocus(focusDirection) }
    }
    fun clearFocus() {
        coroutineScope.launch { focusManager.clearFocus() }
    }
}