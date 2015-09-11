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
