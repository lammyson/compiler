(DATA  a)
(FUNCTION  addThem  [(int d) (int e)]
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Add_I [(r 4)]  [(r 1)(r 2)])
    (OPER 7 Mov [(r 3)]  [(r 4)])
    (OPER 8 Return [(m RetReg)]  [(r 3)])
    (OPER 9 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
)
(FUNCTION  putDigit  [(int s)]
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 7 Mov [(r 2)]  [(i 48)])
    (OPER 8 Add_I [(r 3)]  [(r 2)(r 1)])
    (OPER 9 Pass []  [(r 3)])
    (OPER 6 JSR []  [(s putchar)])
    (OPER 10 Mov [(r 4)]  [(m RetReg)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Mov [(r 4)]  [(i 0)])
    (OPER 7 Mov [(r 3)]  [(r 4)])
    (OPER 8 Mov [(r 5)]  [(i 10000)])
    (OPER 9 GTE [(r 6)]  [(r 1)(r 5)])
    (OPER 10 BEQ [(bb 7)]  [(r 6)(i 0)])
  )
  (BB 5
    (OPER 12 Mov [(r 7)]  [(i 45)])
    (OPER 13 Pass []  [(r 7)])
    (OPER 11 JSR []  [(s putchar)])
    (OPER 14 Mov [(r 8)]  [(m RetReg)])
    (OPER 16 Mov [(r 9)]  [(i 1)])
    (OPER 17 Pass []  [(r 9)])
    (OPER 15 JSR []  [(s putDigit)])
    (OPER 18 Mov [(r 10)]  [(m RetReg)])
    (OPER 19 Jmp []  [(bb 2)])
  )
  (BB 6
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
  (BB 12
    (OPER 50 Mov [(r 29)]  [(i 1)])
    (OPER 51 EQ [(r 30)]  [(r 3)(r 29)])
    (OPER 52 BEQ [(bb 14)]  [(r 30)(i 0)])
  )
  (BB 13
    (OPER 54 Mov [(r 31)]  [(i 0)])
    (OPER 55 Pass []  [(r 31)])
    (OPER 53 JSR []  [(s putDigit)])
    (OPER 56 Mov [(r 32)]  [(m RetReg)])
  )
  (BB 14
    (OPER 57 Jmp []  [(bb 11)])
  )
  (BB 17
    (OPER 71 Mov [(r 41)]  [(i 1)])
    (OPER 72 EQ [(r 42)]  [(r 3)(r 41)])
    (OPER 73 BEQ [(bb 19)]  [(r 42)(i 0)])
  )
  (BB 18
    (OPER 75 Mov [(r 43)]  [(i 0)])
    (OPER 76 Pass []  [(r 43)])
    (OPER 74 JSR []  [(s putDigit)])
    (OPER 77 Mov [(r 44)]  [(m RetReg)])
  )
  (BB 19
    (OPER 78 Jmp []  [(bb 16)])
  )
  (BB 7
    (OPER 20 Mov [(r 11)]  [(i 1000)])
    (OPER 21 GTE [(r 12)]  [(r 1)(r 11)])
    (OPER 22 BEQ [(bb 9)]  [(r 12)(i 0)])
  )
  (BB 8
    (OPER 23 Mov [(r 13)]  [(i 1000)])
    (OPER 24 Div_I [(r 14)]  [(r 1)(r 13)])
    (OPER 25 Mov [(r 2)]  [(r 14)])
    (OPER 27 Pass []  [(r 2)])
    (OPER 26 JSR []  [(s putDigit)])
    (OPER 28 Mov [(r 15)]  [(m RetReg)])
    (OPER 29 Mov [(r 16)]  [(i 1000)])
    (OPER 30 Mul_I [(r 17)]  [(r 2)(r 16)])
    (OPER 31 Sub_I [(r 18)]  [(r 1)(r 17)])
    (OPER 32 Mov [(r 1)]  [(r 18)])
    (OPER 33 Mov [(r 19)]  [(i 1)])
    (OPER 34 Mov [(r 3)]  [(r 19)])
  )
  (BB 9
    (OPER 35 Mov [(r 20)]  [(i 100)])
    (OPER 36 GTE [(r 21)]  [(r 1)(r 20)])
    (OPER 37 BEQ [(bb 12)]  [(r 21)(i 0)])
  )
  (BB 10
    (OPER 38 Mov [(r 22)]  [(i 100)])
    (OPER 39 Div_I [(r 23)]  [(r 1)(r 22)])
    (OPER 40 Mov [(r 2)]  [(r 23)])
    (OPER 42 Pass []  [(r 2)])
    (OPER 41 JSR []  [(s putDigit)])
    (OPER 43 Mov [(r 24)]  [(m RetReg)])
    (OPER 44 Mov [(r 25)]  [(i 100)])
    (OPER 45 Mul_I [(r 26)]  [(r 2)(r 25)])
    (OPER 46 Sub_I [(r 27)]  [(r 1)(r 26)])
    (OPER 47 Mov [(r 1)]  [(r 27)])
    (OPER 48 Mov [(r 28)]  [(i 1)])
    (OPER 49 Mov [(r 3)]  [(r 28)])
  )
  (BB 11
    (OPER 58 Mov [(r 33)]  [(i 10)])
    (OPER 59 GTE [(r 34)]  [(r 1)(r 33)])
    (OPER 60 BEQ [(bb 17)]  [(r 34)(i 0)])
  )
  (BB 15
    (OPER 61 Mov [(r 35)]  [(i 10)])
    (OPER 62 Div_I [(r 36)]  [(r 1)(r 35)])
    (OPER 63 Mov [(r 2)]  [(r 36)])
    (OPER 65 Pass []  [(r 2)])
    (OPER 64 JSR []  [(s putDigit)])
    (OPER 66 Mov [(r 37)]  [(m RetReg)])
    (OPER 67 Mov [(r 38)]  [(i 10)])
    (OPER 68 Mul_I [(r 39)]  [(r 2)(r 38)])
    (OPER 69 Sub_I [(r 40)]  [(r 1)(r 39)])
    (OPER 70 Mov [(r 1)]  [(r 40)])
  )
  (BB 16
    (OPER 80 Pass []  [(r 1)])
    (OPER 79 JSR []  [(s putDigit)])
    (OPER 81 Mov [(r 45)]  [(m RetReg)])
    (OPER 82 Jmp []  [(bb 6)])
  )
)
(FUNCTION  main  []
  (BB 3
    (OPER 5 Func_Entry []  [])
  )
  (BB 4
    (OPER 6 Mov [(r 6)]  [(i 5)])
    (OPER 7 Mov [(r 2)]  [(r 6)])
    (OPER 8 Mov [(r 1)]  [(r 2)])
    (OPER 9 Mov [(r 7)]  [(i 5)])
    (OPER 10 EQ [(r 8)]  [(r 1)(r 7)])
    (OPER 11 BEQ [(bb 7)]  [(r 8)(i 0)])
  )
  (BB 5
    (OPER 12 Load [(r 9)]  [(s a)])
    (OPER 13 Mov [(r 10)]  [(i 3)])
    (OPER 14 Store [(s a)]  [(r 10)])
  )
  (BB 6
    (OPER 18 Mov [(r 14)]  [(i 0)])
    (OPER 19 Mov [(r 3)]  [(r 14)])
    (OPER 20 Mov [(r 15)]  [(i 1)])
    (OPER 21 Mov [(r 5)]  [(r 15)])
    (OPER 22 Mov [(r 16)]  [(i 8)])
    (OPER 23 LTE [(r 17)]  [(r 5)(r 16)])
    (OPER 24 BEQ [(bb 9)]  [(r 17)(i 0)])
  )
  (BB 8
    (OPER 25 Add_I [(r 18)]  [(r 3)(r 5)])
    (OPER 26 Mov [(r 3)]  [(r 18)])
    (OPER 27 Mov [(r 19)]  [(i 1)])
    (OPER 28 Add_I [(r 20)]  [(r 5)(r 19)])
    (OPER 29 Mov [(r 5)]  [(r 20)])
    (OPER 30 Mov [(r 21)]  [(i 8)])
    (OPER 31 LTE [(r 22)]  [(r 5)(r 21)])
    (OPER 32 BNE [(bb 8)]  [(r 22)(i 0)])
  )
  (BB 9
    (OPER 33 Mov [(r 23)]  [(i 3)])
    (OPER 34 Div_I [(r 24)]  [(r 3)(r 23)])
    (OPER 35 Mov [(r 4)]  [(r 24)])
    (OPER 36 Mov [(r 25)]  [(i 4)])
    (OPER 37 Mul_I [(r 26)]  [(r 4)(r 25)])
    (OPER 38 Mov [(r 3)]  [(r 26)])
    (OPER 40 Pass []  [(r 1)])
    (OPER 41 Pass []  [(r 9)])
    (OPER 39 JSR []  [(s addThem)])
    (OPER 42 Mov [(r 27)]  [(m RetReg)])
    (OPER 43 Mov [(r 2)]  [(r 27)])
    (OPER 45 Mov [(r 28)]  [(i 56)])
    (OPER 46 Pass []  [(r 28)])
    (OPER 44 JSR []  [(s putchar)])
    (OPER 47 Mov [(r 29)]  [(m RetReg)])
    (OPER 49 Mov [(r 30)]  [(i 61)])
    (OPER 50 Pass []  [(r 30)])
    (OPER 48 JSR []  [(s putchar)])
    (OPER 51 Mov [(r 31)]  [(m RetReg)])
    (OPER 53 Add_I [(r 32)]  [(r 2)(r 3)])
    (OPER 54 Pass []  [(r 32)])
    (OPER 52 JSR []  [(s putchar)])
    (OPER 55 Mov [(r 33)]  [(m RetReg)])
    (OPER 57 Mov [(r 34)]  [(i 10)])
    (OPER 58 Pass []  [(r 34)])
    (OPER 56 JSR []  [(s putchar)])
    (OPER 59 Mov [(r 35)]  [(m RetReg)])
    (OPER 60 Mov [(r 36)]  [(i 0)])
    (OPER 61 Mov [(r 5)]  [(r 36)])
    (OPER 62 Mov [(r 37)]  [(i 10)])
    (OPER 63 LT [(r 38)]  [(r 5)(r 37)])
    (OPER 64 BEQ [(bb 11)]  [(r 38)(i 0)])
  )
  (BB 10
    (OPER 66 Mov [(r 39)]  [(i 48)])
    (OPER 67 Add_I [(r 40)]  [(r 39)(r 5)])
    (OPER 68 Pass []  [(r 40)])
    (OPER 65 JSR []  [(s putchar)])
    (OPER 69 Mov [(r 41)]  [(m RetReg)])
    (OPER 70 Mov [(r 42)]  [(i 1)])
    (OPER 71 Add_I [(r 43)]  [(r 5)(r 42)])
    (OPER 72 Mov [(r 5)]  [(r 43)])
    (OPER 73 Mov [(r 44)]  [(i 10)])
    (OPER 74 LT [(r 45)]  [(r 5)(r 44)])
    (OPER 75 BNE [(bb 10)]  [(r 45)(i 0)])
  )
  (BB 11
    (OPER 77 Mov [(r 46)]  [(i 10)])
    (OPER 78 Pass []  [(r 46)])
    (OPER 76 JSR []  [(s putchar)])
    (OPER 79 Mov [(r 47)]  [(m RetReg)])
    (OPER 81 Mov [(r 48)]  [(i 67)])
    (OPER 82 Pass []  [(r 48)])
    (OPER 80 JSR []  [(s putchar)])
    (OPER 83 Mov [(r 49)]  [(m RetReg)])
    (OPER 85 Mov [(r 50)]  [(i 83)])
    (OPER 86 Pass []  [(r 50)])
    (OPER 84 JSR []  [(s putchar)])
    (OPER 87 Mov [(r 51)]  [(m RetReg)])
    (OPER 89 Mov [(r 52)]  [(i 3510)])
    (OPER 90 Pass []  [(r 52)])
    (OPER 88 JSR []  [(s printInt)])
    (OPER 91 Mov [(r 53)]  [(m RetReg)])
    (OPER 93 Mov [(r 54)]  [(i 10)])
    (OPER 94 Pass []  [(r 54)])
    (OPER 92 JSR []  [(s putchar)])
    (OPER 95 Mov [(r 55)]  [(m RetReg)])
    (OPER 96 Mov [(r 56)]  [(i 0)])
    (OPER 97 Mov [(r 1)]  [(r 56)])
    (OPER 98 Mov [(r 57)]  [(i 1)])
    (OPER 99 Mov [(r 2)]  [(r 57)])
    (OPER 100 Mov [(r 58)]  [(i 1)])
    (OPER 101 Mov [(r 3)]  [(r 58)])
    (OPER 102 Mov [(r 59)]  [(i 0)])
    (OPER 103 Mov [(r 4)]  [(r 59)])
    (OPER 104 Mov [(r 60)]  [(i 0)])
    (OPER 105 Mov [(r 5)]  [(r 60)])
    (OPER 106 Mov [(r 61)]  [(i 0)])
    (OPER 107 EQ [(r 62)]  [(r 1)(r 61)])
    (OPER 108 BEQ [(bb 14)]  [(r 62)(i 0)])
  )
  (BB 12
    (OPER 109 Mov [(r 63)]  [(i 0)])
    (OPER 110 EQ [(r 64)]  [(r 2)(r 63)])
    (OPER 111 BEQ [(bb 17)]  [(r 64)(i 0)])
  )
  (BB 15
    (OPER 112 Mov [(r 65)]  [(i 1)])
    (OPER 113 Mov [(r 5)]  [(r 65)])
  )
  (BB 16
  )
  (BB 13
    (OPER 132 Mov [(r 74)]  [(i 10)])
    (OPER 133 EQ [(r 75)]  [(r 5)(r 74)])
    (OPER 134 BEQ [(bb 26)]  [(r 75)(i 0)])
  )
  (BB 24
    (OPER 136 Mov [(r 76)]  [(i 99)])
    (OPER 137 Pass []  [(r 76)])
    (OPER 135 JSR []  [(s putchar)])
    (OPER 138 Mov [(r 77)]  [(m RetReg)])
    (OPER 140 Mov [(r 78)]  [(i 0)])
    (OPER 141 Pass []  [(r 78)])
    (OPER 139 JSR []  [(s putDigit)])
    (OPER 142 Mov [(r 79)]  [(m RetReg)])
    (OPER 144 Mov [(r 80)]  [(i 0)])
    (OPER 145 Pass []  [(r 80)])
    (OPER 143 JSR []  [(s putDigit)])
    (OPER 146 Mov [(r 81)]  [(m RetReg)])
    (OPER 148 Mov [(r 82)]  [(i 108)])
    (OPER 149 Pass []  [(r 82)])
    (OPER 147 JSR []  [(s putchar)])
    (OPER 150 Mov [(r 83)]  [(m RetReg)])
  )
  (BB 25
    (OPER 172 Mov [(r 93)]  [(i 10)])
    (OPER 173 Pass []  [(r 93)])
    (OPER 171 JSR []  [(s putchar)])
    (OPER 174 Mov [(r 94)]  [(m RetReg)])
    (OPER 175 Mov [(r 95)]  [(i 0)])
    (OPER 176 Return [(m RetReg)]  [(r 95)])
    (OPER 177 Jmp []  [(bb 2)])
  )
  (BB 2
    (OPER 3 Func_Exit []  [])
    (OPER 4 Return []  [(m RetReg)])
  )
  (BB 7
    (OPER 15 Mov [(r 12)]  [(i 4)])
    (OPER 16 Store [(s a)]  [(r 12)])
    (OPER 17 Jmp []  [(bb 6)])
  )
  (BB 23
    (OPER 124 Mov [(r 72)]  [(i 3)])
    (OPER 125 Mov [(r 5)]  [(r 72)])
    (OPER 126 Jmp []  [(bb 22)])
  )
  (BB 20
    (OPER 119 Mov [(r 69)]  [(i 0)])
    (OPER 120 EQ [(r 70)]  [(r 4)(r 69)])
    (OPER 121 BEQ [(bb 23)]  [(r 70)(i 0)])
  )
  (BB 21
    (OPER 122 Mov [(r 71)]  [(i 10)])
    (OPER 123 Mov [(r 5)]  [(r 71)])
  )
  (BB 22
    (OPER 127 Jmp []  [(bb 19)])
  )
  (BB 17
    (OPER 114 Mov [(r 66)]  [(i 0)])
    (OPER 115 EQ [(r 67)]  [(r 3)(r 66)])
    (OPER 116 BEQ [(bb 20)]  [(r 67)(i 0)])
  )
  (BB 18
    (OPER 117 Mov [(r 68)]  [(i 2)])
    (OPER 118 Mov [(r 5)]  [(r 68)])
  )
  (BB 19
    (OPER 128 Jmp []  [(bb 16)])
  )
  (BB 14
    (OPER 129 Mov [(r 73)]  [(i 0)])
    (OPER 130 Mov [(r 5)]  [(r 73)])
    (OPER 131 Jmp []  [(bb 13)])
  )
  (BB 26
    (OPER 152 Mov [(r 84)]  [(i 98)])
    (OPER 153 Pass []  [(r 84)])
    (OPER 151 JSR []  [(s putchar)])
    (OPER 154 Mov [(r 85)]  [(m RetReg)])
    (OPER 156 Mov [(r 86)]  [(i 97)])
    (OPER 157 Pass []  [(r 86)])
    (OPER 155 JSR []  [(s putchar)])
    (OPER 158 Mov [(r 87)]  [(m RetReg)])
    (OPER 160 Mov [(r 88)]  [(i 100)])
    (OPER 161 Pass []  [(r 88)])
    (OPER 159 JSR []  [(s putchar)])
    (OPER 162 Mov [(r 89)]  [(m RetReg)])
    (OPER 164 Mov [(r 90)]  [(i 61)])
    (OPER 165 Pass []  [(r 90)])
    (OPER 163 JSR []  [(s putchar)])
    (OPER 166 Mov [(r 91)]  [(m RetReg)])
    (OPER 168 Pass []  [(r 5)])
    (OPER 167 JSR []  [(s printInt)])
    (OPER 169 Mov [(r 92)]  [(m RetReg)])
    (OPER 170 Jmp []  [(bb 25)])
  )
)
