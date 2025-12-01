#lang racket/base

(require racket/port
         racket/string
         rackunit
         "day-1.rkt")

(check-equal? -68 (instruction-delta "L68"))
(check-equal? -30 (instruction-delta "L30"))
(check-equal?  48 (instruction-delta "R48"))

(define sample-data
  (map instruction-delta
       (string-split
        "L68
L30
R48
L5
R60
L55
L1
L99
R14
L82"
        "\n")))

(test-case
    "dial rotations are processed sequentially"
  (let ([dial-positions (rotate-dial-iteratively 50 sample-data)])
    
    (check-equal? '(50 82 52 0 95 55 0 99 0 14 32)
                  dial-positions)
    (check-equal? 3 (length (filter zero? dial-positions)))))

(define input-data
  (map instruction-delta
       (string-split
        (port->string (open-input-file "aoc-2025-inputs/day-1.txt"))
        "\n")))

(test-case
    "Part 1"
  (let ([dial-positions (rotate-dial-iteratively 50 input-data)])
    (check-equal? 1141 (length (filter zero? dial-positions)))))
