# -*- coding: utf-8 -*-
"""
    pygments.lexers.xtend
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for openArchitecutreWare Xpand.
    Based on pygments.lexers.checks

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

__all__ = ['XpandLexer']

# based on JavaLexer
class XpandLexer(RegexLexer):
    """
    For Xpand source code (.xpt) [jevon mod]
    """

    name = 'Xpand'
    aliases = ['xpand', 'xpt']
    filenames = ['*.xpt']
    mimetypes = ['text/x-xpand']

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
            (r'(DEFINE|ENDDEFINE|AROUND|ENDAROUND|FOREACH|FOR|IF|ELSE|ELSEIF|ENDIF|EXPAND|'
             r'ENDFOREACH|ENDFOR|IMPORT|EXTENSION|FILE|ENDFILE|LET|ENDLET|SEPARATOR|'
             r'PROTECT|CSTART|CEND|ID|DISABLE|ENDPROTECT|AS|REM|ENDREM|ERROR)\b', Keyword),
            (r'[^\S\n]+', Text),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.]*', Name.Decorator),
            (r'(this|context|cached|if|then|else)\b', Keyword),
            (r'(true|false|null)\b', Keyword.Constant),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r"'\\.'|'[^\\]'|'\\u[0-9a-f]{4}'", String.Char),
            (r'(\.)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Operator, Name.Attribute)),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
    }
