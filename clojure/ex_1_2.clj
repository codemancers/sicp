(ns sicp.ch.1.2)

;; Exercise 1.10.  The following procedure computes a mathematical function
;; called akermann's function.

(defn ackermann_function
  [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (ackermann_function (- x 1)
                                  (ackermann_function x (- y 1)))))

;; What are the values of the following expressions?

(ackermann_function 1 10)
;; 1024

(ackermann_function 2 4)
;; 65536

(ackermann_function 3 3)
;; 65536

;; Consider the following procedures, where ackermann_function
;; is the procedure defined above:

(defn multiple_of_two_of_second_param
  ;; 2*n
  [f n]
  (ackermann_function 0 n))

(defn twisted_power_of_two
  ;; returns 0 when n == 0 else 2^n
  [g n]
  (ackermann_function 1 n))

(defn exponential_power
  ;; 2^2^2^2^....n
  [h n]
  (ackermann_function 2 n))

(defn five_n_square
  [k n]
  (* 5 n n))


;; hidden problem
;; make cc_recur iterative process
;; will do later seems pretty difficult
(defn first-denomination
  [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))


(defn cc_recur
  [amount kinds-of-coins]
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc_recur amount
                           (- kinds-of-coins 1))
                 (cc_recur (- amount
                              (first-denomination kinds-of-coins))
                           kinds-of-coins))))

(defn count_change
  [amount]
  (cc_recur amount 5))

;; Exercise 1.11.  A function f is defined by the rule that
;; f(n) = n if n<3
;; f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n> 3
;; Write a procedure that computes f by means of a recursive process.
;; Write a procedure that computes f by means of an iterative process.

(defn ex_1_11_recur
  [n]
  (if (< n 3)
    n
    (+ (ex_1_11_recur (- n 1))
       (* 2 (ex_1_11_recur (- n 2)))
       (* 3 (ex_1_11_recur (- n 3))))))

(defn ex_1_11_iter
  [n]
  (letfn [(ex_1_11_help
            [n1 n2 n3 cnt]
            (def val (+ n1 (* 2 n2) (* 3 n3)))
            (if (= cnt n)
              val
              (ex_1_11_help val n1 n2 (inc cnt))))]
    (if (< n 3) n
        (ex_1_11_help 2 1 0 3))))

;; Exercise 1.12. Write a procedure that computes elements of
;; Pascal's triangle by means of a recursive process.

(defn pascal
  [row col]
  (cond (or (< col 0) (< row 0) (< row col)) (throw (Exception. "Out of range"))
        (= col row) 1
        (= col 0) 1
        :else (+ (pascal (dec row) (dec col))
                 (pascal (dec row) col))))

;; Exercise 1.14.  Draw the tree illustrating the process generated by the
;; count-change procedure of section 1.2.2 in making change for 11 cents. What are
;; the orders of growth of the space and number of steps used by this process as the
;; amount to be changed increases?

;; space-complexity :
;; this will be height of the tree, which grows LiNEorly with the amount,
;; hence complexity theta(n)

;; time-complexity :
;; T(n,1) = 2n + 1 (number of nodes in a full binary tree)
;; O(n)

;; T(n,2) = T(n,1) + T(n-1,2)
;; T(n-1,1) = = T(n,1) + T(n-1,1) + T(n-2,2)
;; T(n-1,1) = = T(n,1) + T(n-1,1) + T(n-2,1) + T(n-3,2)
;; this will go on up until `n` gets exhausted, that is in `n` steps
;; hence complexity
;; O(n) * n
;; O(n^2)

;; T(n-3) = T(n,2) + T(n-1,3)
;; similar way as the previous one
;; O(n^2) * n
;; O(n^3)

;; On conclusion T(n,k) = O(n^k)


;; Exercise 1.15.  The sine of an angle (specified in radians) can be computed by making use of the
;; approximation sin(x) ~ x if x is sufficiently small, and the trigonometric identity
;; sin r = 3sin(r/3) - 4sin^3(r/3)

;; to reduce the size of the argument of sin. (For purposes of this exercise an angle is considered
;; "sufficiently small" if its magnitude is not greater than 0.1 radians.) These ideas are incorporated
;; in the following procedures:

(defn sine
  [angle]
  (letfn [(cube
            [x]
            (* x x x))]
    (letfn [(p
              [x]
              (- (* 3 x) (* 4 (cube x))))]
      (if (not (> (Math/abs angle) 0.1))
        angle
        (p (sine (/ angle 3.0)))))))

;; a.  How many times is the procedure p applied when (sine 12.15) is evaluated?
;; 5
;; b.  What is the order of growth in space and number of steps (as a function of a)
;; used by the process generated by the sine procedure when (sine a) is evaluated?

;; time complexity :
;; T(n) = T(n-1)/3
;; O(log n)

;; space complexity :
;; height of the tree = number of nodes
;; O(log n)


;; Exercise 1.16.  Design a procedure that evolves an iterative exponentiation process
;; that uses successive squaring and uses a logarithmic number of steps, as does fast-expt.
;; (Hint: Using the observation that (bn/2)2 = (b2)n/2, keep, along with the exponent n and
;;        the base b, an additional state variable a, and define the state transformation
;;        in such a way that the product a bn is unchanged from state to state. At the
;;        beginning of the process a is taken to be 1, and the answer is given by the value
;;        of a at the end of the process. In general, the technique of defining an invariant
;;        quantity that remains unchanged from state to state is a powerful way to think about
;;        the design of iterative algorithms.)

