-- apply T twice, ie
-- Tpq(a, b) = a <- bq + aq + ap, b <- bp + aq
-- Tpq(bq + aq + ap, bp + aq) =
--     a <- b(2pq + q^2) + a(2pq + q^2) + a(p^2 + q^2)
--     b <- b(p^2 + q^2) + a(2pq + q^2)
--
-- hence by looking at the pattern, we can deduce new p, and q

halve x = div x 2

fibIter a b p q count
  | count == 0 = b
  | even count = fibIter a b (p^2 + q^2) (2 * p * q + q^2) (halve count)
  | otherwise  = fibIter (b*q + a*q + a*p) (b*p + a*q) p q (count - 1)

fib n = fibIter 1 0 0 1 n
