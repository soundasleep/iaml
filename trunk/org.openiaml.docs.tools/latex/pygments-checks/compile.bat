rem first, install the mapping
cp checks.py "C:\Program Files\Python\Lib\site-packages\Pygments-1.3.1-py2.6.egg\pygments\lexers\"
rem python "C:\Program Files\Python\Lib\site-packages\Pygments-1.3.1-py2.6.egg\pygments\lexers\_mapping.py"
rem ^ above line doesn't work manually

rem now, compile the code sample
cp code-sample.tex ../../../
cd ../../../
call partial.bat code-sample
cp partial-code-sample.pdf common/doc-tools/pygments-jena/code-sample-checks.pdf
cd common/doc-tools/pygments-checks/