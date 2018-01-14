@echo off
for %%F in (%1\*.tmp) do (

"cmd /c gnuplot -e filename='%%F' ex1.gp"

)