#!/usr/bin/env python

import re
import sys

file = open('./achR', 'r').read()
allfile = file.split(',')

# input file is from battle.net API
for line in sys.stdin:
    line = line.strip()
    ached = re.findall('achievementsCompleted\":\[(.*)\],\"achievementsCompletedTimestamp',line)

    allpoint = ached[0].split(',')

    for eachAch in allfile:
        # print(eachp)
        if eachAch not in allpoint:
            print ('%s\t%s' % (eachAch, 1))

