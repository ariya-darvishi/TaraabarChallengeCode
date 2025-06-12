package net.taraabar.challengecode.utils


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
    LOADING_DATE(fieldName = LOADING_DATEـ_TIME),
}
