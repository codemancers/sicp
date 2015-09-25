ackermann :: Int -> Int -> Int
ackermann x y
  | y == 0 = 0
  | x == 0 = 2 * y
  | y == 1 = 2
  | otherwise = ackermann (x - 1) (ackermann x (y - 1))

-- ackermann 0 n = 2 * n
-- ackermann 1 n = 2^n
-- ackermann 2 n =
