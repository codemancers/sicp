ackermann x 0 = 0
ackermann 0 y = 2 * y
ackermann x 1 = 2
ackermann x y =
  ackermann (x - 1) (ackermann x (y - 1))

-- ackermann 0 n = 2 * n
-- ackermann 1 n = 2^n
-- ackermann 2 n =
