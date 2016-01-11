-- Define a procedure that takes three numbers as arguments and
-- returns the sum of the squares of the two larger numbers.
-- We will do it for n numbers.

import System.Environment
import Data.List

main :: IO ()
main = do
  args <- getArgs
  print . sum . map (^2) . drop(length(args) - 2) . sort . map read $ args
