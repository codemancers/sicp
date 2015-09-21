multiply a 0 = 0
multiply a b = a + multiply a (b-1)


double x = x + x
halve x = div x 2

multiplyExpt a b
  | b == 0 = 0
  | even b = double (multiplyExpt a (halve b))
  | otherwise = a + multiplyExpt a (b - 1)
