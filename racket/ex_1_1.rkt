;; Exercise 1.8.  Newton's method for cube roots is based on the fact that if y is an
;; approximation to the cube root of x, then a better approximation is given by the value
;; (x/y^2 + 2y)/3
;; Use this formula to implement a cube-root procedure analogous to the square-root procedure.
;; (In section 1.3.4 we will see how to implement Newton's method in general as an abstraction
;; of these square-root and cube-root procedures.)

(define (cube-root x)
  (cube-root-iter 1.0 x))

(define (cube-root-iter guess x)
  (if (good-enough? guess x)
    guess
    (cube-root-iter (improve guess x) x)))


(define (good-enough? guess x)
  (< (abs (- (improve guess x) guess)) 0.0001))

(define (square value) (* value value))

(define (improve guess x)
  (/ (+ (/ x (square guess)) (* guess 2)) 3))


(define (cube x) (* x  x x))

; tests
;
;  1/1_000_000
;  root  -> 1/1_00
;        -> .01
(print (cube-root .000001))

;  1_000_000_000
;  root -> 1_000
(print (cube-root 1000000000))
