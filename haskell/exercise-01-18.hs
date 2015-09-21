double x = x + x
halve x = div x 2

multiplyExptHelper a b acc
  | b == 0 = acc
  | even b = multiplyExptHelper (double a) (halve b) acc
  | otherwise = multiplyExptHelper a (b - 1) (acc + a)

multiplyExptIter a b = multiplyExptHelper a b 0
