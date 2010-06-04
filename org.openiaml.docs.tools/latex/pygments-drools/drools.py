# -*- coding: utf-8 -*-
"""
    pygments.lexers.drools
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for Drools.
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

__all__ = ['DroolsLexer']

# based on JavaLexer
class DroolsLexer(RegexLexer):
    """
    For Drools source code (.drl) [jevon mod]
    """

    name = 'Drools'
    aliases = ['drools', 'drl']
    filenames = ['*.drl']
    mimetypes = ['text/x-drl']

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
            (r'(assert|break|case|catch|continue|default|do|else|finally|for|'
             r'rule|query|when|then|end|and|or|not|exists|collect|accumulate|from|forall|eval|'
             r'if|goto|instanceof|new|return|switch|this|throw|try|while)\b',
             Keyword),
            (r'(abstract|const|enum|extends|final|implements|native|private|'
             r'protected|public|static|strictfp|super|synchronized|throws|'
             r'transient|volatile)\b', Keyword.Declaration),
            (r'(boolean|byte|char|double|float|int|long|short|void)\b',
             Keyword.Type),
            (r'(package)(\s+)', bygroups(Keyword.Namespace, Text)),
            (r'(true|false|null)\b', Keyword.Constant),
            (r'(class|interface)(\s+)', bygroups(Keyword.Declaration, Text), 'class'),
            (r'(import)(\s+)', bygroups(Keyword.Namespace, Text), 'import'),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r"'\\.'|'[^\\]'|'\\u[0-9a-f]{4}'", String.Char),
            (r'(\.)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Operator, Name.Attribute)),
            (r'[a-zA-Z_][a-zA-Z0-9_]*:', Name.Label),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
        'class': [
            (r'[a-zA-Z_][a-zA-Z0-9_]*', Name.Class, '#pop')
        ],
        'import': [
            (r'[a-zA-Z0-9_.]+\*?', Name.Namespace, '#pop')
        ],
    }
