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
    (OPER 19 JSR []  [(s foo)])
    (OPER 20 Mov [(r 14)]  [(m RetReg)])
    (OPER 21 Mov [(r 1)]  [(r 14)])
    (OPER 22 Mov [(r 15)]  [(i 0)])
    (OPER 23 EQ [(r 16)]  [(r 2)(r 15)])
    (OPER 24 BEQ [(bb 7)]  [(r 16)(i 0)])
  )
  (BB 5
    (OPER 25 Mov [(r 17)]  [(i 1)])
    (OPER 26 Store [(s a)]  [(r 17)])
  )
  (BB 6
    (OPER 30 Mov [(r 20)]  [(i 0)])
    (OPER 31 Return [(m RetReg)]  [(r 20)])
    (OPER 32 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
  (BB 7
    (OPER 27 Mov [(r 19)]  [(i 5)])
    (OPER 28 Mov [(r 1)]  [(r 19)])
    (OPER 29 Jmp []  [(bb 6)])
  )
)
