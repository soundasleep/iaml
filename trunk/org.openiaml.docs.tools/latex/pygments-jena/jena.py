# -*- coding: utf-8 -*-
"""
    pygments.lexers.jena
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for Jena.
    Based on pygments.lexers.compiled

    :license: BSD, see LICENSE for details.
"""

import re

from pygments.scanner import Scanner
from pygments.lexer import Lexer, RegexLexer, include, bygroups, using, \
                           this, combined
from pygments.util import get_bool_opt, get_list_opt
from pygments.token import \
     Text, Comment, Operator, Keyword, Name, String, Number, Punctuation, \
     Error

# backwards compatibility
from pygments.lexers.functional import OcamlLexer

__all__ = ['JenaLexer']

# based on JavaLexer
class JenaLexer(RegexLexer):
    """
    For Jena source code (.jena) [jevon mod]
    """

    name = 'Jena'
    aliases = ['jena']
    filenames = ['*.jena']
    mimetypes = ['text/x-jena']

    flags = re.MULTILINE | re.DOTALL

    #: optional Comment or Whitespace
    _ws = r'(?:\s|//.*?\n|/[*].*?[*]/)+'

    tokens = {
        'root': [
            (r'[^\S\n]+', Text),
            (r'(<)([^>\n]+)(>)', bygroups(Operator, Name.Url, Operator)),
            (r'(\[)(\s*)([a-zA-Z_][a-zA-Z0-9_]*)(\s*)(:)', bygroups(Operator, Text, Name.Class, Text, Operator)),
            (r'#.*?\n', Comment.Single),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.\-]*', Name.Decorator),
            (r'(rdf:[A-Za-z]+|rdfs:[A-Za-z]+|owl:[A-Za-z]+)\b', Keyword.Construct),
            (r'(all|addOne|bound|difference|drop|equal|ge|greaterThan|hide|isBNode|isDType|isFunctor|isLiteral|le|lessThan|listContains|listEntry|listEqual|listLength|listMapAsObject|listMapAsSubject|listNotContains|listNotEqual|makeInstance|makeInstance|makeSkolem|makeTemp|max|min|noValue|noValue|notBNode|notDType|notEqual|notFunctor|notLiteral|now|print|product|quotient|regex|regex|remove|strConcat|sum|table|tableAll|unbound|uriConcat)', Keyword),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r'\'(\\\\|\\\'|[^\'])*\'', String),
            (r'([a-zA-Z_][a-zA-Z0-9_]*)(:)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Name.Namespace, Operator, Name.Label)),
            (r'\?[a-zA-Z_][a-zA-Z0-9_]*', Name.Variable),
            (r'[a-zA-Z_][a-zA-Z0-9_]*', Name.Label),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
    }
