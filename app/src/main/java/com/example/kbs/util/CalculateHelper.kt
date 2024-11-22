package com.example.kbs.util

class CalculateHelper {

    companion object {
        var num1: Double = 0.0
        var num2: Double = 0.0
        var resultNumber: Double = 0.0
    }

    private fun splitTokens(equation: String): List<Any> {
        val constant = equation.split(" ")
        val constantList = mutableListOf<Any>()
        var number = 0.0
        var flag = false

        for (data in constant) {
            if (data == " ") {
                continue
            }
            if (checkNumber(data)) {
                number = number * 10 + data.toDouble()
                flag = true
            } else {
                if (flag) {
                    constantList.add(number)
                    number = 0.0
                }
                flag = false
                constantList.add(data)
            }
        }

        if (flag) {
            constantList.add(number)
        }

        return constantList
    }

    private fun infixToPostfix(constant: List<Any>): List<Any> {
        val result = mutableListOf<Any>()
        val level = mutableMapOf<String, Int>()
        val stack = ArrayDeque<Any>()

        level["*"] = 3
        level["/"] = 3
        level["+"] = 2
        level["-"] = 2
        level["("] = 1

        for (obj in constant) {
            when {
                obj == ")" -> {
                    while (stack.isNotEmpty() && stack.last() != "(") {
                        val valObj = stack.removeLast()
                        if (valObj != "(") {
                            result.add(valObj)
                        }
                    }
                    stack.removeLast()
                }
                obj == "(" -> stack.add(obj)
                level.containsKey(obj) -> {
                    if (stack.isEmpty()) {
                        stack.add(obj)
                    } else {
                        val stackTop = stack.last() as String
                        if (level[stackTop]!! >= level[obj]!!) {
                            result.add(stack.removeLast())
                            stack.add(obj)
                        } else {
                            stack.add(obj)
                        }
                    }
                }
                else -> result.add(obj)
            }
        }

        while (stack.isNotEmpty()) {
            result.add(stack.removeLast())
        }

        return result
    }

    private fun postFixEval(expr: List<Any>): Double {
        val numberStack = ArrayDeque<Double>()

        for (o in expr) {
            when (o) {
                is Double -> numberStack.add(o)
                "+" -> {
                    num1 = numberStack.removeLast()
                    num2 = numberStack.removeLast()
                    numberStack.add(num2 + num1)
                }
                "-" -> {
                    num1 = numberStack.removeLast()
                    num2 = numberStack.removeLast()
                    numberStack.add(num2 - num1)
                }
                "*" -> {
                    num1 = numberStack.removeLast()
                    num2 = numberStack.removeLast()
                    numberStack.add(num2 * num1)
                }
                "/" -> {
                    num1 = numberStack.removeLast()
                    num2 = numberStack.removeLast()
                    numberStack.add(num2 / num1)
                }
            }
        }

        resultNumber = numberStack.removeLast()
        return resultNumber
    }

    fun process(equation: String): Double {
        val postfix = infixToPostfix(splitTokens(equation))
        return postFixEval(postfix)
    }

    fun checkNumber(char: Char): Boolean {
        return char in '0'..'9' || char == '.'
    }

    fun checkNumber(str: String): Boolean {
        if (str.isEmpty()) return false

        for (i in str.indices) {
            val check = str[i]
            if (check !in '0'..'9' && check != '.') return false
        }

        return true
    }
}
