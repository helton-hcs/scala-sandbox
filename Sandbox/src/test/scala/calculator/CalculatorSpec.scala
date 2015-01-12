package calculator

import org.specs2._
import specification.AllExpectations

class CalculatorSpec extends mutable.Specification with AllExpectations {

  "Infix notations" >> {
    Calculator.toInfix(Calculator.parse("1 2 +")) === "(1 + 2)"
    Calculator.toInfix(Calculator.parse("5 1 2 + 4 * + 3 -")) === "((5 + ((1 + 2) * 4)) - 3)"
    Calculator.toInfix(Calculator.parse("1 2 + 3 4 5 - - * 6 7 8 - * /")) === "(((1 + 2) * (3 - (4 - 5))) / (6 * (7 - 8)))"
  }

  "Prefix notations" >> {
    Calculator.toPrefix(Calculator.parse("1 2 +")) === "+ 1 2"
    Calculator.toPrefix(Calculator.parse("5 1 2 + 4 * + 3 -")) === "- + 5 * + 1 2 4 3"
    Calculator.toPrefix(Calculator.parse("1 2 + 3 4 5 - - * 6 7 8 - * /")) === "/ * + 1 2 - 3 - 4 5 * 6 - 7 8"
  }
  
  "Postfix notations" >> {
    Calculator.toPostfix(Calculator.parse("1 2 +")) === "1 2 +"
    Calculator.toPostfix(Calculator.parse("5 1 2 + 4 * + 3 -")) === "5 1 2 + 4 * + 3 -"
    Calculator.toPostfix(Calculator.parse("1 2 + 3 4 5 - - * 6 7 8 - * /")) === "1 2 + 3 4 5 - - * 6 7 8 - * /"
  }    
    
  "Testing evaluation " >> {
    Calculator.calculate(Calculator.parse("1 2 +")) == 3
    Calculator.calculate(Calculator.parse("5 1 2 + 4 * + 3 -")) === 14
    Calculator.calculate(Calculator.parse("1 2 + 3 4 5 - - * 6 7 8 - * /")) === -2
  } 
  
}