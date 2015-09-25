average x y = (x + y)/2
square x = x * x

improve guess x = average guess (x / guess)
goodEnough guess x = (abs (square guess - x)) < 0.001

sqrtIter guess x =
  if goodEnough guess x
  then guess
  else sqrtIter (improve guess x) (x)


squareRoot x = sqrtIter 1.0 x
