sum2Lists [x] [] = x:[]
sum2Lists [x] [y] = (x+y):[]
sum2Lists (x:xs) (y:ys) = (x+y):sum2Lists xs ys

pascalTriangle 1 = [1]
pascalTriangle n =
  let list = pascalTriangle (n - 1)
  in sum2Lists (0:list) list

pascalTriangleAt n m =
  pascalTriangle(n)!!(m - 1)
