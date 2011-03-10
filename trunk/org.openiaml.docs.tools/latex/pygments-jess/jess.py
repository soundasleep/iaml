# -*- coding: utf-8 -*-
"""
    pygments.lexers.jess
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for Jess.
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

__all__ = ['JessLexer']

# based on JavaLexer
class JessLexer(RegexLexer):
    """
    For Jess source code (.clp) [jevon mod]
    """

    name = 'Jess'
    aliases = ['jess', 'clp']
    filenames = ['*.clp']
    mimetypes = ['text/x-jess']

    flags = re.MULTILINE | re.DOTALL

    #: optional Comment or Whitespace
    _ws = r'(?:\s|//.*?\n|/[*].*?[*]/)+'

    tokens = {
        'root': [
            (r'[^\S\n]+', Text),
            (r';;.*?\n', Comment.Single),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.\-]*', Name.Decorator),
            (r'([a-zA-Z_][a-zA-Z0-9_]\.)+[a-zA-Z_][a-zA-Z0-9_]:', Name.Import),
            (r'(deffacts|deffunction|defglobal|defmodule|defquery|defrule|'
             r'deftemplate|import|watch)\b', Keyword.Construct),
            (r'(\()(OBJECT|set-reset-globals|declare|from-class|test|run-n-times|'
             # from manual/functions.html
             r'abs|add|agenda|and|apply|asc|as-list|assert|assert-string|bag|batch|bind|bit-and|bit-not|bit-or|bload|break|bsave|build|call|call-on-engine|clear|clear-focus-stack|clear-storage|close|complement\$|context|continue|count-query-results|create\$|defadvice|defclass|definstance|delete\$|dependencies|dependents|div|do-backward-chaining|duplicate|e|engine|eq|eq\*|eval|evenp|exit|exp|explode\$|external-addressp|fact-id|facts|fact-slot-value|fetch|filter|first\$|float|floatp|focus|for|foreach|format|gensym\*|get|get-current-module|get-focus|get-focus-stack|get-member|get-multithreaded-io|get-reset-globals|get-salience-evaluation|get-strategy|halt|help|if|implement|implode\$|import|insert\$|instanceof|integer|integerp|intersection\$|java-objectp|jess-type|jess-version-number|jess-version-string|lambda|length\$|lexemep|list|list-deftemplates|list-focus-stack|list-function\$|listp|load-facts|load-function|load-package|log|log10|long|longp|lowcase|map|matches|max|member\$|min|mod|modify|multifieldp|neq|new|not|nth\$|numberp|oddp|open|or|pi|pop-focus|ppdeffacts|ppdeffunction|ppdefglobal|ppdefquery|ppdefrule|ppdeftemplate|printout|progn|provide|random|read|readline|regexp|remove|replace\$|require|require\*|reset|rest\$|retract|retract-string|return|round|rules|run|run-query|run-query\*|run-until-halt|save-facts|save-facts-xml|set|set-current-module|set-factory|setgen|set-member|set-multithreaded-io|set-node-index-hash|set-reset-globals|set-salience-evaluation|set-strategy|set-value-class|set-watch-router|show-deffacts|show-deftemplates|show-jess-listeners|socket|sqrt|store|str-cat|str-compare|str-index|stringp|str-length|subseq\$|subsetp|sub-string|symbolp|sym-cat|synchronized|system|throw|time|try|undefadvice|undeffacts|undefinstance|undefrule|union\$|unwatch|upcase|update|view|watch|while'
             r')\b', bygroups(Operator, Keyword)),
            (r'(TRUE|FALSE|nil|crlf)\b', Keyword.Constant),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r"'\\.'|'[^\\]'|'\\u[0-9a-f]{4}'", String.Char),
            (r'(\?)([a-zA-Z_][a-zA-Z0-9_]*)', bygroups(Operator, Name.Attribute)),
            (r'\?\*?[a-zA-Z_][a-zA-Z0-9_]*\*?', Name.Variable),
            (r'[a-zA-Z_][a-zA-Z0-9_]*:', Name.Label),
            (r'[a-zA-Z_\$][a-zA-Z0-9_]*', Name),
            (r'[~\^\*!%&\[\]\(\)\{\}<>\|+=:;,./?-]', Operator),
            (r'(\-\-|\+\+|\*\*|<=|>=|=>|<>)', Operator),
            (r'[0-9][0-9]*\.[0-9]+([eE][0-9]+)?[fd]?', Number.Float),
            (r'0x[0-9a-f]+', Number.Hex),
            (r'[0-9]+L?', Number.Integer),
            (r'\n', Text)
        ],
    }
