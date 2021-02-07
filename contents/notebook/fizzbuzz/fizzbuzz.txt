#Python3 の FizzBuzz の最短コード（2020/08現在で発見可能な）



for i in range(100):print(i%3//2*"Fizz"+i%5//4*"Buzz"or-~i)



# すごい‼ でも意味わからん



# 各コードを分解して解釈

# for i in range(100): → 0～99まで繰り返す

# 「%」剰余（割った余り）
# i%3 → 0 か 1 か 2           を返す
# i%5 → 0 か 1 か 2 か 3 か 4 を返す

# 「//」切り捨て除算（小数点以下切捨て）
# i%3//2 → 0 か 1 を返す
# i%5//4 → 0 か 1 を返す



# 「or」 左辺がtrueだったら左辺を、falseだったら右辺を出力

# i%3//2*"Fizz"+i%5//4*"Buzz" が false になるのは？

#test
# for i in range(100):print(i%3//2+i%5//4)                  #→ 0 か 1 か 2 を表記
# for i in range(100):print(i%3//2*"Fizz"+i%5//4*"Buzz")    #→ "FizzBuzz" か "Fizz" か "Buzz" か "" を表記

# stringに0を掛けると… → "" これって null? → 文字数が「0」のstringと認識？
# 0 = false ってことのよう



# or-~i → 左辺が""だったら「i」をビット反転させてを出力
# ビット反転？
# 2進数のbit数の0と1を反転させる？ → pythonでは単純に各ビットを反転した値ではなく、~xは-(x+1)となる値を返す?

# 挙動確認コード
#
# sepa = "  :  "
#
# print("「 -~i 」の場合")
# for i in range(10):
#     print( \
#         "i ="        , i        , sepa , \
#         "-~i ="      , -~i      , sepa , \
#         "bin(i) ="   , bin(i)   , sepa , \
#         "bin(-~i) =" , bin(-~i) )
#
# print("\n「 ~i 」の場合")
# for i in range(10):
#     print( \
#         "i ="       , i       , sepa , \
#         "~i ="      , ~i      , sepa , \
#         "bin(i) ="  , bin(i)  , sepa , \
#         "bin(~i) =" , bin(~i) )

# 上記の出力
# bin() → 引数に指定した整数を、2進数表現の文字列に変換
# 0bxxx → ２進数だよ！という明示
#
# 「 -~i 」の場合
# i = 0   :   -~i = 1   :   bin(i) = 0b0   :   bin(-~i) = 0b1
# i = 1   :   -~i = 2   :   bin(i) = 0b1   :   bin(-~i) = 0b10
# i = 2   :   -~i = 3   :   bin(i) = 0b10   :   bin(-~i) = 0b11
# i = 3   :   -~i = 4   :   bin(i) = 0b11   :   bin(-~i) = 0b100
# i = 4   :   -~i = 5   :   bin(i) = 0b100   :   bin(-~i) = 0b101
# i = 5   :   -~i = 6   :   bin(i) = 0b101   :   bin(-~i) = 0b110
# i = 6   :   -~i = 7   :   bin(i) = 0b110   :   bin(-~i) = 0b111
# i = 7   :   -~i = 8   :   bin(i) = 0b111   :   bin(-~i) = 0b1000
# i = 8   :   -~i = 9   :   bin(i) = 0b1000   :   bin(-~i) = 0b1001
# i = 9   :   -~i = 10   :   bin(i) = 0b1001   :   bin(-~i) = 0b1010
#
# 「 ~i 」の場合
# i = 0   :   ~i = -1   :   bin(i) = 0b0   :   bin(~i) = -0b1
# i = 1   :   ~i = -2   :   bin(i) = 0b1   :   bin(~i) = -0b10
# i = 2   :   ~i = -3   :   bin(i) = 0b10   :   bin(~i) = -0b11
# i = 3   :   ~i = -4   :   bin(i) = 0b11   :   bin(~i) = -0b100
# i = 4   :   ~i = -5   :   bin(i) = 0b100   :   bin(~i) = -0b101
# i = 5   :   ~i = -6   :   bin(i) = 0b101   :   bin(~i) = -0b110
# i = 6   :   ~i = -7   :   bin(i) = 0b110   :   bin(~i) = -0b111
# i = 7   :   ~i = -8   :   bin(i) = 0b111   :   bin(~i) = -0b1000
# i = 8   :   ~i = -9   :   bin(i) = 0b1000   :   bin(~i) = -0b1001
# i = 9   :   ~i = -10   :   bin(i) = 0b1001   :   bin(~i) = -0b1010


# 出力値から判断すると…
# 一番右の桁が0だったらその桁を1にする
# 一番右の桁が1だったら桁上がりをしてからその桁を0にする
# つまり、１を足してプラスマイナスを反転させて出力されているよう

# 単純に「 i+1 」じゃダメなのかな？
# やってみたら、「 or 」との間にスペースが必要だった
# その分長くなるからかな？



#python2ではさらに短いのが４パターン

# パターン(1)
# printのカッコが無い！スペースすらない！
#
# for i in range(100):printi%3/2*"Fizz"+i%5/4*"Buzz"or-~i


# パターン(2)
# あれ？２行だ
#
# i=0
# while~i<99:i-=1;print~i%3/2*'Fizz'+~i%5/4*'Buzz'or-i
#
# ~i してるから i-=1 なんだね


# パターン(3)
# exec ? → 引数で与えたPythonコードを動的に解釈、実行できる
# 最後の「 *100 」って使い方、" Python2 "だから？
#
# i=0
# exec"print i%3/2*'Fizz'+i%5/4*'Buzz'or~i;i+=1;"*100


# パターン(4)
# もはや…
#
# i=1:exec"print'FizzBuzz'[i%-3&4:12&8-i%5]or i;i+=1;"*100


#python3でこんなのも
#
#print('\n'.join(['Fizz'*(n%3==0) + 'Buzz'*(n%5==0) or str(n) for n in range(1,101)]))

