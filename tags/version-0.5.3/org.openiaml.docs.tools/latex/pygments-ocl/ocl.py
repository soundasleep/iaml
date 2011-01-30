# -*- coding: utf-8 -*-
"""
    pygments.lexers.ocl
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for OCL.
    Based on pygments.lexers.agile

    :license: BSD, see LICENSE for details.
"""

import re

from pygments.scanner import Scanner
from pygments.lexer import Lexer, RegexLexer, include, bygroups, using, \
                           this, combined
from pygments.token import \
     Text, Comment, Operator, Keyword, Name, String, Number, Punctuation, \
     Error

__all__ = ['OclLexer']

# based on PythonLexer
class OclLexer(RegexLexer):
    """
    For OCL source code [jevon mod].
    """

    name = 'OCL'
    aliases = ['ocl']
    filenames = ['*.ocl']
    mimetypes = ['text/x-ocl', 'application/x-ocl']

    tokens = {
        'root': [
            (r'\n', Text),
            (r'^(\s*)("""(?:.|\n)*?""")', bygroups(Text, String.Doc)),
            (r"^(\s*)('''(?:.|\n)*?''')", bygroups(Text, String.Doc)),
            (r'[^\S\n]+', Text),
            (r'#.*$', Comment),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'[]{}:(),;[]', Punctuation),
            (r'\\\n', Text),
            (r'\\', Text),
            (r'(in|is|and|or|not)\b', Operator.Word),
            (r'<>|!=|==|->|<<|>>|[-~+/*%=<>&^|.!]', Operator),
            include('keywords'),
            include('builtins'),
            include('name'),
            (r'"(\\\\|\\"|[^"])*"', String),
            include('numbers'),
            (r'(oclIsTypeOf|allInstances|includes|isUnique|isEmpty|exists|forAll|size|'
             r'implies|asSet|includes)\b', Name.Function),
        ],
        'keywords': [
        	# TODO not yet populated
            (r'(context|inv|if|else|library|metamodel|require|public|definitions|'
             r'init|derive|pre|body|'
             r'enddefinitions|endlibrary)\b', Keyword),
            (r'(Sequence|Set|Bag|OrderedSet)\b', Keyword.Type),
            (r'(true|false|null)\b', Keyword.Constant),
        ],
        'builtins': [
        	# TODO not yet populated
            (r'(self|none)\b', Name.Builtin),
        ],
        'numbers': [
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
        ],
        'name': [
            (r'@[a-zA-Z0-9_.]+', Name.Decorator),
            ('[a-zA-Z_][a-zA-Z0-9_]*', Name),
        ],
    }
