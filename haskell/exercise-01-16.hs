fastExpt :: Int -> Int -> Int
fastExpt b n
  | n == 0 = 1
  | even n = ( fastExpt b (div n 2) )^2
  | otherwise = b * (fastExpt b (n - 1))


squareUp x times acc
  | times == 0 = acc
  | otherwise = squareUp x (times - 1) (acc * x * x)

fastExptIter b n acc timesToSquare
  | n == 0 = acc * (squareUp b timesToSquare 1)
  | even n = fastExptIter b (div n 2) acc (timesToSquare + 1)
  | otherwise = fastExptIter b (n-1) (acc*b) timesToSquare
