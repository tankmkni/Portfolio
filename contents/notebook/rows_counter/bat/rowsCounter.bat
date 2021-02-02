@echo off
rem
rem フォルダ内（サブフォルダも含めて）のファイル数と
rem その行数の合計を表示する
rem

rem 取得したいファイルの拡張子(extension)を変数に指定
set extension=*.java *.html *.css *.js *.py *.bat *.txt

rem 条件に合うファイル数を取得して表示
for /f %%i in ( 'dir /a-d /s /b %extension% ^| find /c /v "" ' ) do (
    set allFile=%%i
)

rem 各ファイルの行数を取得/合計して表示
set /a totalRow=0
for /f %%i in (' dir %extension% /b /s ') do (
	echo %%i
    for /f %%j in (' type %%i ^| find /c /v "" ') do (
        set /a totalRow=totalRow+%%j
    )
)
echo %1  Files = %allFile% / Rows = %totalRow%

rem 何かキーが押されるまで待機
pause

rem 終了コードを設定して実行を終了
exit /b 0