package calculator

object Calculator {

  type Operator = (Int, Int) => Int
  
  object Operator {
    val operators: Map[String, Operator] = 
      Map("+" -> { _ + _ }, 
          "-" -> { _ - _ },
          "*" -> { _ * _ },
          "/" -> { _ / _ })
     val tokens = operators map { _. swap }
     def unapply(token: String): Option[Operator] = 
       operators.get(token)
  }
  
  object Number {
    def unapply(token: String): Option[Int] = 
      try {
        Some(token.toInt)
      } catch {
        case _: NumberFormatException => None
      }
  }
  
  sealed trait Expression
  case class NumberExpression(value: Int) extends Expression
  case class OperationExpression(lhs: Expression, rhs: Expression, op: Operator) extends Expression
  
  def step(stack: List[Expression], token: String): List[Expression] = token match {
      case Number(num) => NumberExpression(num) :: stack
      case Operator(op) => stack match {
        case rhs :: lhs :: rest => OperationExpression(lhs, rhs, op) :: rest
        case _ => throw new IllegalArgumentException("Not enough operands")
      }
      case _ => throw new IllegalArgumentException("Invalid token: " + token)
  }
  
  def parse(expression: String): Expression = {
    val tokens = expression.split(" ")
    val stack = tokens.foldLeft(List.empty[Expression]) { step }
    stack.head
  }
  
  def calculate(expression: Expression): Int = expression match {
    case NumberExpression(value) => value
    case OperationExpression(lhs, rhs, op) => op(calculate(lhs), calculate(rhs))
  }
  
  def toPrefix(expression: Expression): String = expression match {
    case NumberExpression(value) => value.toString
    case OperationExpression(lhs, rhs, op) => s"${Operator.tokens(op)} ${toPrefix(lhs)} ${toPrefix(rhs)}"    
  }
 
  def toPostfix(expression: Expression): String = expression match {
    case NumberExpression(value) => value.toString
    case OperationExpression(lhs, rhs, op) => s"${toPostfix(lhs)} ${toPostfix(rhs)} ${Operator.tokens(op)}"    
  }
 
  def toInfix(expression: Expression): String = expression match {
    case NumberExpression(value) => value.toString
    case OperationExpression(lhs, rhs, op) => s"(${toInfix(lhs)} ${Operator.tokens(op)} ${toInfix(rhs)})"    
  }

  def main(args: Array[String]): Unit =
    if (args.length != 1)
      throw new IllegalArgumentException("Usage: Calculator <expression>")
    else {
      val expression = parse(args(0))
      println("Prefix...: " + toPrefix(expression))
      println("Postfix..: " + toPostfix(expression))
      println("Infix....: " + toInfix(expression))
      println(s"${toInfix(expression)} = ${calculate(expression)}")
    }

}