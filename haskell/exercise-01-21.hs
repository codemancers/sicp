divides a b = b `rem` a == 0

findDivisor n testDivisor
  | testDivisor^2 > n = n
  | divides testDivisor n = testDivisor
  | otherwise = findDivisor n (testDivisor + 1)

smallestDivisor n = findDivisor n 2

exampleTest =
  show [ smallestDivisor n | n <- [199, 1999, 19999] ]
