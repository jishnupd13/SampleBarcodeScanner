package com.spellknight.barcodescanner.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


fun isEmailValid(email: String): Boolean {
    val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)

    return matcher.matches()
}

fun isPhoneNumberValid(phone: String): Boolean {
    return if (!Pattern.matches("[a-zA-Z]+", phone)) {
        (phone.length == 10)
    } else {
        false
    }
}

fun isOtpValid(otp: String?): Boolean {
    return otp?.length == 4
}

fun isZipCodeValid(zipCode: String?): Boolean {
    return zipCode?.length == 6
}

fun isPasswordValid(password: String?): Boolean {
    return password?.length!! >= 6
}

fun isCharacterPasswordValid(password: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)
    return matcher.matches()
}


fun isValidRegisterCode(regCode: String?): Boolean {

    val pattern = Pattern.compile("[a-zA-Z0-9]*")
    val matcher = pattern.matcher(regCode)
    return !matcher.matches()
}

fun isValidWebSite(website: String?): Boolean {

//    val pattern = Pattern.compile("^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*\$")
    val pattern =
        Pattern.compile("^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\/]@!\\\$&'\\/(\\/)\\*\\+,;=.]+\$")
    val matcher = pattern.matcher(website)
    return !matcher.matches()
}

fun isValidBankAccountNumber(bankAccountNumber: String?): Boolean {
    val pattern = Pattern.compile("[0-9]{9,18}")
    val matcher = pattern.matcher(bankAccountNumber)
    return !matcher.matches()
}

fun isValidIFSCCode(bankIFSC: String?): Boolean {

    val pattern = Pattern.compile("^[A-Za-z]{4}0[A-Z0-9a-z]{6}\$")
    val matcher = pattern.matcher(bankIFSC)
    return !matcher.matches()
}

fun isValidPanCard(panCardNo: String): Boolean {
    val pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    val matcher = pattern.matcher(panCardNo)
    return !matcher.matches()
}

