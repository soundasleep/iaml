# -*- coding: utf-8 -*-
"""
    pygments.lexers.owl
    ~~~~~~~~~~~~~~~~~~~~~~~~

    Lexer for OWL.
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

__all__ = ['OWLLexer']

# based on JavaLexer
class OWLLexer(RegexLexer):
    """
    For OWL source code.
    """

    name = 'OWL'
    aliases = ['owl']
    filenames = ['*.owl']
    mimetypes = ['text/x-owl']

    flags = re.MULTILINE | re.DOTALL

    #: optional Comment or Whitespace
    _ws = r'(?:\s|//.*?\n|/[*].*?[*]/)+'

    tokens = {
        'root': [
            (r'[^\S\n]+', Text),
            (r'//.*?\n', Comment.Single),
            (r'/\*.*?\*/', Comment.Multiline),
            (r'@[a-zA-Z_][a-zA-Z0-9_\.]*', Name.Decorator),
            (r'(Prefix|Ontology|Import|Declaration|Class|Datatype|ObjectProperty|'
             r'AnnotationAssertion|SubAnnotationPropertyOf|AnnotationPropertyDomain|AnnotationPropertyRange|'
             r'ObjectInverseOf|DataIntersectionOf|DataUnionOf|DataComplementOf|DataOneOf|'
             r'DatatypeRestriction|ObjectIntersectionOf|ObjectUnionOf|ObjectComplementOf|'
             r'ObjectOneOf|ObjectSomeValuesFrom|ObjectAllValuesFrom|ObjectHasValue|ObjectHasSelf|'
             r'ObjectMinCardinality|ObjectMaxCardinality|ObjectExactCardinality|DataSomeValuesFrom|'
             r'DataAllValuesFrom|DataHasValue|DataMinCardinality|DataMaxCardinality|DataExactCardinality|'
             r'SubClassOf|EquivalentClasses|DisjointClasses|DisjointUnion|SubObjectPropertyOf|'
             r'ObjectPropertyChain|EquivalentObjectProperties|DisjointObjectProperties|ObjectPropertyDomain|'
             r'ObjectPropertyRange|InverseObjectProperties|FunctionalObjectProperty|InverseFunctionalObjectProperty|'
             r'ReflexiveObjectProperty|IrreflexiveObjectProperty|SymmetricObjectProperty|AsymmetricObjectProperty|'
             r'TransitiveObjectProperty|SubDataPropertyOf|EquivalentDataProperties|DisjointDataProperties|'
             r'DataPropertyDomain|DataPropertyRange|FunctionalDataProperty|DatatypeDefinition|'
             r'HasKey|SameIndividual|DifferentIndividuals|ClassAssertion|ObjectPropertyAssertion|'
             r'NegativeObjectPropertyAssertion|DataPropertyAssertion|NegativeDataPropertyAssertion|'
             r'SubObjectPropertyChain|InverseObjectProperty|' # not in OWL spec?
             r'DataProperty|AnnotationProperty|NamedIndividual|Annotation)\b', Keyword.Declaration),
            (r'<[^>]+>', Name.Namespace),
            (r'(true|false|null)\b', Keyword.Constant),
            (r'"(\\\\|\\"|[^"])*"', String),
            (r"'\\.'|'[^\\]'|'\\u[0-9a-f]{4}'", String.Char),
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

