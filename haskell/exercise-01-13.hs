import Control.Exception

fibRef 0 = 0
fibRef 1 = 1
fibRef n = fibRef(n - 1) + fibRef(n - 2)


squareRoot5 = sqrt 5
fib n = (x^n)/squareRoot5 where x = (1 + squareRoot5)/2


goodEnough(x) = abs(x) < 1
proveByInduction =
  -- for large enough numbers, if difference is less than 1, we are cool!
  goodEnough(fibRef(0) - fib(0)) &&
  goodEnough(fibRef(1) - fib(1)) &&
  goodEnough(fibRef(2) - fib(2)) &&
  goodEnough(fibRef(100) - fib(100)) &&
  goodEnough(fibRef(101) - fib(101))
