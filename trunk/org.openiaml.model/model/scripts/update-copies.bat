@echo off
rem -- updates the copies directory with the latest from SVN --
rem -- assumes that cygwin is installed --

cd ..
cd copies
svn update .
cd ..
cd scripts
