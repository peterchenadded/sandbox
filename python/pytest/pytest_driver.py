#!/usr/bin/python

import sys

sys.path.append('py-1.4.33-py2.py3-none-any.whl')
sys.path.append('pytest-3.1.1-py2.py3-none-any.whl')

import pytest

pytest.main(sys.argv[1:])
