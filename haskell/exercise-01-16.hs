fastExpt :: Int -> Int -> Int
fastExpt b n
  | n == 0 = 1
  | even n = ( fastExpt b (div n 2) )^2
  | otherwise = b * (fastExpt b (n - 1))


fastExptHelper b n acc
  | n == 0 = acc
  | even n = fastExptHelper (b^2) (div n 2) acc
  | otherwise = fastExptHelper b (n-1) (acc*b)

fastExptIter b n = fastExptHelper b n 1
