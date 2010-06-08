<?php

// tests translate_query_to_sqlite()

function print_translated($array) {
	echo "query = '" . $array["query"] . "'\n";
	echo "args =\n";
	foreach ($array["args"] as $key => $value) {
		echo "  $key => $value\n";
	}
	echo "-\n";
}

echo "[test 20] ";
ob_start();
{
	// default
	print_translated(translate_query_to_sqlite("", array()));

	// '1'
	print_translated(translate_query_to_sqlite("1", array()));

	// 'any'
	print_translated(translate_query_to_sqlite("any", array()));

	// a = :a
	print_translated(translate_query_to_sqlite("a = :a", array("a" => "1")));

	// a = ?
	print_translated(translate_query_to_sqlite("a = ?", array("1")));

	// a = :a and b = :b
	print_translated(translate_query_to_sqlite("a = :a and b = :b", array("a" => "1", "b" => "2")));

	// a = :a or b = :b
	print_translated(translate_query_to_sqlite("a = :a or b = :b", array("a" => "1", "b" => "2")));

	// matches(a, :a)
	print_translated(translate_query_to_sqlite("matches(a, :a)", array("a" => "1")));

	// matches(a, ?)
	print_translated(translate_query_to_sqlite("matches(a, ?)", array("1")));

	// matches(a, :a) and matches(b, :b)
	print_translated(translate_query_to_sqlite("matches(a, :a) and matches(b, :b)", array("a" => "1", "b" => "2")));

	// matches(a, :a) with a = 'b c'
	print_translated(translate_query_to_sqlite("matches(a, :a)", array("a" => "b c")));

	// matches(a, :a) with a = ''
	print_translated(translate_query_to_sqlite("matches(a, :a)", array("a" => "")));

	// password = :password and email = :email
	print_translated(translate_query_to_sqlite("password = :password and email = :email", array("password" => "test", "email" => "test@openiaml.org")));

	// password = :password and email = :email, with empty args
	print_translated(translate_query_to_sqlite("password = :password and email = :email", array("password" => "", "email" => "")));

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected20.txt");
