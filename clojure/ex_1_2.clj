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
