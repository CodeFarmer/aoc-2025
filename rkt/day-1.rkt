#lang racket/base

(require racket/list)

(define (instruction-delta astr)
  (let ([d (string-ref astr 0)]
        [n (string->number  (substring astr 1))])
    (* (case d
         [(#\L) -1]
         [(#\R)  1])
       n)))

(define (-rotate-dial-iteratively i aseq acc)
  (if (empty? aseq) (reverse  acc)
      (let ([n (modulo (+ (car aseq) i) 100)])
        (-rotate-dial-iteratively n (cdr aseq) (cons n acc)))))

(define (rotate-dial-iteratively i aseq)
  (-rotate-dial-iteratively i aseq (list i)))

(provide instruction-delta
         rotate-dial-iteratively)

