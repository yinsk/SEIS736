import urllib.request
import urllib.parse
import re
from bs4 import BeautifulSoup

charname = 'Abysslight'

html = urllib.request.urlopen('http://us.battle.net/wow/en/character/illidan/'+charname+'/achievement#').read()

#div = re.findall(r'<div class="bar-contents">(.*)</div>', str(html))
div = re.findall(r'<div class="bar-contents">(.*?)</div>', str(html))

pat = '\xc2\xa0'

line = ""

for eachP in div:
    print(eachP)
    m = re.findall("[-+]?\d+[\.]?\d*", eachP)
    record = m[0] #, m[3], m[4]
    print(record)
    print("\n")
    # strRecord = str(m[0] )+" , "+str(m[3])+" , " + str(m[4]) + "\t"
    strRecord = str(m[0] )+"\t"
    line = line + strRecord

print(line)
