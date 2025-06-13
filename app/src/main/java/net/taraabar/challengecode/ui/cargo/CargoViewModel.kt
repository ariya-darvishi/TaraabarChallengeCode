package net.taraabar.challengecode.ui.cargo

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.challengecode.data.repository.ITaraabarRepository
import net.taraabar.challengecode.utils.CargoStatus
import net.taraabar.challengecode.utils.updateSelectionStatus
import net.taraabar.designsystem.utils.PAGE_MAX_SIZE
import net.taraabar.network.states.apiResultCollector
import javax.inject.Inject


@HiltViewModel
class CargoViewModel @Inject constructor(
    private val repository: ITaraabarRepository,
) : ViewModel() {

    private val _cargoList = MutableStateFlow(CargoResponseList(emptyList()))
    private val _currentCargoSelected = MutableStateFlow(CargoResponse())
    private val _isLoadingCargoList = MutableStateFlow(false)
    private val _isLoadingCargoDetail = MutableStateFlow(false)
    private val _showCargoDetailBottomSheet = MutableStateFlow(false)

    private var _isLoadingData = MutableStateFlow(false)
    private var _hasMoreData = MutableStateFlow(true)

    private var _fullList = MutableStateFlow(CargoResponseList(emptyList()))

    private val _currentLoadedList = MutableStateFlow(CargoResponseList(emptyList()))


    private var currentPage = 0


    val states = CargoStateHolder(
        cargoList = _cargoList.asStateFlow(),
        fullList = _fullList.asStateFlow(),
        currentCargoSelected = _currentCargoSelected.asStateFlow(),
        isLoadingCargoList = _isLoadingCargoList.asStateFlow(),
        isLoadingCargoItemDetail = _isLoadingCargoDetail.asStateFlow(),
        showCargoDetailBottomSheet = _showCargoDetailBottomSheet.asStateFlow(),
        isLoadingData = _isLoadingData.asStateFlow(),
        hasMoreData = _hasMoreData.asStateFlow(),
        currentLoadedList = _currentLoadedList.asStateFlow(),
    )

    val events = object : CargoEvents {

        override fun initCargoScreen() {
            viewModelScope.launch {
                _isLoadingCargoList.value = true
                delay(2000)
                getCargoList()
            }

        }

        override fun onCargoItemClick(item: CargoResponse) {
            _currentCargoSelected.value = item
            _showCargoDetailBottomSheet.value = true
            viewModelScope.launch {
                _isLoadingCargoDetail.value = true
                delay(2000)
                _isLoadingCargoDetail.value = false

            }
        }

        override fun onAcceptCargoBtnClick() {
            _cargoList.value = _cargoList.value.updateSelectionStatus(_currentCargoSelected.value)
            _fullList.value = _fullList.value.updateSelectionStatus(_currentCargoSelected.value)

            this@CargoViewModel.onDismissBottomSheet()
        }

        override fun onDismissRequestBottomSheet() {
            this@CargoViewModel.onDismissBottomSheet()
        }


        override fun onCancelCargoTextBtnClick() {
            val updatedList = _cargoList.value.items.map {
                it.copy(itemStatus = CargoStatus.NONE)
            }
            val updatedListMoreLoad = _fullList.value.items.map {
                it.copy(itemStatus = CargoStatus.NONE)
            }
            _cargoList.value = CargoResponseList(updatedList)
            _fullList.value = CargoResponseList(updatedListMoreLoad)

        }

        override fun loadMore() {
            Log.d("CargoList", "events.loadMore called")
            loadNextPage()
        }

    }


    fun getCargoList() {
        viewModelScope.launch {
            _isLoadingData.value = true
            Log.d("CargoList", "Starting getCargoList")

            try {
                repository.getMockCargoList()
                    .apiResultCollector(states.getCargoListApiCallState) { data ->
                        if (data.isNotEmpty()) {
                            _fullList.value = CargoResponseList(data)
                            _cargoList.value =
                                CargoResponseList(items = _fullList.value.items.take(PAGE_MAX_SIZE))
                            currentPage = 1
                            _hasMoreData.value = _fullList.value.items.size > PAGE_MAX_SIZE
                            Log.d(
                                "CargoList",
                                "getCargoList success: fullList.size=${_fullList.value.items.size}, cargoList.size=${_cargoList.value.items.size}, hasMoreData=${_hasMoreData.value}"
                            )
                        } else {
                            _hasMoreData.value = false
                            Log.d("CargoList", "getCargoList: No data received")
                        }
                    }
            } catch (e: Exception) {
                Log.e("CargoList", "Error in getCargoList: ${e.message}")
            } finally {
                _isLoadingData.value = false
                Log.d("CargoList", "getCargoList finished: isLoading=${_isLoadingData.value}")
            }
        }
    }

    fun loadNextPage() {
        if (_isLoadingData.value || !_hasMoreData.value) {
            Log.d(
                "CargoList",
                "loadNextPage skipped: isLoading=${_isLoadingData.value}, hasMoreData=${_hasMoreData.value}"
            )
            return
        }

        viewModelScope.launch {
            _isLoadingData.value = true
            Log.d("CargoList", "Starting loadNextPage: currentPage=$currentPage")

            try {
                delay(2000)

                val startIndex = currentPage * PAGE_MAX_SIZE
                val nextItems = _fullList.value.items.drop(startIndex).take(PAGE_MAX_SIZE)
                Log.d(
                    "CargoList",
                    "Loading next page: startIndex=$startIndex, nextItems.size=${nextItems.size}"
                )

                if (nextItems.isNotEmpty()) {
                    _cargoList.value = CargoResponseList(items = _cargoList.value.items + nextItems)
                    currentPage++
                    _hasMoreData.value = startIndex + nextItems.size < _fullList.value.items.size
                    Log.d(
                        "CargoList",
                        "Loaded next page: new list size=${_cargoList.value.items.size}, hasMoreData=${_hasMoreData.value}"
                    )
                } else {
                    _hasMoreData.value = false
                    Log.d("CargoList", "No more items to load")
                }
            } catch (e: Exception) {
                Log.e("CargoList", "Error in loadNextPage: ${e.message}")
            } finally {
                _isLoadingData.value = false
                Log.d("CargoList", "loadNextPage finished: isLoading=${_isLoadingData.value}")
            }
        }
    }

    private fun onDismissBottomSheet() {
        _showCargoDetailBottomSheet.value = false
    }


}