package net.taraabar.challengecode.data.remote.model.response

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random


@Stable
@JsonClass(generateAdapter = true)
data class ShipmentResponse(
    @Json(name = "originProvince") val originProvince: String,
    @Json(name = "originCity") val originCity: String,
    @Json(name = "destinationProvince") val destinationProvince: String,
    @Json(name = "destinationCity") val destinationCity: String,
    @Json(name = "weight") val weight: String,
    @Json(name = "weightUnit") val weightUnit: String,
    @Json(name = "amount") val amount: String,
    @Json(name = "amountUnit") val amountUnit: String,
    @Json(name = "cargoType") val cargoType: String,
    @Json(name = "packagingType") val packagingType: String,
    @Json(name = "loadingDate") val loadingDate: String,
    @Json(name = "shipmentDetail") val shipmentDetail: ShipmentDetail
)


@Stable
@JsonClass(generateAdapter = true)
data class ShipmentDetail(
    @Json(name = "originCity") val originCity: String,
    @Json(name = "destinationCity") val destinationCity: String,
    @Json(name = "weightUnit") val weightUnit: String,
    @Json(name = "cargoType") val cargoType: String,
    @Json(name = "packagingType") val packagingType: String,
    @Json(name = "loadingDate") val loadingDate: String,
    @Json(name = "amount") val amount: String,
)


@Stable
@Immutable
data class ShipmentResponseList(
    val items: List<ShipmentResponse>
)


val provincesAndCapitals = listOf(
    "تهران" to "تهران",
    "البرز" to "کرج",
    "اردبیل" to "اردبیل",
    "بوشهر" to "بوشهر",
    "چهارمحال و بختیاری" to "شهرکرد",
    "آذربایجان شرقی" to "تبریز",
    "فارس" to "شیراز",
    "گیلان" to "رشت",
    "گلستان" to "گرگان",
    "همدان" to "همدان",
    "هرمزگان" to "بندرعباس",
    "ایلام" to "ایلام",
    "اصفهان" to "اصفهان",
    "کرمان" to "کرمان",
    "کرمانشاه" to "کرمانشاه",
    "خوزستان" to "اهواز",
    "کهگیلویه و بویراحمد" to "یاسوج",
    "کردستان" to "سنندج",
    "لرستان" to "خرم‌آباد",
    "مرکزی" to "اراک",
    "مازندران" to "ساری",
    "خراسان شمالی" to "بجنورد",
    "قزوین" to "قزوین",
    "قم" to "قم",
    "خراسان رضوی" to "مشهد",
    "سمنان" to "سمنان",
    "سیستان و بلوچستان" to "زاهدان",
    "خراسان جنوبی" to "بیرجند",
    "آذربایجان غربی" to "ارومیه",
    "یزد" to "یزد",
    "زنجان" to "زنجان"
)


object ShipmentResponseListMockData {
    private val cargoType = listOf(
        "برنج",
        "شیشه",
        "فرش",
        "شوینده",
        "سیمان",
        "غذا",
        "حبوبات",
        "لباس",
        "ضایعات ساختمانی",
        "لوازم خانگی",
        "میوه",
        "کفش"
    ).random()
    private val weightUnits = listOf("کیلوگرم", "تن").random()
    private val packagingType = listOf("کارتن", "پالت", "کانتینر").random()
    private val loadingDate = "۱۴۰۴-۰۳-${(10..30).random()}"
    private val amount = (500_000..5_000_000).random().toString()

    val MOCK_DATA = ShipmentResponseList(
        items = List(Random.nextInt(60, 210)) {
            val (originProvince, originCity) = provincesAndCapitals.random()
            var (destinationProvince, destinationCity) = provincesAndCapitals.random()

            //  استان و شهر مبدا با استان و شهر مقصد متفاوت باشند
            while (originProvince == destinationProvince || originCity == destinationCity) {
                destinationProvince = provincesAndCapitals.random().first
                destinationCity = provincesAndCapitals.random().second
            }

            ShipmentResponse(
                originProvince = originProvince,
                originCity = originCity,
                destinationProvince = destinationProvince,
                destinationCity = destinationCity,
                weight = (10..100).random().toString(),
                weightUnit = weightUnits,
                amount = amount,
                amountUnit = "تومان",
                cargoType = cargoType,
                packagingType = packagingType,
                loadingDate = loadingDate,
                shipmentDetail = ShipmentDetail(
                    originCity = originCity,
                    destinationCity = destinationCity,
                    weightUnit = weightUnits,
                    cargoType = cargoType,
                    packagingType = packagingType,
                    loadingDate = loadingDate,
                    amount = amount
                )
            )
        }
    )
}