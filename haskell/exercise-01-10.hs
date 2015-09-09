ackermann x y =
  if y == 0
  then 0
  else if x == 0
       then 2 * y
       else if y == 1
            then 2
            else ackermann (x - 1) (ackermann x (y - 1))

-- ackermann 0 n = 2 * n
-- ackermann 1 n = 2^n
-- ackermann 2 n =
