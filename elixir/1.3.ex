bigger_two = fn a,b,c ->
  cond do
    a <= b and a <= c -> {b, c}
    b <= a and b <= c -> {a, c}
    true -> {a, b}
  end
end

ex_1_3 = fn a,b,c ->
  {x, y} = bigger_two.(a, b, c)
  x*x + y*y
end

IO.puts ex_1_3.(3,4,5)
