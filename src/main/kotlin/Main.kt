fun main(args: Array<String>) {
    println(myPay("VK pay", amountTransfer = 16000))
}

fun myPay(typeCard: String = "VK pay", amountTransfersInDay: Int = 0, amountTransfersInMonth: Int = 0, amountTransfer: Int): String {
    var commission: Int


    if (dayLimit(typeCard, amountTransfersInDay, amountTransfer) && monthLimit(typeCard, amountTransfersInMonth, amountTransfer)) {
        when (typeCard) {
            "MasterCard", "Maestro" ->
                when {
                    amountTransfersInMonth > 75000 -> { commission = amountTransfer * 60 / 10000 + 20 }
                    amountTransfersInMonth + amountTransfer > 75000 -> { commission = (amountTransfersInMonth + amountTransfer - 75000) * 60 / 10000 + 20 }
                    else -> commission = 0
                }

            "Visa", "Мир" ->
                if ((amountTransfer * 75 / 10000) > 35) {
                    commission = amountTransfer * 75 / 10000
                } else {
                    commission = 35
                }

            "VK pay" -> commission = 0
            else -> commission = 1000
        }
        return "Ваш перевод с карты $typeCard в сумме $amountTransfer рублей будет с коммиссией $commission рублей"
    } else if (!dayLimit(typeCard, amountTransfersInDay, amountTransfer)) {
        return "Ваш перевод с карты $typeCard в сумме $amountTransfer отклонен по причине превышения дневного лимита"
    } else {
        return "Ваш перевод с карты $typeCard в сумме $amountTransfer отклонен по причине превышения месячного лимита"
    }
}

fun dayLimit (typeCard: String, amountTransfersInDay: Int, amountTransfer: Int):Boolean {
    when (typeCard) {
        "MasterCard", "Maestro", "Visa", "Мир" ->
            return amountTransfersInDay+amountTransfer <= 150000
        "VK pay" ->
            return amountTransfer <= 15000
        else -> return false
    }
}

fun monthLimit (typeCard: String, amountTransfersInMonth: Int, amountTransfer: Int):Boolean {
    when (typeCard) {
        "MasterCard", "Maestro", "Visa", "Мир" ->
            return amountTransfersInMonth+amountTransfer <= 600000
        "VK pay" ->
            return amountTransfersInMonth+amountTransfer <= 40000
        else -> return false
    }
}