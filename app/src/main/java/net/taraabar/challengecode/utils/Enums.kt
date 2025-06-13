package net.taraabar.challengecode.utils

import net.taraabar.designsystem.utils.CARGO
import net.taraabar.designsystem.utils.DESTINATION
import net.taraabar.designsystem.utils.LOADING_DATE_TIME
import net.taraabar.designsystem.utils.ORIGIN
import net.taraabar.designsystem.utils.PACKAGING
import net.taraabar.designsystem.utils.WEIGHT


enum class CargoStatus {
    NONE,
    SELECTED,
    LOCKED,
}

enum class CargoDetailItem(val fieldName: String) {
    ORIGIN_CITY(fieldName = ORIGIN),
    DESTINATION_CITY(fieldName = DESTINATION),
    WEIGHT_UNIT(fieldName = WEIGHT),
    CARGO_TYPE(fieldName = CARGO),
    PACKAGING_TYPE(fieldName = PACKAGING),
    LOADING_DATE(fieldName = LOADING_DATE_TIME),
}
