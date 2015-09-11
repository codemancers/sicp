fRecursive :: Int -> Int
fRecursive n
  | n < 3 = n
  | otherwise = fRecursive(n - 1) + 2 * fRecursive(n - 2) + 3 * fRecursive(n - 3)


fIter (a, b, c) n
  | n == 2 = a
  | otherwise =
      let val = a + 2 * b + 3 * c
      in fIter (val, a, b) (n - 1)

fIterative n = fIter (2, 1, 0) n
