;; SICP - Exercise 1.1
;; using MIT-scheme Release 9.2

;; sexp                   value
10                        ;;=> 10
(+ 5 3 4)                 ;;=> 12
(- 9 1)                   ;;=> 8
(/ 6 2)                   ;;=> 3
(/ 6 4)                   ;;=> 3/2
(/ 6 4.0)                 ;;=> 1.5
(+ (* 2 4)
   (- 4 6))               ;;=> 6

(define a 3)              ;;=> a
(define b (+ a 1))        ;;=> b
a                         ;;=> 3
b                         ;;=> 4
(define a 4)              ;;=> a
a                         ;;=> 4
b                         ;;=> 4
(define a 3)              ;;=> a
a                         ;;=> 3
b                         ;;=> 4

(+ a b (* a b))           ;;=> 19
(= a b)                   ;;=> #f

;; if-condition
(if (and (> b a)
         (< b (* a b)))
    b
    a)                    ;;=> 4
(cond ((= a 4) 6)
      ((= b 4) (+ 6 7 a))
      (else 25))          ;;=> 16
(cond ((= 1 1) 10)
      ((= 2 2) 20))       ;;=> 10

;; can compose an if-condition
(+ 2 (if (> b a)
         b
         a))              ;;=> 6
(* (cond ((> a b) a)
         ((< a b) b)
         (else -1))
   (+ a 1))               ;;=> 16
