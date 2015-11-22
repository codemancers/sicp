;; This doesn't work for large n because of rounding issues
;; asks us to prove that Fib(n) is the closest integer to φn/√5, where
;; φ = (1 + √5)/2.
;; The exercise also gives us the following hint:
;; Let ψ = (1 - √5)/2. Use induction and the definition of Fibonacci numbers to
;; prove that Fib(n) = (φn - ψn) / √5

(define (fib n) (fib-iter 1 0 n))

(define (fib-iter a b count)
  (if (= count 0)
    b
    (fib-iter (+ a b) a (- count 1))))

(define (phi) (/ (+ 1 (sqrt 5)) 2))
(define (psi) (/ (- 1 (sqrt 5)) 2))
(define (dec number) (- number 1))

(define (raise-to-n value n)
  (if (= n 0)
    1
    (* value (raise-to-n value (dec n)))))

(define (approximation n)
  (round (/ (- (raise-to-n (phi) n) (raise-to-n (psi) n)) (sqrt 5))))

(println (= (approximation 20) (fib 20)))
(println (= (approximation 10) (fib 10)))
