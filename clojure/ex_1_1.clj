(ns sicp.ch1)

;; Exercise 1.2. Translate the following expression into prefix form
;; (5 + 4 + (2 - (3 - (6 + 4/5))))/3(6 - 2)(2 - 7)

(def ex_one_two
  (/
   (+ 5 4
      (- 2
         (- 3
            (+ 6
               (/ 4 5)))))
   (* 3
      (- 6 2)
      (- 2 7 ))))

;; -37/150

;; Exercise 1.3. Define a procedure that takes three numbers as arguments and
;; returns the sum of the squares of the two larger numbers.

(defn get_larger_numbers
  [num1 num2 num3]
  (take-last 2 (sort [num1 num2 num3])))

(defn square
  [num]
  (* num num))

(defn sum_of_square
  [&args]
  (reduce + (map square &args)))

(defn sum_of_square_of_larger_numbers
  [num1 num2 num3]
  (sum_of_square (get_larger_numbers num1 num2 num3)))


;; Exercise 1.7.  The good-enough? test used in computing square roots will not be
;; very effective for finding the square roots of very small numbers. Also, in real
;; computers, arithmetic operations are almost always performed with limited precision.
;; This makes our test inadequate for very large numbers. Explain these statements,
;; with examples showing how the test fails for small and large numbers. An alternative
;; strategy for implementing good-enough? is to watch how guess changes from one iteration
;; to the next and to stop when the change is a very small fraction of the guess. Design a
;; square-root procedure that uses this kind of end test. Does this work better for small
;; and large numbers?

(defn average [x y]
  (/ (+ x y) 2))

(defn abs [n]
  (max n (- n)))

(defn improve [guess x]
   (average guess (/ x guess)))

(defn good-enough? [old-guess new-guess]
  (<= (abs (- old-guess new-guess)) 0.001))

(defn sqrt-iter [guess x]
  (let [new (improve guess x)]
    (if (good-enough? guess new)
      guess
      (sqrt-iter new x))))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

;; Exercise 1.8.  Newton's method for cube roots is based on the fact that if y is an
;; approximation to the cube root of x, then a better approximation is given by the value
;; (x/y^2 + 2y)/3
;; Use this formula to implement a cube-root procedure analogous to the square-root procedure.
;; (In section 1.3.4 we will see how to implement Newton's method in general as an abstraction
;; of these square-root and cube-root procedures.)

(defn cube-root-improve [x y]
  (/ (+ (/ x
           (square y))
        (* 2 y))
     3))

(defn cbrt-iter [guess x]
  (let [new (cube-root-improve guess x)]
    (if (good-enough? guess new)
      guess
      (cbrt-iter new x))))

(defn cbrt [x]
  (cbrt-iter 1.0 x))
