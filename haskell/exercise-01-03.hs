import Data.List

squareOfMax2 x y z =
  sum [ p*p | p <- tail(sort [x, y, z]) ]
