(FUNCTION  test  []
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Mov [(r 3)]  [(i 0)])
    (OPER 7 Mov [(r 1)]  [(r 3)])
    (OPER 8 Mov [(r 4)]  [(i 1)])
    (OPER 9 Mov [(r 2)]  [(r 4)])
    (OPER 10 Mov [(r 5)]  [(i 0)])
    (OPER 11 Mov [(r 2)]  [(r 5)])
    (OPER 12 Mov [(r 1)]  [(r null)])
    (OPER 13 GT [(r 6)]  [(r 1)(r 2)])
    (OPER 14 BEQ [(bb 7)]  [(r 6)(i 0)])
  )
  (BB 5
    (OPER 15 Mov [(r 7)]  [(i 1)])
    (OPER 16 Sub_I [(r 8)]  [(r 2)(r 7)])
    (OPER 17 Mov [(r 2)]  [(r 8)])
  )
  (BB 6
    (OPER 45 EQ [(r 26)]  [(r 1)(r 2)])
    (OPER 46 BEQ [(bb 16)]  [(r 26)(i 0)])
  )
  (BB 15
    (OPER 47 EQ [(r 27)]  [(r 1)(r 2)])
    (OPER 48 BEQ [(bb 18)]  [(r 27)(i 0)])
  )
  (BB 17
    (OPER 49 EQ [(r 28)]  [(r 1)(r 2)])
    (OPER 50 BEQ [(bb 20)]  [(r 28)(i 0)])
  )
  (BB 19
    (OPER 51 Mov [(r 29)]  [(i 1)])
    (OPER 52 Sub_I [(r 30)]  [(r 2)(r 29)])
    (OPER 53 Mov [(r 1)]  [(r 30)])
    (OPER 54 EQ [(r 31)]  [(r 1)(r 2)])
    (OPER 55 BNE [(bb 19)]  [(r 31)(i 0)])
  )
  (BB 20
    (OPER 56 EQ [(r 32)]  [(r 1)(r 2)])
    (OPER 57 BNE [(bb 17)]  [(r 32)(i 0)])
  )
  (BB 18
  )
  (BB 16
    (OPER 58 Mov [(r 33)]  [(i 3)])
    (OPER 59 Mov [(r 2)]  [(r 33)])
    (OPER 60 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
  (BB 12
    (OPER 31 Mov [(r 17)]  [(i 1)])
    (OPER 32 Mov [(r 2)]  [(r 17)])
    (OPER 33 Mov [(r 18)]  [(i 1)])
    (OPER 34 EQ [(r 19)]  [(r 2)(r 18)])
    (OPER 35 BEQ [(bb 14)]  [(r 19)(i 0)])
  )
  (BB 13
    (OPER 36 Mov [(r 20)]  [(i 2)])
    (OPER 37 Add_I [(r 21)]  [(r 2)(r 20)])
    (OPER 38 Mov [(r 2)]  [(r 21)])
    (OPER 39 Mov [(r 22)]  [(i 1)])
    (OPER 40 EQ [(r 23)]  [(r 2)(r 22)])
    (OPER 41 BNE [(bb 13)]  [(r 23)(i 0)])
  )
  (BB 14
  )
  (BB 7
    (OPER 18 Mov [(r 9)]  [(i 2)])
    (OPER 19 Mov [(r 2)]  [(r 9)])
    (OPER 20 Mov [(r 10)]  [(i 2)])
    (OPER 21 EQ [(r 11)]  [(r 2)(r 10)])
    (OPER 22 BEQ [(bb 9)]  [(r 11)(i 0)])
  )
  (BB 8
    (OPER 23 Mov [(r 12)]  [(i 2)])
    (OPER 24 Mov [(r 2)]  [(r 12)])
    (OPER 25 Mov [(r 13)]  [(i 2)])
    (OPER 26 EQ [(r 14)]  [(r 2)(r 13)])
    (OPER 27 BEQ [(bb 12)]  [(r 14)(i 0)])
  )
  (BB 10
    (OPER 28 Mov [(r 15)]  [(i 1)])
    (OPER 29 Add_I [(r 16)]  [(r 2)(r 15)])
    (OPER 30 Mov [(r 2)]  [(r 16)])
  )
  (BB 11
    (OPER 42 Mov [(r 24)]  [(i 2)])
    (OPER 43 EQ [(r 25)]  [(r 2)(r 24)])
    (OPER 44 BNE [(bb 8)]  [(r 25)(i 0)])
  )
  (BB 9
  )
)
