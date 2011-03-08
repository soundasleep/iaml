<?php
/*
 * This script is designed to strip out Cairo-generated images from the postscript.
 * See: http://www.jevon.org/wiki/Unrecoverable_Error_in_ps2ascii_when_using_Inkscape_images
 */

/*
We want to change all instances of:

@setspecial
%%BeginDocument: models/iaml/Domain-Schema1.ps
%!PS-Adobe-3.0
%%Creator: cairo 1.8.8 (http://cairographics.org)
%%CreationDate: Tue Jan 11 16:35:05 2011

...

%%EndDocument
 @endspecial

into:

@setspecial
 @endspecial

*/

define("MAX_LINE", 1024);

if ($argc < 2) {
	throw new Exception("Expected arguments: [input file]");
}

$fp = fopen($argv[1], 'r');
while (!feof($fp)) {
	$line = fgets($fp, MAX_LINE);

	// is this a %%BeginDocument line?
	$skip_line = false;
	if (substr($line, 0, strlen("%%BeginDocument")) == "%%BeginDocument") {
		$current_pointer = ftell($fp);
		while (!feof($fp)) {
			$line2 = fgets($fp, MAX_LINE);
			if (substr($line2, 0, 1) != "%") {
				// ran out of comments; this isn't a cairo document
				fseek($fp, $current_pointer);
				break;
			} elseif (substr($line2, 0, strlen("%%Creator: cairo ")) == "%%Creator: cairo ") {
				// it is a cairo document!
				$skip_line = true;

				// consume lines until we get out of the document
				while (!feof($fp)) {
					$line2 = fgets($fp, MAX_LINE);
					if (substr($line2, 0, strlen("%%EndDocument")) == "%%EndDocument") {
						// we have found the end of the document
						break;
					}
				}

				break;
			}
		}

	}

	// otherwise, print out the line
	if (!$skip_line)
		echo $line;

}
