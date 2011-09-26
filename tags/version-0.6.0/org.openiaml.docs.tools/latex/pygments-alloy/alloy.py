# -*- coding: utf-8 -*-
"""
    pygments.lexers.alloy
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for Alloy.
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

__all__ = ['AlloyLexer']

# based on JavaLexer
class AlloyLexer(RegexLexer):
    """
    For Alloy source code (.als) [jevon mod]
    """

    name = 'Alloy'
    aliases = ['alloy', 'als']
    filenames = ['*.als']
    mimetypes = ['text/x-alloy']

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
            (r'[^\S\n]+', Text),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.]*', Name.Decorator),
            # derived from http://alloy.mit.edu/alloy4/alloy4syntax.txt
            (r'(Int|abstract|all|and|as|assert|check|disj|else|enum|exactly|expect|extends|fact|for|fun|iden|iff|implies|in|int|let|lone|module|no|not|one|open|or|pred|private|run|seq|seq/Int|set|sig|some|sum|this|univ)\b', Keyword),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r'(\.)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Operator, Name.Attribute)),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
    }
