(DATA  a)
(FUNCTION  foo  []
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Mov [(r 1)]  [(i 1)])
    (OPER 7 Return [(m RetReg)]  [(r 1)])
    (OPER 8 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
)
(FUNCTION  main  []
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Load [(r 2)]  [(s a)])
    (OPER 7 Mov [(r 3)]  [(i 0)])
    (OPER 8 Store [(s a)]  [(r 3)])
    (OPER 9 Mov [(r 5)]  [(i 1)])
    (OPER 10 Add_I [(r 6)]  [(r 2)(r 5)])
    (OPER 11 Mov [(r 1)]  [(r 6)])
    (OPER 12 Div_I [(r 7)]  [(r 2)(r 2)])
    (OPER 13 Mov [(r 8)]  [(i 3)])
    (OPER 14 Mul_I [(r 9)]  [(r 7)(r 8)])
    (OPER 15 Sub_I [(r 10)]  [(r 1)(r 9)])
    (OPER 16 Mov [(r 11)]  [(i 4)])
    (OPER 17 Add_I [(r 12)]  [(r 10)(r 11)])
    (OPER 18 Store [(s a)]  [(r 12)])
    (OPER 19 Mov [(r 1)]  [(r null)])
    (OPER 20 Mov [(r 14)]  [(i 0)])
    (OPER 21 Return [(m RetReg)]  [(r 14)])
    (OPER 22 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
)
