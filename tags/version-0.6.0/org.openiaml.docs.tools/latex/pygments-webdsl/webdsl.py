# -*- coding: utf-8 -*-
"""
    pygments.lexers.webdsl
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for WebDSL.
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

__all__ = ['WebDSLLexer']

# based on JavaLexer
class WebDSLLexer(RegexLexer):
    """
    For WebDSL source code (.webdsl) [jevon mod]
    """

    name = 'Alloy'
    aliases = ['webdsl']
    filenames = ['*.webdsl']
    mimetypes = ['text/x-webdsl']

    flags = re.MULTILINE | re.DOTALL

    #: optional Comment or Whitespace
    _ws = r'(?:\s|//.*?\n|/[*].*?[*]/)+'

    tokens = {
        'root': [
            # method names
            (r'^(\s*(?:[a-zA-Z_][a-zA-Z0-9_\.\[\]]*\s+)+?)' # return arguments
             r'([a-zA-Z_][a-zA-Z0-9_]*)'                    # method name
             r'(\s*)(\()',                                  # signature start
             bygroups(using(this), Name.Function, Text, Operator)),
            (r'([a-zA-Z_][a-zA-Z0-9_]*)'					# method name
             r'(\s*)(\()', bygroups(Name.Function, Text, Operator)),					# signature start
            (r'[^\S\n]+', Text),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.]*', Name.Decorator),
            # derived from http://alloy.mit.edu/alloy4/alloy4syntax.txt
            (r'(define|page|title|section|form|par|action|return)\b', Keyword),
            (r'(output|input|navigate)\b', Name.Function),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r'(\.)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Operator, Name.Attribute)),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
    }