(defn fast-expt-iter
  [b n]
  (letfn[(expt-iter
           [ b n a ]
           (cond (= n 0) a
                 (even? n) (expt-iter (* b b) (/ n 2) a)
                 :else (expt-iter b (- n 1) (* a b))))]
    (expt-iter b n 1)))

;; Exercise 1.17.  The exponentiation algorithms in this section are based on performing
;; exponentiation by means of repeated multiplication. In a similar way, one can perform
;; integer multiplication by means of repeated addition. The following multiplication
;; procedure (in which it is assumed that our language can only add, not multiply) is analogous
;; to the expt procedure:

;; This algorithm takes a number of steps that is linear in b. Now suppose we
;; include, together with addition, operations double, which doubles an integer,
;; and halve, which divides an (even) integer by 2. Using these, design a
;; multiplication procedure analogous to fast-expt that uses a logarithmic
;; number of steps.

;; we can build the invariant quantity as this
;; a * b = 2a * b/2 if b is even
;; a * b = a + a * (b - 1) if b is odd

(defn fast-mult-recur
  [a b]
  (letfn [(doub
            [x]
            (+ x x))
          (halve
            [x]
            (/ x 2))]
  (cond (= b 0) 0
        (even? b) (doub (fast-mult-recur a (halve b)))
        :else (+ a (fast-mult-recur a (dec b))))))

;; Exercise 1.18.  Using the results of exercises 1.16 and 1.17, devise procedure
;; a that generates an iterative process for multiplying two integers
;; in terms of adding, doubling, and halving and uses a logarithmic number of
;; steps.

(defn fast-mult-iter
  [a b]
  (letfn [(doub
            [x]
            (+ x x))
          (halve
            [x]
            (/ x 2))
          (fast-mult
            [a b acc]
            (cond (= b 0) acc
                  (even? b) (fast-mult (doub a) (halve b) acc)
                  :else (fast-mult a (dec b) (+ acc a)))
            )]
    (fast-mult a b 0)))

;; Exercise 1.19.  There is a clever algorithm for computing the Fibonacci
;; numbers in a logarithmic number of steps. Recall the transformation of the
;; state variables a and b in the fib-iter of process
;; section 1.2.2: a <- a + b and b <- a. Call this transformation T,
;; and observe that applying T over and over again
;; n times, starting with 1 and 0, produces the pair Fib(n + 1) and Fib(n).
;; In other words, the Fibonacci numbers are produced by applying Tn, the nth
;; power of the transformation T, starting with the pair (1,0). Now consider T
;; to be the special case of p = 0 and q = 1 in a family of transformations Tpq,
;; where Tpq transforms the pair (a,b) according to a = bq + aq + ap and b = bp + aq.
;; Show that if we apply such a transformation Tpq twice, the effect is the same
;; as using a single transformation Tp'q' of the same form, and compute p' and q'
;; in terms of p and q. This gives us an explicit way to square these
;; transformations, and thus we can compute Tn using successive squaring, as in the
;; fast-expt procedure. Put this all together to complete the following procedure,
;; which runs in a logarithmic number of steps

(defn fib-clever
  [n]
  (letfn [(fib-iter
            [a b p q count]
            (cond (= count 0) b
                  (even? count) (fib-iter a
                                          b
                                          (+ (* p p) (* q q))
                                          (+ (* 2 p q) (* q q))
                                          (/ count 2))
                  :else (fib-iter (+ (* b q) (* a q) (* a p))
                                  (+ (* b p) (* a q))
                                  p
                                  q
                                  (- count 1))))]
    (fib-iter 1 0 0 1 n)))


;; Exercise 1.21.  Use the smallest-divisor procedure to find the smallest divisor of each
;; of the following numbers: 199, 1999, 19999.

(defn smallest-divisor
  [n]
  (letfn [(divides?
            [a b]
            (= (mod b a) 0))
          (square
            [x]
            (* x x))
          (get-divisor
            [n test-divisor]
            (cond (> (square test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (recur n (+ test-divisor 1))))]
    (get-divisor n 2)))

(smallest-divisor 199)
                                        ; 199
(smallest-divisor 1999)
                                        ; 1999
(smallest-divisor 19999)
                                        ; 7


;; Exercise 1.22

(defn search-for-primes [from]
  (letfn [(prime?
            [n]
            (= n (smallest-divisor n)))
          (current-time
            []
            (System/nanoTime))
          (report-prime
            [n elapsed-time]
            (println n " *** " elapsed-time))
          (start-prime-test
            [n start-time]
            (if (prime? n)
              (report-prime n (- (current-time) start-time))
              false))
          (timed-prime-test
            [n]
            (start-prime-test n (current-time)))
          (iter
            [i count]
            (if (timed-prime-test i)
              (if (< (inc count) 3)
                (recur (+ i 2) (inc count)))
              (recur (+ i 2) count)))]
    (if (odd? from)
      (iter from 0)
      (iter (inc from) 0))))