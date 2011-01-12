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

if ($argc < 2) {
	throw new Exception("Expected arguments: [input file]");
}

$input = file($argv[1]);
for ($i = 0; $i < count($input); $i++) {
	$line = $input[$i];

	// is this a %%BeginDocument line?
	$skip_line = false;
	if (substr($line, 0, strlen("%%BeginDocument")) == "%%BeginDocument") {
		for ($j = $i + 1; $j < count($input); $j++) {
			if (substr($input[$j], 0, 1) != "%") {
				// ran out of comments; this isn't a cairo document
				break;
			} else {
				if (substr($input[$j], 0, strlen("%%Creator: cairo ")) == "%%Creator: cairo ") {
					// it is a cairo document!
					$skip_line = true;

					// consume lines until we get out of the document
					for ($i = $j; $i < count($input); $i++) {
						if (substr($input[$i], 0, strlen("%%EndDocument")) == "%%EndDocument") {
							// we have found the end of the document
							break;
						}
					}

					break;
				}
			}
		}

	}

	// otherwise, print out the line
	if (!$skip_line)
		echo $line;

}
