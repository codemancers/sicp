cubeRoot :: Double -> Double
cubeRoot x =
  let improve guess = (x/(guess^2) + 2 * guess)/3
      goodEnough guess = (abs (guess^3 - x)) < 0.001
      cubeIter guess =
        if goodEnough guess
        then guess
        else cubeIter (improve guess)
  in cubeIter 1.0
