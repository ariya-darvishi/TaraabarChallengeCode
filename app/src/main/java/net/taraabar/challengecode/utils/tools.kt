package net.taraabar.challengecode.utils

import net.taraabar.challengecode.data.remote.model.request.KeyValueModel
import net.taraabar.challengecode.data.remote.model.request.KeyValueModelList
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.model.response.CargoResponseList
import net.taraabar.designsystem.utils.toPersianNumber


fun CargoResponseList.updateSelectionStatus(selectedItem: CargoResponse): CargoResponseList {
    return CargoResponseList(
        this.items.map { item ->
            if (item == selectedItem) {
                item.copy(itemStatus = CargoStatus.SELECTED)
            } else {
                item.copy(itemStatus = CargoStatus.LOCKED)
            }
        }
    )
}


fun CargoResponse.toKeyValueModelList(): KeyValueModelList {
    return KeyValueModelList(
        listOf(
            KeyValueModel(
                key = CargoDetailItem.ORIGIN_CITY.fieldName,
                value = originCity.orEmpty()
            ),
            KeyValueModel(
                key = CargoDetailItem.DESTINATION_CITY.fieldName,
                value = destinationCity.orEmpty()
            ),
            KeyValueModel(
                key = CargoDetailItem.WEIGHT_UNIT.fieldName,
                value = " ${weight.orEmpty().toPersianNumber()} ${weightUnit.orEmpty()}"
            ),
            KeyValueModel(
                key = CargoDetailItem.CARGO_TYPE.fieldName,
                value = cargoType.orEmpty()
            ),
            KeyValueModel(
                key = CargoDetailItem.PACKAGING_TYPE.fieldName,
                value = packagingType.orEmpty()
            ),
            KeyValueModel(
                key = CargoDetailItem.LOADING_DATE.fieldName,
                value = loadingDate.orEmpty().toPersianNumber()
            ),
        )
    )
}

