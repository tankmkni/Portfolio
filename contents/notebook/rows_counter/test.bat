@echo off
rem
rem フォルダ内（サブフォルダも含めて）の指定した拡張子が一致するファイル数と
rem その行数の合計を表示する
rem

rem 取得したいファイルの拡張子(extension)を変数に指定
set extension=*.java *.jsp *.html *.css *.js *.py *.php *.bat *.txt

rem 条件に合うファイル数を取得して表示
for /f %%i in ( 'dir /a-d /s /b %extension% ^| find /c /v "" ' ) do (
    set allFile=%%i
)
echo total Files = %allFile%

rem 各ファイルの行数を取得/合計して表示
for /f %%i in (' dir %extension% /b /s ') do (
    for /f %%j in (' type %%i ^| find /c /v "" ') do (
        set /a totalRow=totalRow+%%j
    )
)
echo total Rows = %totalRow%

rem 何か入力されるまで待機
pause

rem 終了コードを設定して実行を終了
exit /b 0