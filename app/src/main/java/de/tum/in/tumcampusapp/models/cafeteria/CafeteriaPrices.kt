package de.tum.`in`.tumcampusapp.models.cafeteria

import android.content.Context
import de.tum.`in`.tumcampusapp.auxiliary.Const
import de.tum.`in`.tumcampusapp.auxiliary.Utils

/**
 * Hardcoded cafeteria prices
 */
object CafeteriaPrices {
    private val PRICE_100 = "1,00"
    private val PRICE_155 = "1,55"
    private val PRICE_190 = "1,90"
    private val PRICE_220 = "2,20"
    private val PRICE_240 = "2,40"
    private val PRICE_260 = "2,60"
    private val PRICE_270 = "2,70"
    private val PRICE_280 = "2,80"
    private val PRICE_290 = "2,90"
    private val PRICE_300 = "3,00"
    private val PRICE_320 = "3,20"
    private val PRICE_330 = "3,30"
    private val PRICE_340 = "3,40"
    private val PRICE_350 = "3,50"
    private val PRICE_360 = "3,60"
    private val PRICE_370 = "3,70"
    private val PRICE_390 = "3,90"
    private val PRICE_400 = "4,00"
    private val PRICE_410 = "4,10"
    private val PRICE_440 = "4,40"
    private val PRICE_450 = "4,50"
    private val PRICE_490 = "4,90"
    private val PRICE_540 = "5,40"

    private val STUDENT_PRICES = mapOf(
            "Tagesgericht 1" to PRICE_100,
            "Tagesgericht 2" to PRICE_155,
            "Tagesgericht 3" to PRICE_190,
            "Tagesgericht 4" to PRICE_240,

            "Aktionsessen 1" to PRICE_155,
            "Aktionsessen 2" to PRICE_190,
            "Aktionsessen 3" to PRICE_240,
            "Aktionsessen 4" to PRICE_260,
            "Aktionsessen 5" to PRICE_280,
            "Aktionsessen 6" to PRICE_300,
            "Aktionsessen 7" to PRICE_320,
            "Aktionsessen 8" to PRICE_350,
            "Aktionsessen 9" to PRICE_400,
            "Aktionsessen 10" to PRICE_450,

            "Biogericht 1" to PRICE_155,
            "Biogericht 2" to PRICE_190,
            "Biogericht 3" to PRICE_240,
            "Biogericht 4" to PRICE_260,
            "Biogericht 5" to PRICE_280,
            "Biogericht 6" to PRICE_300,
            "Biogericht 7" to PRICE_320,
            "Biogericht 8" to PRICE_350,
            "Biogericht 9" to PRICE_400,
            "Biogericht 10" to PRICE_450
    )

    private val EMPLOYEE_PRICES = mapOf(
            "Tagesgericht 1" to PRICE_190,
            "Tagesgericht 2" to PRICE_220,
            "Tagesgericht 3" to PRICE_240,
            "Tagesgericht 4" to PRICE_280,

            "Aktionsessen 1" to PRICE_220,
            "Aktionsessen 2" to PRICE_240,
            "Aktionsessen 3" to PRICE_280,
            "Aktionsessen 4" to PRICE_300,
            "Aktionsessen 5" to PRICE_320,
            "Aktionsessen 6" to PRICE_340,
            "Aktionsessen 7" to PRICE_360,
            "Aktionsessen 8" to PRICE_390,
            "Aktionsessen 9" to PRICE_440,
            "Aktionsessen 10" to PRICE_490,

            "Biogericht 1" to PRICE_220,
            "Biogericht 2" to PRICE_240,
            "Biogericht 3" to PRICE_280,
            "Biogericht 4" to PRICE_300,
            "Biogericht 5" to PRICE_320,
            "Biogericht 6" to PRICE_340,
            "Biogericht 7" to PRICE_360,
            "Biogericht 8" to PRICE_390,
            "Biogericht 9" to PRICE_440,
            "Biogericht 10" to PRICE_490
    )

    private val GUEST_PRICES = mapOf(
            "Tagesgericht 1" to PRICE_240,
            "Tagesgericht 2" to PRICE_270,
            "Tagesgericht 3" to PRICE_290,
            "Tagesgericht 4" to PRICE_330,

            "Aktionsessen 1" to PRICE_270,
            "Aktionsessen 2" to PRICE_290,
            "Aktionsessen 3" to PRICE_330,
            "Aktionsessen 4" to PRICE_350,
            "Aktionsessen 5" to PRICE_370,
            "Aktionsessen 6" to PRICE_390,
            "Aktionsessen 7" to PRICE_410,
            "Aktionsessen 8" to PRICE_440,
            "Aktionsessen 9" to PRICE_490,
            "Aktionsessen 10" to PRICE_540,

            "Biogericht 1" to PRICE_270,
            "Biogericht 2" to PRICE_290,
            "Biogericht 3" to PRICE_330,
            "Biogericht 4" to PRICE_350,
            "Biogericht 5" to PRICE_370,
            "Biogericht 6" to PRICE_390,
            "Biogericht 7" to PRICE_410,
            "Biogericht 8" to PRICE_440,
            "Biogericht 9" to PRICE_490,
            "Biogericht 10" to PRICE_540
    )

    /**
     * Gets a [Map] which hashes cafeteria menu's long title
     * to prices.
     *
     * @param context Context
     * @return hash map
     */
    fun getRolePrices(context: Context): Map<String, String> {
        val type = Utils.getSetting(context, Const.ROLE, "")
        when (type) {
            "0" -> return STUDENT_PRICES
            "1" -> return EMPLOYEE_PRICES
            "2" -> return GUEST_PRICES
            else -> return STUDENT_PRICES
        }
    }

    fun getPrice(context: Context, menuType: String): String? {
        return getRolePrices(context)[menuType]
    }
}
