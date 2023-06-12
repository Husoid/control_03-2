fun main(args: Array<String>) {
    println(myPay("MasterCard", amountTransfer = 7000))
}

fun myPay(typeCard: String = "VK pay", amountTransfersInMonth: Int = 0, amountTransfer: Int): String {
    var commission: Int

    when (typeCard) {
        "MasterCard", "Maestro" ->
            if (amountTransfersInMonth > 75000) {
                commission = amountTransfer * 60 / 100 + 20
            } else {
                commission = 0
            }

        "Visa", "Мир" ->
            if ((amountTransfer * 75 / 100) > 35) {
                commission = amountTransfer * 75 / 100
            } else {
                commission = 35
            }

        "VK pay" -> commission = 0
        else -> commission = 0
    }
    return "Ваш перевод на карту $typeCard в сумме $amountTransfer рублей будет с коммиссией $commission рублей"
}