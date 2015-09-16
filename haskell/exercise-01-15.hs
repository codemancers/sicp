p x = 3 * x - 4 * (x^3)

sine angle =
  if (abs angle) <= 0.1
  then angle
  else p (sine (angle/3))


-- sine 12.15
-- p is applied 5 times
-- p (sine 4.05)
--   p (sine 1.35)
--     p (sine 0.45)
--       p (sine 0.15)
--         p (sine 0.05)


-- space complexity = log n
-- number of steps (n) for sine (angle)
--  angle/(3^n) <= 0.1
--  n = log(10*angle)/log(3) + 1

sineSteps angle = log(10 * angle)/log(3)
