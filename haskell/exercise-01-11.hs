fRecursive :: Int -> Int
fRecursive n
  | n < 3 = n
  | otherwise = fRecursive(n - 1) + 2 * fRecursive(n - 2) + 3 * fRecursive(n - 3)


fIter acc n
  | n == 2 = head acc
  | otherwise =
      let im1 = head acc
          im2 = head (tail acc)
          im3 = head (tail (tail acc))
          val = im1 + 2 * im2 + 3 * im3
      in fIter (val:acc) (n - 1)

fIterative n
  | n < 3 = n
  | otherwise = fIter [ 2, 1, 0 ] n
