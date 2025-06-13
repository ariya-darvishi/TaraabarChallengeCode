package net.taraabar.challengecode.ui.cargo

import net.taraabar.challengecode.data.remote.model.response.CargoResponse

interface CargoEvents {
    fun initCargoScreen()
    fun onDismissRequestBottomSheet()
    fun onCargoItemClick(item: CargoResponse)
    fun onAcceptCargoBtnClick()
    fun onCancelCargoTextBtnClick()
    fun loadMore()
}

val defaultCargoEvents = object : CargoEvents {
    override fun initCargoScreen() {}
    override fun onDismissRequestBottomSheet() {}
    override fun onCargoItemClick(item: CargoResponse) {}
    override fun onAcceptCargoBtnClick() {}
    override fun onCancelCargoTextBtnClick() {}
    override fun loadMore() {}
}