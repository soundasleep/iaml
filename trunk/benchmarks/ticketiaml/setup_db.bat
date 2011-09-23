rem delete output/ folder
rm -rf output

rem move output/ in parent folder to local folder
cp --verbose --recursive ../output .

rem copy over all templates
cp --verbose --recursive templates output

rem make output accessible
chmod 777 output --recursive

rem install database
rm output\*.db
sqlite3 output\events.db < events.sql
rem sqlite3 output\default.db < default.sql
sqlite3 output\users.db < users.sql
sqlite3 output\tickets.db < tickets.sql

del output\stored_events.db
del output\php.log
