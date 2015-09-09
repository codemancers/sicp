improve guess x = (x/(guess^2) + 2 * guess)/3
goodEnough guess x = (abs (guess^3 - x)) < 0.001

cubeIter guess x =
  if goodEnough guess x
  then guess
  else cubeIter (improve guess x) (x)


cubeRoot x = cubeIter 1.0 x
